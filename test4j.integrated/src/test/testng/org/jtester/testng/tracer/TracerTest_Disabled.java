package org.jtester.testng.tracer;

import java.io.FileNotFoundException;

import mockit.Mock;

import org.jtester.fortest.hibernate.User;
import org.jtester.fortest.hibernate.UserService;
import org.jtester.module.spring.annotations.SpringBeanByType;
import org.jtester.module.spring.annotations.SpringContext;
import org.jtester.module.tracer.TracerHelper;
import org.test4j.database.table.ITable;
import org.test4j.module.database.IDatabase;
import org.test4j.testng.JTester;
import org.test4j.tools.commons.ResourceHelper;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@SuppressWarnings({ "serial" })
@SpringContext({ "classpath:/org/jtester/fortest/hibernate/project.xml" })
@Test(groups = { "jtester", "tracer", "broken-install", "hibernate" })
public class TracerTest_Disabled extends JTester implements IDatabase {
    @SpringBeanByType
    private UserService userService;

    @BeforeMethod
    public void disabledTracer() {
        new MockUp<TracerHelper>() {
            @Mock
            public boolean doesTracerEnabled() {
                return false;
            }
        };
    }

    @Test
    public void monitorSpringFalse() {
        db.table(ITable.t_tdd_user).clean().insert(3, new DataMap() {
            {
                this.put("id", "1", "2", "3");
                this.put("is_deleted", "false", "false", "true");
            }
        });
        User user1 = userService.getUser(1);
        want.object(user1).notNull();

        User user = new User();
        user.setName("new user");
        userService.newUser(user);
        db.table(ITable.t_tdd_user).queryWhere("name='new user'").reflectionEqMap(1, new DataMap() {
            {
                this.put("count", "1");
            }
        });
    }

    @Test(dependsOnMethods = "monitorSpringFalse")
    public void monitorSpringFalse_check() throws FileNotFoundException {
        String tracerInfo = ResourceHelper
                .readFromFile("target/tracer/org/jtester/tracer/TracerTest#monitorSpringFalse.html");
        want.string(tracerInfo).notContain("paras").notContain("result");
    }

    @Test
    public void monitorJdbcFalse() {
        db.table(ITable.t_tdd_user).clean().insert(3, new DataMap() {
            {
                this.put("id", "1", "2", "3");
                this.put("is_deleted", "false", "false", "true");
            }
        });
        User user1 = userService.getUser(1);
        want.object(user1).notNull();

        User user = new User();
        user.setName("new user");
        userService.newUser(user);
        db.table(ITable.t_tdd_user).queryWhere("name='new user'").reflectionEqMap(1, new DataMap() {
            {
                this.put("count", "1");
            }
        });
    }

    @Test(dependsOnMethods = "monitorSpringFalse")
    public void monitorJdbcFalse_check() throws FileNotFoundException {
        String tracerInfo = ResourceHelper
                .readFromFile("target/tracer/org/jtester/tracer/TracerTest#monitorJdbcFalse.html");
        want.string(tracerInfo).notContain("SQL-Statement");
    }
}
