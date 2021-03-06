package org.test4j.testng.spring;

import org.test4j.fortest.hibernate.AddressService;
import org.test4j.module.spring.annotations.SpringBeanByName;
import org.test4j.module.spring.annotations.SpringContext;
import org.test4j.testng.Test4J;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@SpringContext({ "file:./extern-spring/project.xml" })
@Test(groups = "test4j")
public class SpringModuleTest extends Test4J {
    @SpringBeanByName("addressService")
    private AddressService addressService;

    @Test(description = "更改spring bean的字段的值")
    public void testBeforeTestSetUp_ChangeSpringBeanValue() {
        this.addressService = null;
    }

    @Test(dependsOnMethods = "testBeforeTestSetUp_ChangeSpringBeanValue", description = "验证spring bean的字段的值是从spring容器重新注入的")
    public void testBeforeTestSetUp_CheckSpringBeanValue() {
        want.object(addressService).notNull();
    }

    @BeforeMethod
    public void atestSetupClass() {
        want.object(addressService).notNull();
    }
}
