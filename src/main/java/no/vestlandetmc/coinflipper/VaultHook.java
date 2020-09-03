package no.vestlandetmc.coinflipper;

import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;

import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;

public class VaultHook {

	private static Economy econ = null;
	private static Chat chat = null;

	public static void setupEco() {
		final RegisteredServiceProvider<Economy> rsp = Bukkit.getServicesManager().getRegistration(Economy.class);

		if (rsp != null)
			econ = rsp.getProvider();
	}

	public static void setupChat() {
		final RegisteredServiceProvider<Chat> rsp = Bukkit.getServicesManager().getRegistration(Chat.class);

		if (rsp != null)
			chat = rsp.getProvider();
	}

	public static boolean isEconomySet() {
		return econ != null;
	}

	public static boolean isChatSet() {
		return chat != null;
	}

	public static String getPrefix(Player player) {
		return chat.getPlayerPrefix(player);
	}

	public static String getSuffix(Player player) {
		return chat.getPlayerSuffix(player);
	}

	public static String getEconomyName() {
		return econ.getName();
	}

	public static double getBalance(OfflinePlayer player) {
		Validate.isTrue(isEconomySet(), "The economy is not set");

		return econ.getBalance(player);
	}

	public static String getCurrency(boolean plural) {
		Validate.isTrue(isEconomySet(), "The economy is not set");

		return plural ? econ.currencyNamePlural() : econ.currencyNameSingular();
	}

	public static void withdraw(OfflinePlayer player, double amount) {
		econ.withdrawPlayer(player, amount);
	}

	public static void deposit(OfflinePlayer player, double amount) {
		econ.depositPlayer(player, amount);
	}

}
