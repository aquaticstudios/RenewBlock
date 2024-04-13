package team.aquatic.studios;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import team.aquatic.studios.actions.Commands;
import team.aquatic.studios.actions.Tab;
import team.aquatic.studios.listener.BlockListener;
import team.aquatic.studios.register.Metrics;

public class RenewBlock extends JavaPlugin {

    private static RenewBlock instance;

    public static RenewBlock getInstance() {
        return instance;
    }

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
    }

    @Override
    public void onDisable() {
        getLogger().info("¡Plugin desactivado!");
    }
}