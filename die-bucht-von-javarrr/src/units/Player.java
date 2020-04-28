package units;
import java.awt.Graphics;
import game.Game;
import graphics.Assets;

public class Player extends Unit{
	
	private Game game;
	
	public Player(Game game, float x, float y) {
		super(x,y);
		this.game = game;
	}

	@Override
	public void update() {
		
		if(game.getKeyManager().left)
			x -= 3;
		if(game.getKeyManager().right)
			x += 3;
		
	}

	@Override
	public void render(Graphics graphics) {
		graphics.drawImage(Assets.player, (int) x, (int) y, null);
		
	}
	
	

}
