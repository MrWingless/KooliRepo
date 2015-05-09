import java.io.Serializable;


public class BaseStats implements Serializable{
	String name; // Name of the Actor/Equipment
	
	int health;
	int speed; // Who starts first.
	int strength; // Adds to Melee dmg?
	int dexterity; // Adds to Ranged dmg?
	int intelligence; // Adds to Magic dmg?
	double protection; // % of dmg negated
	double accuracy; // Chance to hit the target (calculated against dodge)
	double dodge; // Chance to dodge the attack (calculated against accuracy)
	
	public BaseStats(String name, int health, int intelligence, int dexterity,
			int strength, int speed, double protection, double accuracy,
			double dodge) {
		super();
		this.name = name;
		this.health = health;
		this.intelligence = intelligence;
		this.dexterity = dexterity;
		this.strength = strength;
		this.speed = speed;
		this.protection = protection;
		this.accuracy = accuracy;
		this.dodge = dodge;
	}
	
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
	
	void printStats(){
		System.out.println("Health: " + getHealth());
		System.out.println("Intelligence: " + getIntelligence());
		System.out.println("Dexterity: " + getDexterity());
		System.out.println("Strength: " + getStrength());
		System.out.println("Speed: " + getSpeed());
		System.out.println("Protection: " + getProtection());
		System.out.println("Accuracy: " + getAccuracy());
		System.out.println("Dodge: " + getDodge());
	}
}
