package com.xxxx.springsecuritydemo.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;

/**
 * @author: UserRequest
 * @date: 2021/2/28
 * @description:
 */
@Getter
@Setter
public class UserRequest {

    @NotNull
    @Email
    @JsonProperty( "email")
    private String account;

    @NotNull
    private String password;

    @NotNull
    @JsonProperty( "tel")
    private String cellPhone;

    @NotNull
    @JsonProperty( "name")
    private String nickname;

}
