package no.vestlandetmc.coinflipper;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import no.vestlandetmc.coinflipper.config.Messages;

public class CoinHandler {

	private final Player player;

	public CoinHandler(Player player) {
		this.player = player;
	}

	public void sendDuelRequest(Player duelPlayer, double amount) {
		if(VaultHook.getBalance(this.player) < amount) {
			MessageHandler.sendMessage(this.player, Messages.NOT_ENOUGH_MONEY);
			return;
		}

		Data.REQUEST.put(duelPlayer.getUniqueId(), this.player.getUniqueId());
		Data.AMOUNT.put(duelPlayer.getUniqueId(), amount);

		MessageHandler.SendClickableMessage(
				duelPlayer,
				Messages.DUEL_REQUEST.replaceAll("%player%", this.player.getName()).replaceAll("%amount%", numberFormat(amount)),
				null,
				"/coinflip " + Messages.COMMAND_ACCEPT);

		MessageHandler.sendMessage(this.player, Messages.SEND_REQUEST.replaceAll("%player%", duelPlayer.getName()).replaceAll("%amount%", numberFormat(amount)));

		new BukkitRunnable() {

			@Override
			public void run() {
				if(Data.REQUEST.containsKey(duelPlayer.getUniqueId())) {
					Data.REQUEST.remove(duelPlayer.getUniqueId());
					Data.AMOUNT.remove(duelPlayer.getUniqueId());
				}

			}
		}.runTaskLater(Coinflipper.getInstance(), (60 * 20L));
	}

	public void startDuel() {
		new BukkitRunnable() {

			@Override
			public void run() {
				final Player mainPlayer = Bukkit.getPlayer(Data.REQUEST.get(player.getUniqueId()));
				final int randNr = new Random().nextInt(2);
				final double amount = Data.AMOUNT.get(player.getUniqueId());
				final String formatted = numberFormat(amount);
				final int delay = 200;
				final ArrayList<String> animate = new ArrayList<>();
				animate.add("_");
				animate.add("_");
				animate.add("_");
				animate.add("-");
				animate.add("`");
				animate.add("`");
				animate.add("'");
				animate.add("´");

				animate.add("¯");
				animate.add("`");
				animate.add("'");
				animate.add("´");
				animate.add("¯");
				animate.add("`");
				animate.add("'");
				animate.add("´");
				animate.add("¯");
				animate.add("`");
				animate.add("'");
				animate.add("´");
				animate.add("¯");
				animate.add("`");
				animate.add("'");
				animate.add("´");

				animate.add("-");
				animate.add("_");
				animate.add("_");
				animate.add("_");

				Data.REQUEST.remove(player.getUniqueId());
				Data.AMOUNT.remove(player.getUniqueId());

				if(VaultHook.getBalance(player) < amount) {
					MessageHandler.sendMessage(player, Messages.NOT_ENOUGH_MONEY);
					return;
				}

				if(mainPlayer == null) {
					MessageHandler.sendMessage(player, Messages.PLAYER_NOT_ONLINE);
					return;
				}

				MessageHandler.sendMessage(mainPlayer, Messages.ACCEPTED_DUEL.replaceAll("%player%", player.getName()));

				try {
					MessageHandler.sendTitle(mainPlayer, Messages.READY_TITLE, Messages.READY_SUBTITLE.replaceAll("%amount%", formatted));
					MessageHandler.sendTitle(player, Messages.READY_TITLE, Messages.READY_SUBTITLE.replaceAll("%amount%", formatted));
					TimeUnit.SECONDS.sleep(6);

					for(final String s : animate) {
						MessageHandler.sendTitle(player, "&6" + s, "", 0, 1, 0);
						MessageHandler.sendTitle(mainPlayer, "&6" + s, "", 0, 1, 0);
						TimeUnit.MILLISECONDS.sleep(delay);
					}

					TimeUnit.SECONDS.sleep(3);

				} catch (final InterruptedException e) {
					e.printStackTrace();
				}

				if(randNr == 1) {
					MessageHandler.sendTitle(mainPlayer, Messages.LOSER_TITLE, "");
					MessageHandler.sendTitle(player, Messages.WINNER_TITLE, "");
					MessageHandler.sendMessage(player, Messages.WINNER.replaceAll("%amount%", formatted));
					MessageHandler.sendMessage(mainPlayer, Messages.LOSER.replaceAll("%amount%", formatted));

					VaultHook.deposit(player, amount);
					VaultHook.withdraw(mainPlayer, amount);
				} else {
					MessageHandler.sendTitle(player, Messages.LOSER_TITLE, "");
					MessageHandler.sendTitle(mainPlayer, Messages.WINNER_TITLE, "");
					MessageHandler.sendMessage(mainPlayer, Messages.WINNER.replaceAll("%amount%", formatted));
					MessageHandler.sendMessage(player, Messages.LOSER.replaceAll("%amount%", formatted));

					VaultHook.deposit(mainPlayer, amount);
					VaultHook.withdraw(player, amount);
				}

			}
		}.runTaskAsynchronously(Coinflipper.getInstance());
	}

	private String numberFormat(double amount) {
		final DecimalFormat formatter = (DecimalFormat)NumberFormat.getInstance(Locale.US);
		final DecimalFormatSymbols symbols = formatter.getDecimalFormatSymbols();
		symbols.setGroupingSeparator(',');
		formatter.setDecimalFormatSymbols(symbols);

		return formatter.format(amount);
	}

	public boolean isDouble(String str) {
		try {
			Double.parseDouble(str);
			return true;
		} catch (final NumberFormatException e) {
			return false;
		}
	}

}
