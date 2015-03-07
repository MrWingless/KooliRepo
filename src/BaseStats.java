
public class BaseStats {
	String name; // Name of the Character
	
	int health;
	int speed; // Who starts first.
	int strength; // Adds to Melee dmg?
	int dexterity; // Adds to Ranged dmg?
	int intelligence; // Adds to Magic dmg?
	double protection; // % of dmg negated
	double accuracy; // Chance to hit the target (calculated against dodge)
	double dodge; // Chance to dodge the attack (calculated against accuracy)
}
