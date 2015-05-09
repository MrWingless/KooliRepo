import java.io.Serializable;

public class Equipment extends BaseStats implements Serializable{
	int price; // Price of the piece of equipment
	
	public Equipment(String name, int health, int intelligence, int dexterity,
			int strength, int speed, double protection, double accuracy,
			double dodge, int price) {
		super(name, health, intelligence, dexterity, strength, speed, protection,
				accuracy, dodge);
		this.price = price;
	}
	
	public int getPrice() {
		return price;
	}
	
	void printStatBonuses(){
		if (health > 0)
			System.out.println("Max Health \t+ " + getHealth());
		if (health < 0)
			System.out.println("Max Health \t- " + -getHealth());
		
		if (intelligence > 0)
			System.out.println("Intelligence \t+ " + getIntelligence());
		if (intelligence < 0)
			System.out.println("Intelligence \t- " + -getIntelligence());
		
		if (dexterity > 0)
			System.out.println("Dexterity \t+ " + getDexterity());
		if (dexterity < 0)
			System.out.println("Dexterity \t- " + -getDexterity());

		if (strength > 0)
			System.out.println("Strength \t+ " + getStrength());
		if (strength < 0)
			System.out.println("Strength \t- " + -getStrength());
		
		if (speed > 0)
			System.out.println("Speed \t\t+ " + getSpeed());
		if (speed < 0)
			System.out.println("Speed \t\t- " + -getSpeed());

		if (protection > 0)
			System.out.println("Protection \t+ " + getProtection());
		if (protection < 0)
			System.out.println("Protection \t- " + -getProtection());

		if (accuracy > 0)
			System.out.println("Accuracy \t+ " + getAccuracy());
		if (accuracy < 0)
			System.out.println("Accuracy \t- " + -getAccuracy());
		
		if (dodge > 0)
			System.out.println("Dodge \t\t+ " + getDodge());
		if (dodge < 0)
			System.out.println("Dodge \t\t- " + -getDodge());
	}
	
	void printName(){
		if (!name.isEmpty()) 
			System.out.println("Name: \t\t" + getName());
		else
			System.out.println("Name: \t\tUnknown Item");
	}
	
	void printPrice(){
		System.out.println("Price: \t\t" + getPrice());
	}
	
	void printStats(){
		printName();
		printStatBonuses();
		printPrice();
	}
}
