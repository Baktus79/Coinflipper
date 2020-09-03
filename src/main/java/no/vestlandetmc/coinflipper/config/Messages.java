package no.vestlandetmc.coinflipper.config;

public class Messages extends ConfigHandler {

	private Messages(String fileName) {
		super(fileName);
	}

	public static String
	NO_ARGS,
	NO_ARGS_MONEY,
	USAGE,
	INVALID_PLAYER,
	COMMAND_ACCEPT,
	NO_REQUEST,
	DUEL_REQUEST,
	WRONG_FORMAT,
	PLAYER_NOT_ONLINE,
	ACCEPTED_DUEL,
	READY_TITLE,
	READY_SUBTITLE,
	WINNER_TITLE,
	LOSER_TITLE,
	NOT_ENOUGH_MONEY,
	LOSER,
	WINNER,
	DUEL_SELF,
	SEND_REQUEST;

	private void onLoad() {

		NO_ARGS = getString("no-args");
		NO_ARGS_MONEY = getString("no-args-money");
		USAGE = getString("usage");
		INVALID_PLAYER = getString("invalid-player");
		COMMAND_ACCEPT = getString("command-accept");
		NO_REQUEST = getString("no-request");
		DUEL_REQUEST = getString("duel-request");
		WRONG_FORMAT = getString("wrong-format");
		PLAYER_NOT_ONLINE = getString("player-not-online");
		ACCEPTED_DUEL = getString("accepted-duel");
		READY_TITLE = getString("ready-title");
		READY_SUBTITLE = getString("ready-subtitle");
		WINNER_TITLE = getString("winner-title");
		LOSER_TITLE = getString("loser-title");
		NOT_ENOUGH_MONEY = getString("not-enough-money");
		LOSER = getString("loser");
		WINNER = getString("winner");
		DUEL_SELF = getString("duel-self");
		SEND_REQUEST = getString("send-request");

	}

	public static void initialize() {
		new Messages("messages.yml").onLoad();
	}

}
