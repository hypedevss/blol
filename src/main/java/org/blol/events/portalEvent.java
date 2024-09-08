package org.blol.events;

import org.blol.Main;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class portalEvent implements Listener {

    @EventHandler
    public void onPlayerPortal(PlayerPortalEvent event) {
        Player player = event.getPlayer();
        Main plugin = JavaPlugin.getPlugin(Main.class);

    }

    @EventHandler
    public void onPlayerTeleport (PlayerTeleportEvent event) {
        Player player = event.getPlayer();
        Main plugin = JavaPlugin.getPlugin(Main.class);
        String cause = event.getCause().name();

        if (cause.equalsIgnoreCase(("NETHER_PORTAL"))) {
            if (!plugin.getConfig().getBoolean("portals.nether")) {
                event.setCancelled(true);
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "» &cNether jest obecnie wyłączony."));
            }
        }
        Integer endPortalSentMsg = 0;
        if (cause.equalsIgnoreCase(("END_PORTAL"))) {
            if (!plugin.getConfig().getBoolean("portals.end")) {
                event.setCancelled(true);
                if (endPortalSentMsg == 0) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "» &cEnd jest obecnie wyłączony."));
                    endPortalSentMsg++;
                }
            }
        }
    }

}
