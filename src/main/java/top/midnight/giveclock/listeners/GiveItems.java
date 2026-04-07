package top.midnight.giveclock.listeners;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import top.midnight.giveclock.GiveClock;
import top.midnight.giveclock.items.Items;

public class GiveItems implements Listener {

    private Items items = new Items();

    public GiveItems() {
        // 默认构造函数
    }

    /**
     * 检查玩家背包中是否已存在指定类型的物品
     * 兼容 1.8.8+ 所有版本
     * 
     * @param playerInventory 玩家背包
     * @param targetType 目标物品类型
     * @return 如果存在返回 true
     */
    private boolean hasItemByType(org.bukkit.inventory.PlayerInventory inventory, Material targetType) {
        for (ItemStack item : inventory.getContents()) {
            if (item != null && item.getType() == targetType) {
                return true;
            }
        }
        return false;
    }

    @EventHandler
    public void onPlayerJoinServer(PlayerJoinEvent event) {
        FileConfiguration config = GiveClock.getInstance().getConfig();
        
        ItemStack clock = items.getItem("clock");
        if (clock == null) return;

        // 确定钟的材料类型
        Material clockMaterial;
        try {
            clockMaterial = Material.valueOf("CLOCK"); // 1.13+ 新版本
        } catch (IllegalArgumentException e) {
            clockMaterial = Material.valueOf("WATCH"); // 1.12- 旧版本
        }

        // 检查玩家背包中是否已有该类型的物品
        if (!hasItemByType(event.getPlayer().getInventory(), clockMaterial)) {
            if (event.getPlayer().getInventory().firstEmpty() != -1) {
                event.getPlayer().getInventory().addItem(clock);
                
                // 从配置读取并给予物品的消息
                String giveMessage = config.getString("message.give", "&a[自动给钟] &f你获得了一个用来打开菜单的钟!");
                event.getPlayer().sendMessage(GiveClock.colorize(giveMessage));
            } else {
                // 背包已满，从配置读取提示消息
                String fullMessage = config.getString("message.full", "&c[自动给钟] &f背包已满，请腾出空间后重新登录服务器获取钟。");
                event.getPlayer().sendMessage(GiveClock.colorize(fullMessage));
            }
        }
    }
}