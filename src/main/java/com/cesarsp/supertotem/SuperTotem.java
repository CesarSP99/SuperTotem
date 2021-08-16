package com.cesarsp.supertotem;

import com.cesarsp.supertotem.Listerners.DeathListener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class SuperTotem extends JavaPlugin {
    @Override
    public void onEnable() {
        // Plugin startup logic
        Logger logger = this.getLogger();
        logger.info("Enabling SuperTotem");
        getServer().getPluginManager().registerEvents(new DeathListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Logger logger = this.getLogger();
        logger.info("Disabling SuperTotem");
    }
}
