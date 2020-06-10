package game;


public class Statistics {

	
	// Initialisiert die Stats des Spielers. Static, damit auf die Klasse zugegriffen werden kann
    private int damage = 5;
	private int health = 100;
    private int maxHealth = 100;
    private double attackSpeed = 0.2; // Zeit in ms, die vergehen muss, bis der Spieler wieder schiessen kann.
    private int levelNo = 1;
    private int score = 0;

	public Statistics () {
	}

	public int getDamage() {
		return damage;
	}
	public void setDamage(int damage) {
		this.damage = damage;
	}
	public int getHealth() {
		return health;
	}
	public void setHealth(int health) {
		this.health = health;
	}
	public int getMaxHealth() {
		return maxHealth;
	}
	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}
	public double getAttackSpeed() {
		return attackSpeed;
	}
	public void setAttackSpeed(double attackSpeed) {
		this.attackSpeed = attackSpeed;
	}
	public int getLevelNo() {
		return levelNo;
	}
	public void setLevelNo(int levelNo) {
		this.levelNo = levelNo;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
    
}