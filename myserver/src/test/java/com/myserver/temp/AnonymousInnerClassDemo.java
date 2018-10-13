package com.myserver.temp;


class Car{
    void move(){}
}
interface Person{
    void learn();
}
abstract class Animal{
    abstract void eat();
}

/**
 * 匿名局部内部类
 *
 * 一般的用途：
 * 1、覆盖某个超类的方法，并且该扩展类只在本类内用一次。
 * 2、继承抽象类，并实例化其抽象方法，并且该扩展类只在本类内用一次。
 * 3、实现接口，实例化其方法，并且该扩展类只在本类内用一次。
 *
 * 几点说明：
 * 一、由于匿名内部类没有名字，所以它没有构造函数。因为没有构造函数，所以它必须完全借用父类的构造函数来实例化，匿名内部类完全把创建对象的任务交给了父类去完成。
 * 二、在匿名内部类里创建新的方法没有太大意义，但它可以通过覆盖父类的方法达到神奇效果，如上例所示。这是多态性的体现。 
 * 三、因为匿名内部类没有名字，所以无法进行向下的强制类型转换，持有对一个匿名内部类对象引用的变量类型一定是它的直接或间接父类类型。 
 * 四、注意匿名内部类的声明是在编译时进行的，实例化在运行时进行。这意味着for循环中的一个new语句会创建相同匿名类的几个实例，而不是创建几个不同匿名类的一个实例。
 * ---------------------
 * 本文来自 LiQiyaoo 的CSDN 博客 ，全文地址请点击：https://blog.csdn.net/L_BestCoder/article/details/77676342?utm_source=copy
 */
public class AnonymousInnerClassDemo {

    public static void main(String[] args) {
        Car car = new Car(){
            @Override
            void move() {
                System.out.println("匿名内部类的move方法");
            }
        };
        car.move();

        Person person = new Person() {

            public void learn() {
                System.out.println("匿名内部类的learn方法");
            }
        };
        person.learn();

        Animal animal = new Animal() {
            @Override
            void eat() {
                System.out.println("匿名内部类的eat方法");
            }
        };
        animal.eat();
    }
}