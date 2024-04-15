package team.aquatic.studios;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import team.aquatic.studios.actions.Commands;
import team.aquatic.studios.actions.Tab;
import team.aquatic.studios.listener.BlockListener;
import team.aquatic.studios.register.Metrics;
import team.aquatic.studios.tools.Utils;

public class RenewBlock extends JavaPlugin {

    private static RenewBlock instance;

    public static RenewBlock getInstance() {
        return instance;
    }

    PluginDescriptionFile aquatic = this.getDescription();

    @Override
    public void onEnable() {
        instance = this;
        int pluginId = 21585;
        Metrics metrics = new Metrics(this, pluginId);
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new BlockListener(), this);
        this.getCommand("renewblock").setExecutor(new Commands(this));
        this.getCommand("renewblock").setTabCompleter(new Tab());
        Bukkit.getConsoleSender().sendMessage(Utils.translateHexColorCodes(Utils.Color(" &r")));
        Bukkit.getConsoleSender().sendMessage(Utils.translateHexColorCodes(Utils.Color(" &#08FB1D   __                           ___ _            _    ")));
        Bukkit.getConsoleSender().sendMessage(Utils.translateHexColorCodes(Utils.Color(" &#08FB1D  /__\\ ___ _ __   _____      __/ __\\ | ___   ___| | __")));
        Bukkit.getConsoleSender().sendMessage(Utils.translateHexColorCodes(Utils.Color(" &#08FB1D / \\/// _ \\ '_ \\ / _ \\ \\ /\\ / /__\\// |/ _ \\ / __| |/ /")));
        Bukkit.getConsoleSender().sendMessage(Utils.translateHexColorCodes(Utils.Color(" &#08FB1D/ _  \\  __/ | | |  __/\\ V  V / \\/  \\ | (_) | (__|   < ")));
        Bukkit.getConsoleSender().sendMessage(Utils.translateHexColorCodes(Utils.Color(" &#08FB1D\\/ \\_/\\___|_| |_|\\___| \\_/\\_/\\_____/_|\\___/ \\___|_|\\_\\")));
        Bukkit.getConsoleSender().sendMessage(Utils.translateHexColorCodes(Utils.Color("                                                      ")));
        Bukkit.getConsoleSender().sendMessage(Utils.translateHexColorCodes(Utils.Color("  &fStatus: &aEnabled &f- Version: &e" + aquatic.getVersion() + "-SNAPSHOT")));
        Bukkit.getConsoleSender().sendMessage(Utils.translateHexColorCodes(Utils.Color("  &fAll rights reserved by &bSxntido & Aquatic Studios")));
        Bukkit.getConsoleSender().sendMessage(Utils.translateHexColorCodes(Utils.Color("&r")));
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(Utils.translateHexColorCodes(Utils.Color(" &r")));
        Bukkit.getConsoleSender().sendMessage(Utils.translateHexColorCodes(Utils.Color(" &#08FB1D   __                           ___ _            _    ")));
        Bukkit.getConsoleSender().sendMessage(Utils.translateHexColorCodes(Utils.Color(" &#08FB1D  /__\\ ___ _ __   _____      __/ __\\ | ___   ___| | __")));
        Bukkit.getConsoleSender().sendMessage(Utils.translateHexColorCodes(Utils.Color(" &#08FB1D / \\/// _ \\ '_ \\ / _ \\ \\ /\\ / /__\\// |/ _ \\ / __| |/ /")));
        Bukkit.getConsoleSender().sendMessage(Utils.translateHexColorCodes(Utils.Color(" &#08FB1D/ _  \\  __/ | | |  __/\\ V  V / \\/  \\ | (_) | (__|   < ")));
        Bukkit.getConsoleSender().sendMessage(Utils.translateHexColorCodes(Utils.Color(" &#08FB1D\\/ \\_/\\___|_| |_|\\___| \\_/\\_/\\_____/_|\\___/ \\___|_|\\_\\")));
        Bukkit.getConsoleSender().sendMessage(Utils.translateHexColorCodes(Utils.Color("                                                       ")));
        Bukkit.getConsoleSender().sendMessage(Utils.translateHexColorCodes(Utils.Color("  &fStatus: &cDisabled &f- Version: &e" + aquatic.getVersion() + "-SNAPSHOT")));
        Bukkit.getConsoleSender().sendMessage(Utils.translateHexColorCodes(Utils.Color("  &fAll rights reserved by &bSxntido & Aquatic Studios")));
        Bukkit.getConsoleSender().sendMessage(Utils.translateHexColorCodes(Utils.Color("&r")));
    }
}