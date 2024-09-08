package org.blol.commands;

import org.blol.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class chatManagementCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        Main plugin = JavaPlugin.getPlugin(Main.class);
        // chat off
        if (cmd.getName().equalsIgnoreCase("chatoff")) {
            if (!sender.hasPermission(("blol.chatmgr"))) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "» &cNie masz uprawnień."));
                return false;
            }
           if (plugin.getConfig().getBoolean("isChatOff")) {
               sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "» &cCzat jest obecnie wyłączony."));
               return false;
           }
           // if conditions not met disable chat
            plugin.getConfig().set("isChatOff", true);
            plugin.saveConfig();
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "» &aCzat został wyłączony."));
            // send notice to every player
            for (Player player : Bukkit.getOnlinePlayers()) {
                String message = "&f&m--------&r Czat został &cwyłączony&r przez &f&l" + sender.getName() + " &r&f&m--------";
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
            }
            return true;
        }
        // chat on
        if (cmd.getName().equalsIgnoreCase("chaton")) {
            if (!sender.hasPermission(("blol.chatmgr"))) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "» &cNie masz uprawnień."));
                return false;
            }
            if (!plugin.getConfig().getBoolean("isChatOff")) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "» &cCzat jest obecnie włączony."));
                return false;
            }
            plugin.getConfig().set("isChatOff", false);
            plugin.saveConfig();
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "» &aCzat został włączony."));
            // send notice to every player
            for (Player player : Bukkit.getOnlinePlayers()) {
                String message = "&f&m--------&r Czat został &awłączony&r przez &f&l" + sender.getName() + " &r&f&m--------";
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
            }
            return true;
        }
        return false;
    }
}
