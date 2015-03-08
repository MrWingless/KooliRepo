import java.util.Scanner;


public class GameFlow {
	//static int[] optionNumber = new int[10]; // Will not need this, since Array already has numbers?
	static String[] optionText = new String[10];

	// This should start the main game flow
	public void start(){
		do{
			encounter();
		}
		while(!Player.isDead());
	}

	// This is the encounter
	void encounter(){
		//displayCurrentStatus();
		displayChoices();
		//int playerChose = offerChoice(); // Can't be used here, must be used inside Enounter.
	}

	// This will randomly choose one of the encounters
	void getRandomEncounter(){
		
	}

	// This offers the player a choice and checks that player's answer falls within requested parameters
	int offerChoice(){
		int enteredValue = 0;
		Scanner sisend = new Scanner(System.in);
		boolean choiceMade = false;
		do{
			System.out.println("Make your Choice:");
			String choice = sisend.next();
			if (choice.length() < 1){
				System.out.println("You must have forgotten to enter someting. Please Try again.");
			} else {
				char[] choiceAsCharArray = choice.toCharArray();
				
				if (!Character.isDigit(choiceAsCharArray[0])){
					System.out.println("You must enter a number. Please Try again.");
				}
				else{
					int enteredCharValue = Character.getNumericValue(choiceAsCharArray[0]);
					if (enteredCharValue > 10 || enteredCharValue < 0){
						System.out.println("Your choice " + choiceAsCharArray[0] + " was not one of the numers. Please Try again.");
					} else {
						if (optionText[enteredCharValue].isEmpty()){ // This checks if That option is available.
							System.out.println("That wasn't one of the avaliable choices. Please Try again.");
						} else {
							enteredValue = enteredCharValue;
							choiceMade = true;
						}
					}
				}
			}
		}
		while(!choiceMade);
		sisend.close();
		return enteredValue;
	}
	
	// Displays the choices player can make.
	void displayChoices(){
		for (int i = 0; i < optionText.length; i++){
			if (!optionText[i].isEmpty()){
				System.out.println("["+i+"] - " + optionText[i]);
			}
		}
	}
}
