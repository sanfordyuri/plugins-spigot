package me.impalinha.impcoins;

import me.impalinha.impcoins.Commands.CmdCoins;
import me.impalinha.impcoins.Connection.SQLManager;
import me.impalinha.impcoins.Economy.EcoCoins;
import me.impalinha.impcoins.Economy.PlayerEco;
import me.impalinha.impcoins.Events.ImpCoinsEvents;
import me.impalinha.impcoins.Utils.UpdateDbTimer;
import me.impalinha.impcoins.Vault.VaultService;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;
import java.util.UUID;

import static me.impalinha.impcoins.Utils.Constants.*;

public final class ImpCoins extends JavaPlugin {

    VaultService vaultService;
    EcoCoins eco;
    SQLManager sql;
    public HashMap<UUID, PlayerEco> playerEcoHashMap;
    public UpdateDbTimer updatedb;

    public Plugin getInstance() {
        return this;
    }

    @Override
    public void onEnable() {
        saveDefaultConfig();
        initClasses();
        initDatabase();
        getSqlManager().saveMoneyOnHashMap();
        checkVault();
        getCommand("Money").setExecutor(new CmdCoins(this));
        setupTaskAsync();
        Bukkit.getServer().getPluginManager().registerEvents(new ImpCoinsEvents(this), this);
    }

    @Override
    public void onDisable() {
        getSqlManager().sendMoneyToDb();
        sql.onDisable();
        saveConfig();
    }

    private void checkVault() {
        this.getLogger().info(CHECKING_VAULT);
        if(!vaultService.setupEconomy(eco)) {
            this.getLogger().info(VAULT_NOT_FOUND);
            getServer().getPluginManager().disablePlugin(this);
        } else {
            this.getLogger().info(VAULT_FOUND);
            this.getLogger().info(ECO_OK);
        }
    }

    public void initClasses( ) {
        vaultService = new VaultService(this);
        eco = new EcoCoins(this);
        playerEcoHashMap = new HashMap<>();
        updatedb = new UpdateDbTimer(this);
    }

    public void initDatabase() {
        sql = new SQLManager(this);
        if(sql.checkConnectionOk()) {
            this.getLogger().info(CONNECTION_OK);
        } else {
            this.getLogger().info(CONNECTION_OFF);
        }
    }

    public void setupTaskAsync() {
        int min = getConfig().getInt("Update-Time");
        BukkitTask task = new UpdateDbTimer(this).runTaskTimer(this, 60*20, (60*20) * min);
    }

    public SQLManager getSqlManager() {
        return sql;
    }


}
