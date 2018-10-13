package com.myserver.temp;

public class Temp {

    private Integer num;

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public void add(Integer n){

        if(num == null){
            num = n;
        } else {
            Integer temp = num;
            System.out.println(num);
            System.out.println(temp);
            System.out.println(num == temp);
        }
    }


}
