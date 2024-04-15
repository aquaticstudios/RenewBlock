package team.aquatic.studios.actions;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import team.aquatic.studios.RenewBlock;
import team.aquatic.studios.tools.Utils;

public class Commands implements CommandExecutor {

    private RenewBlock plugin;

    public Commands(RenewBlock plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command command, String Label, String[] args) {
        if (!(sender instanceof Player)) {
            if (args.length == 0) {
                sender.sendMessage(Utils.Color("&r"));
                sender.sendMessage(Utils.translateHexColorCodes(Utils.Color("&#5CFF7E⛏ &lRenewBlock &f(v1.0 - @Sxntido)")));
                sender.sendMessage(Utils.translateHexColorCodes(Utils.Color("&fAll rights reserved by &#00D1FFAquatic Studios")));
                sender.sendMessage(Utils.Color("&r"));
                return true;
            }
            if (args[0].equalsIgnoreCase("reload")) {
                plugin.reloadConfig();
                sender.sendMessage(Utils.translateHexColorCodes(Utils.Color(plugin.getConfig().getString("messages.reload").replace("%prefix%", plugin.getConfig().getString("prefix")))));
                return true;
            }
            if (args[0].equalsIgnoreCase("help")) {
                sender.sendMessage(Utils.Color("&r"));
                sender.sendMessage(Utils.translateHexColorCodes(Utils.Color("&#5CFF7E⛏ &lRenewBlock &f(v1.0 - @Sxntido)")));
                sender.sendMessage(Utils.Color("&r"));
                sender.sendMessage(Utils.translateHexColorCodes(Utils.Color(" &#60FF6E/renewblock help &7- &fDisplay the help message")));
                sender.sendMessage(Utils.translateHexColorCodes(Utils.Color(" &#60FF6E/renewblock reload &7- &fReload the plugin config.yml")));
                sender.sendMessage(Utils.Color("&r"));
                return true;
            }
            sender.sendMessage(Utils.translateHexColorCodes(Utils.Color(plugin.getConfig().getString("messages.unknown").replace("%prefix%", plugin.getConfig().getString("prefix")))));
            return true;
        }

        Player p = (Player) sender;
        if (args.length == 0) {
            p.sendMessage(Utils.translateHexColorCodes(Utils.Color("&r")));
            p.sendMessage(Utils.translateHexColorCodes(Utils.Color("    &#5CFF7E⛏ &lRenewBlock &f(v1.0 - @Sxntido)")));
            p.sendMessage(Utils.translateHexColorCodes(Utils.Color("  &fAll rights reserved by &#00D1FFAquatic Studios")));
            p.sendMessage(Utils.translateHexColorCodes(Utils.Color("&r")));
            return true;
        }

        if (args[0].equalsIgnoreCase("help")) {
            if (p.hasPermission("renewblock.admin")) {
                p.sendMessage(Utils.translateHexColorCodes(Utils.Color("&r")));
                p.sendMessage(Utils.translateHexColorCodes(Utils.Color("      &#5CFF7E⛏ &lRenewBlock &f(v1.0 - @Sxntido)")));
                p.sendMessage(Utils.translateHexColorCodes(Utils.Color("&r")));
                p.sendMessage(Utils.translateHexColorCodes(Utils.Color(" &#60FF6E/renewblock help &7- &fDisplay the help message")));
                p.sendMessage(Utils.translateHexColorCodes(Utils.Color(" &#60FF6E/renewblock reload &7- &fReload the plugin config.yml")));
                p.sendMessage(Utils.translateHexColorCodes(Utils.Color("&r")));
                return true;
            }
            p.sendMessage(Utils.translateHexColorCodes(Utils.Color(plugin.getConfig().getString("messages.no-permission").replace("%prefix%", plugin.getConfig().getString("prefix")))));
            return true;
        }
        if (args[0].equalsIgnoreCase("reload")) {
            if (p.hasPermission("renewblock.admin")) {
                plugin.reloadConfig();
                p.sendMessage(Utils.translateHexColorCodes(Utils.Color(plugin.getConfig().getString("messages.reload").replace("%prefix%", plugin.getConfig().getString("prefix")))));
                return true;
            }
            p.sendMessage(Utils.translateHexColorCodes(Utils.Color(plugin.getConfig().getString("messages.no-permission").replace("%prefix%", plugin.getConfig().getString("prefix")))));
            return true;
        }

        p.sendMessage(Utils.translateHexColorCodes(Utils.Color(plugin.getConfig().getString("messages.unknown").replace("%prefix%", plugin.getConfig().getString("prefix")))));
        return true;
    }
}
