package ru.mail.terletskaya.katerina.repository;

import ru.mail.terletskaya.katerina.repository.model.Card;
import ru.mail.terletskaya.katerina.repository.model.OrderItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CardRepositoryImpl implements CardRepository {
    private static CardRepositoryImpl instance;

    private CardRepositoryImpl() {
    }

    public static CardRepositoryImpl getInstance() {
        if (instance == null) {
            instance = new CardRepositoryImpl();
        }
        return instance;
    }

    private ConnectionService connectionService = ConnectionServiceImpl.getInstance();
    @Override
    public List<Card> getListCardForUser(Integer idUser) {
        List<Card> listCardForUser = new ArrayList<>();
        Connection connection = null;
        try {
            connection = connectionService.getConnection();
            if (connection != null) {
                try (PreparedStatement preparedStatement = connection.prepareStatement(
                        "SELECT F_NUMBER_CARD, F_MONTH, F_YEAR, F_DESCRIPTION FROM t_card " +
                                "WHERE t_card.F_USER_ID=? ;"
                )) {
                    preparedStatement.setInt(1, idUser);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    while (resultSet.next()) {
                        listCardForUser.add(Card.newBuilder()
                                .number(resultSet.getString("F_NUMBER_CARD"))
                                .month(resultSet.getInt("F_MONTH"))
                                .year(resultSet.getInt("F_YEAR"))
                                .description(resultSet.getString("F_DESCRIPTION"))
                                .build()
                        );
                    }
                    return listCardForUser;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public boolean addCard(Card infoAboutCard, Integer idUser) {
        Connection connection = null;
        try {
            connection = connectionService.getConnection();
            if (connection != null) {
                try (PreparedStatement preparedStatement = connection.prepareStatement(
                        "INSERT  INTO t_card( F_USER_ID, F_NUMBER_CARD, F_MONTH, F_YEAR, F_DESCRIPTION) " +
                                "VALUES( ?, ?, ?, ?, ?);", PreparedStatement.RETURN_GENERATED_KEYS
                )) {
                    connection.setAutoCommit(false);
                    preparedStatement.setInt(1, idUser);
                    preparedStatement.setString(2, infoAboutCard.getNumber());
                    preparedStatement.setInt(3, infoAboutCard.getMonth());
                    preparedStatement.setInt(4, infoAboutCard.getYear());
                    preparedStatement.setString(5, infoAboutCard.getDescription());
                    preparedStatement.executeUpdate();
                }
                connection.commit();
                return true;
            }
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                connection.setAutoCommit(true);
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
