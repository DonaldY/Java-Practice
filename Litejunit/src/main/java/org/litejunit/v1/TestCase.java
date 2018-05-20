package org.litejunit.v1;

import junit.framework.Assert;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * Created by DonaldY on 2017/8/22.
 */
public abstract class TestCase extends Assert implements Test {

    public String name;

    public TestCase(String name) {
        this.name = name;
    }

    public int countTestCases() {
        return 1;
    }

    protected void runTest() throws Throwable{
        Method runMethod= null;
        try {
            runMethod= getClass().getMethod(name, null);
        } catch (NoSuchMethodException e) {
            fail("Method \""+name+"\" not found");
        }
        if (!Modifier.isPublic(runMethod.getModifiers())) {
            fail("Method \""+name+"\" should be public");
        }

        try {
            runMethod.invoke(this, new Class[0]);
        }
        catch (InvocationTargetException e) {
            e.fillInStackTrace();
            throw e.getTargetException();
        }
        catch (IllegalAccessException e) {
            e.fillInStackTrace();
            throw e;
        }
    }

    protected void setUp() {
    }

    protected void tearDown() {
    }

    public void run(TestResult tr) {
        tr.run(this);
    }

    public void doRun() throws Throwable{
        setUp();
        try{
            runTest();
        }
        finally{
            tearDown();
        }
    }
}
