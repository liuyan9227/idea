package com.myserver.temp;

public class TryCache {

    public static void main(String[] args) {
        // 调用方法先输出方法内的结果,最后打印↓
        System.out.println("结果： " + new TryCache().test());
    }

    static int test(){
        int i = 1;
        try {
            System.out.println("try里面的i : " + i);
            // 返回此时的值给调用者,但finally的代码必须执行
            return i;
        }finally{
            System.out.println("进入finally...");
            ++i;
            System.out.println("fianlly里面的i : " + i);
            // 如果在finally中将结果return,那么结果将是finally中的结果
            //return i;
        }
    }
}
