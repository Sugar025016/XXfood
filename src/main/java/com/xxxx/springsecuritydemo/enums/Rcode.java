package com.xxxx.springsecuritydemo.enums;

import lombok.Getter;

@Getter
public enum  Rcode {

    //1.Account
    //2.Password
    //3.Chatcha
    //4.Token
    //5.Parameter輸入資料部正確
    //9.other
    Success(0,"成功"),//成功
    Not_Login(1,"請登入"),//
    ROLE_Admin(2,"角色錯誤"),//
    Account_Not_exist(101,"帳戶不存在"),//帳戶不存在
    Account_Or_Password_Incorrect(102,"帳號或密碼錯誤"),//帳號或密碼錯誤
    Account_Duplicate(103,"帳戶重複"),//帳戶不存在
    Path_Incorrect(105,"帳號或密碼錯誤"),//帳號或密碼錯誤
    Error(109,"錯誤"),//帳號或密碼錯誤
    Account_Blocked(103,"帳戶被封鎖"),//帳戶被封鎖
    Account_Role_Error(104,"成功"),
    Email_Already_Used(112,"成功"),

    Confirm_Password_Not_Match(201,"確認密碼 錯誤"),//確認密碼 錯誤

    Chatcha_Incorrect(303,"圖片驗證碼不正確"),//圖片驗證碼不正確
    Chatcha_Expired(303,"圖片驗證碼過期"),//圖片驗證碼過期

    Token_Expired(401,"Token已過期"),//Token已過期

    Parameter_Incorrect(500,"參數部正確"),//參數部正確
    Area_Id_Is_Not_Exist(501,"區域ID不存在"),//區域ID不存在
    Cell_Phone_Length_Incorrect(502,"手機格式錯誤"),//手機格式錯誤
    Address_Type_Code_Incorrect(503,"地址類型代碼不正確"),//地址類型代碼不正確
    Community_Code_Not_Exist(504,"社區代碼不存在"),//社區代碼不存在
    Community_Code_Incorrect(505,"成功"),//
    Community_Incorrect(506,"成功"),

    Credit_Encrypt_Error(601,"Credit加密錯誤"),//Credit加密錯誤
    Credit_ToJSONString_Error(602,"成功"),
    Credit_NotFount_Error(603,"成功"),


    Access_Error(991,"沒有權限"),//沒有權限
    Cellphone_Random_Time_Error(997,"手機認證送出時間未超過5分出錯"),//手機認證送出時間未超過5分出錯
    Cellphone_Send_Random_Error(998,"手機認證送出出錯"),//手機認證送出出錯
    Cellphone_Random_Error(999,"手機認證出錯"),//手機認證出錯
    Domain_Error(911,"成功"),
    Email_Send_Error(988,"成功"),
    Application_Error(989,"成功"),
    URL_Error(989,"成功");

    Rcode(int key, String chinese){
        this.key = key;
        this.chinese = chinese;
    }

    private final int key;
    private final String chinese;

//    public static Rcode getRcode(int i){

//        switch(i){
//            case 0: return SUN;
//            case 1: return MON;
//            case 2: return TUE;
//            case 3: return WED;
//            case 4: return THU;
//            case 5: return FRI;
//            case 6: return SAT;
//            default: return null;
//        }

//    }


}
