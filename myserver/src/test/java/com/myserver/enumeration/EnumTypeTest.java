package com.myserver.enumeration;

import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 对自定义的枚举类进行测试
 */
public class EnumTypeTest {

    public static void main(String[] args){
        // 获得枚举类型的所有静态实例
        EnumType[] enumTypes = EnumType.values();
        for(EnumType enumType : enumTypes){
            // 默认情况下，toString()方法返回实例的变量名
            System.out.println(enumType);
            // 输出枚举的索引，默认从0开始，跟定义的实例的顺序一致。这是一个final修饰的方法。
            System.out.println(enumType.ordinal());
            /*
                LARGE
                 0
                MEDIUM
                 1
                SMALL
                 2
                HELLO
                 3
             */
        }
        System.out.println(EnumType.HELLO.getName()); // hello world

        // 枚举类本质上也是一种类型
        // 用LinkedHashMap保证不会改变加入的顺序
        Map<EnumType, Integer> map = new LinkedHashMap<EnumType, Integer>();
        for(EnumType enumType : enumTypes){
            map.put(enumType, enumType.ordinal());
        }
        System.out.println(map); // {LARGE=0, MEDIUM=1, SMALL=2, HELLO=3}

        // 输出所有的键
        System.out.println(map.keySet()); // [LARGE, MEDIUM, SMALL, HELLO]
        // 输出所有的值
        System.out.println(map.values()); // [0, 1, 2, 3]

        // 用枚举类作为构造函数，键只能是枚举类
        EnumMap<EnumType, String> enumMap = new EnumMap<EnumType, String>(EnumType.class);
        for(EnumType enumType : enumTypes){
            enumMap.put(enumType, enumType.getName());
        }
        System.out.println(enumMap); // {LARGE=large, MEDIUM=medium, SMALL=small, HELLO=hello world}
    }

}
