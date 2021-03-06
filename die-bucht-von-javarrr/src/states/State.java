package states;
import java.awt.Graphics;
import game.Game;

public abstract class State {
	
	private static State currentState = null;
	
	
	// Getter und Setter der Status 
	public static void setState(State state) {
		currentState = state;
	}

	public static State getState() {
		return currentState;
	}
	
	protected Game game;
	
	public State(Game game) {
		this.game = game;
	}
	
	public abstract void update();
	
	public abstract void render(Graphics graphics);
}
