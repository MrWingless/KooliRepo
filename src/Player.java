
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
	
	private static int minDmg; // Minimum player Damage
	private static int maxDmg; // Maximum player Damage
	
	private static int role; // Mage[0] / Rogue[1] / Warrior[2]
	
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
	
	public static int getMingDmg() {
		return minDmg;
	}

	public static int getMaxDmg() {
		return maxDmg;
	}

	public static int getRole() {
		return role;
	}

	public static void setFirstGame(boolean isFirstGame) {
		Player.isFirstGame = isFirstGame;
	}
	
	public static void setName(String name) {
		Player.name = name;
	}
	
	void createMage(){
		role = 0;
		
		health = 20;
		maxHealth = 20;
		
		strength = 5;
		dexterity = 5;
		intelligence = 10;

		calculateDmg();

		speed = 5;
		protection = 0;
		accuracy = 0;
		dodge = 0;
	}
	void createRogue(){
		role = 1;
		
		health = 25;
		maxHealth = 25;
		
		strength = 5;
		dexterity = 10;
		intelligence = 5;

		calculateDmg();

		speed = 5;
		protection = 0;
		accuracy = 0;
		dodge = 0;
	}
	void createWarrior(){
		role = 2;
		
		health = 30;
		maxHealth = 30;
		
		strength = 10;
		dexterity = 5;
		intelligence = 5;
		
		calculateDmg();

		speed = 5;
		protection = 0;
		accuracy = 0;
		dodge = 0;
		
	}
	
	// Calculates Dmg numbers for the player.
	void calculateDmg(){
		int minWeaponDmg;
		int maxWeaponDmg;
		if (weapon == null){
			minWeaponDmg = 0;
			maxWeaponDmg = 0;
		} else {
			minWeaponDmg = weapon.getMinDmg();
			maxWeaponDmg = weapon.getMaxDmg();
		}
		switch(role){
		case 0: // Mage
			minDmg = 1 + minWeaponDmg + ((int)Math.round(intelligence * 0.5));
			maxDmg = 1 + maxWeaponDmg + ((int)Math.round(intelligence * 0.5));
			break;
		case 1: // Rogue
			minDmg = 1 + minWeaponDmg + ((int)Math.round(dexterity * 0.5));
			maxDmg = 1 + maxWeaponDmg + ((int)Math.round(dexterity * 0.5));
			break;
		case 2: // Warrior
			minDmg = 1 + minWeaponDmg + ((int)Math.round(strength * 0.5));
			maxDmg = 1 + maxWeaponDmg + ((int)Math.round(strength * 0.5));
			break;
		default: // Other
			System.out.println("ERROR: Calculating Dmg - Invalid Role");
		}
	}

	static boolean isDead(){
		if (health <= 0)
			return true;
		else
			return false;
	}
}
