import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.Glow;
import javafx.scene.effect.Light.Distant;
import javafx.scene.effect.Lighting;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;



public class GameMain extends Application {
	public static Weapon[] weapons;
	public static Armor[] armors;
	public static Enemy[] enemies;

	public static int gameX = 800;
	public static int gameY = 600;
	public static ImageView menuBgView;
	public static Text theGameThing = new Text("the Game Thing");

	boolean secondaryMenuVisible = false;

	public static void main(String[] args) throws Exception{
		//System.out.println("You have started The Game Thing.");

		// Loading Data:
		weapons = DataManager.getWeapons();
		armors = DataManager.getArmors();
		enemies = DataManager.getEnemies();

		launch(args);

		/*
		for (Weapon weapon : weapons){
			System.out.println();
			weapon.printStats();
		}

		for (Armor armor : armors){
			System.out.println();
			armor.printStats();
		}*/


		/*
		System.out.println("This is a Console Based Turn Based Role Playing Game. CBTBRPG - In short.");
		System.out.println("First Things First, We must find out if you've played before.");
		System.out.println();


		System.out.println("Please Enter Your Character Name:");
		Player.setName(GameFlow.sisend.next()); 


		// Kontrollb, kas mängija on varem mänginud.
		DataManager.checkIfPlayerPlayedBefore();
		if (Player.getFirstGame()){
			// Creates a new Player According to the Choice The Player Makes.
			Encounter.chooseClass();
			Player.save();
		}
		else{
			// Load The Data From the File!
			Player.loadPlayerData();
			Player.displayInfo();
		}

		GameFlow.startMainGameFlow();


		//Katsetan Varustust
		System.out.println();

		//sword.printStats();


		 */

		/*
		do{}
		while(!Player.isDead());*/
	}

	public static void refreshEnemies() throws Exception{
		enemies = DataManager.getEnemies();
	}

	private GameMenu gameMenu;

	@Override
	public void start(Stage primaryStage) throws Exception {

		Pane root = new Pane();
		//root.setPrefSize(gameX, gameY);

		InputStream is = Files.newInputStream(Paths.get("Data/images/menu_bg.jpg"));
		Image menu_bg = new Image(is);
		is.close();

		menuBgView = new ImageView(menu_bg);
		gameMenu = new GameMenu(primaryStage);

			menuBgView.setFitWidth(gameX);
			menuBgView.setFitHeight(gameY);
			
			root.getChildren().addAll(menuBgView, gameMenu);

			Scene scene = new Scene(root);
			primaryStage.setWidth(gameX);
			primaryStage.setHeight(gameY);
			primaryStage.setResizable(false);
			primaryStage.setScene(scene);
			primaryStage.setOnCloseRequest(event -> {
				Alert alert = new Alert(AlertType.CONFIRMATION, "Exit game?", ButtonType.YES, ButtonType.NO);
				alert.setHeaderText("The Game Thing");
				alert.setResizable(false);
				alert.setTitle("");
				alert.showAndWait();

				if (alert.getResult() == ButtonType.YES) {
					System.exit(0);
				} else {
					event.consume();
				}
			});
			primaryStage.show();

	}// start end

	/**
	 * Class to create Game Menu
	 * No menu, no game
	 */
	private class GameMenu extends Parent {
		public GameMenu(Stage primaryStage) {
			VBox mainMenu = new VBox(15);
			VBox loadMenu = new VBox(15);
			VBox optionsMenu = new VBox(15);
			VBox videoMenu = new VBox(15);
			VBox playerInfo = new VBox(5);
			VBox newPlayerMenu = new VBox(20);
			
			HBox characters = new HBox(20);
			HBox nameField = new HBox(5);
			characters.setAlignment(Pos.CENTER);
			
			mainMenu.setTranslateX(gameX/5);
			mainMenu.setTranslateY(gameY/4);

			loadMenu.setTranslateX(gameX);
			loadMenu.setTranslateY(gameY/4);

			optionsMenu.setTranslateX(gameX);
			optionsMenu.setTranslateY(gameY/4);

			videoMenu.setTranslateX(gameX);
			videoMenu.setTranslateY(gameY/4);
			
			playerInfo.setTranslateX(gameX);
			playerInfo.setTranslateY(gameY/6);
			
			newPlayerMenu.setTranslateX(gameX);
			newPlayerMenu.setTranslateY(gameY/4);

			
			//Main Menu buttons
			MenuButton btnMainNew = new MenuButton("New Player");
			MenuButton btnMainLoad = new MenuButton("Load Player");
			MenuButton btnMainOptions = new MenuButton("Options");
			MenuButton btnMainExit = new MenuButton("Exit");
			
			//New Player Menu buttons, text and textField
			Text insertName = new Text("Player name:");
			TextField newName = new TextField();
			CharacterButton mageBtn = new CharacterButton("Mage");
			CharacterButton rogueBtn = new CharacterButton("Rogue");
			CharacterButton warriorBtn = new CharacterButton("Warrior");
			MenuButton nPBack = new MenuButton("Back");
			
			//Load Player Menu buttons
			
			//Options Menu Buttons
			MenuButton btnOptVideo = new MenuButton("Video");
			MenuButton btnOptBack = new MenuButton("Back");
			
			//Video Options buttons and text.
			Text txVideoMenu = new Text("Choose Resolution");
			MenuButton low = new MenuButton("640x480");
			MenuButton medium = new MenuButton("800x600");
			MenuButton high = new MenuButton("1024x756");
			
			
			btnMainNew.setOnMouseClicked(event -> {
				transistWindow(mainMenu, newPlayerMenu, null);
			});

			insertName.setFont(Font.font("Showcard Gothic", 20));
			insertName.setFill(Color.WHITE);
			
			nameField.getChildren().addAll(insertName, newName);
			characters.getChildren().addAll(warriorBtn, rogueBtn, mageBtn);
			
			Alert nameMissing = new Alert(AlertType.ERROR);
			
			mageBtn.setOnMouseClicked(event -> {
				try {
					createPlayer(0, newName);
				} catch (NameMissingException e) {
					nameMissing.setContentText(e.getMessage().toString());
					nameMissing.showAndWait();
					event.consume();
					return;
				}
				StartGameWindow(primaryStage);
			});
			
			rogueBtn.setOnMouseClicked(event -> {
				
				try {
					createPlayer(0, newName);
				} catch (NameMissingException e) {
					nameMissing.setContentText(e.getMessage().toString());
					nameMissing.showAndWait();
					event.consume();
					return;
				}
				StartGameWindow(primaryStage);
			});
			
			warriorBtn.setOnMouseClicked(event -> {
				try {
					createPlayer(0, newName);
				} catch (NameMissingException e) {
					nameMissing.setContentText(e.getMessage().toString());
					nameMissing.showAndWait();
					event.consume();
					return;
				}
				StartGameWindow(primaryStage);
			});
			
			nPBack.setOnMouseClicked(event -> {
				transistWindow(newPlayerMenu, mainMenu, null);
			});

			btnMainLoad.setOnMouseClicked(event -> {
				VBox playerMenu = new VBox(15);
				playerMenu.setTranslateX(gameX);
				playerMenu.setTranslateY(gameY/5);
				ArrayList<MenuButton> playersButtons = new ArrayList<MenuButton>();
				for (File playerFile : DataManager.getAvailablePlayerFiles()){
					String filename = playerFile.getName();
					String playerName = filename.replaceFirst(".txt", "");
					//System.out.println(playerName);
					MenuButton playerButton = new MenuButton(playerName);
					
					playerButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
					    @Override
					    public void handle(MouseEvent mouseEvent) {
					        if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
					            if(mouseEvent.getClickCount() == 2){
									StartGameWindow(primaryStage);
					            }
					            if(mouseEvent.getClickCount() == 1){
									Player.setName(playerName);
									try {
										
										Player.loadPlayerData();
										Player.displayInfotoVBox(playerInfo);
										Text theText = new Text("Double Click the Player name to Load");
										theText.setFont(Font.font("Showcard Gothic", 12));
										theText.setFill(Color.AQUAMARINE.darker());
										playerInfo.getChildren().add(theText);
										
										if (!secondaryMenuVisible) {
											getChildren().addAll(playerInfo);
											secondaryMenuVisible = true;
										} else{
											getChildren().remove(playerInfo);
											getChildren().add(playerInfo);
										}
										TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), playerInfo);
										//TranslateTransition tt2 = new TranslateTransition(Duration.seconds(0.25), playerInfo);
										tt1.setToX(gameX*3/5);
										tt1.play();
									} catch (FileNotFoundException e) {
										System.out.println(e.getMessage());
									}
					            }
					        }
					    }
					});
					
					playersButtons.add(playerButton);
				}
				playersButtons.add(new MenuButton("Back"));
				
				
				for (int i = 0; i < playersButtons.size(); i++){
					if (i == (playersButtons.size() - 1)){
						playersButtons.get(i).setOnMouseClicked(event2 -> {
							transistWindow(playerMenu, mainMenu, playerInfo);
						});
					} 
				}

				playerMenu.getChildren().addAll(playersButtons);
				transistWindow(mainMenu, playerMenu, null);

			});

			
			btnMainOptions.setOnMouseClicked(event -> {
				transistWindow(mainMenu, optionsMenu, null);
			});

			
			btnMainExit.setOnMouseClicked(event -> {
				FadeTransition ft = new FadeTransition(Duration.seconds(0.5), this);
				FadeTransition ft1 = new FadeTransition(Duration.seconds(0.5), this);
				ft.setFromValue(1);
				ft.setToValue(0);
				ft.play();

				ft.setOnFinished(event1 -> {
					Alert alert = new Alert(AlertType.CONFIRMATION, "Exit game?", ButtonType.YES, ButtonType.NO);
					alert.setHeaderText("The Game Thing");
					alert.setResizable(false);
					alert.setTitle("");
					alert.showAndWait();

					if (alert.getResult() == ButtonType.YES) {
						System.exit(0);
					} else {
						ft1.setFromValue(0);
						ft1.setToValue(1);
						ft1.play();
					}
				});
			});
			
			txVideoMenu.setFont(Font.font("Showcard Gothic", 20));
			txVideoMenu.setFill(Color.WHITE);

			btnOptVideo.setOnMouseClicked(event -> {
				if (!secondaryMenuVisible) {
					getChildren().add(videoMenu);
					secondaryMenuVisible = true;
				}
				TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), videoMenu);
				TranslateTransition tt2 = new TranslateTransition(Duration.seconds(0.25), videoMenu);
				tt1.setToX(gameX*3/5);
				tt1.play();

				low.setOnMouseClicked(event1 -> {
					tt2.setToX(gameX);
					tt2.play();
					tt2.setOnFinished(event11 -> { getChildren().remove(videoMenu); });
					secondaryMenuVisible = false;
					gameX = 600;
					gameY = 480;
					refreshStage(primaryStage);
				});

				medium.setOnMouseClicked(event1 -> {
					tt2.setToX(gameX);
					tt2.play();
					tt2.setOnFinished(event11 -> { getChildren().remove(videoMenu); });
					secondaryMenuVisible = false;
					gameX = 800;
					gameY = 600;
					refreshStage(primaryStage);
				});

				high.setOnMouseClicked(event1 -> {
					tt2.setToX(gameX);
					tt2.play();
					tt2.setOnFinished(event11 -> { getChildren().remove(videoMenu); });
					secondaryMenuVisible = false;
					gameX = 1024;
					gameY = 756;
					refreshStage(primaryStage);
				});
			});

			
			btnOptBack.setOnMouseClicked(event -> {
				transistWindow(optionsMenu, mainMenu, videoMenu);
			});

			
			//Adding buttons and texts under menus.
			mainMenu.getChildren().addAll(btnMainNew, btnMainLoad, btnMainOptions, btnMainExit);
			optionsMenu.getChildren().addAll(btnOptVideo, btnOptBack);
			videoMenu.getChildren().addAll(txVideoMenu,low, medium, high);
			newPlayerMenu.getChildren().addAll(nameField, characters, nPBack);

			Rectangle bg = new Rectangle(1024, 756);
			bg.setFill(Color.GRAY);
			bg.setOpacity(0.4);
			
			//Game title
			theGameThing.setFont(Font.font("Showcard Gothic", 40));
			theGameThing.setFill(Color.WHITE);
			theGameThing.setTranslateX(gameX/3);
			theGameThing.setTranslateY(gameY/10);
			DropShadow drop = new DropShadow(100, Color.RED);
			drop.setInput(new Glow());
			
			theGameThing.setOnMousePressed(event -> {
				theGameThing.setEffect(drop);
				theGameThing.setFill(Color.BLACK);
				getChildren().remove(playerInfo);
				playerInfo.getChildren().clear();
				Text theText = new Text("\n\nInformation About the game:");
				theText.setFont(Font.font("Showcard Gothic", FontWeight.BOLD, 14));
				theText.setFill(Color.WHITE.darker());
				Text theText2 = new Text("This game is a turn based adventure simulator."
						+ "\nDuring the game you have a set of choices"
						+ "\nthat you have to choose from, to create"
						+ "\nyour own adventure.");
				theText2.setFont(Font.font("Showcard Gothic", 12));
				theText2.setFill(Color.WHITE.darker());
				playerInfo.getChildren().addAll(theText, theText2);
				getChildren().add(playerInfo);
				
				TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), playerInfo);
				//TranslateTransition tt2 = new TranslateTransition(Duration.seconds(0.25), playerInfo);
				tt1.setFromX(gameX);
				tt1.setToX(gameX*3/5);
				tt1.play();
			});
			theGameThing.setOnMouseReleased(event -> {
				theGameThing.setEffect(null);
				theGameThing.setFill(Color.WHITE);
				getChildren().remove(playerInfo);
				playerInfo.getChildren().clear();
				playerInfo.setTranslateX(gameX);
				playerInfo.setTranslateY(gameY/6);
			});

			getChildren().addAll(bg, mainMenu, theGameThing);
		} // GameMenu end

		/**
		 * Method to create new Player
		 * @param type [0] - Mage, [1] - Rogue, [2] - Warrior
		 * @param newName - Players Name TextField.
		 * @throws NameMissingException
		 */
		public void createPlayer(int type, TextField newName) throws NameMissingException {
			
			if (DataManager.getAvailablePlayerFiles().length > 6) {
				throw new NameMissingException("Too many Players, delete some of them.");
			}
			
			if (newName.getLength() == 0) {
				
					throw new NameMissingException("Player name missing.");
				
			} else if (newName.getLength() < 3) {
				
					throw new NameMissingException("Player name too short, has to be at least 3 letters.");
				
			} else {
				
					try {
						DataManager.checkIfPlayerExists(newName.getText());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						Alert nameExists = new Alert(AlertType.WARNING, "Do you want to overwrite?", ButtonType.YES, ButtonType.NO);
						nameExists.setHeaderText(e.getMessage());
						nameExists.showAndWait();
						if (nameExists.getResult() == ButtonType.NO) 
							return;
					}
					
					Player.setName(newName.getText());
					Player.chooseClass(type);
					//Player.displayInfo();
					try {
						Player.save();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
		} // CreatePlayer end
		
		/**
		 * Method to refresh game after resolution change
		 * @param primaryStage
		 */
		public void refreshStage(Stage primaryStage) {
			primaryStage.setWidth(gameX);
			primaryStage.setHeight(gameY);
			menuBgView.setFitWidth(gameX);
			menuBgView.setFitHeight(gameY);
			theGameThing.setTranslateX(gameX/3);
			theGameThing.setTranslateY(gameY/10);
		}
		
		/**
		 * Method to move menus around.
		 * @param from - this menu will be gone
		 * @param to - this menu will appear out of.. magic
		 * @param otherMenu - sometimes there are too many menus
		 */
		public void transistWindow(VBox from, VBox to, VBox otherMenu){
			getChildren().add(to);
			// Moves the first window away
			TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), from);
			tt.setToX(-gameX/4);
			// Brings the next window in
			TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), to);
			tt1.setFromX(gameX);
			tt1.setToX(gameX/5);
			
			if (secondaryMenuVisible) {
				TranslateTransition tt2 = new TranslateTransition(Duration.seconds(0.25), otherMenu);
				tt2.setToX(gameX);
				tt2.play();
				secondaryMenuVisible = false;
				tt2.setOnFinished(event2 -> getChildren().remove(otherMenu));
			}
			
			tt.play();
			tt1.play();
			tt.setOnFinished(event1 -> getChildren().remove(from));
		}
		
		/**
		 * And this actually starts the game. so kinda important
		 * @param primaryStage
		 */
		public void StartGameWindow(Stage primaryStage) {
			try {
				GameFlow.GameWindow(primaryStage);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Class to make menu buttons.
	 * @author Margus
	 *
	 */
	public static class MenuButton extends StackPane {

		private Text text;
		
		/**
		 * Method to make a menu button
		 * @param name - String value for button name
		 */
		public MenuButton(String name) {
			text = new Text(name);
			text.setFont(Font.font("Showcard Gothic", 15));
			text.setFill(Color.WHITE);
			text.setTranslateX(5);

			Rectangle bg = new Rectangle(200, 30);
			bg.setOpacity(0.5);
			bg.setFill(Color.BLACK);
			bg.setEffect(new GaussianBlur(3.5));

			setAlignment(Pos.CENTER_LEFT);

			getChildren().addAll(bg, text);

			setOnMouseEntered(event -> {
				bg.setTranslateX(10);
				text.setFont(Font.font("Showcard Gothic", 18));
				text.setTranslateX(20);
				bg.setFill(Color.WHITE);
				text.setFill(Color.BLACK);
			});

			setOnMouseExited(event -> {
				bg.setTranslateX(0);
				text.setFont(Font.font("Showcard Gothic", 15));
				text.setTranslateX(5);
				bg.setFill(Color.BLACK);
				text.setFill(Color.WHITE);
			});

			DropShadow drop = new DropShadow(50, Color.WHITE);
			drop.setInput(new Glow());

			setOnMousePressed(event -> setEffect(drop));
			setOnMouseReleased(event -> setEffect(null));
		}
	} // MenuButton end
	
	/**
	 * Class to make Character Buttons for New Player Menu
	 * @author Margus
	 *
	 */
	private static class CharacterButton extends StackPane {
		
		private Text charType;
		private ImageView charBtnImg;
		
		/**
		 * Method for Character button
		 * @param type - String value for character type: Mage, Rogue or Warrior
		 */
		public CharacterButton (String type) {
			charType = new Text(type);
			charType.setFont(Font.font("Showcard Gothic", 15));
			charType.setFill(Color.WHITE);
			
			try {
				InputStream is = Files.newInputStream(Paths.get("Data/images/" + type + "_portrait.png"));
				Image charImg = new Image(is);
				is.close();
				charBtnImg = new ImageView(charImg);
			} catch (Exception e) {
				System.out.println("Character portrait picture missing - " + type);
			}
			
			DropShadow drop = new DropShadow(10, Color.LIGHTBLUE);
			drop.setInput(new Glow());
			
			VBox cBtn = new VBox(5);
			cBtn.setAlignment(Pos.CENTER);
			cBtn.getChildren().addAll(charBtnImg, charType);
			
			Distant light = new Distant();
	        light.setAzimuth(-15.0f);
	 
	        Lighting l = new Lighting();
	        l.setLight(light);
	        l.setSurfaceScale(5.0f);
			
	        setEffect(l);
			setOnMouseEntered(event -> {
				setEffect(drop);
			});
			setOnMouseExited(event -> {
				setEffect(l);
			});
			
			getChildren().setAll(cBtn);
		}
	} // CharacterButton end

}


















