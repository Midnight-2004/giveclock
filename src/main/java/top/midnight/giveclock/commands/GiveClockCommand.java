package top.midnight.giveclock.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import top.midnight.giveclock.GiveClock;

public class GiveClockCommand implements CommandExecutor {

    private final GiveClock plugin;

    public GiveClockCommand(GiveClock plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // 检查是否有 reload 子命令
        if (args.length == 0) {
            sender.sendMessage(GiveClock.colorize("&c用法: /giveclock reload"));
            return true;
        }

        String subCommand = args[0].toLowerCase();

        if (subCommand.equals("reload")) {
            // 检查权限
            if (!sender.hasPermission("giveclock.reload")) {
                sender.sendMessage(GiveClock.colorize("&c你没有权限执行此命令!"));
                return true;
            }

            // 重载配置
            plugin.reloadConfig();
            sender.sendMessage(GiveClock.colorize("&a配置已重载!"));
            
            // 如果是玩家执行的，记录日志
            if (sender instanceof Player) {
                plugin.getLogger().info("玩家 " + ((Player) sender).getName() + " 重载了插件配置.");
            } else {
                plugin.getLogger().info("控制台重载了插件配置.");
            }
            
            return true;
        }

        // 未知子命令
        sender.sendMessage(GiveClock.colorize("&c未知子命令. 用法: /giveclock reload"));
        return true;
    }
}
