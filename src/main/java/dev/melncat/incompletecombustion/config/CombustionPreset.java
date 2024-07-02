package dev.melncat.incompletecombustion.config;

import dev.melncat.incompletecombustion.CombustionManager;
import org.bukkit.Material;
import org.bukkit.Tag;

import java.util.function.Consumer;

public enum CombustionPreset {
	DARK_OAK(m -> {
		m.addTagConversion(Tag.PLANKS, Material.DARK_OAK_PLANKS);
		m.addTagConversion(Tag.WOODEN_STAIRS, Material.DARK_OAK_STAIRS);
		m.addTagConversion(Tag.WOODEN_SLABS, Material.DARK_OAK_SLAB);
		m.addTagConversion(Tag.WOODEN_FENCES, Material.DARK_OAK_FENCE);
		m.addTagConversion(Tag.WOODEN_PRESSURE_PLATES, Material.DARK_OAK_PRESSURE_PLATE);
		m.addTagConversion(Tag.WOODEN_BUTTONS, Material.DARK_OAK_BUTTON);
		m.addTagConversion(Tag.LOGS_THAT_BURN, Material.DARK_OAK_LOG);
	}),
	NETHER_BRICK(m -> {
		m.addTagConversion(Tag.PLANKS, Material.NETHER_BRICKS);
		m.addTagConversion(Tag.WOODEN_STAIRS, Material.NETHER_BRICK_STAIRS);
		m.addTagConversion(Tag.WOODEN_SLABS, Material.NETHER_BRICK_SLAB);
		m.addTagConversion(Tag.WOODEN_FENCES, Material.NETHER_BRICK_FENCE);
		m.addTagConversion(Tag.WOODEN_PRESSURE_PLATES, Material.DARK_OAK_PRESSURE_PLATE);
		m.addTagConversion(Tag.WOODEN_BUTTONS, Material.DARK_OAK_BUTTON);
		m.addTagConversion(Tag.LOGS_THAT_BURN, Material.CRIMSON_STEM);
	}),
	COAL(m -> {
		m.addTagConversion(Tag.PLANKS, Material.COAL_BLOCK);
		m.addTagConversion(Tag.LOGS_THAT_BURN, Material.COAL_BLOCK);
	});
	
	private final Consumer<CombustionManager> callback;
	
	private CombustionPreset(Consumer<CombustionManager> callback) {
		this.callback = callback;
	}
	
	public void apply(CombustionManager manager) {
		callback.accept(manager);
	}
}