package com.xxxx.springsecuritydemo.enums;

public enum RangeTypeEnums {
    Day(1,"每天"),
    Week(2,"每星期"),
    Month(3,"每月");


    RangeTypeEnums(int key, String chinese){
        this.key = key;
        this.chinese = chinese;
    }

    private final int key;
    private final String chinese;

    public static RangeTypeEnums getRole(int i){

        switch(i){
            case 1: return Day;
            case 2: return Week;
            case 3: return Month;
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
