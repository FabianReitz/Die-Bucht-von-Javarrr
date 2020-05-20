package game;

public class Statistics {

	
	// Initialisiert die Stats des Spielers
    private static int damage = 1;
	private static int health = 100;
    private static int maxHealth = 100;
    private static int attackSpeed = 1;
    private static int levelNo = 1;
    private static int score = 0;
    

	public static int getDamage() {
		return damage;
	}
	public static void setDamage(int damage) {
		Statistics.damage = damage;
	}
	public static int getHealth() {
		return health;
	}
	public static void setHealth(int health) {
		Statistics.health = health;
	}
	public static int getMaxHealth() {
		return maxHealth;
	}
	public static void setMaxHealth(int maxHealth) {
		Statistics.maxHealth = maxHealth;
	}
	public static int getAttackSpeed() {
		return attackSpeed;
	}
	public static void setAttackSpeed(int attackSpeed) {
		Statistics.attackSpeed = attackSpeed;
	}
	public static int getLevelNo() {
		return levelNo;
	}
	public static void setLevelNo(int levelNo) {
		Statistics.levelNo = levelNo;
	}
	public static int getScore() {
		return score;
	}
	public static void setScore(int score) {
		Statistics.score = score;
	}
    
    
    
    

}