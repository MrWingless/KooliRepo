
public class BaseStats {
	String name; // Name of the Actor/Equipment
	
	int health;
	int speed; // Who starts first.
	int strength; // Adds to Melee dmg?
	int dexterity; // Adds to Ranged dmg?
	int intelligence; // Adds to Magic dmg?
	double protection; // % of dmg negated
	double accuracy; // Chance to hit the target (calculated against dodge)
	double dodge; // Chance to dodge the attack (calculated against accuracy)
	
	public String getName() {
		return name;
	}
	public int getHealth() {
		return health;
	}
	public int getSpeed() {
		return speed;
	}
	public int getStrength() {
		return strength;
	}
	public int getDexterity() {
		return dexterity;
	}
	public int getIntelligence() {
		return intelligence;
	}
	public double getProtection() {
		return protection;
	}
	public double getAccuracy() {
		return accuracy;
	}
	public double getDodge() {
		return dodge;
	}
	
	
}
