// Contains all encounter types - most of these will require separate coding.
public class Encounter {
	public static boolean isInCombat = false;
	
	public static void chooseClass(){
		String[] choices = {
				"Mage \t(Main attribute: Intelligence)", 
				"Rogue \t(Main attribute Dexterity)", 
				"Warrior \t(Main attribute Strength)"};
		Player.chooseClass(GameFlow.makeChoice("- Please Choose Your Class: -", choices)-1);
		Player.displayInfo();
	}
	
	public static void combat(){
		isInCombat = true;
		Combat.enemyMalee();
		try {
			GameMain.refreshEnemies();
		} catch (Exception e) {
			e.printStackTrace();
		}
		isInCombat = false;
	}

	public static void traveller(){
		String[] choices = {"Buy the item", "More Info on the Item", "Leave"};
	}
	
	public static void location(){
		Location.healingAltar();
	}
}
