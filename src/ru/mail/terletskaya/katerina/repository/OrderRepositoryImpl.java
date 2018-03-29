package ru.mail.terletskaya.katerina.repository;

import ru.mail.terletskaya.katerina.repository.model.Order;
import ru.mail.terletskaya.katerina.repository.model.OrderItem;
import ru.mail.terletskaya.katerina.repository.model.Pizza;
import ru.mail.terletskaya.katerina.service.model.OrderStatus;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderRepositoryImpl implements OrderRepository {
    private static OrderRepositoryImpl instance;

    private OrderRepositoryImpl() {
    }

    public static OrderRepositoryImpl getInstance() {
        if (instance == null) {
            instance = new OrderRepositoryImpl();
        }
        return instance;
    }

    private ConnectionService connectionService = ConnectionServiceImpl.getInstance();


    @Override
    public boolean addToOrder(int userId, List<Integer> pizzasId, BigDecimal price) {
        Connection connection = null;
        try {
            connection = connectionService.getConnection();
            if (connection != null) {
                try (PreparedStatement preparedStatement = connection.prepareStatement(
                        "INSERT  INTO t_order( F_USER_ID, F_PRICE, F_STATUS) " +
                                "VALUES( ?, ?, ?);", PreparedStatement.RETURN_GENERATED_KEYS
                )) {
                    connection.setAutoCommit(false);
                    int newId;

                    preparedStatement.setInt(1, userId);
                    preparedStatement.setBigDecimal(2, price);
                    preparedStatement.setString(3, OrderStatus.statusWaiting);
                    preparedStatement.executeUpdate();

                    ResultSet keys = preparedStatement.getGeneratedKeys();
                    keys.next();
                    newId = Integer.parseInt(keys.getString(1));

                    try (PreparedStatement preparedStatement1 = connection.prepareStatement(
                            "INSERT  INTO t_order_items(F_ORDER_ID, F_ITEM_ID)" +
                                    " VALUES(?,?);"
                    )) {
                        for (Integer pizzaId : pizzasId) {
                            preparedStatement1.setInt(1, newId);
                            preparedStatement1.setLong(2, pizzaId);
                            preparedStatement1.executeUpdate();
                        }
                    }
                }
                connection.commit();
                return true;
            }
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                connection.setAutoCommit(true);
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public List<Order> getOrders(List<OrderItem> orderItemList) {
        Connection connection = null;
        List<Order> orderList = new ArrayList<>();
        try {
            connection = connectionService.getConnection();
            if (connection != null) {
                try (PreparedStatement preparedStatement = connection.prepareStatement(
                        "SELECT t_pizza.F_NAME_PIZZA, t_pizza.F_PRICE FROM t_order_items, t_order, t_pizza " +
                                "WHERE t_order.F_USER_ID=? AND t_order.F_ORDER_ID=? " +
                                "AND t_order.F_ORDER_ID=t_order_items.F_ORDER_ID " +
                                "AND t_order_items.F_ITEM_ID=t_pizza.F_ID_PIZZA;"
                )) {
                    for (OrderItem orderItem : orderItemList) {

                        preparedStatement.setInt(1, orderItem.getUserId());
                        preparedStatement.setInt(2, orderItem.getId());
                        ResultSet resultSet = preparedStatement.executeQuery();
                        List<Pizza> pizzas = new ArrayList<>();
                        while (resultSet.next()) {
                            pizzas.add(Pizza.newBuilder()
                                    .namePizza(resultSet.getString("t_pizza.F_NAME_PIZZA"))
                                    .price(resultSet.getBigDecimal("t_pizza.F_PRICE"))
                                    .build()
                            );
                        }
                        orderList.add(Order.newBuilder()
                                .id(orderItem.getId())
                                .user(orderItem.getUser())
                                .price(orderItem.getPrice())
                                .status(orderItem.getStatus())
                                .pizzas(pizzas)
                                .build()
                        );
                    }
                    return orderList;
                }
            }
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public List<OrderItem> getOrderItemForUser(int userId) {
        List<OrderItem> orderItemList = new ArrayList<>();
        Connection connection = null;
        try {
            connection = connectionService.getConnection();
            if (connection != null) {
                try (PreparedStatement preparedStatement = connection.prepareStatement(
                        "SELECT DISTINCT t_order.F_ORDER_ID, t_order.F_PRICE, t_order.F_STATUS FROM t_order_items, t_order, t_pizza " +
                                "WHERE t_order.F_USER_ID=? AND " +
                                "t_order.F_ORDER_ID=t_order_items.F_ORDER_ID AND " +
                                "t_order_items.F_ITEM_ID=t_pizza.F_ID_PIZZA;"
                )) {
                    connection.setAutoCommit(false);
                    preparedStatement.setInt(1, userId);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    while (resultSet.next()) {
                        orderItemList.add(OrderItem.newBuilder()
                                .id(resultSet.getInt("t_order.F_ORDER_ID"))
                                .userId(userId)
                                .price(resultSet.getBigDecimal("t_order.F_PRICE"))
                                .status(resultSet.getString("t_order.F_STATUS"))
                                .build()
                        );
                    }
                    return orderItemList;
                }
            }
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                connection.setAutoCommit(true);
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public void deleteOrder(int orderId) {
        Connection connection = null;
        try {
            connection = connectionService.getConnection();
            if (connection != null) {
                try (PreparedStatement preparedStatement = connection.prepareStatement(
                        "DELETE FROM t_order_items WHERE t_order_items.F_ORDER_ID=?;"
                )) {
                    connection.setAutoCommit(false);

                    preparedStatement.setInt(1, orderId);
                    preparedStatement.executeUpdate();
                    try (PreparedStatement preparedStatement1 = connection.prepareStatement(
                            "DELETE FROM t_order WHERE t_order.F_ORDER_ID=?;"
                    )) {
                        preparedStatement1.setInt(1, orderId);
                        preparedStatement1.executeUpdate();
                    }
                }
            }
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                connection.setAutoCommit(true);
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<OrderItem> getOrderItemForAllUser() {
        List<OrderItem> orderItemList = new ArrayList<>();
        Connection connection = null;
        try {
            connection = connectionService.getConnection();
            if (connection != null) {
                try (PreparedStatement preparedStatement = connection.prepareStatement(
                        "SELECT DISTINCT t_order.F_ORDER_ID, t_user.F_ID," +
                                "t_user.F_USERNAME, t_order.F_PRICE, t_order.F_STATUS " +
                                "FROM t_order_items, t_order, t_pizza, t_user WHERE t_user.F_ID=t_order.F_USER_ID" +
                                " AND t_order.F_ORDER_ID=t_order_items.F_ORDER_ID " +
                                "AND t_order_items.F_ITEM_ID=t_pizza.F_ID_PIZZA;"
                )) {
                    ResultSet resultSet = preparedStatement.executeQuery();
                    while (resultSet.next()) {
                        orderItemList.add(OrderItem.newBuilder()
                                .id(resultSet.getInt("t_order.F_ORDER_ID"))
                                .userId(resultSet.getInt("t_user.F_ID"))
                                .user(resultSet.getString("t_user.F_USERNAME"))
                                .price(resultSet.getBigDecimal("t_order.F_PRICE"))
                                .status(resultSet.getString("t_order.F_STATUS"))
                                .build()
                        );
                    }
                    return orderItemList;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        return null;
    }

    @Override
    public void updateStatusOrder(int orderId) {
        Connection connection = null;
        try {
            connection = connectionService.getConnection();
            if (connection != null) {
                try (PreparedStatement preparedStatement = connection.prepareStatement(
                        "UPDATE t_order SET t_order.F_STATUS=? WHERE t_order.F_ORDER_ID=?;")) {
                    connection.setAutoCommit(false);
                    preparedStatement.setString(1, OrderStatus.statusCompleted);
                    preparedStatement.setInt(2, orderId);
                    preparedStatement.executeUpdate();
                }
            }
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                connection.setAutoCommit(true);
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

