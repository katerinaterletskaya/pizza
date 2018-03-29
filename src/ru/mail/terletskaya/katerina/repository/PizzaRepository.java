package ru.mail.terletskaya.katerina.repository;

import ru.mail.terletskaya.katerina.repository.model.Pizza;

import java.util.List;

public interface PizzaRepository {
    List<Pizza> getPizzaList();
    Pizza getPizzaByImageName(String pizzaImageName);
    Pizza getPizzaByID(Integer id);
}
