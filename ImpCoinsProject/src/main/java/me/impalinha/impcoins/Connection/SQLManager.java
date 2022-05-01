package me.impalinha.impcoins.Connection;

import me.impalinha.impcoins.Economy.EcoCoins;
import me.impalinha.impcoins.Economy.PlayerEco;
import me.impalinha.impcoins.ImpCoins;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import static me.impalinha.impcoins.Utils.Constants.*;

public class SQLManager {

    private final ConnectionPoolManager pool;
    private ImpCoins plugin;

    public SQLManager(ImpCoins plugin) {
        this.plugin = plugin;
        pool = new ConnectionPoolManager(plugin);
        makeTable();
    }

    public void onDisable() {
        pool.closePool();
    }

    private void makeTable() {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = pool.getConnection();
            ps = conn.prepareStatement(CREATE_TABLE);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            pool.close(conn, ps, null);
        }
    }

    public void createPlayer(Player p) {
        Bukkit.getScheduler().runTaskAsynchronously(plugin.getInstance(), () -> {
            Connection conn = null;
            PreparedStatement ps = null;
            try {
                conn = pool.getConnection();
                ps = conn.prepareStatement(INSERT);
                ps.setString(1, p.getUniqueId().toString());
                ps.setString(2, p.getName());
                ps.setDouble(3, 0.0);
                ps.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                pool.close(conn, ps, null);
            }
        });
    }

    public void setMoney(String uuid, double amount) {
       // Bukkit.getServer().getScheduler().runTaskAsynchronously(plugin, () -> {
            Connection conn = null;
            PreparedStatement ps = null;
            try {
                conn = pool.getConnection();
                ps = conn.prepareStatement(UPDATE_MONEY);
                ps.setDouble(1, amount);
                ps.setString(2, uuid);
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                pool.close(conn, ps, null);
            }
        //});
    }

    public void saveMoneyOnHashMap() {
        plugin.getServer().getScheduler().runTaskAsynchronously(plugin, () -> {
            Connection conn = null;
            PreparedStatement ps = null;
            try {
                conn = pool.getConnection();
                ps = conn.prepareStatement(SELECT);
                ResultSet resultSet = ps.executeQuery();
                plugin.getLogger().info("Recuperando dados do Banco...");
                while (resultSet.next()) {
                    UUID uuid = UUID.fromString(resultSet.getString("UUID"));
                    plugin.playerEcoHashMap.put(uuid,
                            new PlayerEco(uuid, resultSet.getDouble("Amount")));
                }
                plugin.getLogger().info("Recuperação de dados finalizada.");
            } catch (SQLException e) {
                plugin.getLogger().info("Falha na recuperação de dados.");
                plugin.getLogger().info("Salvando de forma local...");
                e.printStackTrace();
            } finally {
                pool.close(conn, ps, null);
            }
        });
    }

    public void sendMoneyToDb() {
        plugin.getLogger().info(SEND_DATA_DB);
        for(UUID uuid : plugin.playerEcoHashMap.keySet()) {
            plugin.getSqlManager().setMoney(uuid.toString(), plugin.playerEcoHashMap.get(uuid).getAmount());
        }
        plugin.getLogger().info(SEND_DATA_FINISHED);
    }


    public boolean checkConnectionOk() {
        try {
            if(!pool.getConnection().isClosed()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            plugin.getPluginLoader().disablePlugin(plugin);
            return false;
        }
        plugin.getPluginLoader().disablePlugin(plugin);
        return false;
    }

}
