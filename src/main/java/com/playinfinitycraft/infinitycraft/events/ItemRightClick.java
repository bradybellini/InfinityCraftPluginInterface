package com.playinfinitycraft.infinitycraft.events;

import com.playinfinitycraft.infinitycraft.items.ItemManager;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandException;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class ItemRightClick implements Listener {

    @EventHandler
    public static void onRightClick(PlayerInteractEvent event) {
        try {
            if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                if (event.getItem() != null) {
                    if (event.getItem().getItemMeta().equals(ItemManager.humanityPoints.getItemMeta())) {
                        Player p = event.getPlayer();
                        Bukkit.getLogger().info(p.getDisplayName());
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "killplayer " + p.getDisplayName());
                    }
                }
            }
        } catch (CommandException e) {
            Bukkit.getLogger().info(e.toString());
        }

    }

}
