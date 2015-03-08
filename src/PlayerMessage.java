
public class PlayerMessage{
	
	static void welcomePlayer(){
		if (Player.getFirstGame())
			System.out.println("Welcome " + Player.getName());
		else
			System.out.println("Welcome Back " + Player.getName());
	}
	
}
