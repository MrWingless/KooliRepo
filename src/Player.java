
public class Player {
	private static String name;
	private static boolean isFirstGame;	
	
	private static int exp; // Current Amount Of Experience Gathered
	private static int gold; // Current Gold Amount
	private static int nextLevelAt;
	private static int level;
	
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

	public static String getRole() {
		return (role == 0 ? "Mage" : (role == 1 ? "Rogue" : (role == 2 ? "Warrior" : "Unknown")));
	}

	public static void setFirstGame(boolean isFirstGame) {
		Player.isFirstGame = isFirstGame;
	}
	
	public static void setName(String name) {
		Player.name = name;
	}
	
	private static void createMage(){
		role = 0;
		level = 0;
		nextLevelAt = 10;
		
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
	private static void createRogue(){
		role = 1;
		level = 0;
		nextLevelAt = 10;
		
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
	private static void createWarrior(){
		role = 2;
		level = 0;
		nextLevelAt = 10;
		
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
	
	public static void chooseClass(int i){
		switch(i){
			case 0:
				createMage(); break;
			case 1:
				createRogue(); break;
			case 2:
				createWarrior(); break;
			default:
				System.out.println("Error: Player.chooseClass - Class choice higher than 2 :(");
		}
	}
	
	// Calculates Dmg numbers for the player.
	static void calculateDmg(){
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
			minDmg = 1 + minWeaponDmg + ((int)Math.round(intelligence * 0.35));
			maxDmg = 1 + maxWeaponDmg + ((int)Math.round(intelligence * 0.6));
			break;
		case 1: // Rogue
			minDmg = 1 + minWeaponDmg + ((int)Math.round(dexterity * 0.30));
			maxDmg = 1 + maxWeaponDmg + ((int)Math.round(dexterity * 0.55));
			break;
		case 2: // Warrior
			minDmg = 1 + minWeaponDmg + ((int)Math.round(strength * 0.25));
			maxDmg = 1 + maxWeaponDmg + ((int)Math.round(strength * 0.5));
			break;
		default: // Other
			System.out.println("ERROR: Calculating Dmg - Invalid Role");
		}
	}
	
	static void displayInfo(){
		System.out.println("PLAYER INFO:");
		System.out.println("Name: \t\t" + name);
		System.out.println("Level: \t\t" + level + " " + getRole());
		System.out.println("Exp: \t\t" + exp + " / " + nextLevelAt);
		System.out.println("Health: \t" + health + " / " + maxHealth);
		System.out.println("Damage: \t" + minDmg + " - " + maxDmg);
		System.out.println("Intelligence: \t" + intelligence);
		System.out.println("Dexterity: \t" + dexterity);
		System.out.println("Strength: \t" + strength);
		System.out.println("Speed: \t\t" + speed);
		if (protection != 0.0)
			System.out.println("Protection: \t" + protection);
		if (accuracy != 0.0)
			System.out.println("Accuracy: \t" + accuracy);
		if (dodge != 0.0)
			System.out.println("Dodge: \t\t" + dodge);
	}
	
	static void levelUp(){
		level++;
		exp = exp - nextLevelAt;
		health = maxHealth;
		nextLevelAt = (int)Math.round(nextLevelAt * 1.5);
		System.out.println("Congratulations! You have earned a new level! You are now level: " + level);
		System.out.println("Your Base Attributes have been updated:");
		switch(role){
		case 0: // Mage
			System.out.println("Intelligence: \t " + intelligence + " -> " + (intelligence + 2));
			intelligence +=2;
			System.out.println("Dexterity: \t " + dexterity + " -> " + (dexterity + 1));
			dexterity +=1;
			System.out.println("Strength: \t " + strength + " -> " + (strength + 1));
			strength +=1;
			break;
		case 1: // Rogue
			System.out.println("Intelligence: \t " + intelligence + " -> " + (intelligence + 1));
			intelligence +=1;
			System.out.println("Dexterity: \t " + dexterity + " -> " + (dexterity + 2));
			dexterity +=2;
			System.out.println("Strength: \t " + strength + " -> " + (strength + 1));
			strength +=1;
			break;
		case 2: // Warrior
			System.out.println("Intelligence: \t " + intelligence + " -> " + (intelligence + 1));
			intelligence +=1;
			System.out.println("Dexterity: \t " + dexterity + " -> " + (dexterity + 1));
			dexterity +=1;
			System.out.println("Strength: \t " + strength + " -> " + (strength + 2));
			strength +=2;
			break;
		default: // Other
			System.out.println("ERROR: Leveling Player - Invalid Role");
		}
		calculateDmg();
	}

	static boolean isDead(){
		if (health <= 0)
			return true;
		else
			return false;
	}
}
