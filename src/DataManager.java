public class DataManager{

	// Checks If The Player Already Exists
	static void checkPlayer() throws Exception{
		java.io.File playerData = new java.io.File("/Players/"+ Player.getName() +".txt");
		if (!playerData.exists()){
			Player.setFirstGame(true);
			playerData.createNewFile();
			// Creates a new Player According to the Choice The Player Makes.
		}
		else{
			Player.setFirstGame(false);
			// Load The Data From the File!
		}
	}
}
