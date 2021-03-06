package com.playinfinitycraft.infinitycraft.gui.commands;

import com.playinfinitycraft.infinitycraft.InfinityCraft;
import com.playinfinitycraft.infinitycraft.gui.menus.SuicideConfirmMenu;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SuicideCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {

            Player p = (Player) sender;

            new SuicideConfirmMenu(InfinityCraft.getPlayerMenuUtility(p)).open();

        }


        return true;
    }
}
