package br.com.impalinha.ImpCoins.Connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static br.com.impalinha.ImpCoins.Utils.Constants.CREATE_TABLE;

public class ConnectionMethods {

    ConnectionService cs = new ConnectionService();

    public void createTable() {
        try {
            Connection connection = cs.getHikari().getConnection();
            PreparedStatement str = connection.prepareStatement(CREATE_TABLE);
            str.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
