package ru.mail.terletskaya.katerina.service;

public interface UserService {
    boolean isUserValid(String username, String password);
    String getUserRole(String username);
}
