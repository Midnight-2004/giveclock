package top.midnight.giveclock.items;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.ChatColor;
import java.util.ArrayList;
import java.util.List;

public class Items {

    // 获取Items方法，返回一个ItemStack对象
    public ItemStack getItem(String itemName) {
        if (itemName.equalsIgnoreCase("clock")) {
            ItemStack clock = new ItemStack(Material.CLOCK, 1); 
            ItemMeta meta = clock.getItemMeta();
            if (meta != null) {
                meta.setDisplayName(ChatColor.RESET + "菜单"); // 显示名称为"菜单"
                
                List<String> lore = new ArrayList<>();
                lore.add("拿在手上右键打开菜单"); // lore为"拿在手上右键打开菜单"
                meta.setLore(lore);
                
                clock.setItemMeta(meta);
            }
            return clock;
        }
        
        return null; // 如果找不到对应物品，返回null
    }
}
