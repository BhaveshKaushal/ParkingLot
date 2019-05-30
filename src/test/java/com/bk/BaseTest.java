package com.bk;

import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.junit.rules.TestName;

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

   public void printStart(){
        System.out.println("\n>>>>>>>>>> "+testName.getMethodName() + " STARTS <<<<<<<<<<<<");
    }

    public void printEnd(){
        System.out.println("<<<<<<<<<< "+testName.getMethodName() + " ENDS >>>>>>>>>>>>>\n");
    }
}
