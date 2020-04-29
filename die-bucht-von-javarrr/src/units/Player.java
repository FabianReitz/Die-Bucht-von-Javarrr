package units;
import java.awt.Graphics;
import game.*;
import graphics.Assets;

public class Player extends Unit{
	
	private Game game;
	private int damage, kanonen, maxLeben;
	
	public Player(Game game, float x, float y) {
		super(x,y);
		this.game = game;
		damage = 1;
		kanonen = 1;
		maxLeben = 100;
	}

	@Override
	public void update() {
		
		if(game.getKeyManager().left && x > 1) {
			x -= 3;
			System.out.println("Left");
		}

		if(game.getKeyManager().right && x < 493) {
			x += 3;
			System.out.println("Right");
		}
		
	}

	@Override
	public void render(Graphics graphics) {
		graphics.drawImage(Assets.player, (int) x, (int) y, null);
	}
	
	

}
