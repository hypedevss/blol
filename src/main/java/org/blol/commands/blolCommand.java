package org.blol.commands;

import org.blol.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class blolCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (cmd.getName().equalsIgnoreCase("blol")) {
            if (args.length == 0) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "» &cNiepoprawny argument."));
                return false;
            }
            String option = args[0];
            Main plugin = JavaPlugin.getPlugin(Main.class);
            switch (option) {
                case "reload":
                    if (!sender.hasPermission("blol.reload")) {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "» &cNie masz uprawnień."));
                        return false;
                    }
                    plugin.reloadConfig();
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "» &aKonfiguracja została przeładowana."));
                    break;
                case "about":
                    String message = "&6ʙᴜʟᴇʀᴍᴀɴ&1.&2ʟᴏʟ&r\n&7&l» &rWersja: &6" + plugin.getDescription().getVersion() + "\n&7&l» &rZrobiony przez: y";
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
                    break;
                default:
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "» &cNiepoprawny argument."));
                    break;
            }
        }
        return false;
    }
}
