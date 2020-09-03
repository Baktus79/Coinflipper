package no.vestlandetmc.coinflipper.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import no.vestlandetmc.coinflipper.CoinHandler;
import no.vestlandetmc.coinflipper.Data;
import no.vestlandetmc.coinflipper.MessageHandler;
import no.vestlandetmc.coinflipper.config.Messages;

public class Coinflip implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(!(sender instanceof Player)) {
			MessageHandler.sendConsole("&cYou cannot use this command from console.");
			return true;
		}

		final Player player = (Player) sender;
		final CoinHandler coin = new CoinHandler(player);

		if(args.length == 0) {
			MessageHandler.sendMessage(player,
					Messages.NO_ARGS,
					Messages.USAGE);
			return true;
		}

		if(player.getName().equalsIgnoreCase(args[0])) {
			MessageHandler.sendMessage(player, Messages.DUEL_SELF);
			return true;
		}

		if(args[0].equals(Messages.COMMAND_ACCEPT)) {
			if(Data.REQUEST.containsKey(player.getUniqueId())) {
				coin.startDuel();
				return true;
			} else {
				MessageHandler.sendMessage(player, Messages.NO_REQUEST);
				return true;
			}
		}

		if(args.length == 1) {
			MessageHandler.sendMessage(player,
					Messages.NO_ARGS_MONEY,
					Messages.USAGE);
			return true;
		}

		if(!coin.isDouble(args[1])) {
			MessageHandler.sendMessage(player,
					Messages.WRONG_FORMAT,
					Messages.USAGE);
		}

		final Player duelPlayer = Bukkit.getPlayer(args[0]);

		if(duelPlayer == null) {
			MessageHandler.sendMessage(player, Messages.INVALID_PLAYER);
			return true;
		}

		coin.sendDuelRequest(duelPlayer, Data.convertStringDouble(args[1]));

		return true;
	}

}
