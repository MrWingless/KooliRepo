import java.util.Scanner;

public class GameMain {
	public static void main(String[] args) throws Exception{
		System.out.println("You have started The Game Thing.");
		System.out.println("This is a Console Based Turn Based Role Playing Game. CBTBRPG - In short.");
		System.out.println("First Things First, We must find out if you've played before.");
		System.out.println();
		Scanner sisend = new Scanner(System.in);
		System.out.println("Please Enter your Character name:");
		Player.setName(sisend.next()); 
		sisend.close();
		
		// Kontrollb, kas m�ngija on varem mn�nginud.
		DataManager.checkIfPlayerPlayedBefore();
		if (Player.getFirstGame()){
			// Creates a new Player According to the Choice The Player Makes.
		}
		else{
			// Load The Data From the File!
		}
		
		
		//Katsetan
		
		/*
		do{}
		while(!Player.isDead());*/
	}
}
