package ru.mail.terletskaya.katerina.service;

import ru.mail.terletskaya.katerina.service.model.CardDTO;

import java.util.List;

public interface CardService {
    List<CardDTO> getListCardForUser(String userName);
    void addCardForUser(CardDTO cardDTO, String userName);
}
