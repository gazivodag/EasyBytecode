package statics;

import java.util.HashMap;
import java.util.Map;
import javassist.bytecode.ConstPool;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum MyStringReplacement
{
	//login screen
	WELCOME_SCREEN("Welcome to RuneScape", "<col=a10000>Welcome to Avant-Garde</col>"),
	ENTER_USER_PASS("Enter your username/email & password.", "<col=a10000>Enter your username/email & password.</col>"),
	PLEASE_ENTER_USER_OR_EMAIL("Please enter your username/email address.", "<col=a10000>Please enter your username/email address.</col>"),
	PLEASE_ENTER_USERNAME("Please enter your username.", "<col=a10000>Please enter your username.</col>"),
	PLEASE_ENTER_PASSWORD("Please enter your password.", "<col=a10000>Please enter your password.</col>"),
	PLEASE_ENTER_6_DIGIT_PIN("Please enter a 6-digit PIN.", "<col=a10000>Please enter a 6-digit PIN.</col>"),

	//connecting to server
	CONNECTING_TO_SERVER("Connecting to server...", "<col=a10000>Unlocking Greatness...</col>"),

	//connection timed out
	CONNECTION_TIMED_OUT("Connection timed out.", "<col=a10000>Connection timed out.</col>"),

	//error connecting to server.
	ERROR_CONNECTING_TO_SERVER("Error connecting to server.", "<col=a10000>Error connecting to server.</col>"),

	//you were disconnected from the server
	YOU_WERE_DISCONNECTED_FROM_THE_SERVER("You were disconnected from the server.", "<col=a10000>You were disconnected from the server.</col>"),

	//before the login screen is shown
	RS_LOADING_PLEASE_WAIT("RuneScape is loading - please wait...", "<col=a10000>Avant-Garde is loading - please wait...</col>"),

	//account disabled
	YOUR_ACCOUNT_HAS_BEEN_DISABLED("Your account has been disabled.", "<col=a10000>This account is banned.</col>"),

	//account already logged in
	YOUR_ACCOUNT_HAS_NOT_LOGGED_OUT("Your account has not logged out from its last", "<col=a10000>Your account has not logged out from its last</col>"),
	SESSION_OR_TOO_BUSY("session or the server is too busy right now.", "<col=a10000>session or the server is too busy right now.</col>"),

	//loading screen
	LOADED_UPDATE_LIST("Loaded update list", "Avant-Garde: Loaded update list"),
	LOADED_TITLE_SCREEN("Loaded title screen", "Avant-Garde: Loaded title screen"),
	LOADED_CONFIG("Loaded config", "Avant-Garde: Loaded config"),
	LOADED_TEXTURES("Loaded textures", "Avant-Garde: Loaded textures"),
	LOADED_WORDPACK("Loaded wordpack", "Avant-Garde: Loaded wordpack"),
	LOADED_INTERFACES("Loaded interfaces", "Avant-Garde: Loaded interfaces"),
	LOADED_FONTS("Loaded fonts", "Avant-Garde: Loaded fonts"),
	LOADED_SPRITES("Loaded sprites", "Avant-Garde: Loaded sprites"),
	LOADED_WORLD_MAP("Loaded world map", "Avant-Garde: Loaded world map"),
	LOADED_INPUT_HANDLER("Loaded input handler", "Avant-Garde: Loaded input handler"),
	PREPARE_VISIBILITY_MAP("Prepared visibility map", "Avant-Garde: Prepared visibility map"),
	PREPARE_SOUND_ENGINE("Prepared sound engine", "Avant-Garde: Prepared sound engine"),

	//existing user screen
	NEW_USER("New User", "<col=a10000>New User</col>"),
	EXISTING_USER("Existing User", "<col=a10000>Existing User</col>"),
	LOGIN_LABEL("Login: ", "<col=a10000>Login: </col>"),
	PASSWORD_LABEL("Password: ", "<col=a10000>Password: </col>"),
	REMEMBER_USERNAME("Remember username", "<col=a10000>Remember username</col>"),
	HIDE_USER("Hide username", "<col=a10000>Hide username</col>"),
	CANT_LOGIN("Can't login? Click here.", "<col=a10000>Can't login? Click here.</col>"),
	CLICK_TO_SWITCH("Click to switch", "<col=a10000>Click to switch</col>"),

	//incorrect login screen
	INVALID_CREDENTIALS("Invalid credentials.", "<col=a10000>Invalid credentials.</col>"),
	FOR_ACCOUNTS_CREATED("For accounts created after 24th November 2010, please use your", "<col=a10000>For accounts created after 24th November 2010, please use your</col>"),
	EMAIL_ADDRESS_TO_LOGIN("email address to login. Otherwise please login with your username.", "<col=a10000>email address to login. Otherwise please login with your username.</col>"),

	//miscellaneous
	FOR_PAT("You can't add yourself to your own friend list", "nigga why u addin yourself lol lonely nigga hours"),
	FOR_PAT_2("Your friend list is full. Max of 200 for free users, and 400 for members", "Damn bro you popular as fuck lmao. 400 max btw"),
	;

	@Getter
	private String originalString;
	@Getter
	private String replacementString;

	public static MyStringReplacement hasReplacement(String string)
	{
		for (MyStringReplacement replacement : values())
		{
			if (string.equals(replacement.originalString))
			{
				return replacement;
			}
		}
		return null;
	}

	/**
	 * Dumping our string replacements into the const pool, and putting their pointers into a map for future reference
	 * @param constPool
	 * @return
	 */
	public static Map<String, Integer> addNewConstants(ConstPool constPool)
	{
		Map<String, Integer> newPointers = new HashMap<>();
		for (MyStringReplacement replacement : values())
		{
			constPool.addUtf8Info(replacement.getReplacementString());
			int replacementIndex = constPool.addStringInfo(replacement.getReplacementString());
			newPointers.put(replacement.getReplacementString(), replacementIndex);

			System.out.println("added " + replacement.getReplacementString() + " string ref to pointer: " + replacementIndex);
		}
		return newPointers;
	}
}
