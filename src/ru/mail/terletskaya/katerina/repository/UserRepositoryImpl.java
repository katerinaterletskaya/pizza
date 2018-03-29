package ru.mail.terletskaya.katerina.repository;

import ru.mail.terletskaya.katerina.repository.model.User;
import ru.mail.terletskaya.katerina.repository.property.AdminProperty;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class UserRepositoryImpl implements UserRepository {
    private static UserRepositoryImpl instance;

    private UserRepositoryImpl() {
    }

    public static UserRepositoryImpl getInstance() {
        if (instance == null) {
            instance = new UserRepositoryImpl();
        }
        return instance;
    }

    private ConnectionService connectionService = ConnectionServiceImpl.getInstance();

    @Override
    public User getByUserName(String username) {
        Connection connection = null;
        try {
            connection = connectionService.getConnection();
            if (connection != null) {
                try (PreparedStatement statement = connection.prepareStatement(
                        "SELECT * FROM t_user WHERE F_USERNAME=?")) {
                    statement.setString(1, username);
                    ResultSet resultSet = statement.executeQuery();
                    if (resultSet.next()) {
                        return User.newBuilder()
                                .id(resultSet.getInt("F_ID"))
                                .username(resultSet.getString("F_USERNAME"))
                                .password(resultSet.getString("F_PASSWORD"))
                                .role(resultSet.getString("F_ROLE"))
                                .build();
                    }
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                }
            }
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        return null;
    }
}
