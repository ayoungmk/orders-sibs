package com.ayoungmk.orders_sibs.service;

public interface EmailService {
    public void sendSimpleMessage(String to, String subject, String text);

}
