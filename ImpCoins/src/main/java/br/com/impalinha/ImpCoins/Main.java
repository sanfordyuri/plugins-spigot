package br.com.impalinha.ImpCoins;

import br.com.impalinha.ImpCoins.Connection.ConnectionService;
import br.com.impalinha.ImpCoins.Vault.VaultService;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import static br.com.impalinha.ImpCoins.Utils.Constants.VAULT_NOT_FOUND;

public class Main extends JavaPlugin {

    VaultService vaultService = new VaultService();
    ConnectionService cs = new ConnectionService();

    @Override
    public void onEnable() {
        this.getLogger().info(ChatColor.AQUA + "Iniciando ImpCoins");
        checkVault();
        cs.open();
    }

    @Override
    public void onDisable() {
        cs.close();
    }

    private void checkVault() {
        if(!vaultService.setupEconomy()) {
            this.getLogger().info(ChatColor.AQUA + VAULT_NOT_FOUND);
            getServer().getPluginManager().disablePlugin(this);
        }
    }


}
