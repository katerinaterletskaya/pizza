package ru.mail.terletskaya.katerina.service.converter;

import ru.mail.terletskaya.katerina.repository.model.Order;
import ru.mail.terletskaya.katerina.service.model.OrderDTO;

import java.util.ArrayList;
import java.util.List;

public class OrderConverter {
    PizzaConverter converter = new PizzaConverter();

    public OrderDTO orderToOrderDTO(Order order) {
        return OrderDTO.newBuilder()
                .id(order.getId())
                .user(order.getUser())
                .price(order.getPrice())
                .status(order.getStatus())
                .pizzas(converter.converterPizzaList(order.getPizzas()))
                .build();
    }

    public List<OrderDTO> converterListOrderToOrderDTO(List<Order> orderList) {
        List<OrderDTO> orderDTOList = new ArrayList<>();
        for (Order order : orderList) {
            orderDTOList.add(orderToOrderDTO(order));
        }
        return orderDTOList;
    }
}