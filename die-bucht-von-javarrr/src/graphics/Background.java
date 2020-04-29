package graphics;

import java.awt.Graphics;

import game.Game;

public class Background {

	//Animation
	private Animation wasser;
	
    private Game game;
    
    public Background(Game game) {
    	this.game = game;
    	
    	//Animation initialisieren
    	wasser = new Animation(2000, Assets.background);
    }
    
    public void update() {
    	
    	// Ruft die Updatemethode aus der Klasse Animation auf
    	wasser.update();
    }
    
    public void render(Graphics graphics) {
    	
    	graphics.drawImage(wasser.getCurrentFrame(), (int) 0, (int) 0, null);
    }
    
    
}
