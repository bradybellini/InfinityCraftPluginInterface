package com.playinfinitycraft.infinitycraft.gui.commands;

import com.playinfinitycraft.infinitycraft.gui.menus.KillPlayerMenu;
import com.playinfinitycraft.infinitycraft.InfinityCraft;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class KillCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {

                Player p = (Player) sender;

                new KillPlayerMenu(InfinityCraft.getPlayerMenuUtility(p)).open();

        }

        if (!(sender instanceof Player)){
            Player p = (Player) Bukkit.getPlayer(args[0]);
            new KillPlayerMenu(InfinityCraft.getPlayerMenuUtility(p)).open();
        }

        return true;
    }
}
