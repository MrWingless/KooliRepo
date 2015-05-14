import javafx.scene.text.Text;


public class Combat {
	// TODO check accuracy, dodge and protection formulas.
	// TODO protection not used in damage calculations.
	// TODO 
	
	static Enemy e;
	public static void enemyMalee(){
		
		//Get Enemies and choose 1 randomly 
		
		double amtOfEnemies = GameMain.enemies.length - 1;
		int rand = (int)(Math.round(Math.random()*amtOfEnemies));
		e = new Enemy(GameMain.enemies[rand]);
		
		GameFlow.whatsUp = new Text("You are encountered by " + e.getName());
		GameFlow.gameInnerBPane.setCenter(GameFlow.whatsUp);
		GameFlow.gameOuterBPane.setCenter(GameFlow.gameInnerBPane);
		GameFlow.scene.setRoot(GameFlow.gameOuterBPane);
		//System.out.println("You are encountered by " + e.getName());
		//e.printStats();
		//System.out.println("Hit chance: " + chanceToHit(false) + "%");
		
		
		
		String[] choices = {"Attack.", "Run."};
		
		while (!Player.isDead() && !e.isDead()) {
			int choice = GameFlow.makeChoice("What do you do?", choices);
		
			if (choice == 1){
				makeCombat();
			} else {
				if (choice == 2){
					if (runAway()) {
						break;
					} //if
				} //if
			} //else
		} //while
		
	}
	
	//
	public static void makeCombat(){
		int damage = 0;
		
		//checks, if Player hits enemy and deals damage, when hit occurs 
		damage = willHit(true);
		if (damage > 0) {
			e.damageHealth(damage);
		} else {
			System.out.println("You missed!");
		}
		
		 //If enemy dies, then enemy wont hit back.
		if(e.isDead()){
			System.out.println("\n");
			Player.killedEnemy(e);
			return;
		}
		
		//checks, if enemy hits Player and deals damage, when hit occurs
		damage=willHit(false);
		if (damage > 0) {
			Player.damageHealth(damage);
		} else {
			System.out.println(e.getName() + " missed!");
		}
		if(Player.isDead())
			return;
		
	}
	
	//
	public static boolean runAway(){
		System.out.println("You try to run away.");
		if (isFleeing()) {
			System.out.println("You managed to run away and left " + e.getName() + " far behind");
			return true;
		} else {
			System.out.println(e.getName() + " caught you and attacked.");
			int damage=willHit(false);
			if (damage > 0) {
				Player.damageHealth(damage);
				return false;
			} else {
				System.out.println(e.getName() + " missed!");
			return false;
			}
		}	
	}
	
	/**
	 * Calculates attack hit chance
	 * @param player [true] - Player hits enemy [false] - enemy hits Player
	 * @return Hit change % 
	 */
	public static double chanceToHit(boolean player){
		double accuracy;
		double dodge;
		if (player) {
			accuracy = Player.getAccuracy();
			dodge = e.getDodge();
		} else {
			accuracy = e.getAccuracy();
			dodge = Player.getDodge();
		}
		double chance = Math.round(((accuracy - dodge)/accuracy)*100);
		if (chance > 100.00) {
			chance = 100.00;
		}
		
		if (chance < 0.00) {
			chance = 0.00;
		}
		
		return chance;
	}

	/**
	 * Calculates, will attack hit and returns damage made.
	 * @param player [true] - Player hits enemy [false] - enemy hits Player
	 * @return Attack damage when attack hits, 0 when misses.
	 */
	public static int willHit(boolean player){
		double hitChance = chanceToHit(player);
	
		if (hitChance < 0) {
			return 0;
		} else if (Math.random()*100 > hitChance) {
			return 0;
		} else {
			return hitDamage(player); //DOTO add dam calculation here
		}
	}
	
	// Calculates the change that player has to run away
	public static double chanceToFlee(){
		
		double runAway = Player.getSpeed() + (Player.getIntelligence() + Player.getDexterity()) * 0.1;
		double runAfter = e.getSpeed() + (e.getIntelligence() + e.getDexterity()) * 0.1;
		
		double chance = Math.round(((runAway - runAfter)/runAway) * 100.00);
		if (chance > 100.00) {
			chance = 100.00;
		}
		if (chance < 0.00) {
			chance = 0.00;
		}
		return chance;
	}
	
	//Will see, is the player running away or is enemy catching up
	public static boolean isFleeing(){
		
		double fleeChance = chanceToFlee();
		
		if (fleeChance < 0) {
			return false;
		} else if (Math.random()*100 > fleeChance) {
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * Calculates hit damage. Random between min and max damage.
	 * @param player [true] - Player hits enemy [false] - enemy hits Player
	 * @return Amount of damage hit makes
	 */
	public static int hitDamage(boolean player) {
		int min, max;
		if (player) {
			min = Player.getMinDmg();
			max = Player.getMaxDmg();
		} else {
			min = e.minDmg;
			max = e.maxDmg;
		}
		
		return (int) (Math.random() * (max - min +1)) + min;
		//return 2;
	}
		
}
