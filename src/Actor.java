
public class Actor extends BaseStats{
	int valueInGold; // How much gold does killing this guy award
	int valueInExp; // How much experience does killing this guy award

	int maxHealth; // Max Health of this guy
	
	boolean isDead(){
		if (this.health <= 0)
			return true;
		else
			return false;
	}
}
