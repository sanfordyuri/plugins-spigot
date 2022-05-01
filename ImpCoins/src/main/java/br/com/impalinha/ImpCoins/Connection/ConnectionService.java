package br.com.impalinha.ImpCoins.Connection;

import com.zaxxer.hikari.HikariDataSource;

import static br.com.impalinha.ImpCoins.Utils.Constants.*;

public class ConnectionService {

    private HikariDataSource hikari;
    private ConnectionMethods cm = new ConnectionMethods();

    public void registerConnection() {
        hikari = new HikariDataSource();
        hikari.setDataSourceClassName(DATASOURCE);
        hikari.addDataSourceProperty("serverName", SERVER_NAME);
        hikari.addDataSourceProperty("port", PORT);
        hikari.addDataSourceProperty("databaseName", DATABASE);
        hikari.addDataSourceProperty("user", USER);
        hikari.addDataSourceProperty("password", PASSWORD);
    }

    public void open() {
        registerConnection();
        cm.createTable();
    }

    public void close() {
        if(hikari != null) {
            hikari.close();
        }
    }

    public HikariDataSource getHikari() {
        return hikari;
    }

}
