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
	private double schussY;
	private ArrayList<Gegner> cd = new ArrayList<Gegner>();
	private ArrayList<Gegner> amSchiessen = new ArrayList<Gegner>();
	private static ArrayList<Gegner> amSchiessen2 = new ArrayList<Gegner>();
	
	public Gegner(Game game, float x, float y) {
        super(x,y, Unit.STANDARD_UNIT_WIDTH, Unit.STANDARD_UNIT_HEIGHT);
        this.game = game;
        damage = 1;
        kanonen = 1;
        maxLeben = 100;
        cooldown = 0; 
        schuss = schuss;
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
//		schuss();

		if(amSchiessen.size() > 1) {
//		schieﬂen();
		}

	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}

	
	public Shoot getSchuss() {
		return schuss;
	}

	@Override
	public void render(Graphics graphics) {
		graphics.drawImage(Assets.gegner, (int) x, (int) y, width, height, null);
		
	}

}
