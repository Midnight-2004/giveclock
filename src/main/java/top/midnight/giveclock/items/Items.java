package top.midnight.giveclock.items;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import top.midnight.giveclock.GiveClock;

import java.util.ArrayList;
import java.util.List;

public class Items {

    /**
     * 获取物品，返回一个 ItemStack 对象
     * 物品的名称和描述从 config.yml 中读取
     * 
     * @param itemName 物品名称标识 (clock/watch)
     * @return ItemStack 或 null
     */
    public ItemStack getItem(String itemName) {
        if (itemName.equalsIgnoreCase("clock") || itemName.equalsIgnoreCase("watch")) {
            FileConfiguration config = GiveClock.getInstance().getConfig();
            
            // 根据 Minecraft 版本确定材料类型
            Material materialType;
            try {
                materialType = Material.valueOf("CLOCK"); // 1.13+ 新版本
            } catch (IllegalArgumentException e) {
                materialType = Material.valueOf("WATCH"); // 1.12- 旧版本
            }
            
            ItemStack item = new ItemStack(materialType, 1);
            ItemMeta meta = item.getItemMeta();
            
            if (meta != null) {
                // 从配置读取物品名称并解析颜色
                String displayName = config.getString("item.name", "&e菜单");
                meta.setDisplayName(GiveClock.colorize(displayName));
                
                // 从配置读取 Lore 并解析颜色
                List<String> loreConfig = config.getStringList("item.lore");
                if (!loreConfig.isEmpty()) {
                    List<String> lore = new ArrayList<>();
                    for (String line : loreConfig) {
                        lore.add(GiveClock.colorize(line));
                    }
                    meta.setLore(lore);
                }
                
                item.setItemMeta(meta);
            }
            
            return item;
        }
        
        return null; // 如果找不到对应物品，返回 null
    }
}
