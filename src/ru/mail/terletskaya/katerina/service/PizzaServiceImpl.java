package ru.mail.terletskaya.katerina.service;

import ru.mail.terletskaya.katerina.repository.PizzaRepository;
import ru.mail.terletskaya.katerina.repository.PizzaRepositoryImpl;
import ru.mail.terletskaya.katerina.service.converter.PizzaConverter;
import ru.mail.terletskaya.katerina.service.model.PizzaDTO;

import java.util.ArrayList;
import java.util.List;

public class PizzaServiceImpl implements PizzaService {
    private static PizzaServiceImpl instance;

    private PizzaServiceImpl() {
    }

    public static PizzaServiceImpl getInstance() {
        if (instance == null) {
            instance = new PizzaServiceImpl();
        }
        return instance;
    }

    private PizzaRepository pizzaRepository = PizzaRepositoryImpl.getInstance();
    private PizzaConverter pizzaConverter = new PizzaConverter();

    @Override
    public List<PizzaDTO> getPizzas() {
        return pizzaConverter.converterPizzaList(pizzaRepository.getPizzaList());
    }

    @Override
    public PizzaDTO getPizzaByImageName(String imageName) {
        return pizzaConverter.pizzaToPizzaDTO(pizzaRepository.getPizzaByImageName(imageName));
    }
}
