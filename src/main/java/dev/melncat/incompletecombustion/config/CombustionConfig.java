package dev.melncat.incompletecombustion.config;

import dev.melncat.incompletecombustion.CombustionManager;
import net.kyori.adventure.key.Key;
import org.bukkit.Material;
import org.bukkit.Registry;
import org.bukkit.Tag;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;

import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class CombustionConfig {
	public CombustionConfig(ConfigurationSection config) {
		ConfigurationSection combustion = config.getConfigurationSection("combustion");
		mode = CombustionMode.valueOf(Objects.requireNonNull(combustion).getString("mode"));
		preset = CombustionPreset.valueOf(Objects.requireNonNull(combustion).getString("preset"));
		overrides = new EnumMap<>(Material.class);
		for (var entry : Objects.requireNonNull(combustion.getConfigurationSection("overrides")).getValues(false).entrySet()) {
			String key = entry.getKey();
			if (!Key.parseable(key))
				throw new RuntimeException(new InvalidConfigurationException("Key " + key + " in combustion.overrides is not a valid key."));
			Key keyKey = Key.key(key);
			Material keyMaterial = Registry.MATERIAL.get(keyKey);
			if (keyMaterial == null)
				throw new RuntimeException(new InvalidConfigurationException("Key " + key + " in combustion.overrides is not a valid material."));
			if (!keyMaterial.isBlock())
				throw new RuntimeException(new InvalidConfigurationException("Key " + key + " in combustion.overrides is not a block."));
			String value = entry.getValue().toString();
			if (!Key.parseable(value))
				throw new RuntimeException(new InvalidConfigurationException("Value " + value + " in combustion.overrides is not a valid key."));
			Key valueKey = Key.key(value);
			Material valueMaterial = Registry.MATERIAL.get(valueKey);
			if (valueMaterial == null)
				throw new RuntimeException(new InvalidConfigurationException("Value " + value + " in combustion.overrides is not a valid material."));
			if (!valueMaterial.isBlock())
				throw new RuntimeException(new InvalidConfigurationException("Value " + value + " in combustion.overrides is not a block."));
			if (keyMaterial.equals(valueMaterial))
				throw new RuntimeException(new InvalidConfigurationException("Key " + keyMaterial + " in combustion.overrides cannot be converted to itself."));
			overrides.put(keyMaterial, valueMaterial);
		}
	}
	
	private final CombustionMode mode;
	private final CombustionPreset preset;
	private final Map<Material, Material> overrides;
	
	public CombustionMode mode() {
		return this.mode;
	}
	
	public CombustionPreset preset() {
		return this.preset;
	}
	
	public Map<Material, Material> overrides() {
		return this.overrides;
	}
	
	public enum CombustionMode {
		PRESET,
		MANUAL
	}
}
