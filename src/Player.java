
public class Player {
	private static String name;
	private static boolean isFirstGame;	
	
	private static int exp; // Current Amount Of Experience Gathered
	private static int gold; // Current Gold Amount
	
	private static int health; // Current health amount
	private static int maxHealth; // Maximum amount of health
	private static int speed; // Who starts first.
	private static int strength; // Adds to Melee dmg?
	private static int dexterity; // Adds to Ranged dmg?
	private static int intelligence; // Adds to Magic dmg?
	private static double protection; // % of dmg negated
	private static double accuracy; // Chance to hit the target (calculated against dodge)
	private static double dodge; // Chance to dodge the attack (calculated against accuracy)
	
	private static Armor armor;
	private static Weapon weapon;
	
	
	public static boolean getFirstGame(){
		return isFirstGame;
	}

	public static String getName() {
		return name;
	}

	public static int getExp() {
		return exp;
	}

	public static int getGold() {
		return gold;
	}

	public static int getHealth() {
		return health;
	}

	public static int getMaxHealth() {
		return maxHealth;
	}

	public static int getSpeed() {
		return speed;
	}

	public static int getStrength() {
		return strength;
	}

	public static int getDexterity() {
		return dexterity;
	}

	public static int getIntelligence() {
		return intelligence;
	}

	public static double getProtection() {
		return protection;
	}

	public static double getAccuracy() {
		return accuracy;
	}

	public static double getDodge() {
		return dodge;
	}

	public static Armor getArmor() {
		return armor;
	}

	public static Weapon getWeapon() {
		return weapon;
	}
	
	public static void setFirstGame(boolean isFirstGame) {
		Player.isFirstGame = isFirstGame;
	}
	
	public static void setName(String name) {
		Player.name = name;
	}

	static boolean isDead(){
		if (health <= 0)
			return true;
		else
			return false;
	}
}
