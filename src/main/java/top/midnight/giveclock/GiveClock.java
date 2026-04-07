package top.midnight.giveclock;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import top.midnight.giveclock.bstats;
import top.midnight.giveclock.commands.GiveClockCommand;
import top.midnight.giveclock.listeners.GiveItems;

public class GiveClock extends JavaPlugin {

    private static GiveClock instance;

    @Override
    public void onEnable() {
        instance = this;
        
        // 保存默认配置文件
        saveDefaultConfig();
        
        // 初始化 bStats
        int pluginId = 30636;
        Metrics metrics = new Metrics(this, pluginId);
        
        // Log plugin enabled message
        getLogger().info("GiveClock plugin enabled!");
        getLogger().info("作者: 午夜_Midnight");

        // 注册命令执行器
        getCommand("giveclock").setExecutor(new GiveClockCommand(this));

        // Register event listeners
        Bukkit.getPluginManager().registerEvents(new GiveItems(), this);
    }

    @Override
    public void onDisable() {
        // Log plugin disabled message
        getLogger().info("GiveClock plugin disabled!");
    }

    /**
     * 获取插件实例
     */
    public static GiveClock getInstance() {
        return instance;
    }

    /**
     * 解析颜色代码 (支持 & 格式)
     * 兼容 1.8.8+ 所有版本
     * 
     * @param text 需要解析颜色的文本
     * @return 解析后的文本
     */
    public static String colorize(String text) {
        if (text == null || text.isEmpty()) {
            return text;
        }
        
        // 处理 & 颜色代码
        return ChatColor.translateAlternateColorCodes('&', text);
    }
}