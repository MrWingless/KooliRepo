


public class Combat {
	public static void enemyMalee(){
		
		//Get Enemies and choose 1 randomly 
		double amtOfEnemies = GameMain.enemies.length -1;
		int rand = (int)(Math.round(Math.random()*amtOfEnemies));
		Enemy e = GameMain.enemies[rand];
		
		System.out.println(e.getName());
		e.printStats();
		
		
		
		String[] choices = {"Attack.", "Run. (there is a " + Player.chanceToFlee(e) + " chance, that you get away)"};
		
		int choice = GameFlow.makeChoice("You are encountered by " + e.getName() + ". What do you do?", choices);
		
		
		if (choice == 1){
			makeCombat(e, true);
		} else {
			if (choice == 2){
				
				runAway(e);
			}
		}	
		
	}
	
	public static void makeCombat(Enemy e, boolean playerStarts){	
		if (playerStarts) {
			Player.damageHealth(1);
		} 
		
		do {
			e.damageHealth(1);
			Player.damageHealth(1);
		} while (!Player.isDead() && !e.isDead());
	}
	
	public static void runAway(Enemy e){
		System.out.println("You try to run away.");
		if (Player.isFleeing(e)) {
			System.out.println("You managed to run away and left " + e.getName() + " far behind");
		} else {
			System.out.println(e.getName() + " catched you and attacked.");
			makeCombat(e, false);
		}	
	}
	
	
}
