package ru.mail.terletskaya.katerina.service;

import ru.mail.terletskaya.katerina.service.model.OrderDTO;

import java.util.List;

public interface OrderService {
    void addOrder(String username, String[] idPizza);
    List<OrderDTO> getListOrderForUser(String username);
    void deleteOrder(int idOrder);
    List<OrderDTO> getListAllOrder();
    void updateStatusOrder(int idOrder);
}
