package ru.mail.terletskaya.katerina.repository;

import ru.mail.terletskaya.katerina.repository.model.Order;
import ru.mail.terletskaya.katerina.repository.model.OrderItem;

import java.math.BigDecimal;
import java.util.List;

public interface OrderRepository {
    boolean addToOrder(int userId, List<Integer> pizzaId, BigDecimal price);
    List<Order> getOrders(List<OrderItem> orderItemList);
    List<OrderItem> getOrderItemForUser(int userId);
    void deleteOrder(int orderId);
    List<OrderItem> getOrderItemForAllUser();
    void updateStatusOrder(int orderId);
}
