package me.impalinha.impcoins.Vault;

import me.impalinha.impcoins.Economy.EcoCoins;
import me.impalinha.impcoins.ImpCoins;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.ServicePriority;

import static org.bukkit.Bukkit.getServer;

public class VaultService {

    public EcoCoins ecoCoinsClass;
    private final ImpCoins plugin;

    public VaultService(ImpCoins plugin) {
        ecoCoinsClass = new EcoCoins(plugin);
        this.plugin = plugin;
    }

    public static Economy econ = null;

    public boolean setupEconomy(EcoCoins eco) {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        getServer().getServicesManager().register(Economy.class, eco, plugin, ServicePriority.Highest);
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        Economy provider = rsp.getProvider();
        return !(provider == null);
    }

    public Economy getEconomy() {
        return econ;
    }


}
