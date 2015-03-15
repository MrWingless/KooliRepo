import java.io.FileNotFoundException;
import java.io.IOException;
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

	// Reads WeaponData from the Weapons file
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

	// Reads ArmorData from the Armors file
	static Armor[] getArmors() throws FileNotFoundException{
		String[] armorData = readFile("Data/Armors.txt");
		Armor[] armors = new Armor[armorData.length];
		
		for (int i = 0; i < armorData.length; i++){
			String[] thisArmorData = armorData[i].split("/");
			armors[i]= new Armor(
					thisArmorData[0],
					Integer.parseInt(thisArmorData[1]),
					Integer.parseInt(thisArmorData[2]),
					Integer.parseInt(thisArmorData[3]),
					Integer.parseInt(thisArmorData[4]),
					Integer.parseInt(thisArmorData[5]),
					Double.parseDouble(thisArmorData[6]),
					Double.parseDouble(thisArmorData[7]),
					Double.parseDouble(thisArmorData[8]),
					Integer.parseInt(thisArmorData[9]));
		}
		
		return armors;
	}
	
	// Reads EnemyData from the Enemies file
	static Enemy[] getEnemies() throws FileNotFoundException {
		String[] enemyData = readFile("Data/Enemies.txt");
		Enemy[] enemies = new Enemy[enemyData.length];
		
		for (int i = 0; i<enemyData.length; i++){
			String[] thisEnemyData = enemyData[i].split("/");
			enemies[i] = new Enemy(
					thisEnemyData[0],
					Integer.parseInt(thisEnemyData[1]),
					Integer.parseInt(thisEnemyData[2]),
					Integer.parseInt(thisEnemyData[3]),
					Integer.parseInt(thisEnemyData[4]),
					Integer.parseInt(thisEnemyData[5]),
					Integer.parseInt(thisEnemyData[6]),
					Double.parseDouble(thisEnemyData[7]),
					Double.parseDouble(thisEnemyData[8]),
					Double.parseDouble(thisEnemyData[9]),
					Integer.parseInt(thisEnemyData[10]),
					Integer.parseInt(thisEnemyData[11]),
					Integer.parseInt(thisEnemyData[12]),
					Integer.parseInt(thisEnemyData[13]));
		}
		
		return enemies;
	}
	
	// Save Player Data into a File
	static void savePlayerData() throws IOException{
		String[] player = Player.getInfoAsStringArray();
		java.io.File playerData = new java.io.File("Players/" + player[0] +".txt");
		if (!playerData.exists()){
			playerData.createNewFile();
		}
		java.io.PrintWriter writer = new java.io.PrintWriter(playerData);
		//[0] Name, 
		writer.println("# Name");
		writer.println(player[0]);
		//[1] Level, 
		writer.println("# Level");
		writer.println(player[1]);
		//[2] getRoleAsInt(),
		writer.println("# Role/Class");
		writer.println(player[2]);
		//[3] exp,
		writer.println("# Exp");
		writer.println(player[3]);
		//[4] nextLevelAt,
		writer.println("# Exp Required for Next Level Up");
		writer.println(player[4]);
		//[5] health,
		writer.println("# Current Health");
		writer.println(player[5]);
		//[6] maxHealth,
		writer.println("# Max Health");
		writer.println(player[6]);
		//[7] intelligence,
		writer.println("# Intelligence");
		writer.println(player[7]);
		//[8] dexterity,
		writer.println("# Dexterity");
		writer.println(player[8]);
		//[9] strength,
		writer.println("# Strength");
		writer.println(player[9]);
		//[10] speed,
		writer.println("# Speed");
		writer.println(player[10]);
		//[11] protection,
		writer.println("# Protection");
		writer.println(player[11]);
		//[12] accuracy,
		writer.println("# Accuracy");
		writer.println(player[12]);
		//[13] dodge,
		writer.println("# Dodge");
		writer.println(player[13]);
		//[14] weaponCode,
		writer.println("# Weapon's Code");
		writer.println(player[14]);
		//[15] armorCode,
		writer.println("# Armor's Code");
		writer.println(player[15]);
		//[16] Gold,
		writer.println("# Gold");
		writer.println(player[16]);
		writer.close();
	}

	// Read played data from the file and returns the data as an Array os Strings
	static String[] readPlayerData() throws FileNotFoundException{
		java.io.File player = new java.io.File("Players/" + Player.getName() +".txt");
		java.util.Scanner fileScanner = new java.util.Scanner(player);  
		String[] tempPlayerData = new String[17];
		int counter = 0;
		while (fileScanner.hasNextLine()) {
			String s = fileScanner.nextLine();
			if (!s.startsWith("#")){
				tempPlayerData[counter] = s;
				counter++;
			}
		}
		fileScanner.close();
		return tempPlayerData;
	}
	
	static void deletePlayerFile(){
		java.io.File player = new java.io.File("Players/" + Player.getName() +".txt");
		player.delete();
	}
}
