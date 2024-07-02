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
        combustionManager.addTagConversion(Tag.PLANKS, Material.DARK_OAK_PLANKS);
        combustionManager.addTagConversion(Tag.WOODEN_STAIRS, Material.DARK_OAK_STAIRS);
        combustionManager.addTagConversion(Tag.WOODEN_SLABS, Material.DARK_OAK_SLAB);
        combustionManager.addTagConversion(Tag.WOODEN_FENCES, Material.DARK_OAK_FENCE);
        
        
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
