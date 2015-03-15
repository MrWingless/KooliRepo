


public class Combat {
	public static void enemyMalee(){
		
		//Get Enemies and choose 1 randomly 
		
		double amtOfEnemies = GameMain.enemies.length -1;
		int rand = (int)(Math.round(Math.random()*amtOfEnemies));
		Enemy e = GameMain.enemies[rand];
		
		System.out.println("You are encountered by " + e.getName());
		e.printStats();
		
		
		
		String[] choices = {"Attack. ("+ Player.changeToHit(e) + "% chance to hit", "Run. (There is a " + Player.chanceToFlee(e) + "% chance, that you get away)"};
		
		int choice = GameFlow.makeChoice("What do you do?", choices);
		
		
		if (choice == 1){
			makeCombat(e, true);
		} else {
			if (choice == 2){
				runAway(e);
			}
		}				
		
	}
	
	public static void makeCombat(Enemy e, boolean playerStarts){
		int damage = 0;
		if (!playerStarts) {
			Player.damageHealth(1);
		} 
		
		while (!Player.isDead() && !e.isDead()) {
			
			damage = Player.willPlayerHit(e);
			if (damage > 0) {
				e.damageHealth(damage);
			} else {
				System.out.println("You missed!");
			}
			
			if(e.isDead()){
				System.out.println("\n");
				Player.killedEnemy(e);
				break;
			}
			damage=Player.willEnemyHit(e);
			if (damage > 0) {
				Player.damageHealth(damage);
			} else {
				System.out.println(e.getName() + " missed!");
			}
			
			if(Player.isDead())
				break;
		}
	}
	
	public static void runAway(Enemy e){
		System.out.println("You try to run away.");
		if (Player.isFleeing(e)) {
			System.out.println("You managed to run away and left " + e.getName() + " far behind");
		} else {
			System.out.println(e.getName() + " caught you and attacked.");
			makeCombat(e, false);
		}	
	}
	
	static boolean isPlayerHit(Enemy e){
		return true;
	}
	
	
	
}
