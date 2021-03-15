package com.playinfinitycraft.infinitycraft.general.commands;

import com.playinfinitycraft.infinitycraft.items.ItemManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class BaseCommands implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
//        if (!(sender instanceof Player)) {
//            sender.sendMessage("Only players can currently user this command");
//            return true;
//        }

//        ConsoleCommandSender console = (ConsoleCommandSender) sender;

        if (!(sender instanceof Player)) {

                if (args[0].equalsIgnoreCase("give")) {
                    if (args[2].equalsIgnoreCase("hp")) {
                        Bukkit.getPlayer(args[1]).getInventory().addItem(ItemManager.humanityPoints);
                    }

                }

        }

//        if (command.getName().equalsIgnoreCase("ic")) {
//            if (args.length >= 2) {
//                if (args[0].equalsIgnoreCase("give")) {
//                    if (args[1].equalsIgnoreCase("humanitypoints")) {
//                        player.getInventory().addItem(ItemManager.humanityPoints);
//                    }
//                }
//
//            }
//        }
        return true;
    }

}
