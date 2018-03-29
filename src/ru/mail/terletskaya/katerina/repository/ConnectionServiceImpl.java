package ru.mail.terletskaya.katerina.repository;

import ru.mail.terletskaya.katerina.repository.property.ConnectionProperty;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionServiceImpl implements ConnectionService {
    private static ConnectionServiceImpl instance;

    private ConnectionServiceImpl() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    public static ConnectionServiceImpl getInstance() {
        if (instance == null) {
            instance = new ConnectionServiceImpl();
        }
        return instance;
    }

    @Override
    public Connection getConnection() {
        try {
            return DriverManager.getConnection(
                    ConnectionProperty.URL,
                    ConnectionProperty.USERNAME,
                    ConnectionProperty.PASSWORD
            );
        } catch (SQLException e) {
            System.out.println("SQLException: "+e.getMessage());
            System.out.println("SQLState: "+e.getSQLState());
            System.out.println("VendorError: "+e.getErrorCode());
        }
        return null;
    }
}
