package top.midnight.giveclock;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import top.midnight.giveclock.listeners.GiveItems;

public class GiveClock extends JavaPlugin {

    @Override
    public void onEnable() {
        // Log plugin enabled message
        getLogger().info("GiveClock plugin enabled!");

        // Register event listeners
        Bukkit.getPluginManager().registerEvents(new GiveItems(), this);

    }

    @Override
    public void onDisable() {
        // Log plugin disabled message
        getLogger().info("GiveClock plugin disabled!");
    }
}