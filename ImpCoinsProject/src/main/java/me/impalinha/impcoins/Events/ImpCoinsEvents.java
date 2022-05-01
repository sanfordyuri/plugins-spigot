package me.impalinha.impcoins.Events;

import me.impalinha.impcoins.Economy.EcoCoins;
import me.impalinha.impcoins.Economy.PlayerEco;
import me.impalinha.impcoins.ImpCoins;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class ImpCoinsEvents implements Listener {

    private final ImpCoins plugin;
    private EcoCoins ecoCoins;

    public ImpCoinsEvents(ImpCoins plugin) {
        this.plugin = plugin;
        ecoCoins = new EcoCoins(plugin);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        if(e.getPlayer() != null) {
            Player player = e.getPlayer();
            if(!player.hasPlayedBefore()) {
                ecoCoins.createPlayerAccount(player.getUniqueId().toString());
                plugin.playerEcoHashMap.put(player.getUniqueId(), new PlayerEco(player.getUniqueId(), plugin.getConfig().getDouble("Saldo-inicial")));
            }
        }
    }

}
