package no.vestlandetmc.coinflipper;

import java.util.HashMap;
import java.util.UUID;

public class Data {

	public static HashMap<UUID, UUID> REQUEST = new HashMap<>();
	public static HashMap<UUID, Double> AMOUNT = new HashMap<>();

	public static double convertStringDouble(String amount) {
		return Double.parseDouble(amount);
	}

}
