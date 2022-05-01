package me.impalinha.impcoins.Commands;

import me.impalinha.impcoins.Economy.EcoCoins;
import me.impalinha.impcoins.ImpCoins;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Locale;
import java.util.UUID;

import static me.impalinha.impcoins.Utils.Constants.PERMISSION;
import static me.impalinha.impcoins.Utils.Constants.PREFIX;

public class CmdCoins implements CommandExecutor {

    private final ImpCoins plugin;
    private final EcoCoins eco;
    public CmdCoins(ImpCoins plugin) {
        this.plugin = plugin;
        this.eco = new EcoCoins(plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender != null) {
            if(sender instanceof Player) {
                Player p = (Player) sender;
                if (command.getName().equalsIgnoreCase("Money")) {
                    if (args.length == 0) {
                        p.sendMessage(PREFIX + "Você possui §e" + eco.getBalance(p.getName()) + " §fmoeda(s)");
                        if(p.hasPermission(PERMISSION)) {
                           p.sendMessage("§7Disponível para Admin:");
                           p.sendMessage("§e/Money set <Jogador> <Quantia>");
                           p.sendMessage("§e/Money add <Jogador> <Quantia>");
                           p.sendMessage("§e/Money remove <Jogador> <Quantia>");
                        }
                        return false;
                    }
                    if(args[0].equalsIgnoreCase("enviar")) {
                        return enviarSaldo(args, p);
                    }
                    if(p.hasPermission(PERMISSION)) {
                        if(args[0].equalsIgnoreCase("add")) {
                            try {
                                OfflinePlayer target = Bukkit.getOfflinePlayer(args[1]);
                                double saldo = Double.parseDouble(args[2]);
                                eco.depositPlayer(target, saldo);
                                p.sendMessage(PREFIX + "§e" + saldo + " §fMoeda(s) Enviada(s) para §e" + target.getName());
                            } catch (Exception e) {
                                e.printStackTrace();
                                p.sendMessage(PREFIX + "§cJogador não encontrado.");
                            }
                        } else if(args[0].equalsIgnoreCase("remove")) {
                            try {
                                OfflinePlayer target = Bukkit.getOfflinePlayer(args[1]);
                                double saldo = Double.parseDouble(args[2]);
                                eco.withdrawPlayer(target.getUniqueId(), saldo);
                                p.sendMessage(PREFIX + "§e" + saldo + " §fMoeda(s) Retirada(s) de §e" + target.getName());
                            } catch (Exception e) {
                                e.printStackTrace();
                                p.sendMessage(PREFIX + "§cJogador não encontrado.");
                            }
                        } else if(args[0].equalsIgnoreCase("set")) {
                            try {
                                OfflinePlayer target = Bukkit.getOfflinePlayer(args[1]);
                                double saldo = Double.parseDouble(args[2]);
                                p.sendMessage(target.getUniqueId().toString());
                                eco.setBalance(target.getUniqueId(), saldo);
                                p.sendMessage(PREFIX + "§fSaldo de §e" + target.getName() + " §fsetado para §e" + saldo);
                            } catch (Exception e) {
                                e.printStackTrace();
                                p.sendMessage(PREFIX + "§cJogador não encontrado.");
                            }
                        } else {
                            p.sendMessage("§cComando não reconhecido pelo servidor.");
                            return false;
                        }
                    } else {
                        p.sendMessage("§cComando não reconhecido pelo servidor.");
                        return false;
                    }
                }

            }
        }
        return false;
    }


    private boolean enviarSaldo(String[] args, Player p) {
        if(args.length != 3) {
            p.sendMessage(PREFIX + "§cUso correto: /Coin enviar <Jogador> <Quantia>");
            return false;
        }
        try {
            double quantia = Double.parseDouble(args[2]);
            OfflinePlayer target = Bukkit.getOfflinePlayer(args[1]);
            if((target.getUniqueId() == p.getUniqueId()) || (quantia <= 0)) {
                p.sendMessage(PREFIX + "§cNão é possível concluir a operação.");
                return false;
            }
            boolean transferir = eco.transferir(p, target, quantia);
            if(!transferir) {
                p.sendMessage(PREFIX + "§cSem saldo o suficiente.");
                return false;
            }
            p.sendMessage(PREFIX + "§aEnviado §e" + quantia + " §apara §e" + target.getName());
            if(target.isOnline()) {
                Player t = (Player) target;
                t.sendMessage(PREFIX + "§aRecebido §e" + quantia + " §ade §e" + p.getName());
            }
        } catch (Exception e) {
            p.sendMessage(PREFIX + "§cJogador não encontrado.");
        }
        return false;
    }
}
