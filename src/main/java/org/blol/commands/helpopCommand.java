package org.blol.commands;

import org.blol.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import okhttp3.*;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public class helpopCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        if (cmd.getName().equalsIgnoreCase("helpop")) {
            String message = String.join(" ", args);
            Main plugin = JavaPlugin.getPlugin(Main.class);
            if (args.length == 0) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "» &cHalo gdzie wiadomość?."));
                return false;
            }
            // send the actual request
            String webhookUrl = plugin.getConfig().getString("webhookUrl");
            OkHttpClient client = new OkHttpClient();
            String jsonBody = "{\"username\": \"Bulerman.lol | HelpOp\", \"embeds\": [{\"title\": \"Nowy helpop\", \"color\": 16711680, \"fields\": [{\"name\": \"Gracz\", \"value\": \"" + sender.getName() + "\", \"inline\": true}, {\"name\": \"Wiadomość\", \"value\": \"" + message + "\", \"inline\": false}]}]}";
            RequestBody body = RequestBody.create(jsonBody, MediaType.parse("application/json"));
            Request request = new Request.Builder()
                    .url(webhookUrl)
                    .post(body)
                    .header("Content-Type", "application/json")
                    .build();
            // send on chat to admins
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (player.hasPermission("blol.helpop.receive")) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cHELPOP &f» " + sender.getName() + ": " + message));
                    player.playSound(player.getLocation(), org.bukkit.Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.0F);
                }
            }
            // send shit
            try {
                Response res = client.newCall(request).execute();
                if (res.code() == 204) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "» &aWiadomość została wysłana."));
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }
}
