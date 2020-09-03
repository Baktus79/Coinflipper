package no.vestlandetmc.coinflipper;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class MessageHandler {

	public static void sendAction(Player player, String message) {
		player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(colorize(message)));
	}

	public static void sendTitle(Player player, String title, String subtitle) {
		player.sendTitle(colorize(title), colorize(subtitle), 20, 3 * 20, 10);
	}

	public static void sendTitle(Player player, String title, String subtitle, int fadein, int stay, int fadeout) {
		player.sendTitle(colorize(title), colorize(subtitle), fadein, (stay * 20), fadeout);
	}

	public static void sendMessage(Player player, String... messages) {
		for(final String message : messages) {
			player.sendMessage(colorize(message));
		}
	}

	public static void sendAnnounce(String... messages) {
		for(final Player player : Bukkit.getOnlinePlayers()) {
			for(final String message : messages) {
				player.sendMessage(colorize(message));
			}
		}
	}

	public static void sendConsole(String... messages) {
		for(final String message : messages) {
			Coinflipper.getInstance().getServer().getConsoleSender().sendMessage(colorize(message));
		}
	}

	@SuppressWarnings("deprecation")
	public static void SendClickableMessage(Player player, String message, String hoverMessage, String command) {
		final TextComponent finalMessage = new TextComponent(colorize(message));

		if(command != null)
			finalMessage.setClickEvent( new ClickEvent( ClickEvent.Action.RUN_COMMAND, command));

		if(hoverMessage != null)
			finalMessage.setHoverEvent( new HoverEvent( HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(colorize(hoverMessage)).create()));

		player.spigot().sendMessage(finalMessage);
	}

	public static String colorize(String message) {
		return ChatColor.translateAlternateColorCodes('&', message);
	}

}
