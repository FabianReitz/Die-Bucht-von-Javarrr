package units;

// TEST
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

	public EnemyShot schuss;
	private double lastFire;
	private double cooldown;
	
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
        cooldown = 0; 
        schuss = new EnemyShot(game, x+ 36, y + 40 );
     
        
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
	
	public EnemyShot getSchuss() {
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
