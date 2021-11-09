package com.xxxx.springsecuritydemo.service;



public interface IEmailService {

    public void sendSimpleMessage(
            String to, String subject, String text);
}
