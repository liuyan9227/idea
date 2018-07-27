package com.ly.entity;


/**
 * @author liuyan
 * @date 2018/7/15
 */
public class Dell implements Sale {
    @Override
    public void salePC(String name) {
        System.out.println("DELL出货+1" + name);
    }

    @Override
    public int save(){
        System.out.println("德玛西亚");
        int num = 100 / 2;
        return num;
    }
}
