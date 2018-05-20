package org.litejunit.v1;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;


/**
 * Created by DonaldY on 2017/8/24.
 */
public class TestSuite implements Test {

    private final List<Test> fTests = new ArrayList<Test>(10);
    private String fName;

    public TestSuite(String name) {
        setName(name);
    }

    public TestSuite(final Class<?> theClass) {
        this.fName = theClass.getName();
        Constructor<?> constructor = null;
        try {
            constructor = getConstructor(theClass);
        } catch (NoSuchElementException e) {
            addTest(warning("Class "+theClass.getName()+" has no public constructor TestCase(String name) or TestCase()"));
            return;
        } catch (NoSuchMethodException e) {
            addTest(warning("Class "+theClass.getName()+" has no such constructor TestCase(String name) or TestCase()" +
                ""));
            return;
        }

        /** 方法是否是publish类型 **/
        if (!Modifier.isPublic(theClass.getModifiers())) {
            addTest(warning("Class "+theClass.getName()+" is not public"));
            return;
        }

        /** 记录测试方法名字 **/
        Vector<String> names = new Vector<String>();
        Method[] methods = theClass.getDeclaredMethods();
        for (int i = 0; i < methods.length; ++i) {
            addTestMethod(methods[i], names, constructor);
        }
        if (fTests.size() == 0)
            addTest(warning("No tests found in "+theClass.getName()));

    }

    private void addTestMethod(Method m, Vector names, Constructor<?> constructor) {
        String name= m.getName();
        if (names.contains(name))
            return;
        if (isPublicTestMethod(m)) {
            names.addElement(name);

            Object[] args= new Object[]{name};
            try {
                /**
                 * 对应每个test方法，都生成一个对象，并添加进去。
                 * 例如：
                 *   CalculatorTest 中 有 test1() , test2()
                 *
                 *   addTest(new Calculator("test1"))
                 *   addTest(new Calculator("test2"))
                 */
                addTest((Test)constructor.newInstance(args));
            } catch (InstantiationException e) {
                addTest(warning("Cannot instantiate test case: "+name+" ("+exceptionToString(e)+")"));
            } catch (InvocationTargetException e) {
                addTest(warning("Exception in constructor: "+name+" ("+exceptionToString(e.getTargetException())+")"));
            } catch (IllegalAccessException e) {
                addTest(warning("Cannot access test case: "+name+" ("+exceptionToString(e)+")"));
            }

        } else { // almost a test method
            if (isTestMethod(m))
                addTest(warning("Test method isn't public: "+m.getName()));
        }
    }


    private String exceptionToString(Throwable t) {
        StringWriter stringWriter= new StringWriter();
        PrintWriter writer= new PrintWriter(stringWriter);
        t.printStackTrace(writer);
        return stringWriter.toString();
    }

    private boolean isTestMethod(Method m) {
        String name= m.getName();
        Class[] parameters= m.getParameterTypes();
        Class returnType= m.getReturnType();
        return parameters.length == 0 && name.startsWith("test") && returnType.equals(Void.TYPE);
    }

    private boolean isPublicTestMethod(Method method) {
        return isTestMethod(method) && Modifier.isPublic(method.getModifiers());
    }

    public Constructor getConstructor(Class theClass) throws NoSuchMethodException {
        Class[] args= { String.class };
        try {
            return theClass.getConstructor(args);
        } catch (NoSuchMethodException e) {
            // fall through
        }
        return theClass.getConstructor(new Class[0]);
    }


    @Override
    public int countTestCases() {
        int count= 0;

        for (Iterator<Test> e = fTests(); e.hasNext(); ) {
            Test test= e.next();
            count= count + test.countTestCases();
        }
        return count;
    }

    public Iterator<Test> fTests() {
        return this.fTests.iterator();
    }

    @Override
    public void run(TestResult result) {
        for (Iterator<Test> e= fTests(); e.hasNext(); ) {
            if (result.shouldStop() ){
                break;
            }
            Test test= (Test)e.next();
            test.run(result);
        }
    }

    public void addTest(Test test) {
        this.fTests.add(test);
    }

    public void setName(String name) {
        this.fName = name;
    }

    private static Test warning(final String message) {
        return new TestCase("warning") {
            @Override
            public void doRun() {
                fail(message);
            }
        };
    }
}
