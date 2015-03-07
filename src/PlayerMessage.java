public class PlayerMessage{
	static void welcomePlayer(){
		if (Player.isFirstGame)
			System.out.println("Welcome " + Player.name);
		else
			System.out.println("Welcome Back " + Player.name);
	}
}
