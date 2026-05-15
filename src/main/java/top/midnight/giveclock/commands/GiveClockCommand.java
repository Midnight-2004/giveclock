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
        // 无参数或 help 子命令
        if (args.length == 0 || args[0].equalsIgnoreCase("help")) {
            sendHelp(sender);
            return true;
        }

        String subCommand = args[0].toLowerCase();

        if (subCommand.equals("reload")) {
            // 检查权限
            if (!sender.hasPermission("giveclock.reload")) {
                sender.sendMessage(GiveClock.colorize("&c[GiveClock] 你没有权限执行此命令!"));
                return true;
            }

            // 重载配置
            plugin.reloadConfig();
            sender.sendMessage(GiveClock.colorize("&a[GiveClock] 配置已重载!"));
            
            // 如果是玩家执行的，记录日志
            if (sender instanceof Player) {
                plugin.getLogger().info("玩家 " + ((Player) sender).getName() + " 重载了插件配置。");
            } else {
                plugin.getLogger().info("控制台重载了插件配置。");
            }
            
            return true;
        } else if (subCommand.equals("version")) {
            sendVersion(sender);
            return true;
        }

        // 未知子命令
        sender.sendMessage(GiveClock.colorize("&c[GiveClock] 未知命令. 使用 /giveclock help 查看帮助"));
        return true;
    }

    /**
     * 发送帮助信息
     */
    private void sendHelp(CommandSender sender) {
        sender.sendMessage(GiveClock.colorize("&6/giveclock 插件帮助"));
        sender.sendMessage(GiveClock.colorize("&e/giveclock reload &7- 重载插件配置"));
        sender.sendMessage(GiveClock.colorize("&e/giveclock version &7- 显示插件版本"));
    }

    /**
     * 发送版本信息
     */
    private void sendVersion(CommandSender sender) {
        String version = plugin.getDescription().getVersion();
        sender.sendMessage(GiveClock.colorize("&a[GiveClock] 版本: " + version + " | 作者: Midnight-2004"));
    }
}
