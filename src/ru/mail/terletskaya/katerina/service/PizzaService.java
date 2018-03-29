package ru.mail.terletskaya.katerina.service;

import ru.mail.terletskaya.katerina.service.model.PizzaDTO;

import java.util.List;

public interface PizzaService {
    List<PizzaDTO> getPizzas();
    PizzaDTO getPizzaByImageName(String imageName);
}
