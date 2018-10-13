package com.myserver.enumeration;

/**
 * 枚举类型
 *
 * Java提供了枚举类型，可以将所有全局变量集中在一个类中。
 * 但枚举类的构造方法只能是private，不能被继承，要覆盖的话只能在枚举类的内部进行覆盖。
 * 同时也不能继承其他类，因为enum类型默认会继承Enum，但可以实现多个接口。
 * 所有的实例在类的内部被定义好。可以通过values()静态方法得到由所有静态实例的数组。
 * 枚举类型本质上也是一种类类型，可以作为map的键，从而可以填充枚举类的所有静态实例。
 * 枚举类的好处是可以使用枚举作为传递参数，
 * 或者使用枚举类实例的具体方法得到代表的字符串来与传递进来的参数匹配，避免常量广泛使用。
 * ---------------------
 * 本文来自 ac_dao_di 的CSDN 博客 ，全文地址请点击：https://blog.csdn.net/ac_dao_di/article/details/57416754?utm_source=copy
 */
public enum EnumType {
    // 默认是public static final
    LARGE("large"), MADIUM("madium"),SMALL("small"),

    HELLO("hello"){
        // 定义一个子类,但是直接将这个子类实例化了,直接在括号里个给出父类的构造参数
        public String getName(){
            return super.name + "word";
        }
    };
    private String name;
    // 构造方法默认是private，也只能是private，说明枚举类不能在外面实例化。
    // 另外，由于枚举类已经继承了Enum<T>，不能再继承其他类，可以实现多个接口。而且私有的构造方法导致枚举不能被继承
    EnumType(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

}
