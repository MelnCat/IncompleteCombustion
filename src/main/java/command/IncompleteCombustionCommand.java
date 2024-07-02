package command;

import dev.melncat.incompletecombustion.IncompleteCombustion;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class IncompleteCombustionCommand implements CommandExecutor, TabCompleter {
	private final IncompleteCombustion plugin;
	
	public IncompleteCombustionCommand(IncompleteCombustion plugin) {
		this.plugin = plugin;
	}
	
	@SuppressWarnings("UnstableApiUsage")
	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
		if (args.length == 0) {
			sender.sendMessage(MiniMessage.miniMessage().deserialize(
				"<gradient:red:yellow>Incomplete Combustion</gradient> <gray>v</gray><green><version></green>\n" +
				"<gold>Made by</gold> <color:#b0ffc6><author></color>",
				Placeholder.unparsed("version", plugin.getPluginMeta().getVersion()),
				Placeholder.unparsed("author", plugin.getPluginMeta().getAuthors().getFirst())
			));
			return true;
		}
		if (args.length == 1) {
			if (args[0].equalsIgnoreCase("reload")) {
				if (!sender.hasPermission("incompletecombustion.command.incompletecombustion.reload")) {
					sender.sendMessage(Component.text("You do not have permission to use this command.", NamedTextColor.RED));
					return true;
				}
				plugin.loadConfig();
				sender.sendMessage(Component.text("Plugin configuration was successfully reloaded.", NamedTextColor.GREEN));
				return true;
			} else return false;
		}
		return false;
	}
	
	@Override
	public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
		if (args.length == 1 && sender.hasPermission("incompletecombustion.command.incompletecombustion.reload"))
			return List.of("reload");
		return null;
	}
}
