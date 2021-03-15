package com.playinfinitycraft.infinitycraft.general;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.InventoryHolder;

public class PlayerBar implements Listener {

    @EventHandler
    public void onPlayerBarClick(InventoryClickEvent e) {

        InventoryHolder holder = e.getInventory().getHolder();


//        if (e.getSlotType().equals(InventoryType.SlotType.QUICKBAR) && )


    }

}
