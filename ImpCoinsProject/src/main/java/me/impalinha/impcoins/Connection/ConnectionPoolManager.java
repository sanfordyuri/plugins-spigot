package me.impalinha.impcoins.Connection;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import me.impalinha.impcoins.ImpCoins;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static me.impalinha.impcoins.Utils.Constants.*;

public class ConnectionPoolManager {

    private HikariDataSource dataSource;
    private final ImpCoins plugin;

    public String SERVER_NAME;
    public String PORT;
    public String DATABASE;
    public String USER;
    public String PASSWORD;

    public ConnectionPoolManager(ImpCoins plugin) {
        this.plugin = plugin;
        SetupDbParam(plugin);
        setupPool();
    }

    private void SetupDbParam(ImpCoins plugin) {
        SERVER_NAME = plugin.getConfig().getConfigurationSection("Mysql").getString("server-andress");
        PORT = plugin.getConfig().getConfigurationSection("Mysql").getString("port");
        DATABASE = plugin.getConfig().getConfigurationSection("Mysql").getString("database");
        USER = plugin.getConfig().getConfigurationSection("Mysql").getString("user");
        PASSWORD = plugin.getConfig().getConfigurationSection("Mysql").getString("password");
    }


    private void setupPool() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(
                "jdbc:mysql://" +
                        SERVER_NAME +
                        ":" +
                        PORT +
                        "/" +
                        DATABASE
        );
        config.setDriverClassName(DATASOURCE);
        config.setUsername(USER);
        config.setPassword(PASSWORD);
        config.setMaximumPoolSize(10);
        dataSource = new HikariDataSource(config);
    }


    public void close(Connection conn, PreparedStatement ps, ResultSet res) {
        if (conn != null) try { conn.close(); } catch (SQLException ignored) {}
        if (ps != null) try { ps.close(); } catch (SQLException ignored) {}
        if (res != null) try { res.close(); } catch (SQLException ignored) {}
    }

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public void closePool() {
        if (dataSource != null && !dataSource.isClosed()) {
            dataSource.close();
        }
    }

}
