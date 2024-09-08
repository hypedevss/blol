package org.blol;

import org.blol.tabcompletions.portalConfigTab;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.blol.commands.*;
import org.blol.events.*;
import org.blol.tabcompletions.*;
public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        // config setup
        FileConfiguration config = this.getConfig();
        config.addDefault("isChatOff", false);
        config.addDefault("webhookUrl", "https://discord.com/api/webhooks/0000000000000000000/XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
        config.addDefault("portals.nether", true);
        config.addDefault("portals.end", true);
        config.options().copyDefaults(true);
        saveConfig();
        // Plugin startup logic
        getLogger().info("Bulerman.lol plugin został uruchomiony!");
        // load commands
        getCommand("adm").setExecutor(new adminChatCommand());
        getCommand("chaton").setExecutor(new chatManagementCommand());
        getCommand("chatoff").setExecutor(new chatManagementCommand());
        getCommand("helpop").setExecutor(new helpopCommand());
        getCommand("portalcfg").setExecutor(new portalConfigCommand());
        getCommand("portalcfg").setTabCompleter(new portalConfigTab());
        getCommand("blol").setExecutor(new blolCommand());
        getCommand("blol").setTabCompleter(new aboutTab());
        // load events
        getServer().getPluginManager().registerEvents(new chatManagementEvent(), this);
        getServer().getPluginManager().registerEvents(new antiBedrockEvent(), this);
        getServer().getPluginManager().registerEvents(new portalEvent(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("Bulerman.lol plugin został wyłączony!");
    }
}
