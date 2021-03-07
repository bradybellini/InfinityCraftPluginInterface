package com.playinfinitycraft.infinitycraft.gui.menus;

import com.playinfinitycraft.infinitycraft.gui.PaginatedMenu;
import com.playinfinitycraft.infinitycraft.gui.PlayerMenuUtility;
import com.playinfinitycraft.infinitycraft.InfinityCraft;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.UUID;

public class KillPlayerMenu extends PaginatedMenu {

    public KillPlayerMenu(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }

    @Override
    public String getMenuName() {
        return "Kill a Player";
    }

    @Override
    public int getSlots() {
        return 54;
    }

    @Override
    public void handleMenu(InventoryClickEvent e) {

        Player p = (Player) e.getWhoClicked();

        ArrayList<Player> players = new ArrayList<Player>(Bukkit.getServer().getOnlinePlayers());

        if (e.getCurrentItem().getType().equals(Material.PLAYER_HEAD)) {

            playerMenuUtility.setPlayerToKill(Bukkit.getPlayer(UUID.fromString(e.getCurrentItem().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(InfinityCraft.getPlugin(), "uuid"), PersistentDataType.STRING))));

            new KillConfirmMenu(playerMenuUtility).open();

        }else if (e.getCurrentItem().getType().equals(Material.BARRIER)) {

            //close inventory
            p.closeInventory();

        }else if(e.getCurrentItem().getType().equals(Material.DARK_OAK_BUTTON)){
            if (ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName()).equalsIgnoreCase("Left")){
                if (page == 0){
                    p.sendMessage(ChatColor.GRAY + "You are already on the first page.");
                }else{
                    page = page - 1;
                    super.open();
                }
            }else if (ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName()).equalsIgnoreCase("Right")){
                if (!((index + 1) >= players.size())){
                    page = page + 1;
                    super.open();
                }else{
                    p.sendMessage(ChatColor.GRAY + "You are on the last page.");
                }
            }
        }


    }

    @Override
    public void setMenuItems() {

        addMenuBorder();

        ArrayList<Player> players = new ArrayList<>(Bukkit.getServer().getOnlinePlayers());

        if (players != null && !players.isEmpty()) {
            for(int i = 0; i < super.maxItemsPerPage; i++) {
                index = getMaxItemsPerPage() * page + i;
                if(index >= players.size()) break;
                if (players.get(index) != null) {

                    ItemStack playerItem = new ItemStack(Material.PLAYER_HEAD, 1);
                    ItemMeta playerMeta = playerItem.getItemMeta();
                    playerMeta.setDisplayName(ChatColor.RED + players.get(index).getDisplayName());

                    playerMeta.getPersistentDataContainer().set(new NamespacedKey(InfinityCraft.getPlugin(), "uuid"), PersistentDataType.STRING, players.get(index).getUniqueId().toString());

                    playerItem.setItemMeta(playerMeta);

                    inventory.addItem(playerItem);

                }
            }
        }

    }
}
