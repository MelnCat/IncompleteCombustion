package dev.melncat.incompletecombustion;

import dev.melncat.incompletecombustion.config.CombustionConfig;
import dev.melncat.incompletecombustion.listener.BurnListener;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Tag;
import org.bukkit.plugin.java.JavaPlugin;

public final class IncompleteCombustion extends JavaPlugin {
    private final CombustionManager combustionManager = new CombustionManager();
    private CombustionConfig config;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        loadConfig();
        Bukkit.getPluginManager().registerEvents(new BurnListener(this), this);
    }

    @Override
    public void onDisable() {
        combustionManager.clearConversions();
    }
    
    public void loadConfig() {
        reloadConfig();
        config = new CombustionConfig(getConfig());
        combustionManager.applyConfig(config);
    }
    
    public CombustionManager getCombustionManager() {
        return combustionManager;
    }
}
