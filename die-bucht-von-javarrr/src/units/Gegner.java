package units;


import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;
import game.Game;
import game.Window;
import graphics.Assets;
import states.GameState;



public class Gegner extends Unit{
	
	private int kanonen, damage, maxLeben;
	private Game game;
	public Shoot schuss;
	private double lastFire;
	private double cooldown;



	
	public Gegner(Game game, float x, float y) {
        super(x,y, Unit.STANDARD_UNIT_WIDTH, Unit.STANDARD_UNIT_HEIGHT);
        this.game = game;
        damage = 1;
        kanonen = 1;
        maxLeben = 100;
        cooldown = 0; 
        schuss = new Shoot(game, x+ 36, y + 40 );
     
        
	}
 
	//Bewegung
	public void bewegung() {
			xMove = 0;
			xMove += getRichtung();
			if(x >= 440) {
				richtungLinks();
			}
			if(x <= 0) {
				richtungRechts();
			}
			}

	@Override
	public void update() {
		bewegung();
		move();
		}
	
	public Shoot getSchuss() {
		return schuss;
	}

	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}

	@Override
	public void render(Graphics graphics) {
		graphics.drawImage(Assets.gegner, (int) x, (int) y, width, height, null);
		
	}

}
