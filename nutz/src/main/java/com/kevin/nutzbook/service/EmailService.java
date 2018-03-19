package com.kevin.nutzbook.service;

/**
 * Created with IntelliJ IDEA.
 * User: Kevin
 * Date: 2018/3/14
 * Time: 14:40
 */
public interface EmailService {

    boolean send(String to, String subject, String html);

}