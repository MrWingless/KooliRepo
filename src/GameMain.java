import java.util.Scanner;

public class GameMain {
	public static void main(String[] args) throws Exception{
		Scanner sisend = new Scanner(System.in);
		System.out.println("Please Enter your Character name:");
		Player.setName(sisend.next()); 
		sisend.close();
		
		// Loo v�i loe m�ngija andmed failist
		DataManager.checkPlayer();
		
		PlayerMessage.welcomePlayer();
		
		
		
		/*
		do{}
		while(!Player.isDead());*/
	}
}
