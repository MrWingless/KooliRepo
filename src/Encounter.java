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
		
	}

	public static void traveller(){
		
	}
	
	public static void location(){
		String[] choices = {"Pray at the altar.", "Leave"};
		int choice = GameFlow.makeChoice("You come upon a holy altar. What do you do?", choices);
		if (choice == 1){
			double healAmount = Math.random()*30+10; // 10 - 40
			System.out.println("--> The Altar heals you for " + healAmount + "% of your max Health");
			Player.heal(healAmount);
		} else {
			if (choice == 2){
				System.out.println("You leave the altar as it is.");
			}
		}
	}
}
