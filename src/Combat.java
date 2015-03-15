


public class Combat {
	public static void enemyMalee(){
		
		//Get Enemies and choose 1 randomly 
		
		double amtOfEnemies = GameMain.enemies.length -1;
		int rand = (int)(Math.round(Math.random()*amtOfEnemies));
		Enemy e = GameMain.enemies[rand];
		
		System.out.println("You are encountered by " + e.getName());
		e.printStats();
		System.out.println("Hit chance: " + Player.chanceToHitP(e) + "%");
		
		
		String[] choices = {"Attack. ("+ Player.chanceToHit(e) + "% chance to hit)", "Run. (There is a " + Player.chanceToFlee(e) + "% chance, that you get away)"};
		
		while (!Player.isDead() && !e.isDead()) {
			int choice = GameFlow.makeChoice("What do you do?", choices);
		
			if (choice == 1){
				makeCombat(e, true);
			} else {
				if (choice == 2){
					if (runAway(e)) {
						break;
					}
					
				}
			}		
		}
		
	}
	
	public static void makeCombat(Enemy e, boolean playerStarts){
		int damage = 0;
		if (!playerStarts) {
			damage=Player.willEnemyHit(e);
				if (damage > 0) {
					Player.damageHealth(damage);
				} else {
					System.out.println(e.getName() + " missed!");
			}
		}
		damage = Player.willPlayerHit(e);
		if (damage > 0) {
			e.damageHealth(damage);
		} else {
			System.out.println("You missed!");
		}
			
		if(e.isDead()){
			System.out.println("\n");
			Player.killedEnemy(e);
			return;
		}
		damage=Player.willEnemyHit(e);
		if (damage > 0) {
			Player.damageHealth(damage);
		} else {
			System.out.println(e.getName() + " missed!");
		}
		if(Player.isDead())
			return;
		
	}
	
	public static boolean runAway(Enemy e){
		System.out.println("You try to run away.");
		if (Player.isFleeing(e)) {
			System.out.println("You managed to run away and left " + e.getName() + " far behind");
			return true;
		} else {
			System.out.println(e.getName() + " caught you and attacked.");
			int damage=Player.willEnemyHit(e);
			if (damage > 0) {
				Player.damageHealth(damage);
				return false;
			} else {
				System.out.println(e.getName() + " missed!");
			return false;
			}
		}	
	}
		
}
