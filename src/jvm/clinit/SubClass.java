package jvm.clinit;

public class SubClass extends SuperClass {

    public static String sub = "sub";

    static {
        System.out.println("SubClass");
    }

}
