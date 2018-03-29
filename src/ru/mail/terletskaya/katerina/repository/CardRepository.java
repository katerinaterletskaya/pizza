package ru.mail.terletskaya.katerina.repository;

import ru.mail.terletskaya.katerina.repository.model.Card;

import java.util.List;

public interface CardRepository {
    List<Card> getListCardForUser(Integer idUser);
    boolean addCard(Card infoAboutCard, Integer idUser);
}
