import java.util.Scanner;

public class GameMain {
	public static void main(String[] args) {
		Scanner sisend = new Scanner(System.in);
		System.out.println("Please Enter your Character name:");
		Player.name = sisend.next(); 
		sisend.close();
		
		// Loo v�i loe m�ngija andmed failist
		
		PlayerMessage.welcomePlayer();
		
	}

}
