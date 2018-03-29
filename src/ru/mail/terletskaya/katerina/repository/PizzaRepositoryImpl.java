package ru.mail.terletskaya.katerina.repository;

import ru.mail.terletskaya.katerina.repository.model.Pizza;
import ru.mail.terletskaya.katerina.repository.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PizzaRepositoryImpl implements PizzaRepository {
    private static PizzaRepositoryImpl instance;

    private PizzaRepositoryImpl() {
    }

    public static PizzaRepositoryImpl getInstance() {
        if (instance == null) {
            instance = new PizzaRepositoryImpl();
        }
        return instance;
    }

    private ConnectionService connectionService = ConnectionServiceImpl.getInstance();

    @Override
    public List<Pizza> getPizzaList() {
        List<Pizza> pizzas = new ArrayList<>();
        Connection connection = null;
        try {
            connection = connectionService.getConnection();
            if (connection != null) {
                try (PreparedStatement statement = connection.prepareStatement(
                        "SELECT * FROM t_pizza")) {
                    ResultSet resultSet = statement.executeQuery();
                    while (resultSet.next()) {
                        pizzas.add(Pizza.newBuilder()
                                .idPizza(resultSet.getInt("F_ID_PIZZA"))
                                .namePizza(resultSet.getString("F_NAME_PIZZA"))
                                .size(resultSet.getInt("F_SIZE"))
                                .weight(resultSet.getInt("F_WEIGHT"))
                                .price(resultSet.getBigDecimal("F_PRICE"))
                                .imageName(resultSet.getString("F_IMAGE_NAME"))
                                .build()
                        );
                    }
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                }
                return pizzas;
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

    @Override
    public Pizza getPizzaByImageName(String pizzaImageName) {
        Connection connection = null;
        try {
            connection = connectionService.getConnection();
            if (connection != null) {
                try (PreparedStatement statement = connection.prepareStatement(
                        "SELECT * FROM t_pizza WHERE F_IMAGE_NAME=?")) {
                    statement.setString(1, pizzaImageName);
                    ResultSet resultSet = statement.executeQuery();
                    if (resultSet.next()) {
                        return Pizza.newBuilder()
                                .idPizza(resultSet.getInt("F_ID_PIZZA"))
                                .namePizza(resultSet.getString("F_NAME_PIZZA"))
                                .size(resultSet.getInt("F_SIZE"))
                                .weight(resultSet.getInt("F_WEIGHT"))
                                .price(resultSet.getBigDecimal("F_PRICE"))
                                .imageName(resultSet.getString("F_IMAGE_NAME"))
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
    
    @Override
    public Pizza getPizzaByID(Integer id) {
        Connection connection = null;
        try {
            connection = connectionService.getConnection();
            if (connection != null) {
                try (PreparedStatement statement = connection.prepareStatement(
                        "SELECT * FROM t_pizza WHERE F_ID_PIZZA=?")) {
                    statement.setInt(1, id);
                    ResultSet resultSet = statement.executeQuery();
                    if (resultSet.next()) {
                        return Pizza.newBuilder()
                                .idPizza(resultSet.getInt("F_ID_PIZZA"))
                                .namePizza(resultSet.getString("F_NAME_PIZZA"))
                                .size(resultSet.getInt("F_SIZE"))
                                .weight(resultSet.getInt("F_WEIGHT"))
                                .price(resultSet.getBigDecimal("F_PRICE"))
                                .imageName(resultSet.getString("F_IMAGE_NAME"))
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
