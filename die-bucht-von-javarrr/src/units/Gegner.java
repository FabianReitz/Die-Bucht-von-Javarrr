package units;


import java.awt.Graphics;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;
import game.Game;
import game.Window;
import graphics.Assets;



public class Gegner extends Unit{
	
	private int kanonen, damage, maxLeben;
	private Game game;
	private static float xCoord;
	private static float yCoord;
	private Shoot schuss;
	Timer pause = new Timer();	
	
	
	
	public Gegner(Game game, float x, float y) {
        super(x,y, Unit.STANDARD_UNIT_WIDTH, Unit.STANDARD_UNIT_HEIGHT);
        this.game = game;
        damage = 1;
        kanonen = 1;
        maxLeben = 100;
        schuss = new Shoot(game, x, y);
       

	}
	//Bewegung
	public void bewegung() {
			xMove = 0;
			xMove += getRichtung();
			if(x >= 440) {
				richtungLinks();
			}
			if(x <= 5) {
				richtungRechts();
			}
			}
	
	public void CoordUpdate() {
		xCoord = x;
		yCoord = y;
	}	
	public static float getXCoord() {
		return xCoord;
	}
	public static float getYCoord() {
		return yCoord;
	}

	public Shoot getSchuss() {
		return schuss;
	}

	@Override
	public void update() {
		CoordUpdate();
		bewegung();
		move();	
		schuss.schuss();
	
	}
	@Override
	public void render(Graphics graphics) {
		graphics.drawImage(Assets.gegner, (int) x, (int) y, width, height, null);
		
	}

}
