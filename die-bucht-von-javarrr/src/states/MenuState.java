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
	
	//Die Variable wurde eingefuehrt, um zu pruefen, ob bereits ein gameState initalisiert wurde.
	private boolean gameStateActive = false;
	
	public MenuState(Game game, Fleet fleet, Player player) {
		super(game);	
		this.fleet = fleet;
		this.player = player;
		initButtons();
		background = new Background();
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
	
	
	
	/* Die im Window erstellten Buttons und die Menüpunkte erhalten hier ActionListener, um auf 
	 * Klicks zu reagieren.
	 */
	private void initButtons() {
		//Button Start im Menue
        game.getWindow().btStartSpiel.addActionListener( e -> {
        	if(gameStateActive) gameState.reset(); //besteht bereits ein GameState, wird dieser resettet
        	gameState = new GameState(game, menuState, fleet, player);
        	gameStateActive = true; //nachdem ein GameState erstellt wurde, wird die Boolean auf true gesetzt
        	game.setGameState(gameState);
        	State.setState(gameState);
        	game.getWindow().menuVisible(false);
        	game.getWindow().scoreboardVisible(true);
        });
        
        //Button Verlassen im Menue
        game.getWindow().btVerlassenSpiel.addActionListener( e -> {
        	System.exit(0);
        });
        
        // Spiel neu starten
 		game.getWindow().start.addActionListener(new ActionListener() {
 			public void actionPerformed(ActionEvent e) {
 				if (gameStateActive) gameState.reset();
 				gameStateActive = true;
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
}
