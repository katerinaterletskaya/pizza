package ru.mail.terletskaya.katerina.service;

import ru.mail.terletskaya.katerina.repository.CardRepository;
import ru.mail.terletskaya.katerina.repository.CardRepositoryImpl;
import ru.mail.terletskaya.katerina.repository.UserRepository;
import ru.mail.terletskaya.katerina.repository.UserRepositoryImpl;
import ru.mail.terletskaya.katerina.service.converter.CardConverter;
import ru.mail.terletskaya.katerina.service.model.CardDTO;

import java.util.List;

public class CardServiceImpl implements CardService {
    private static CardServiceImpl instance;

    private CardServiceImpl() {
    }

    public static CardServiceImpl getInstance() {
        if (instance == null) {
            instance = new CardServiceImpl();
        }
        return instance;
    }
    private CardConverter cardConverter=new CardConverter();
    private CardRepository cardRepository= CardRepositoryImpl.getInstance();
    private UserRepository userRepository= UserRepositoryImpl.getInstance();
    @Override
    public List<CardDTO> getListCardForUser(String userName) {
        return cardConverter.convertListCardToListCardDTO(cardRepository.getListCardForUser(userRepository.getByUserName(userName).getId()));
    }

    @Override
    public void addCardForUser(CardDTO cardDTO, String userName) {
        cardRepository.addCard(cardConverter.convertCardDTOToCard(cardDTO),userRepository.getByUserName(userName).getId());
    }
}
