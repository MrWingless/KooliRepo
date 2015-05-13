import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class GameFlow extends Parent {
	
	static Stage gameStage;
	
	public static Scanner sisend = new Scanner(System.in);
	//static int[] optionNumber = new int[10]; // Will not need this, since Array already has numbers?
	static String[] optionText = new String[10];

	private static void setOption(int x, String displayText){
		optionText[x] = displayText;
	}

	public static void setOptions(String[] displayTexts){
		optionText[0] = "Display Player Info...";
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
						if (optionText[enteredCharValue] == null){
							System.out.println("That wasn't one of the avaliable choices. Please Try again.");
						}else {
							if (optionText[enteredCharValue].isEmpty() || optionText[enteredCharValue].equals("")){ // This checks if That option is available.
								System.out.println("That wasn't one of the avaliable choices. Please Try again.");
							} else {
								enteredValue = enteredCharValue;
								choiceMade = true;
							}
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
					System.out.println();
					Player.getWeapon().printStats();
					System.out.println();
					Player.getArmor().printStats();
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
		
		System.out.println(" Whoops. It seems like you dieded.");
		if (Player.isDead()){
			DataManager.deletePlayerFile();
			System.out.println("Your Saves have been deleted");
		}
		System.out.println("Oh well, you can start again by Starting the game again.");
		System.out.println("Thank You for playing");
	}
	
	// This will randomly choose one of the encounters
	static void getRandomEncounter(){
		int randomNumber = (int)Math.round(Math.random()*100); // 0 - 100
		
		// 70% chance of an Enemy encounter
		if (randomNumber < 71){
			Encounter.combat();
		} else {
			// 20% chance of Location encounter
			if (randomNumber < 91){
				Encounter.location();
			}else{
				// 10% chance of Traveler encounter
				Encounter.traveller();
			}
		}
	}

	public static void exitGame(){
		try {
			Player.save();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			System.out.println("Thank You for playing!");
			System.exit(0);
		}
	}
	
	/**
	 * This is where all the game will be drawn
	 * @param primaryStage - Game menu, will be hidden, to be brought up when required.
	 * @throws IOException - If any of the images is missing
	 */
	public static void GameWindow(Stage primaryStage) throws IOException {
		gameStage = new Stage();
		primaryStage.hide();
		
		gameStage.setTitle("The Game Thing");
		
		Pane gameRoot = new Pane();
		
		InputStream is = Files.newInputStream(Paths.get("Data/images/menu_bg.jpg"));
		Image menu_bg = new Image(is);

		// Getting player related images
		is = Files.newInputStream(Paths.get("Data/images/player_portrait_mage.png"));
		Image player_portrait = new Image(is);
		ImageView playerPortrait = new ImageView();
		playerPortrait.setImage(player_portrait);
		playerPortrait.setX(0);
		playerPortrait.setY(0);
		is = Files.newInputStream(Paths.get("Data/images/player_idle.png"));
		Image player_idle = new Image(is);
		ImageView playerIdle = new ImageView();
		playerIdle.setImage(player_idle);
		playerIdle.setX(20);
		playerIdle.setY(GameMain.gameY - 350);
		Image[] player_attack = new Image[] {
				new Image(Files.newInputStream(Paths.get("Data/images/player_atk_1.png"))),
				new Image(Files.newInputStream(Paths.get("Data/images/player_atk_2.png"))),
				new Image(Files.newInputStream(Paths.get("Data/images/player_atk_3.png")))
		};
		
		// Getting enemy related images ?? 
		is = Files.newInputStream(Paths.get("Data/images/enemy_portrait.png"));
		Image enemy_portrait = new Image(is);
		ImageView enemyPortrait = new ImageView();
		enemyPortrait.setImage(enemy_portrait);
		enemyPortrait.setX(GameMain.gameX - 158);
		enemyPortrait.setY(0);
		is = Files.newInputStream(Paths.get("Data/images/enemy_idle.png"));
		Image enemy_idle = new Image(is);
		ImageView enemyIdle = new ImageView();
		enemyIdle.setImage(enemy_idle);
		enemyIdle.setX(GameMain.gameX - 20 - 133);
		enemyIdle.setY(GameMain.gameY - 350);

		// Getting health bar images
		int playerHealthBarX = 308-250;
		int enemyHealthBarX = GameMain.gameX-150 - 158;
		is = Files.newInputStream(Paths.get("Data/images/health_bar.png"));
		Image health_bar = new Image(is);
		ImageView playerHealthBar = new ImageView();
		playerHealthBar.setImage(health_bar);
		playerHealthBar.setX(playerHealthBarX);
		ImageView enemyHealthBar = new ImageView();
		enemyHealthBar.setImage(health_bar);
		enemyHealthBar.setX(enemyHealthBarX);
		is = Files.newInputStream(Paths.get("Data/images/health_damaged.png"));
		Image health_damaged = new Image(is);
		ImageView playerHealthDamaged = new ImageView();
		playerHealthDamaged.setImage(health_damaged);
		playerHealthDamaged.setX(playerHealthBarX);
		ImageView enemyHealthDamaged = new ImageView();
		enemyHealthDamaged.setImage(health_damaged);
		enemyHealthDamaged.setX(enemyHealthBarX);
		is = Files.newInputStream(Paths.get("Data/images/health_surround.png"));
		Image health_surround = new Image(is);
		ImageView playerHealthSurround = new ImageView();
		playerHealthSurround.setImage(health_surround);
		playerHealthSurround.setX(playerHealthBarX-3);
		ImageView enemyHealthSurround = new ImageView();
		enemyHealthSurround.setImage(health_surround);
		enemyHealthSurround.setX(enemyHealthBarX-3);
		
		is.close();
		ImageView gameBgView= new ImageView(menu_bg);
		
		gameRoot.getChildren().addAll(//gameBgView, //Background doesn't really work well with everything else we have hand-drawn
				playerHealthSurround, 
				enemyHealthSurround, 
				playerHealthDamaged, 
				enemyHealthDamaged, 
				playerHealthBar, 
				enemyHealthBar, 
				playerPortrait, 
				enemyPortrait,
				playerIdle,
				enemyIdle);
		
		Scene scene = new Scene(gameRoot);
		gameStage.setWidth(GameMain.gameX);
		gameStage.setHeight(GameMain.gameY);
		gameStage.setResizable(false);
		gameStage.setScene(scene);
		
		//gameStage.show();

		
		
		gameStage.setOnCloseRequest(event -> {
			Alert alert = new Alert(AlertType.CONFIRMATION, "Back to main menu?", ButtonType.YES, ButtonType.NO);
			alert.setHeaderText("The Game Thing");
			alert.setResizable(false);
			alert.setTitle("");
			alert.showAndWait();

			if (alert.getResult() == ButtonType.YES) {
				gameStage.hide();
				primaryStage.show();
			} else {
				event.consume();
			}
		});
		
		gameStage.show();
	}
	
}
