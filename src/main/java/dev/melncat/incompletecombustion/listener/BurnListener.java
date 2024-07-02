package dev.melncat.incompletecombustion.listener;

import dev.melncat.incompletecombustion.IncompleteCombustion;
import org.bukkit.Material;
import org.bukkit.Tag;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBurnEvent;

import java.util.Optional;

public class BurnListener implements Listener {
	private final IncompleteCombustion plugin;
	
	public BurnListener(IncompleteCombustion plugin) {
		this.plugin = plugin;
	}
	@EventHandler
	private void on(BlockBurnEvent event) {
		Block block = event.getBlock();
		Optional<Material> conversion = plugin.getCombustionManager().getConversion(block.getType());
		if (conversion.isEmpty()) return;
		event.setCancelled(true);
		block.setType(conversion.get());
	}
}
