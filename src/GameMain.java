import java.util.Scanner;

public class GameMain {
	public static Weapon[] weapons;;
	public static Armor[] armors;
	public static void main(String[] args) throws Exception{
		System.out.println("You have started The Game Thing.");
		
		// Loading Data:
		weapons = DataManager.getWeapons();
		
		for (Weapon weapon : weapons){
			System.out.println();
			weapon.printStats();
		}
		
		armors = DataManager.getArmors();
		for (Armor armor : armors){
			System.out.println();
			armor.printStats();
		}
		
		System.out.println("This is a Console Based Turn Based Role Playing Game. CBTBRPG - In short.");
		System.out.println("First Things First, We must find out if you've played before.");
		System.out.println();
		
		
		System.out.println("Please Enter your Character name:");
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
		}
		
		
		//Katsetan Varustust
		System.out.println();
		
		//sword.printStats();
		
		/*
		do{}
		while(!Player.isDead());*/
	}
}
