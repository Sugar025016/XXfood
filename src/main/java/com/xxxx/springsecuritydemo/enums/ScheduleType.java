package com.xxxx.springsecuritydemo.enums;

public enum ScheduleType {

    OpenTime(1,"營業時間"),
    OutTime(2,"外送時間");


    ScheduleType(int key, String chinese){
        this.key = key;
        this.chinese = chinese;
    }

    private final int key;
    private final String chinese;

    public static ScheduleType getRole(int i){

        switch(i){
            case 1: return OpenTime;
            case 2: return OutTime;
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
