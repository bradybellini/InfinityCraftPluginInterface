package com.playinfinitycraft.infinitycraft.gui.menus;

import com.playinfinitycraft.infinitycraft.gui.Menu;
import com.playinfinitycraft.infinitycraft.gui.PlayerMenuUtility;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class CreateCorporation extends Menu {
    public CreateCorporation(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }

    @Override
    public String getMenuName() {
        return "Create a Corp";
    }

    @Override
    public int getSlots() {
        return 10;
    }

    @Override
    public void handleMenu(InventoryClickEvent e) {

        switch (e.getCurrentItem().getType()) {
            case EMERALD:

                Player target = super.playerMenuUtility.getPlayerToKill();
                target.setHealth(0.0);

                e.getWhoClicked().sendMessage("They have been killed");
                break;
            case BARRIER:
                new com.playinfinitycraft.infinitycraft.gui.menus.KillPlayerMenu(playerMenuUtility).open();
                break;

        }

    }

    @Override
    public void setMenuItems() {

        ItemStack yes = new ItemStack(Material.EMERALD, 1);
        ItemMeta yes_meta = yes.getItemMeta();
        yes_meta.setDisplayName(ChatColor.GREEN + "Yes");
        ArrayList<String> yes_lore = new ArrayList<>();
        yes_lore.add(ChatColor.AQUA + "Create your own Corp");
        yes_meta.setLore(yes_lore);
        yes.setItemMeta(yes_meta);
        ItemStack no = new ItemStack(Material.BARRIER, 1);
        ItemMeta no_meta = no.getItemMeta();
        no_meta.setDisplayName(ChatColor.DARK_RED + "You can always come back!");
        no.setItemMeta(no_meta);

        inventory.setItem(3, yes);
        inventory.setItem(5, no);

    }
}
