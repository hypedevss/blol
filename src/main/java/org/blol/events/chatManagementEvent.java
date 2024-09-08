package org.blol.events;

import org.blol.Main;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class chatManagementEvent implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        Main plugin = JavaPlugin.getPlugin(Main.class);
        Boolean chatState = plugin.getConfig().getBoolean("isChatOff");
        if (chatState) {
            if (player.hasPermission("blol.chatmgr.bypass")) {
                return;
            }
            String message = "&f» &fWiadomość nie została wysłana, bo czat jest obecnie &c&lwyłączony&r.";
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
            event.setCancelled(true);
            return;
        }
    }
}
