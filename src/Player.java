import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class Player implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static String name;
	private static boolean isFirstGame;
	private static boolean hasBeenCreated = false;
	
	private static int exp; // Current Amount Of Experience Gathered
	private static int gold; // Current Gold Amount
	private static int nextLevelAt;
	private static int level;
	
	private static int health; // Current health amount
	private static int maxHealth; // Maximum amount of health
	private static int speed; // Who starts first.
	private static int strength; // Adds to Melee dmg?
	private static int dexterity; // Adds to Ranged dmg?
	private static int intelligence; // Adds to Magic dmg?
	private static double protection; // % of dmg negated
	private static double accuracy; // Chance to hit the target (calculated against dodge)
	private static double dodge; // Chance to dodge the attack (calculated against accuracy)
	
	private static int minDmg; // Minimum player Damage
	private static int maxDmg; // Maximum player Damage
	
	private static int role; // Mage[0] / Rogue[1] / Warrior[2]
	
	private static Armor armor;
	private static Weapon weapon;
	
	public static boolean getFirstGame(){
		return isFirstGame;
	}

	public static String getName() {
		return name;
	}

	public static int getExp() {
		return exp;
	}

	public static int getGold() {
		return gold;
	}

	public static int getHealth() {
		return health;
	}

	public static int getMaxHealth() {
		return maxHealth;
	}

	public static int getSpeed() {
		return speed;
	}

	public static int getStrength() {
		return strength;
	}

	public static int getDexterity() {
		return dexterity;
	}

	public static int getIntelligence() {
		return intelligence;
	}

	public static double getProtection() {
		return protection;
	}

	public static double getAccuracy() {
		return accuracy;
	}

	public static double getDodge() {
		return dodge;
	}

	public static Armor getArmor() {
		return armor;
	}

	public static Weapon getWeapon() {
		return weapon;
	}
	
	public static int getMinDmg() {
		return minDmg;
	}

	public static int getMaxDmg() {
		return maxDmg;
	}

	public static String getRole() {
		return (role == 0 ? "Mage" : (role == 1 ? "Rogue" : (role == 2 ? "Warrior" : "Unknown")));
	}
	
	private static int getRoleAsInt(){
		return role;
	}
	
	public static boolean isCreated(){
		return Player.hasBeenCreated;
	}

	public static void setFirstGame(boolean isFirstGame) {
		Player.isFirstGame = isFirstGame;
	}
	
	public static void setName(String name) {
		Player.name = name;
	}
	
	private static void setExp(int exp) {
		Player.exp = exp;
	}

	private static void setGold(int gold) {
		Player.gold = gold;
	}

	private static void setNextLevelAt(int nextLevelAt) {
		Player.nextLevelAt = nextLevelAt;
	}

	private static void setLevel(int level) {
		Player.level = level;
	}

	private static void setHealth(int health) {
		Player.health = health;
	}

	private static void setMaxHealth(int maxHealth) {
		Player.maxHealth = maxHealth;
	}

	private static void setSpeed(int speed) {
		Player.speed = speed;
	}

	private static void setStrength(int strength) {
		Player.strength = strength;
	}

	private static void setDexterity(int dexterity) {
		Player.dexterity = dexterity;
	}

	private static void setIntelligence(int intelligence) {
		Player.intelligence = intelligence;
	}

	private static void setProtection(double protection) {
		Player.protection = protection;
	}

	private static void setAccuracy(double accuracy) {
		Player.accuracy = accuracy;
	}

	private static void setDodge(double dodge) {
		Player.dodge = dodge;
	}

	private static void setRole(int role) {
		Player.role = role;
	}

	private static void setArmor(int armorCode) {
		Player.armor = GameMain.armors[armorCode];
	}

	private static void setWeapon(int weaponCode) {
		Player.weapon = GameMain.weapons[weaponCode];
	}
	
	private static void setCreated(){
		Player.hasBeenCreated = true;
	}

	private static void createMage(){
		role = 0;
		level = 0;
		nextLevelAt = 10;
		
		health = 20;
		maxHealth = 20;
		
		strength = 5;
		dexterity = 5;
		intelligence = 10;

		//calculateDmg();

		speed = 5;
		//protection = armor.getProtection() + (int)(strength*0.1);
		//accuracy = armor.getAccuracy() + (int)(dexterity*0.1);
		//dodge = armor.getDodge() + (int)(dexterity*0.1);
	}
	private static void createRogue(){
		role = 1;
		level = 0;
		nextLevelAt = 10;
		
		health = 25;
		maxHealth = 25;
		
		strength = 5;
		dexterity = 10;
		intelligence = 5;

		//calculateDmg();

		speed = 5;
		//protection = armor.getProtection() + (int)(strength*0.1);
		//accuracy = armor.getAccuracy() + (int)(dexterity*0.1);
		//dodge = armor.getDodge() + (int)(dexterity*0.1);
	}
	private static void createWarrior(){
		role = 2;
		level = 0;
		nextLevelAt = 10;
		
		health = 30;
		maxHealth = 30;
		
		strength = 10;
		dexterity = 5;
		intelligence = 5;
		
		//calculateDmg();

		speed = 5;
		//protection = armor.getProtection() + (int)(strength*0.1);
		//accuracy = armor.getAccuracy() + (int)(dexterity*0.1);
		//dodge = armor.getDodge() + (int)(dexterity*0.1);
		
	}
	
	public static void damageExp(int i){
		Player.exp = Player.exp - i;
		System.out.println("Player: You lose " + i + " point of experience");
	}
	
	public static void loseGold(int i){
		Player.gold = Player.gold - i;
		if (Player.gold < 0)
			setGold(0);
		System.out.println("Player: You have lost " + i + " gold. You now have: " + getGold() + "g");
	}
	
	// Choosing a Class
	public static void chooseClass(int i){
		switch(i){
			case 0:
				createMage(); break;
			case 1:
				createRogue(); break;
			case 2:
				createWarrior(); break;
			default:
				System.out.println("Error: Player.chooseClass - Class choice higher than 2 :(");
		}
		setWeapon(0);
		setArmor(0);
		calculateDmg();
		setCreated();
	}
	
	// Calculates Dmg numbers for the player.
	static void calculateDmg(){
		int minWeaponDmg;
		int maxWeaponDmg;
		if (weapon == null){
			minWeaponDmg = 0;
			maxWeaponDmg = 0;
		} else {
			minWeaponDmg = weapon.getMinDmg();
			maxWeaponDmg = weapon.getMaxDmg();
		}
		switch(role){
		case 0: // Mage
			minDmg = 1 + minWeaponDmg + ((int)Math.round(intelligence * 0.35));
			maxDmg = 1 + maxWeaponDmg + ((int)Math.round(intelligence * 0.6));
			break;
		case 1: // Rogue
			minDmg = 1 + minWeaponDmg + ((int)Math.round(dexterity * 0.30));
			maxDmg = 1 + maxWeaponDmg + ((int)Math.round(dexterity * 0.55));
			break;
		case 2: // Warrior
			minDmg = 1 + minWeaponDmg + ((int)Math.round(strength * 0.25));
			maxDmg = 1 + maxWeaponDmg + ((int)Math.round(strength * 0.5));
			break;
		default: // Other
			System.out.println("ERROR: Calculating Dmg - Invalid Role");
		}
		protection = weapon.getAccuracy() + armor.getProtection() + (int)(strength*0.2);
		accuracy = weapon.getAccuracy() + armor.getAccuracy() + 5;
		dodge = weapon.getDodge() + armor.getDodge() + 3;
	}
	
	// Displays Player Info to Console.
	static void displayInfo(){
		calculateDmg();
		System.out.println("PLAYER INFO:");
		System.out.println("Name: \t\t" + name);
		System.out.println("Level: \t\t" + level + " " + getRole());
		System.out.println("Exp: \t\t" + exp + " / " + nextLevelAt);
		System.out.println("Gold: \t\t" + gold);
		System.out.println("Health: \t" + health + " / " + maxHealth);
		System.out.println("Damage: \t" + minDmg + " - " + maxDmg);
		System.out.println("Intelligence: \t" + intelligence);
		System.out.println("Dexterity: \t" + dexterity);
		System.out.println("Strength: \t" + strength);
		System.out.println("Speed: \t\t" + speed);
		if (protection != 0.0)
			System.out.println("Protection: \t" + protection);
		if (accuracy != 0.0)
			System.out.println("Accuracy: \t" + accuracy);
		if (dodge != 0.0)
			System.out.println("Dodge: \t\t" + dodge);
	}
	
	static void displayInfotoVBox(VBox infoPage){

		Rectangle bg = new Rectangle(200, 800);
		bg.setOpacity(0.5);
		bg.setFill(Color.BLACK);
		bg.setEffect(new GaussianBlur(3.5));
		
		ArrayList<Text> textArray = new ArrayList<Text>();
		Text title = new Text("Player Info:");
		title.setFont(Font.font("Showcard Gothic", 20));
		title.setFill(Color.WHITE);
		textArray.add(title);
		calculateDmg();
		textArray.add(setPlayerInfoFont("Name: \t\t" + name));
		textArray.add(setPlayerInfoFont("Level: \t\t" + level + " " + getRole()));
		textArray.add(setPlayerInfoFont("Exp: \t\t\t" + exp + " / " + nextLevelAt));
		textArray.add(setPlayerInfoFont("Gold: \t\t" + gold));
		textArray.add(setPlayerInfoFont("Health: \t\t" + health + " / " + maxHealth));
		textArray.add(setPlayerInfoFont("Damage: \t\t" + minDmg + " - " + maxDmg));
		textArray.add(setPlayerInfoFont("Intelligence: \t" + intelligence));
		textArray.add(setPlayerInfoFont("Dexterity: \t" + dexterity));
		textArray.add(setPlayerInfoFont("Strength: \t\t" + strength));
		textArray.add(setPlayerInfoFont("Speed: \t\t" + speed));
		if (protection != 0.0)
			textArray.add(setPlayerInfoFont("Protection: \t" + protection));
		if (accuracy != 0.0)
			textArray.add(setPlayerInfoFont("Accuracy: \t" + accuracy));
		if (dodge != 0.0)
			textArray.add(setPlayerInfoFont("Dodge: \t\t" + dodge));

		infoPage.getChildren().clear();
		//infoPage.getChildren().add(bg);
		//infoPage.backgroundProperty().set(new Background(new BackgroundFill(Color.BLACK, null, null)));
		//infoPage.setStyle("-fx-background-color: rgba(0, 0, 0, 0);");
		infoPage.getChildren().addAll(textArray);
	}
	
	private static Text setPlayerInfoFont(String text){
		Text theText = new Text(text);
		theText.setFont(Font.font("Showcard Gothic", 12));
		theText.setFill(Color.WHITE);
		return theText;
	}
	
	// Gets Player Info and Returns it as String Array (Used for Saving Player Info)
	static String[] getInfoAsStringArray(){
		String[] player = {
				name, 
				""+level, 
				""+getRoleAsInt(),
				""+exp,
				""+nextLevelAt,
				""+health,
				""+maxHealth,
				""+intelligence,
				""+dexterity,
				""+strength,
				""+speed,
				""+protection,
				""+accuracy,
				""+dodge,
				""+weapon.getCode(),
				""+armor.getCode(),
				""+gold};
		
		return player;
	}
	
	// Saves player Data to Player's File
	static void save() throws IOException{

		if (Player.isCreated()){
			if (Encounter.isInCombat){
				damageExp(1);
			}
			DataManager.savePlayerData();
		}
		else{
			DataManager.deletePlayerFile();
		}
	}
	
	static void load() {
		
		
		
	}
	
	static void loadPlayerData() throws FileNotFoundException{
		String[] playerData = DataManager.readPlayerData();
		//[0] Name,
		setName(playerData[0]);
		//[1] Level,   
		setLevel(Integer.parseInt(playerData[1]));
		//[2] role,
		setRole(Integer.parseInt(playerData[2]));
		//[3] exp,  
		setExp(Integer.parseInt(playerData[3]));
		//[4] nextLevelAt,  
		setNextLevelAt(Integer.parseInt(playerData[4]));
		//[5] health,  
		setHealth(Integer.parseInt(playerData[5]));
		//[6] maxHealth,  
		setMaxHealth(Integer.parseInt(playerData[6]));
		//[7] intelligence,  
		setIntelligence(Integer.parseInt(playerData[7]));
		//[8] dexterity,  
		setDexterity(Integer.parseInt(playerData[8]));
		//[9] strength,  
		setStrength(Integer.parseInt(playerData[9]));
		//[10] speed,  
		setSpeed(Integer.parseInt(playerData[10]));
		//[11] protection,  
		setProtection(Double.parseDouble(playerData[11]));
		//[12] accuracy,  
		setAccuracy(Double.parseDouble(playerData[12]));
		//[13] dodge,  
		setDodge(Double.parseDouble(playerData[13]));
		//[14] weaponCode,  
		if (!playerData[14].isEmpty())
			setWeapon(Integer.parseInt(playerData[14]));
		//[15] armorCode,  
		if (!playerData[15].isEmpty())
			setArmor(Integer.parseInt(playerData[15]));
		//[16] Gold
		setGold(Integer.parseInt(playerData[16]));
		setCreated();
		//displayInfo();
	}
	
	static void rest(){
		int healAmount = (int)Math.round(maxHealth * 0.4);
		heal(healAmount);
		damageExp(1);
	}
	
	static void heal(int healAmount){
		System.out.println("Player: You're being healed by " + healAmount + " point of health.");
		if (health + healAmount > maxHealth)
			health = maxHealth;
		else
			health = health + healAmount;
		System.out.println("Player: Your current health is now: " + health + " / " + maxHealth);
	}
	
	static void heal(double amount){
		int healAmount = (int)Math.round((maxHealth * amount)/100);
		heal(healAmount);
	}
	
	static void levelUp(){
		level++;
		exp = exp - nextLevelAt;
		health = maxHealth;
		nextLevelAt = (int)Math.round(nextLevelAt * 1.5);
		System.out.println("Congratulations! You have earned a new level! You are now level: " + level);
		System.out.println("Your Base Attributes have been updated:");
		switch(role){
		case 0: // Mage
			System.out.println("Intelligence: \t " + intelligence + " -> " + (intelligence + 2));
			intelligence +=2;
			System.out.println("Dexterity: \t " + dexterity + " -> " + (dexterity + 1));
			dexterity +=1;
			System.out.println("Strength: \t " + strength + " -> " + (strength + 1));
			strength +=1;
			break;
		case 1: // Rogue
			System.out.println("Intelligence: \t " + intelligence + " -> " + (intelligence + 1));
			intelligence +=1;
			System.out.println("Dexterity: \t " + dexterity + " -> " + (dexterity + 2));
			dexterity +=2;
			System.out.println("Strength: \t " + strength + " -> " + (strength + 1));
			strength +=1;
			break;
		case 2: // Warrior
			System.out.println("Intelligence: \t " + intelligence + " -> " + (intelligence + 1));
			intelligence +=1;
			System.out.println("Dexterity: \t " + dexterity + " -> " + (dexterity + 1));
			dexterity +=1;
			System.out.println("Strength: \t " + strength + " -> " + (strength + 2));
			strength +=2;
			break;
		default: // Other
			System.out.println("ERROR: Leveling Player - Invalid Role");
		}
		calculateDmg();
	}

	static boolean isDead(){
		if (health <= 0)
			return true;
		else
			return false;
	}
	
	static void damageHealth(int i){
		setHealth(getHealth() - i);
		if (getHealth() <= 0) {
			System.out.println("You are hit! You got " + i + " points of damage.");
		} else {
			System.out.println("You are hit! You got " + i + " points of damage and have " + health + "/" + maxHealth + " healt left.\nBe careful!");
		}
	}
	
	public static void killedEnemy(Enemy e){
		exp += e.valueInExp;
		gold += e.valueInGold;
		System.out.println("You got " + e.valueInGold + " Gold and " + e.valueInExp + " XP");
		if (exp >= nextLevelAt) {
			levelUp();
		}
	}
}























