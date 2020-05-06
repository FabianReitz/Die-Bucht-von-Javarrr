package states;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import game.Game;
import graphics.Animation;
import graphics.Background;

public class MenuState extends State{

	private GameState gameState;
	private Background background;
	
	//Animation
	private Animation wasser;
	
	
	public MenuState(Game game) {
		super(game);	
		initButtons();
		background = new Background(game);
	}
	

	@Override
	public void update() {
		background.update();
	}

	@Override
	public void render(Graphics graphics) {
		background.render(graphics);
		
	}
	
	
	private void initButtons() {
        game.getWindow().btStartSpiel.addActionListener( e -> {
        	gameState = new GameState(game);
        	State.setState(gameState);
        	game.getWindow().menuUnsichtbar();
        });
        
        game.getWindow().btVerlassenSpiel.addActionListener( e -> {
        	System.exit(0);
        });
        
        // Spiel neu starten
 		game.getWindow().start.addActionListener(new ActionListener() {
 			public void actionPerformed(ActionEvent e) {
 				gameState = new GameState(game);
 	        	State.setState(gameState);
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
