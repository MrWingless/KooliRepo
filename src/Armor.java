import java.io.Serializable;


public class Armor extends Equipment implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7799782809686444388L;
	private static int counter = 0;
	private int code;
	public Armor(String name, int health, int intelligence, int dexterity,
			int strength, int speed, double protection, double accuracy,
			double dodge, int price) {
		super(name, health, intelligence, dexterity, strength, speed, protection,
				accuracy, dodge, price);
		this.code = counter;
		counter++;
	}
	public int getCode(){
		return code;
	}
	void printStats(){
		System.out.println("ARMOR INFO:");
		printName();
		super.printStatBonuses();
		super.printPrice();
	}
	
}
