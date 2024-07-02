package dev.melncat.incompletecombustion;

import dev.melncat.incompletecombustion.config.CombustionConfig;
import org.bukkit.Material;
import org.bukkit.Tag;

import javax.annotation.Nullable;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CombustionManager {
	private final EnumMap<Material, Material> conversionMap = new EnumMap<>(Material.class);
	
	public Optional<Material> getConversion(Material material) {
		return Optional.ofNullable(conversionMap.get(material));
	}
	
	public void addTagConversion(Tag<Material> tag, Material material) {
		for (Material mat : tag.getValues()) conversionMap.put(mat, material);
	}
	
	public void clearConversions() {
		conversionMap.clear();
	}
	
	public void applyConfig(CombustionConfig config) {
		if (config.mode() == CombustionConfig.CombustionMode.PRESET) {
			config.preset().apply(this);
		}
		conversionMap.putAll(config.overrides());
	}
}
