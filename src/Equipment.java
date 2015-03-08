public class Equipment extends BaseStats {
	int price; // Price of the piece of equipment
	
	public int getPrice() {
		return price;
	}

	void printEquipmentStats(){
		if (health > 0)
			System.out.println("Max Health + " + getHealth());
		if (health < 0)
			System.out.println("Max Health - " + getHealth());
		
		if (intelligence > 0)
			System.out.println("Intelligence + " + getIntelligence());
		if (intelligence < 0)
			System.out.println("Intelligence - " + getIntelligence());
		
		if (dexterity > 0)
			System.out.println("Dexterity + " + getDexterity());
		if (dexterity < 0)
			System.out.println("Dexterity - " + getDexterity());

		if (strength > 0)
			System.out.println("Strength + " + getStrength());
		if (strength < 0)
			System.out.println("Strength - " + getStrength());
		
		if (speed > 0)
			System.out.println("Speed + " + getSpeed());
		if (speed < 0)
			System.out.println("Speed - " + getSpeed());

		if (protection > 0)
			System.out.println("Protection + " + getProtection());
		if (protection < 0)
			System.out.println("Protection - " + getProtection());

		if (accuracy > 0)
			System.out.println("Accuracy + " + getAccuracy());
		if (accuracy < 0)
			System.out.println("Accuracy - " + getAccuracy());
		
		if (dodge > 0)
			System.out.println("Dodge + " + getDodge());
		if (dodge < 0)
			System.out.println("Dodge - " + getDodge());
	}
	
	void printName(){
		if (!name.isEmpty()) 
			System.out.println("Name: " + getName());
		else
			System.out.println("Name: Unknown Item");
	}
	
	void printPrice(){
		System.out.println("Price: " + getPrice());
	}
}
