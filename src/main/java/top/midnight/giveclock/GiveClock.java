package top.midnight.giveclock;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import org.bstats.bukkit.Metrics;
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
        
        // 记录插件启用信息
        getLogger().info("GiveClock plugin has been enabled!");
        getLogger().info("Author: 午夜_Midnight");

        // 注册命令执行器和自动补全器
        GiveClockCommand giveClockCommand = new GiveClockCommand(this);
        getCommand("giveclock").setExecutor(giveClockCommand);
        getCommand("giveclock").setTabCompleter(giveClockCommand);

        // 注册事件监听器
        Bukkit.getPluginManager().registerEvents(new GiveItems(), this);
    }

    @Override
    public void onDisable() {
        // 记录插件禁用信息
        getLogger().info("GiveClock plugin has been disabled!");
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