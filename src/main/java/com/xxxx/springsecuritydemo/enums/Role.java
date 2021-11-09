package com.xxxx.springsecuritydemo.enums;

public enum Role {
    ROLE_USER(1,"一般會員"),
    ROLE_ADMIN(2,"管理者");


    Role(int key, String chinese){
        this.key = key;
        this.chinese = chinese;
    }

    private final int key;
    private final String chinese;

    public static Role getRole(int i){

        switch(i){
            case 1: return ROLE_USER;
            case 2: return ROLE_ADMIN;
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
