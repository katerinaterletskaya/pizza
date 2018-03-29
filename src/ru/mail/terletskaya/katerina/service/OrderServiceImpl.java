package ru.mail.terletskaya.katerina.service;

import ru.mail.terletskaya.katerina.repository.*;
import ru.mail.terletskaya.katerina.service.converter.OrderConverter;
import ru.mail.terletskaya.katerina.service.model.OrderDTO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class OrderServiceImpl implements OrderService {
    private static OrderServiceImpl instance;

    private OrderServiceImpl() {
    }

    public static OrderServiceImpl getInstance() {
        if (instance == null) {
            instance = new OrderServiceImpl();
        }
        return instance;
    }

    private OrderRepository orderRepository = OrderRepositoryImpl.getInstance();
    private UserRepository userRepository = UserRepositoryImpl.getInstance();
    private OrderConverter orderConverter = new OrderConverter();
    private PizzaRepository pizzaRepository = PizzaRepositoryImpl.getInstance();


    @Override
    public void addOrder(String username, String[] pizzasID) {
        int userId = userRepository.getByUserName(username).getId();
        List<Integer> listIdPizza = new ArrayList<>();
        BigDecimal allPrice = BigDecimal.ZERO;
        for (String pizzaID : pizzasID) {
            listIdPizza.add(Integer.parseInt(pizzaID));
            BigDecimal price = pizzaRepository.getPizzaByID(Integer.parseInt(pizzaID)).getPrice();
            allPrice = allPrice.add(price);
        }
        orderRepository.addToOrder(userId, listIdPizza, allPrice);
    }

    @Override
    public List<OrderDTO> getListOrderForUser(String username) {
        return orderConverter.converterListOrderToOrderDTO(orderRepository.getOrders(orderRepository.getOrderItemForUser(userRepository.getByUserName(username).getId())));
    }

    @Override
    public void deleteOrder(int idOrder) {
        orderRepository.deleteOrder(idOrder);
    }

    @Override
    public List<OrderDTO> getListAllOrder() {
        return orderConverter.converterListOrderToOrderDTO(orderRepository.getOrders(orderRepository.getOrderItemForAllUser()));
    }

    @Override
    public void updateStatusOrder(int idOrder) {
        orderRepository.updateStatusOrder(idOrder);
    }
}
