package ru.mail.terletskaya.katerina.repository;

import ru.mail.terletskaya.katerina.repository.model.User;

public interface UserRepository {
    User getByUserName(String username);
}
