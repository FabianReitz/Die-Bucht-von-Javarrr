package graphics;

import java.awt.Graphics;

import game.Game;

public class Background {

	//Animation
	private Animation wasser;
	
    private Game game;
    
    public Background(Game game) {
    	this.game = game;
    	
    	//Animation
    	wasser = new Animation(200, Assets.background);
    }
    
    public void update() {
    	
    	wasser.update();
    }
    
    public void render(Graphics graphics) {
    	
    	graphics.drawImage(wasser.getCurrentFrame(), (int) 0, (int) 0, null);
    }
    
    
}
