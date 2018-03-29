package ru.mail.terletskaya.katerina.service.converter;

import ru.mail.terletskaya.katerina.repository.model.Pizza;
import ru.mail.terletskaya.katerina.service.model.PizzaDTO;

import java.util.ArrayList;
import java.util.List;

public class PizzaConverter {
    public PizzaDTO pizzaToPizzaDTO(Pizza pizza) {
        PizzaDTO pizzaDTO = PizzaDTO.newBuilder()
                .idPizza(pizza.getIdPizza())
                .namePizza(pizza.getNamePizza())
                .size(pizza.getSize())
                .weight(pizza.getWeight())
                .price(pizza.getPrice())
                .imageName(pizza.getImageName())
                .build();
        return pizzaDTO;
    }

    public List<PizzaDTO> converterPizzaList(List<Pizza> pizzaList) {
        List<PizzaDTO> pizzaDTOList = new ArrayList<>();
        for (Pizza pizza : pizzaList) {
            pizzaDTOList.add(pizzaToPizzaDTO(pizza));
        }
        return pizzaDTOList;
    }
}
