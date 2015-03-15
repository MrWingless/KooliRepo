import java.io.IOException;
import java.util.Scanner;


public class GameFlow {
	public static Scanner sisend = new Scanner(System.in);
	//static int[] optionNumber = new int[10]; // Will not need this, since Array already has numbers?
	static String[] optionText = new String[10];

	private static void setOption(int x, String displayText){
		optionText[x] = displayText;
	}

	public static void setOptions(String[] displayTexts){
		optionText[0] = "Display More Info...";
		for (int i = 0; i < 9; i++){
			if (i < displayTexts.length)
				setOption(i+1, displayTexts[i]);
			else 
				setOption(i+1, "");
		}
		optionText[9] = "Exit Game";
	}

	// This offers the player a choice and checks that player's answer falls within requested parameters
	private static int offerChoice(){
		int enteredValue = 0;
		boolean choiceMade = false;
		do{
			//System.out.println();
			System.out.println("-- Your Choice: --");
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
		//sisend.close();
		return enteredValue;
	}

	// Displays the choices player can make.
	private static void displayChoices(String offer){
		System.out.println();
		System.out.println(offer);
		for (int i = 0; i < optionText.length; i++){
			if (optionText[i] != null){
				if (!optionText[i].isEmpty()){
					System.out.println("["+i+"] - " + optionText[i]);
				}
			}
		}
	}

	// This is the choice maker
	public static int makeChoice(String offer, String[] choices){
		String[] newChoices = new String[10];
		for (int i = 0; i < choices.length; i++){
			newChoices[i] = choices[i];
		}
		
		boolean isChoiceMade = false;
		int choiceMade = 9;
		do {
			// Sets options
			setOptions(newChoices);

			// Displays Choices
			displayChoices(offer);

			// Reads the Choice and Makes the choice
			choiceMade = offerChoice();

			if (choiceMade == 9)
				exitGame();
			else{
				if (choiceMade == 0){
					Player.displayInfo();
				}
				else{
					isChoiceMade = true;
				} 

			}
		}
		while (!isChoiceMade);
		return choiceMade;
	}
	
	public static void startMainGameFlow(){
		String[] choices = {
				"Rest: Heal 40% Health and Lose 1 exp", 
				"Continue on with your travels"};
		int choice = 0;
		do{
			choice = GameFlow.makeChoice("What would you like to do?", choices);
			if (choice == 1)
				Player.rest();
			if (choice == 2)
				getRandomEncounter();
		} while(!Player.isDead());
		
		System.out.println(" Whoops. It seems like you had an accident.");
		if (Player.isDead()){
			DataManager.deletePlayerFile();
			System.out.println("Your Saves have been deleted");
		}
		System.out.println("Oh well, you can start again by Starting the game again.");
		System.out.println("Thank You for playing");
	}
	
	// This will randomly choose one of the encounters
	static void getRandomEncounter(){
		
	}

	public static void exitGame(){
		try {
			Player.save();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			System.exit(0);
		}
	}
}
