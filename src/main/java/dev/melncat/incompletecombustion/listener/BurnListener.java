package dev.melncat.incompletecombustion.listener;

import dev.melncat.incompletecombustion.IncompleteCombustion;
import org.bukkit.Material;
import org.bukkit.Tag;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Fire;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBurnEvent;

import java.util.Optional;
import java.util.Set;

public class BurnListener implements Listener {
	private final IncompleteCombustion plugin;
	
	private final Set<BlockFace> directions = Set.of(BlockFace.DOWN, BlockFace.UP, BlockFace.NORTH, BlockFace.SOUTH, BlockFace.EAST, BlockFace.WEST);
	
	public BurnListener(IncompleteCombustion plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	private void on(BlockBurnEvent event) {
		Block block = event.getBlock();
		Optional<Material> conversion = plugin.getCombustionManager().getConversion(block.getType());
		if (conversion.isEmpty()) return;
		event.setCancelled(true);
		BlockData oldData = block.getBlockData();
		block.setType(conversion.get());
		BlockData newData = block.getBlockData();
		oldData.copyTo(newData);
		block.setBlockData(newData);
		for (BlockFace direction : directions) {
			Block other = block.getRelative(direction);
			if (other.getType().isFlammable()) {
				boolean hasAir = false;
				for (BlockFace dir : directions)
					if (other.getRelative(dir).getType().isEmpty()) {
						Block rel = other.getRelative(dir);
						BlockState relState = rel.getState();
						relState.setType(Material.FIRE);
						relState.update(true);
						hasAir = true;
					}
				if (!hasAir) {
					Optional<Material> otherConversion = plugin.getCombustionManager().getConversion(other.getType());
					otherConversion.ifPresent(m -> {
						BlockData oldD = other.getBlockData();
						other.setType(m);
						BlockData newD = other.getBlockData();
						oldD.copyTo(newD);
						other.setBlockData(newD);
					});
				}
			}
		}
	}
}
