package me.impalinha.impcoins.Utils;

import me.impalinha.impcoins.ImpCoins;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;


public class UpdateDbTimer extends BukkitRunnable {

    private final ImpCoins plugin;

    public UpdateDbTimer(ImpCoins plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        Bukkit.getServer().getScheduler().runTaskAsynchronously(plugin, () -> {
            plugin.getSqlManager().sendMoneyToDb();
        });
    }
}
