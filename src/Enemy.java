
public class Enemy extends Actor{
	int powerLevel;
	int minDmg;
	int maxDmg;
	public int getPowerLevel() {
		return powerLevel;
	}
	public Enemy(String name, int level, int minDmg, int maxDmg, int health, int intelligence, int dexterity,
			int strength, int speed, double protection, double accuracy,
			double dodge, int valueGold, int valueExp) {
		super(name, health, intelligence, dexterity, strength, speed, protection,
				accuracy, dodge, valueGold, valueExp);
		this.minDmg = minDmg;
		this.maxDmg = maxDmg;
		this.powerLevel = level;
	}
}
