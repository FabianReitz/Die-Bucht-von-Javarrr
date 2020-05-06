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
	private String enemy;
	private int width, height;
	
	public Gegner(Game game, float x, float y, String enemy) {
        super(x,y);
        this.game = game;
        this.enemy = enemy;
        this.width = enemyWidth(enemy);
        this.height = enemyHeight(enemy);
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
		if (enemy == "small") graphics.drawImage(Assets.enemySmall, (int) x, (int) y, width, height, null);
		else if (enemy == "medium") graphics.drawImage(Assets.enemyMedium, (int) x, (int) y, width, height, null);
		else if(enemy == "big") graphics.drawImage(Assets.enemyBig, (int) x, (int) y, width, height, null);
		else graphics.drawImage(Assets.enemyBoss, (int) x, (int) y, width, height, null);
		
	}
	
	//Hier wird je nach Gegnertyp die Breite/Hoehe ausgegeben, um die Grafik skalieren zu koennen
	
	private int enemyWidth(String enemy) {
		int enemyWidth;
		if (enemy == "small") enemyWidth = 45;
		else if (enemy == "medium")  enemyWidth = 60;
		else if(enemy == "big") enemyWidth = 85;
		else enemyWidth = 4;
		return enemyWidth;
	}
	
	private int enemyHeight(String enemy) {
		int enemyHeight;
		if (enemy == "small") enemyHeight = 35;
		else if (enemy == "medium")  enemyHeight = 45;
		else if(enemy == "big") enemyHeight = 55;
		else enemyHeight = 4;
		return enemyHeight;
	}

	

}
