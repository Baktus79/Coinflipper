package no.vestlandetmc.coinflipper;

import org.bukkit.plugin.java.JavaPlugin;

import no.vestlandetmc.coinflipper.command.Coinflip;
import no.vestlandetmc.coinflipper.config.Messages;

public class Coinflipper extends JavaPlugin {

	private static Coinflipper instance;

	public static Coinflipper getInstance() {
		return instance;
	}


	@Override
	public void onEnable() {
		instance = this;

		this.getCommand("coinflip").setExecutor(new Coinflip());
		Messages.initialize();

		if(getServer().getPluginManager().getPlugin("Vault") != null) {
			VaultHook.setupEco();
			MessageHandler.sendConsole("[" + getDescription().getPrefix() + "] Vault ble funnet og koblet til");
		}

	}

}
