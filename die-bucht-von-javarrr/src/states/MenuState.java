package states;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import game.Game;
import game.Musik;
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
		Musik.music("assets/Musik/Musik.wav","ja");
		
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
        	game.getWindow().scoreboardSichtbar();
        });
        
        game.getWindow().btVerlassenSpiel.addActionListener( e -> {
        	System.exit(0);
        });
        
        // Menu: Spiel neu starten
 		game.getWindow().start.addActionListener(new ActionListener() {
 			public void actionPerformed(ActionEvent e) {
 				gameState = new GameState(game);
 	        	State.setState(gameState);
 	        	game.getWindow().scoreboardSichtbar();
 	        	
 			}
 		});

 		// Menu: Spiel verlassen
     		game.getWindow().exit.addActionListener(new ActionListener() {
     			public void actionPerformed(ActionEvent e) {
     				System.exit(0);
     			}
     		});
     		
     	// Menu: Einstellungen
     		game.getWindow().settings.addActionListener(new ActionListener() {
     			public void actionPerformed(ActionEvent e) {
     				
     			}
     		});
     		
     	// Menu: Musik an
     		 game.getWindow().btMusikAn.addActionListener( e -> {
     			Musik.restart();
     			game.getWindow().btMusikAn.setVisible(false);
     			game.getWindow().btMusikAus.setVisible(true);
     		 });
     		 
     	// Menu: Musik aus
     		 game.getWindow().btMusikAus.addActionListener( e -> {
     			 Musik.stop();
     			 
     			game.getWindow().btMusikAn.setVisible(true);
     			game.getWindow().btMusikAus.setVisible(false);
     		 });
     		 
     		 
     	// Menu: Musik lauter
     		 game.getWindow().btMusikLauter.addActionListener( e -> {
     			 Musik.lauter();
     		 });
     		 
     		 
     	// Menu: Musik leiser
     		 game.getWindow().btMusikLeiser.addActionListener( e -> {
     			Musik.leiser();
     		 });
     		
     		
	}

}