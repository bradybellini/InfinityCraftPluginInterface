package com.playinfinitycraft.infinitycraft.gui.menus;


import com.playinfinitycraft.infinitycraft.gui.PlayerMenuUtility;
import com.playinfinitycraft.infinitycraft.gui.Menu;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.awt.*;
import java.util.ArrayList;

public class SuicideConfirmMenu extends Menu {


    public SuicideConfirmMenu(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }

    @Override
    public String getMenuName() {
        return "Confirm: Kill Yourself?";
    }

    @Override
    public int getSlots() {
        return 9;
    }

    @Override
    public void handleMenu(InventoryClickEvent e) {

        switch(e.getCurrentItem().getType()) {
            case EMERALD:
                e.getWhoClicked().closeInventory();
                e.getWhoClicked().setHealth(0.0);
                break;
            case BARRIER:
                e.getWhoClicked().sendMessage("Saved");
                e.getWhoClicked().closeInventory();
                break;
        }

    }

    @Override
    public void setMenuItems() {

        ItemStack yes = new ItemStack(Material.EMERALD, 1);
        ItemMeta yes_meta = yes.getItemMeta();
        yes_meta.setDisplayName(ChatColor.GREEN + "Yes");
        ArrayList<String> yes_lore = new ArrayList<>();
        yes_lore.add(ChatColor.AQUA + "RIP?");
        yes_meta.setLore(yes_lore);
        yes.setItemMeta(yes_meta);

        ItemStack no = new ItemStack(Material.BARRIER, 1);
        ItemMeta no_meta = no.getItemMeta();
        no_meta.setDisplayName(ChatColor.DARK_RED + "No");
        no.setItemMeta(no_meta);

        inventory.setItem(3, yes);
        inventory.setItem(5, no);

    }
}
