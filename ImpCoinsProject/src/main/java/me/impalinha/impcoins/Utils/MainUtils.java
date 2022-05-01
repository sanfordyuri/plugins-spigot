package me.impalinha.impcoins.Utils;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public class MainUtils {

    public Plugin getPlugin() {
        return Bukkit.getServer().getPluginManager().getPlugin("ImpCoins");
    }

    public void console(String msg) {
        getPlugin().getLogger().info(msg);
    }

}
