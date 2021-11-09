package com.xxxx.springsecuritydemo.model.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class MailRequest {
    private Integer id;
    @NotEmpty(message = "name不能為空")
    private String title;
    @NotNull(message = "roomId不能為空")
    private String content;
    @NotNull(message = "userId不能為空")
    private int userId;


}
