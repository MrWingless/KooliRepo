
public class Actor extends BaseStats{

	int valueInGold; // How much gold does killing this guy award
	int valueInExp; // How much experience does killing this guy award

	int maxHealth; // Max Health of this guy
	
	public Actor(String name, int health, int intelligence, int dexterity,
			int strength, int speed, double protection, double accuracy,
			double dodge, int valueGold, int valueExp) {
		super(name, health, intelligence, dexterity, strength, speed, protection,
				accuracy, dodge);
		this.maxHealth = health;
		this.valueInGold = valueGold;
		this.valueInExp = valueExp;
	}
	
	boolean isDead(){
		if (this.health <= 0)
			return true;
		else
			return false;
	}
	
	public void damageHealth(int i){
		super.health = super.health - i;
		if(isDead()){
			System.out.println("Congratulation! You killed " + super.getName());
		} else {
			System.out.println(super.getName() + " is hit by your attack and has " + super.getHealth() + "/" + maxHealth + " health left.");
		}
	}
}
