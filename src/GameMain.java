import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;





import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.animation.FadeTransition;import javafx.animation.TranslateTransition;



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
			primaryStage.show();
		
		
		
	}


	public void changeResolution() {

	}

	private class GameMenu extends Parent {
		public GameMenu(Stage primaryStage) {
			VBox mainMenu = new VBox(15);
			VBox loadMenu = new VBox(15);
			VBox optionsMenu = new VBox(15);
			VBox videoMenu = new VBox(15);
			

			mainMenu.setTranslateX(gameX/5);
			mainMenu.setTranslateY(gameY/4);

			loadMenu.setTranslateX(gameX);
			loadMenu.setTranslateY(gameY/4);

			optionsMenu.setTranslateX(gameX);
			optionsMenu.setTranslateY(gameY/4);

			videoMenu.setTranslateX(gameX);
			videoMenu.setTranslateY(gameY/4);



			//Main Menu buttons
			MenuButton btnMainNew = new MenuButton("New Player");
			btnMainNew.setOnMouseClicked(event -> {
				
			});

			MenuButton btnMainLoad = new MenuButton("Load Player");
			btnMainLoad.setOnMouseClicked(event -> {
				VBox playerMenu = new VBox(15);
				playerMenu.setTranslateX(gameX);
				playerMenu.setTranslateY(gameY/4);
				ArrayList<MenuButton> playersButtons = new ArrayList<MenuButton>();
				for (File playerFile : DataManager.getAvailablePlayerFiles()){
					String filename = playerFile.getName();
					String playerName = filename.replaceFirst(".txt", "");
					//System.out.println(playerName);
					playersButtons.add(new MenuButton(playerName));
				}
				playersButtons.add(new MenuButton("Back"));
				
				
				for (int i = 0; i < playersButtons.size(); i++){
					if (i == (playersButtons.size() - 1)){
						playersButtons.get(i).setOnMouseClicked(event2 -> {
							transistWindow(playerMenu, mainMenu, null);
						});
					}
				}

				playerMenu.getChildren().addAll(playersButtons);
				transistWindow(mainMenu, playerMenu, null);

			});

			MenuButton btnMainOptions = new MenuButton("Options");
			btnMainOptions.setOnMouseClicked(event -> {
				transistWindow(mainMenu, optionsMenu, null);
				/*
				getChildren().add(optionsMenu);
				//Move Options Menu away
				TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), mainMenu);
				tt.setToX(-gameX/4);
				//Get Main Menu back
				TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), optionsMenu);
				tt1.setToX(gameX/5);

				tt.play();
				tt1.play();
				tt.setOnFinished(event1 -> getChildren().remove(mainMenu));*/
			});

			MenuButton btnMainExit = new MenuButton("Exit");
			btnMainExit.setOnMouseClicked(event -> {
				FadeTransition ft = new FadeTransition(Duration.seconds(0.5), this);
				ft.setFromValue(1);
				ft.setToValue(0);
				ft.play();

				ft.setOnFinished(event1 -> System.exit(0));
			});

			//Options Menu Buttons

			Text txVideoMenu = new Text("Choose Resolution");
			txVideoMenu.setFont(Font.font("Showcard Gothic", 20));
			txVideoMenu.setFill(Color.WHITE);
			MenuButton low = new MenuButton("640x480");
			MenuButton medium = new MenuButton("800x600");
			MenuButton high = new MenuButton("1024x756");

			videoMenu.getChildren().addAll(txVideoMenu,low, medium, high);


			MenuButton btnOptVideo = new MenuButton("Video");
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

			MenuButton btnOptBack = new MenuButton("Back");
			btnOptBack.setOnMouseClicked(event -> {
				transistWindow(optionsMenu, mainMenu, videoMenu);
				/*
				getChildren().add(mainMenu);
				//Move Options Menu away
				TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), optionsMenu);
				tt.setToX(gameX);
				//Get Main Menu back
				TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), mainMenu);
				tt1.setToX(gameX/5);

				if (secondaryMenuVisible) {
					TranslateTransition tt2 = new TranslateTransition(Duration.seconds(0.25), videoMenu);
					tt2.setToX(gameX);
					tt2.play();
					secondaryMenuVisible = false;
					tt2.setOnFinished(event2 -> getChildren().remove(videoMenu));
				}

				tt.play();
				tt1.play();
				tt.setOnFinished(event1 -> getChildren().remove(optionsMenu));*/
			});

			mainMenu.getChildren().addAll(btnMainNew, btnMainLoad, btnMainOptions, btnMainExit);
			optionsMenu.getChildren().addAll(btnOptVideo, btnOptBack);

			Rectangle bg = new Rectangle(1024, 756);
			bg.setFill(Color.GRAY);
			bg.setOpacity(0.4);
			
			
			theGameThing.setFont(Font.font("Showcard Gothic", 40));
			theGameThing.setFill(Color.WHITE);
			theGameThing.setTranslateX(gameX/3);
			theGameThing.setTranslateY(gameY/10);
			DropShadow drop = new DropShadow(100, Color.RED);
			drop.setInput(new Glow());
			
			theGameThing.setOnMousePressed(event -> {
				theGameThing.setEffect(drop);
				theGameThing.setFill(Color.BLACK);
			});
			theGameThing.setOnMouseReleased(event -> {
				theGameThing.setEffect(null);
				theGameThing.setFill(Color.WHITE);
			});

			getChildren().addAll(bg, mainMenu, theGameThing);

		}
		
		public void refreshStage(Stage primaryStage) {
			primaryStage.setWidth(gameX);
			primaryStage.setHeight(gameY);
			menuBgView.setFitWidth(gameX);
			menuBgView.setFitHeight(gameY);
			theGameThing.setTranslateX(gameX/3);
			theGameThing.setTranslateY(gameY/10);
		}
		
		public void transistWindow(VBox from, VBox to, VBox otherMenu){
			getChildren().add(to);
			// Moves the first window away
			TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), from);
			tt.setToX(-gameX/4);
			// Brings the next window in
			TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), to);
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
	}

	
	
	private static class MenuButton extends StackPane {

		private Text text;

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
	}



}


















