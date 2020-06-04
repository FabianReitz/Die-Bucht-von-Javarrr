package states;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import game.Game;
import graphics.Animation;
import graphics.Assets;
import graphics.Background;
import units.Fleet;
import units.Gegner;
import units.Player;

public class MenuState extends State{

	private GameState gameState;
	private Background background;
	private MenuState menuState;
	private Fleet fleet;
	private Player player;
	
	//Animation
	private int gameStateCount = 0;
	
	public MenuState(Game game, Fleet fleet, Player player) {
		super(game);	
		this.fleet = fleet;
		this.player = player;
		initButtons();
		background = new Background(game);
		menuState = this;
		
	}
	

	@Override
	public void update() {
		background.update();
	}

	@Override
	public void render(Graphics graphics) {
		background.render(graphics);
		graphics.drawImage(Assets.gameLogo, (int) 25, (int) 80, 700, 75, null);
	}
	
	
	
	
	private void initButtons() {
        game.getWindow().btStartSpiel.addActionListener( e -> {
        	if (gameStateCount > 0) gameState.reset();
        	gameState = new GameState(game, menuState, fleet, player);
        	gameStateCount += 1;
        	game.setGameState(gameState);
        	State.setState(gameState);
        	game.getWindow().menuVisible(false);
        	game.getWindow().scoreboardVisible(true);
        });
        
        game.getWindow().btVerlassenSpiel.addActionListener( e -> {
        	System.exit(0);
        });
        
        // Spiel neu starten
 		game.getWindow().start.addActionListener(new ActionListener() {
 			public void actionPerformed(ActionEvent e) {
 				if (gameStateCount > 0)	gameState.reset();
 				gameStateCount += 1;
 				gameState = new GameState(game, menuState, fleet, player);
 				game.setGameState(gameState);
 	        	State.setState(gameState);
 	        	game.getWindow().scoreboardVisible(true);
 			}
 		});

 		// Spiel verlassen
     		game.getWindow().exit.addActionListener(new ActionListener() {
     			public void actionPerformed(ActionEvent e) {
     				System.exit(0);
     			}
     		});
	}

	public GameState getGameState() {
		return gameState;
	}
}
