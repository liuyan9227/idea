package com.myserver.temp;

/**
 * 将字符串反转
 */
public class StringTest {

    public static String reverse(String originStr) {
        if(originStr == null || originStr.length() <= 1)
            return originStr;
        return reverse(originStr.substring(1)) + originStr.charAt(0);
    }

}
