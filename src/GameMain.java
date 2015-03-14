import java.util.Scanner;

public class GameMain {
	public static void main(String[] args) throws Exception{
		System.out.println("You have started The Game Thing.");
		System.out.println("This is a Console Based Turn Based Role Playing Game. CBTBRPG - In short.");
		System.out.println("First Things First, We must find out if you've played before.");
		System.out.println();
		
		
		//Scanner sisend = new Scanner(System.in);
		System.out.println("Please Enter your Character name:");
		Player.setName(GameFlow.sisend.next()); 
		//sisend.close();
		
		
		
		// Kontrollb, kas mängija on varem mnänginud.
		DataManager.checkIfPlayerPlayedBefore();
		if (Player.getFirstGame()){
			// Creates a new Player According to the Choice The Player Makes.
		}
		else{
			// Load The Data From the File!
		}
		
		Encounter.chooseClass();
		
		//Katsetan Revli
		System.out.println();
		Equipment sword = new Weapon("Mõõk", 10, 12, 3, -7, 5, -4, 7, 2.1, -2.3, 2.5, 600);
		Weapon[] relvad = DataManager.getWeapons();
		
		for (Weapon relv : relvad){
			System.out.println();
			relv.printStats();
		}
		//sword.printStats();
		
		/*
		do{}
		while(!Player.isDead());*/
	}
}
