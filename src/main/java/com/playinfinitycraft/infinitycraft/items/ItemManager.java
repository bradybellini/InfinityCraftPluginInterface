package com.playinfinitycraft.infinitycraft.items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemManager {

    public static ItemStack humanityPoints;

    public static void init() {
        createHumanityPoints();

    }

    private static void createHumanityPoints(){
        ItemStack item = new ItemStack(Material.HEART_OF_THE_SEA, 10);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.LIGHT_PURPLE + "Humanity Points");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.YELLOW + "# of Humanity Points you have");
        meta.setLore(lore);
        meta.addEnchant(Enchantment.LUCK, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        humanityPoints = item;
    }

    private static void createCustomUI(){
        ItemStack item = new ItemStack(Material.HEART_OF_THE_SEA, 10);
        ItemMeta meta = item.getItemMeta();
//        meta.setCustomModelData();
    }


}
