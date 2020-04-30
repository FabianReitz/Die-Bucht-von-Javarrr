package states;
import java.awt.Graphics;
import game.Game;
import game.Scoreboard;
import graphics.Assets;
import graphics.Background;
import units.Player;

public class GameState extends State{

	private Player player;
	private Background background1;
	
	public GameState(Game game) {
		super(game);
<<<<<<< Updated upstream
		player = new Player(game, 250, 250);
		background1 = new Background(game, 0, 0 );
=======
		player = new Player(game, 256, 450);
		background = new Background(game);		
		Scoreboard scoreboard = new Scoreboard(game, this);
>>>>>>> Stashed changes
	}

	@Override
	public void update() {
		
		player.update();
	}
	
	@Override
	public void render(Graphics graphics) {

		background1.render(graphics);
		graphics.drawImage(Assets.scoreboard, (int) 512, (int) 0, null);
		player.render(graphics);
		
	}

	public Player getPlayer() {
		return player;
	}
	
}
