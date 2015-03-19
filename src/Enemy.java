
public class Enemy extends Actor{
	int powerLevel;
	int minDmg;
	int maxDmg;
	public int getPowerLevel() {
		return powerLevel;
	}
	public Enemy(String name, int level, int health, int intelligence, int dexterity,
			int strength, int speed, double protection, double accuracy,
			double dodge, int valueGold, int valueExp, int minDmg, int maxDmg) {
		super(name, health, intelligence, dexterity, strength, speed, protection,
				accuracy, dodge, valueGold, valueExp);
		this.minDmg = minDmg;
		this.maxDmg = maxDmg;
		this.powerLevel = level;
	}
	public Enemy(Enemy e) {
		super(e.name, e.health, e.intelligence, e.dexterity, e.strength, e.speed,
				e.protection, e.accuracy, e.dodge, e.valueInGold, e.valueInExp);
		this.powerLevel = e.powerLevel;
		this.minDmg = e.minDmg;
		this.maxDmg = e.maxDmg;
	}
	
	
	
	
	
	
	
	

	
	
}
