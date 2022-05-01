package impalinha.impmb;

import impalinha.commands.MbCmd;
import impalinha.events.AtackEvent;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Impmb extends JavaPlugin {

    @Override
    public void onEnable() {
        getCommand("mb").setExecutor(new MbCmd());
        Bukkit.getPluginManager().registerEvents(new AtackEvent(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
