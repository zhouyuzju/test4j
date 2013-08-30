package org.jtester.testng.database;

import java.util.Arrays;
import java.util.List;

import mockit.Mock;

import org.test4j.module.core.utility.ModulesLoader;
import org.test4j.testng.JTester;
import org.test4j.tools.commons.ConfigHelper;
import org.testng.annotations.Test;

@Test(groups = "jtester")
public class ModulesLoaderTest extends JTester {

	@Test(description = "测试database.type未设置时,database和dbfit模块失效")
	public void testFilterModules() {
		new MockUp<ConfigHelper>() {
			@Mock
			public String databaseType() {
				return null;
			}
		};
		List<String> list = reflector.invokeStatic(ModulesLoader.class, "filterModules",
				Arrays.asList("database", "dbfit", "jmock", "jmockit", "inject", "spring", "tracer"));
		want.collection(list).not(the.collection().hasItems("database")).sizeEq(5);
	}
}
