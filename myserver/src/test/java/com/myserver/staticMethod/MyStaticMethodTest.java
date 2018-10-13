package com.myserver.staticMethod;

/**
 * 静态方法与非静态方法之间的调用
 *
 * 静态方法: 仅仅可以调用 静态变量, 静态代码块;
 * 非静态方法: 可以调用 非静态方法, 静态方法和静态变量
 */
public class MyStaticMethodTest {

    // 创建一个静态变量
    public static String stringStr = "static成员";
    // 创建一个非静态成员
    public String string = "非static成员";

    // 创建一个静态方法
    public static void methodStr(){
        // 静态方法只可以调用静态变量, 不可以调用普通变量
        stringStr = "改变";
        System.out.println("这是static方法, static方法与对象无关");
    }

    // 创建一个非静态方法
    public void method(){
        // 普通方法即可以调用静态变量也可以调用非静态变量
        stringStr = "string1";
        string = "string2";
        // 普通方法可以调用静态方法, 静态方法不可以调用非静态方法
        methodStr();
        System.out.println("这是非static方法, 此方法必须和制定的对相关联起来才起作用");
    }

    // 用main方法来测试, 运行即可, 结果控制台会打印静态方法的sout
    public static void main(String[] args) {
        MyStaticMethodTest methodTest = new MyStaticMethodTest();
        // 非静态方法
        methodTest.method();
        // 静态方法
        methodTest.methodStr();
    }


}
