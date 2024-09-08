package org.blol.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class adminChatCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (cmd.getName().equalsIgnoreCase("adm")) {
            if (!sender.hasPermission("blol.adminchat")) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "» &cNie masz uprawnień."));
                return false;
            }
            // check if there's a message provided
            if (args.length == 0) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "» &cHalo gdzie wiadomość?."));
                return false;
            }
            // get the message
            String message = String.join(" ", args);
            // send the message to all players
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (player.hasPermission("blol.adminchat")) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cADM &f» " + sender.getName() + ": " + message));

                }
            }
            return true;
        }
        return false;
    }
}
