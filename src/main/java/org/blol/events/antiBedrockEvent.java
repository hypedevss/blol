package org.blol.events;

import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDropItemEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerDropItemEvent;

public class antiBedrockEvent implements Listener {

    // anti bedrock chat
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        HumanEntity player = event.getWhoClicked();
        // not for admins
        if (player.hasPermission("blol.blockres.bypass")) {
            return;
        }
        // check if clicked block is bedrock
        if (event.getCurrentItem().getType() == Material.BEDROCK) {
            event.setCancelled(true);
        }
    }
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        if (player.hasPermission("blol.blockres.bypass")) {
            return;
        }
        if (event.getBlock().getType() == Material.BEDROCK) {
            event.setCancelled(true);
            // delete bedrock from player's inventory
            player.getInventory().remove(event.getItemInHand());
        }
    }
    @EventHandler
    public void onEntityPickupItem(EntityPickupItemEvent event) {
        Player player = (Player) event.getEntity();
        if (player.hasPermission("blol.blockres.bypass")) {
            return;
        }
        if (event.getItem().getItemStack().getType() == Material.BEDROCK) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent event) {
        Player player = event.getPlayer();
        if (player.hasPermission("blol.blockres.bypass")) {
            return;
        }
        if (event.getItemDrop().getItemStack().getType() == Material.BEDROCK) {
            event.setCancelled(true);
        }
    }
    @EventHandler
    public void onBlockDropItem(BlockDropItemEvent event) {
        // remove bedrock if dropped
        event.getItems().forEach(itemStack -> {
            if (itemStack.getItemStack().getType() == Material.BEDROCK) {
                itemStack.setItemStack(null);
                return;
            }
        });

    }
}
