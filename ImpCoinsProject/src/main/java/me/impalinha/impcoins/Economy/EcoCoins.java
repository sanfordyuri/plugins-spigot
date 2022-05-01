package me.impalinha.impcoins.Economy;

import me.impalinha.impcoins.ImpCoins;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.UUID;

public class EcoCoins implements Economy {

    private final ImpCoins plugin;
    public EcoCoins(ImpCoins plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public boolean hasBankSupport() {
        return false;
    }

    @Override
    public int fractionalDigits() {
        return 0;
    }

    @Override
    public String format(double v) {
        return null;
    }

    @Override
    public String currencyNamePlural() {
        return null;
    }

    @Override
    public String currencyNameSingular() {
        return null;
    }

    @Override
    public boolean hasAccount(String s) {
        Player p = Bukkit.getPlayer(s);
        return plugin.playerEcoHashMap.containsKey(p.getUniqueId());
    }

    @Override
    public boolean hasAccount(OfflinePlayer offlinePlayer) {
        return hasAccount(offlinePlayer.getName());
    }

    @Override
    public boolean hasAccount(String s, String s1) {
        return hasAccount(s);
    }

    @Override
    public boolean hasAccount(OfflinePlayer offlinePlayer, String s) {
        return hasAccount(offlinePlayer.getName());
    }

    @Override
    public double getBalance(String s) {
        return getBalance(Bukkit.getOfflinePlayer(s));
    }

    @Override
    public double getBalance(OfflinePlayer offlinePlayer) {
        return plugin.playerEcoHashMap.get(offlinePlayer.getUniqueId()).getAmount();
    }

    @Override
    public double getBalance(String s, String s1) {
        return getBalance(s);
    }

    @Override
    public double getBalance(OfflinePlayer offlinePlayer, String s) {
        return getBalance(offlinePlayer.getName());
    }

    @Override
    public boolean has(String s, double v) {
        Player player = Bukkit.getPlayer(s);
        PlayerEco playerEco = plugin.playerEcoHashMap.get(player.getUniqueId());
        return (playerEco.getAmount() >= v);
    }

    @Override
    public boolean has(OfflinePlayer offlinePlayer, double v) {
        return has(offlinePlayer.getName(), v);
    }

    @Override
    public boolean has(String s, String s1, double v) {
        return has(s, v);
    }

    @Override
    public boolean has(OfflinePlayer offlinePlayer, String s, double v) {
        return has(offlinePlayer.getName(), v);
    }

    @Override
    public EconomyResponse withdrawPlayer(String s, double v) {
        OfflinePlayer player = Bukkit.getOfflinePlayer(s);
        withdrawPlayer(player.getUniqueId(),v);
        return null;
    }

    @Override
    public EconomyResponse withdrawPlayer(OfflinePlayer offlinePlayer, double v) {
        withdrawPlayer(offlinePlayer.getUniqueId(),v);
        return null;
    }

    public void withdrawPlayer(UUID uuid, double v) {
        OfflinePlayer player = Bukkit.getOfflinePlayer(uuid);
        PlayerEco playerEco = plugin.playerEcoHashMap.get(player.getUniqueId());
        playerEco.removeAmount(v);
    }

    @Override
    public EconomyResponse withdrawPlayer(String s, String s1, double v) {
        withdrawPlayer(s, v);
        return null;
    }

    @Override
    public EconomyResponse withdrawPlayer(OfflinePlayer offlinePlayer, String s, double v) {
        withdrawPlayer(offlinePlayer.getName(), v);
        return null;
    }

    @Override
    public EconomyResponse depositPlayer(String name, double v) {
        UUID uuid = UUID.randomUUID();
        for(UUID uuids : plugin.playerEcoHashMap.keySet()) {
            if(Bukkit.getOfflinePlayer(uuids).getName().equals(ChatColor.stripColor(name))) {
                uuid = uuids;
                break;
            }
        }
        Bukkit.broadcastMessage(uuid.toString());
        plugin.playerEcoHashMap.get(uuid).addAmount(v);

        return new EconomyResponse(v, getBalance(Bukkit.getOfflinePlayer(uuid)), EconomyResponse.ResponseType.SUCCESS, "Sucesso");
    }

    public void setBalance(UUID uuid, double newAmount) {
        OfflinePlayer player = Bukkit.getOfflinePlayer(uuid);
        plugin.playerEcoHashMap.get(player.getUniqueId()).setAmount(newAmount);
    }


    @Override
    public EconomyResponse depositPlayer(OfflinePlayer offlinePlayer, double v) {
        return depositPlayer(offlinePlayer.getName(), v);
    }

    public boolean transferir(Player from, OfflinePlayer to, double amount) {
        PlayerEco playerFrom = plugin.playerEcoHashMap.get(from.getUniqueId());
        PlayerEco playerTo = plugin.playerEcoHashMap.get(to.getUniqueId());
        return playerFrom.sendAmount(amount, playerTo);
    }

    @Override
    public EconomyResponse depositPlayer(String s, String s1, double v) {
        depositPlayer(s,v);
        return null;
    }

    @Override
    public EconomyResponse depositPlayer(OfflinePlayer offlinePlayer, String s, double v) {
        depositPlayer(s,v);
        return null;
    }

    @Override
    public EconomyResponse createBank(String s, String s1) {
        return null;
    }

    @Override
    public EconomyResponse createBank(String s, OfflinePlayer offlinePlayer) {
        return null;
    }

    @Override
    public EconomyResponse deleteBank(String s) {
        return null;
    }

    @Override
    public EconomyResponse bankBalance(String s) {
        return null;
    }

    @Override
    public EconomyResponse bankHas(String s, double v) {
        return null;
    }

    @Override
    public EconomyResponse bankWithdraw(String s, double v) {
        return null;
    }

    @Override
    public EconomyResponse bankDeposit(String s, double v) {
        return null;
    }

    @Override
    public EconomyResponse isBankOwner(String s, String s1) {
        return null;
    }

    @Override
    public EconomyResponse isBankOwner(String s, OfflinePlayer offlinePlayer) {
        return null;
    }

    @Override
    public EconomyResponse isBankMember(String s, String s1) {
        return null;
    }

    @Override
    public EconomyResponse isBankMember(String s, OfflinePlayer offlinePlayer) {
        return null;
    }

    @Override
    public List<String> getBanks() {
        return null;
    }

    @Override
    public boolean createPlayerAccount(String uuid) {
        if(!hasAccount(uuid)) {
            try {
                Player player = Bukkit.getPlayer(uuid);
                plugin.getSqlManager().createPlayer(player);
            } catch (Exception e) {
                return false;
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean createPlayerAccount(OfflinePlayer offlinePlayer) {
        return createPlayerAccount(offlinePlayer.getUniqueId().toString());
    }

    @Override
    public boolean createPlayerAccount(String s, String s1) {
        return createPlayerAccount(s);
    }

    @Override
    public boolean createPlayerAccount(OfflinePlayer offlinePlayer, String s) {
        return createPlayerAccount(offlinePlayer.getUniqueId().toString());
    }
}
