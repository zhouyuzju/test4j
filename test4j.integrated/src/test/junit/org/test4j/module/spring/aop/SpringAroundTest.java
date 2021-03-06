package org.test4j.module.spring.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.junit.Test;
import org.springframework.aop.framework.ProxyFactory;
import org.test4j.junit.Test4J;
import org.test4j.module.core.utility.MessageHelper;

public class SpringAroundTest extends Test4J {

    @Test
    public void testSpringAround() {
        MessageHelper.info("test spring around");
        Hello target = new Hello();// target class
        ProxyFactory pf = new ProxyFactory();// create the proxy
        pf.addAdvice(new MethodTracer());// add advice
        pf.setTarget(target);// setTarget
        Hello proxy = (Hello) pf.getProxy();
        proxy.greeting();
    }

    public static class MethodTracer implements MethodInterceptor {

        @Override
        public Object invoke(MethodInvocation invocation) throws Throwable {
            MessageHelper.info("before invoke");
            Object o = invocation.proceed();
            MessageHelper.info("after invoke");
            return o;
        }
    }

    public static class Hello {
        public void greeting() {
            MessageHelper.info("reader");
        }
    }
}
