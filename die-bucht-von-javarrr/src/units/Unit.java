package units;
import units.Entity;

public abstract class Unit extends Entity {

	
	
	//Legt Standardwerte für die Schiffe/Units fest, die seperat aber noch geändert werden können
	public static final int STANDARD_HP = 5;
	public static final float STANDARD_SPEED = 3.0f;
	
	//Standardisiertes Scaling
	public static final int STANDARD_UNIT_WIDTH = 72, STANDARD_UNIT_HEIGHT = 44;
	
	
	protected int health;
	protected float movespeed, xMove;
	
	
	
	public Unit(float x, float y, int width, int height) {
		super(x, y, width, height);
		health = STANDARD_HP;
		movespeed = STANDARD_SPEED;
		xMove = 0;
	}

	public void move() {
		x += xMove;
	}
	
	
	// Getter und Setter, um von außen drauf zugreifen zu können

	public float getXmove() {
		return xMove;
	}



	public void setXmove(float xmove) {
		this.xMove = xmove;
	}



	public int getHealth() {
		return health;
	}


	public void setHealth(int health) {
		this.health = health;
	}


	public float getMovespeed() {
		return movespeed;
	}


	public void setMovespeed(float movespeed) {
		this.movespeed = movespeed;
	}
}
