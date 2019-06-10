package com.tong.kit;

import java.util.Random;

/**
 * @Author stonewong
 * @Description TODO
 * @Date 2019/6/10 14:32
 * @Param
 * @return
 **/
public class StringKit {

    private static final Random RANDOM = new Random();
    private static final char SEPARATOR = '_';

    public static int rand(int min, int max){
        return RANDOM.nextInt(max) % (max - min + 1) + min;
    }

    public static String rand(int size){
        StringBuffer num = new StringBuffer();
        for(int i = 0; i < size; i++){
            double a = Math.random() * 9;
            a = Math.ceil(a);
            int randomNum = new Double(a).intValue();
            num.append(randomNum);
        }
        return num.toString();
    }

    public static boolean isNotBlank(String... str){
        if(str == null){
            return false;
        }
        for(String s:str){
            if(isBlank(s)){
                return false;
            }
        }
        return true;
    }

    public static boolean isBlank(String str) {
        return null == str || "".equals(str.trim());
    }

    public static boolean isEmpty(String str) {
        return null == str || str.isEmpty();
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }
}
