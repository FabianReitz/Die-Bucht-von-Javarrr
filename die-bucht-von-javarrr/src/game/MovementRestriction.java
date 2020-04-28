package game;

public class MovementRestriction {

	private float value;
	
	public MovementRestriction(float value) {
		
		if (value < 0 || value > 512) {
			throw new IllegalArgumentException();
		} else {
			this.value = value;
		}
	}
	
	public float getMovementValue() {
		return value;
	}
	
	public void setMovementValue(float newValue) {
		if (newValue < 0 || newValue > 512) {
			throw new IllegalArgumentException();
		} else {
			value = newValue;
		}
	}
	
}
