
class Weapon extends Equipment{
	int minDmg; // How much additional Minimum Damage is added to the player
	int maxDmg; // How much additional Minimum Damage is added to the player
	public int getMinDmg() {
		return minDmg;
	}
	public int getMaxDmg() {
		return maxDmg;
	}
	
	void printDmg(){
		System.out.println("Damage: " + getMinDmg() + " - " + getMaxDmg());
	}
	
	void displayWeaponStats(){
		printName();
		printDmg();
		printEquipmentStats();
		printPrice();
	}
}
