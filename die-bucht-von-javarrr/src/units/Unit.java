package units;
import units.Entity;

public abstract class Unit extends Entity {
	
	protected int health;
	
	public Unit(float x, float y) {
		super(x, y);
		health = 10;
	}
}
