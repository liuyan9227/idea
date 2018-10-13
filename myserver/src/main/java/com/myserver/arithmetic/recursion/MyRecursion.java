package com.myserver.arithmetic.recursion;

/**
 * 递归算法
 *
 *
 */
public class MyRecursion {

    /**
     * 递归求和
     */
    public Integer recursionSum(Integer n){
        if(n > 0){
            // n + (n-1 + (n-1-1 + (n-1-1-1 ...(n-n=0时截止))))
            return n + recursionSum(n - 1);
        } else {
            // 当n=0时,递归结束
            return 0;
        }
    }

    /**
     * 递归阶乘
     */
    public Integer recursionMulity(Integer n){
        if(n == 1){
            return 1;
        }
        return n * recursionMulity(n - 1);
    }

    /**
     * 判定字符串数组中是否有相同内容
     */
    public boolean fun(int n,String[] a){
        boolean b = false;
        if(n == a.length){
            b = true;
        }else{
            for(int i = n; i < a.length-1; i++){
                System.out.println(n+"    "+(i+1));
                if(a[n].equals(a[i+1])){
                    return false;
                }
            }
            n++;
            fun(n,a);
        }
        return b;
    }






}
