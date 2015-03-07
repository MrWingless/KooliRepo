
public class Player {
	static String name;
	static boolean isFirstGame;	
	
	static int exp; // Current Amount Of Experience Gathered
	static int gold; // Current Gold Amount
	
	static int health; // Current health amount
	static int maxHealth; // Maximum amount of health
	static int speed; // Who starts first.
	static int strength; // Adds to Melee dmg?
	static int dexterity; // Adds to Ranged dmg?
	static int intelligence; // Adds to Magic dmg?
	static double protection; // % of dmg negated
	static double accuracy; // Chance to hit the target (calculated against dodge)
	static double dodge; // Chance to dodge the attack (calculated against accuracy)
	
	static boolean isDead(){
		if (health <= 0)
			return true;
		else
			return false;
	}
}
