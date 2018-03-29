package ru.mail.terletskaya.katerina.service;

import ru.mail.terletskaya.katerina.repository.UserRepository;
import ru.mail.terletskaya.katerina.repository.UserRepositoryImpl;
import ru.mail.terletskaya.katerina.repository.model.User;

import java.util.Objects;

public class UserServiceImpl implements UserService {
    private static UserServiceImpl instance;

    private UserServiceImpl() {
    }

    public static UserServiceImpl getInstance() {
        if (instance == null) {
            instance = new UserServiceImpl();
        }
        return instance;
    }

    private UserRepository userRepository = UserRepositoryImpl.getInstance();

    @Override
    public boolean isUserValid(String username, String password) {
        User user = userRepository.getByUserName(username);
        return null != user && Objects.equals(user.getPassword(), password);
    }

    @Override
    public String getUserRole(String username) {
        return userRepository.getByUserName(username).getRole();
    }

}
