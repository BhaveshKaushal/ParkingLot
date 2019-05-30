package com.bk;

import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.junit.rules.TestName;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

public class BaseTest {

    protected String reg1 = "reg1";
    protected String reg2 = "reg2";
    protected String reg3 = "reg3";

    protected String color1 = "red";
    protected String color2 = "black";
    protected String color3 = "white";
    protected static int size = 3;

    @Rule
    public final TestName testName = new TestName();

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Rule
    public TestWatcher testWatcher = new TestWatcher() {
        @Override
        protected void starting(final Description description) {
            String methodName = description.getMethodName();
            String className = description.getClassName();
            className = className.substring(className.lastIndexOf('.') + 1);
            System.out.println("\n>>>>>>>>>>>>>>>> Starting JUnit-test: " + className + "." + methodName);
        }
        @Override
        protected void finished(Description description) {
            String methodName = description.getMethodName();
            String className = description.getClassName();
            className = className.substring(className.lastIndexOf('.') + 1);
            System.out.println("<<<<<<<<<<<< Ended JUnit-test: " + className + "." + methodName + "\n");
        }
    };
}
