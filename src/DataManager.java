import java.io.FileNotFoundException;
import java.io.PrintWriter;

// This class should manage the files - as in get info from files and save info to files.
public class DataManager{
	// Checks If The Player Already Exists
	static void checkIfPlayerPlayedBefore() throws Exception{
		java.io.File playerData = new java.io.File("Players/" + Player.getName() +".txt");
		if (!playerData.exists()){
			Player.setFirstGame(true);
			playerData.createNewFile();
		}
		else{
			Player.setFirstGame(false);
		}
		PlayerMessage.welcomePlayer();
	}
	
	// Returns file's Lines as Separate String in String Array where values are separated by '/'-s
	private static String[] readFile(String path) throws FileNotFoundException{
		java.io.File file = new java.io.File(path);
		java.util.Scanner sc = new java.util.Scanner(file);   
		StringBuilder content = new StringBuilder();
		while (sc.hasNextLine()) {
		    String line = sc.nextLine();
		    if (!line.startsWith("!--")){
		    	line = line.trim();
		    	String[] lineContent = line.split("/");
		    	for (String entry : lineContent){
		    		content.append(entry);
		    		content.append("/");
		    	}
		    	content.append("!");
		    }
		}
		sc.close();
		return content.toString().split("!");
	}
	
	static Weapon[] getWeapons() throws FileNotFoundException{
		String[] weaponData = readFile("Data/Weapons.txt");
		Weapon[] weapons = new Weapon[weaponData.length];
		
		for (int i = 0; i < weaponData.length; i++){
			String[] thisWeaponData = weaponData[i].split("/");
			weapons[i] = new Weapon(
					thisWeaponData[0],
					Integer.parseInt(thisWeaponData[1]),
					Integer.parseInt(thisWeaponData[2]),
					Integer.parseInt(thisWeaponData[3]),
					Integer.parseInt(thisWeaponData[4]),
					Integer.parseInt(thisWeaponData[5]),
					Integer.parseInt(thisWeaponData[6]),
					Integer.parseInt(thisWeaponData[7]),
					Double.parseDouble(thisWeaponData[8]),
					Double.parseDouble(thisWeaponData[9]),
					Double.parseDouble(thisWeaponData[10]),
					Integer.parseInt(thisWeaponData[11]));
		}
		
		return weapons;
	}
}
