package top.midnight.giveclock.listeners;

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

    @EventHandler
    public void onPlayerJoinServer(PlayerJoinEvent event) {
        FileConfiguration config = GiveClock.getInstance().getConfig();
        
        ItemStack clock = items.getItem("clock");
        if (clock == null) return;

        // 如果玩家背包中没有该物品，则尝试给予
        if (!event.getPlayer().getInventory().contains(clock)) {
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