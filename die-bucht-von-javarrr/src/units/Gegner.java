package units;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import game.Game;
import graphics.Assets;



public class Gegner extends Unit{
	
	private int kanonen, damage, maxLeben;
	Timer bewegung = new Timer();

	private Game game;
	
	public Gegner(Game game, float x, float y) {
        super(x,y, Unit.STANDARD_UNIT_WIDTH, Unit.STANDARD_UNIT_HEIGHT);
        this.game = game;
        damage = 1;
        kanonen = 1;
        maxLeben = 100;
     
	}
	//Bewegung
	public void bewegung() {
		
		bewegung.schedule(new TimerTask() {
			@Override	
			public void run() {
			
			xMove = 0;
			xMove += getRichtung();
			
			if(x >= 440) {
				richtungLinks();
			}
			if(x <= 0) {
				richtungRechts();
			if(y == 600) {
				bewegung.cancel();
			}
			}
			}
			
		}, 0, 7);
		
	}
	
	
	//Gegner stirbt
	
	public void schaden() {
	maxLeben =- damage;
	if(maxLeben <= 0) {
		y = 600;
	}
	}
	@Override
	public void update() {
		bewegung();
		move();
		
	}
	@Override
	public void render(Graphics graphics) {
		graphics.drawImage(Assets.gegner, (int) x, (int) y, width, height, null);
		
	}

	

}
