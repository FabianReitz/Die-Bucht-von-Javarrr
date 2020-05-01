package units;

import java.awt.Graphics;
import java.util.Timer;
import java.util.TimerTask;

import game.Game;
import graphics.Assets;



public class Gegner extends Unit{
	
	private int kanonen, damage, maxLeben;
	private Game game;
	Timer bewegung;
	public Gegner(Game game, float x, float y) {
        super(x,y, Unit.STANDARD_UNIT_WIDTH, Unit.STANDARD_UNIT_HEIGHT);
        this.game = game;
        damage = 1;
        kanonen = 1;
        maxLeben = 100;
	}
	//Bewegung
	public void bewegung() {
		bewegung = new Timer();
		bewegung.schedule(new TimerTask() {
			@Override
			public void run() {
			xMove = 0;
			
			xMove =+ movespeed;
			if(x > 510) {
				x = 1;
			}
			}	
		}, 0, 7);
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
