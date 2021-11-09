package com.xxxx.springsecuritydemo.enums;



/**
 * @author: ErrorCode
 * @date: 2021/2/28
 * @description:
 */

public enum  ErrorCode {
    SUN(101,"星期日"),
    MON(102,"星期一"),
    TUE(103,"星期二"),
    WED(104,"星期三"),
    THU(105,"星期四"),
    FRI(106,"星期五"),
    SAT(107,"星期六");


    ErrorCode(int key, String chinese){
        this.key = key;
        this.chinese = chinese;
    }

    private final int key;
    private final String chinese;

    public static ErrorCode getWeek(int i){

        switch(i){
            case 0: return SUN;
            case 1: return MON;
            case 2: return TUE;
            case 3: return WED;
            case 4: return THU;
            case 5: return FRI;
            case 6: return SAT;
            default: return null;
        }

    }

    public int getKey(){
        return this.key;
    }

    public String getChinese(){
        return this.chinese;
    }

}
