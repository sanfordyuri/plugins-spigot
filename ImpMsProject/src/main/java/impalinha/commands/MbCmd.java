package impalinha.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class MbCmd implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

            if(args.length == 0) {
                CmdsMsg((Player) sender);
            } else {
                if(args.length == 1) {
                    if(args[0].equalsIgnoreCase("lista")) {
                        sender.sendMessage(ChatColor.YELLOW + " Lista de MobSpawner: ");
                        sender.sendMessage(" ");
                        sender.sendMessage(ChatColor.GRAY + " ID = 1 | Mob: Zombie");
                        sender.sendMessage(ChatColor.GRAY + " ID = 2 | Mob: Skeleton");
                        sender.sendMessage(ChatColor.GRAY + " ID = 3 | Mob: Creeper");
                        sender.sendMessage(ChatColor.GRAY + " ID = 4 | Mob: Mushroom cow");
                        sender.sendMessage(ChatColor.GRAY + " ID = 5 | Mob: Blaze");
                        sender.sendMessage(ChatColor.GRAY + " ID = 6 | Mob: Pig Zombie");
                        sender.sendMessage(ChatColor.GRAY + " ID = 7 | Mob: Spider");
                        sender.sendMessage(ChatColor.GRAY + " ID = 8 | Mob: Iron Golem");
                        return false;
                    }
                    CmdsMsg((Player) sender);
                    return false;
                } else {
                    if(args.length >= 3) {
                        if(args[0].equalsIgnoreCase("givar")) {
                            try {
                                int id = Integer.parseInt(args[1]);
                                Player target = Bukkit.getPlayer(args[2]);
                                int quantia = 1;
                                if (!(args.length == 3)) {
                                    if (!((args[3] == null) || args[3].equalsIgnoreCase(""))) {
                                        quantia = Integer.parseInt(args[3]);
                                    }
                                }
                                if(id == 1) {
                                    giveMs(target, EntityType.ZOMBIE, quantia);
                                    return false;
                                }
                                if(id == 2) {
                                    giveMs(target, EntityType.SKELETON, quantia);
                                    return false;
                                }
                                if(id == 3) {
                                    giveMs(target, EntityType.CREEPER, quantia);
                                    return false;
                                }
                                if(id == 4) {
                                    giveMs(target, EntityType.MUSHROOM_COW, quantia);
                                    return false;
                                }
                                if(id == 5) {
                                    giveMs(target, EntityType.BLAZE, quantia);
                                    return false;
                                }
                                if(id == 6) {
                                    giveMs(target, EntityType.PIG_ZOMBIE, quantia);
                                    return false;
                                }
                                if(id == 7) {
                                    giveMs(target, EntityType.SPIDER, quantia);
                                    return false;
                                }
                                if(id == 8) {
                                    giveMs(target, EntityType.IRON_GOLEM, quantia);
                                    return false;
                                }
                                sender.sendMessage(ChatColor.RED + "ID não encontrado. Use /Mb lista");
                            } catch (Exception e) {
                                sender.sendMessage(ChatColor.RED + "Erro ao executar o comando.");
                            }
                            return false;
                        }
                        CmdsMsg((Player) sender);
                    }
                    CmdsMsg((Player) sender);
                    return false;
                }
            }

        return false;
    }

    private void CmdsMsg(Player player) {
        player.sendMessage(" ");
        player.sendMessage(ChatColor.YELLOW + " /mb lista " + ChatColor.WHITE + " - " + ChatColor.GRAY + "Lista de MobSpawners");
        player.sendMessage(ChatColor.YELLOW + " /mb givar <Id> <Jogador> <Quantia> " + ChatColor.WHITE + " - " + ChatColor.GRAY + "Give um MobSpawners");
        player.sendMessage(" ");
    }

    private void giveMs(Player target, EntityType type, int quantia) {
        ItemStack spawner = new ItemStack(Material.MOB_SPAWNER);
        ItemMeta meta = spawner.getItemMeta();
        BlockStateMeta bsm = (BlockStateMeta) meta;
        BlockState bs = bsm.getBlockState();
        CreatureSpawner cs = (CreatureSpawner)bs;
        cs.setSpawnedType(type);
        bsm.setBlockState(bs);
        meta.setDisplayName(ChatColor.YELLOW + "Gerador de Monstros");
        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.GRAY + "Tipo: " + ChatColor.WHITE + type.getName());
        meta.setLore(lore);
        spawner.setItemMeta(meta);
        for(int i=0; i<quantia; i++) {
            target.getInventory().addItem(spawner);
        }
        target.sendMessage(ChatColor.GREEN + "Você recebeu um Spawner de " + type.getName());
    }


}
