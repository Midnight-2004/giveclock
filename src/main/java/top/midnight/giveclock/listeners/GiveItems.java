package top.midnight.giveclock.listeners;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import top.midnight.giveclock.items.Items;

public class GiveItems implements Listener {

    private Items items = new Items();

    public GiveItems() {
        // 默认构造函数
    }

    @EventHandler
    public void onPlayerJoinServer(PlayerJoinEvent event){
        ItemStack clock = items.getItem("clock");
        if (clock == null) return;

        // 如果玩家背包中没有该物品，则尝试给予
        if (!event.getPlayer().getInventory().contains(clock)) {
            if (event.getPlayer().getInventory().firstEmpty() != -1) {
                event.getPlayer().getInventory().addItem(clock);
                event.getPlayer().sendMessage(ChatColor.AQUA + "你获得了一个菜单时钟!");
            } else {
                // 背包已满，掉落到玩家位置并提示
                event.getPlayer().getWorld().dropItemNaturally(event.getPlayer().getLocation(), clock);
                event.getPlayer().sendMessage(ChatColor.RED + "背包已满，菜单时钟已掉落在你身边。");
            }
        }
    }
}