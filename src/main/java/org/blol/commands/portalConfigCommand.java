package org.blol.commands;

import org.blol.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class portalConfigCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (cmd.getName().equalsIgnoreCase("portalcfg")) {
            // check permissions
            Main plugin = JavaPlugin.getPlugin(Main.class);
            if (!sender.hasPermission("blol.portalcfg")) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "» &cNie masz uprawnień."));
                return false;
            }
            if (args.length == 0 || args.length == 1) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "» &cPoprawne użycie komendy to &f&l/portalcfg <end/nether> <on/off>&r."));
            }
            if (args.length == 2) {
                if (args[0].equalsIgnoreCase("end") || args[0].equalsIgnoreCase("nether")) {
                    if (args[1].equalsIgnoreCase("on")) {
                        // for nether
                        if (args[0].equalsIgnoreCase("nether")) {
                            plugin.getConfig().set("portals.nether", true);
                            plugin.saveConfig();
                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "» &aPortal do netheru został włączony."));
                            return false;
                        }
                        // for end
                        if (args[0].equalsIgnoreCase("end")) {
                            plugin.getConfig().set("portals.end", true);
                            plugin.saveConfig();
                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "» &aPortal do endu został włączony."));
                            return false;
                        }
                    }
                    // turn off
                    if (args[1].equalsIgnoreCase("off")) {
                        if (args[0].equalsIgnoreCase("nether")) {
                            plugin.getConfig().set("portals.nether", false);
                            plugin.saveConfig();
                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "» &aPortal do netheru został wyłączony."));
                            return false;
                        }
                        if (args[0].equalsIgnoreCase("end")) {
                            plugin.getConfig().set("portals.end", false);
                            plugin.saveConfig();
                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "» &aPortal do endu został wyłączony."));
                            return false;
                        }
                    }
                }
            }
        }
        return false;
    }
}
