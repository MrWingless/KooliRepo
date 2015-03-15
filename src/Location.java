
public class Location {
	public static void healingAltar(){
		String[] choices = {"Pray at the altar.", "Leave"};
		int choice = GameFlow.makeChoice("You come upon a holy altar. What do you do?", choices);
		if (choice == 1){
			double healAmount =Math.round(Math.random()*30+10); // 10 - 40
			System.out.println("--> The Altar heals you for " + healAmount + "% of your max Health");
			Player.heal(healAmount);
		} else {
			if (choice == 2){
				System.out.println("You leave the altar as it is.");
			}
		}
	}
	
	public static void oldChurch(){
		String[] choices = {"Go into old Church.", "Leave it be."};
		int choise = GameFlow.makeChoice("You found an old Church. What do you do?", choices);
		
	}
}
