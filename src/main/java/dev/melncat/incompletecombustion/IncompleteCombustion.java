package dev.melncat.incompletecombustion;

import dev.melncat.incompletecombustion.listener.BurnListener;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Tag;
import org.bukkit.plugin.java.JavaPlugin;

public final class IncompleteCombustion extends JavaPlugin {
    private final CombustionManager combustionManager = new CombustionManager();

    @Override
    public void onEnable() {
        
        
        Bukkit.getPluginManager().registerEvents(new BurnListener(this), this);
    }

    @Override
    public void onDisable() {
        combustionManager.clearConversions();
    }
    
    public CombustionManager getCombustionManager() {
        return combustionManager;
    }
}
