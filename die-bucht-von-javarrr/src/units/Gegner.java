package units;

// TEST
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;
import game.Game;
import game.Statistics;
import game.Window;
import graphics.Assets;
import states.GameState;
import units.EnemyShot;

public class Gegner extends Unit {

	private Game game;
	private String enemy;
	private int width, height;

	private int scorePoints;
	private EnemyShot schuss;

	private boolean alive;


	public Gegner(Game game, float x, float y, String enemy) {
		super(x, y);
		this.game = game;
		this.enemy = enemy;
		this.width = enemyWidth();
		this.height = enemyHeight();
		health = enemyHealth();
		damage = enemyDamage();
		scorePoints = enemyScorePoints();
		alive = true;
	}

	@Override
	public void update() {
		bewegung();
		move();
	}

	
	// je nach Gegnertyp wird ein anderes Bild gerendert.

	@Override
	public void render(Graphics graphics) {
		if (enemy == "small")
			graphics.drawImage(Assets.enemySmall, (int) x, (int) y, width, height, null);
		else if (enemy == "medium")
			graphics.drawImage(Assets.enemyMedium, (int) x, (int) y, width, height, null);
		else if (enemy == "big")
			graphics.drawImage(Assets.enemyBig, (int) x, (int) y, width, height, null);
		else if (enemy == "boss")
			graphics.drawImage(Assets.enemyBoss, (int) x, (int) y, width, height, null);

	}

	/* Hier wird je nach Gegnertyp die Breite/Hoehe ausgegeben, um die Grafik
	 skalieren zu koennen. Es werden auch Schaden, Leben und die Punkte, die bei Abschuss erzielt werden, je nach Gegnertyp vergeben */

	private int enemyWidth() {
		int enemyWidth = 0;
		if (enemy == "small")
			enemyWidth = 45;
		else if (enemy == "medium")
			enemyWidth = 60;
		else if (enemy == "big")
			enemyWidth = 85;
		else if (enemy == "boss")
			enemyWidth = 160;
		return enemyWidth;
	}

	private int enemyHeight() {
		int enemyHeight = 0;
		if (enemy == "small")
			enemyHeight = 35;
		else if (enemy == "medium")
			enemyHeight = 45;
		else if (enemy == "big")
			enemyHeight = 55;
		else if (enemy == "boss")
			enemyHeight = 75;
		return enemyHeight;
	}
	
	private int enemyHealth() {
		int enemyHealth = 0;
		if (enemy == "small")
			enemyHealth = 10;
		else if (enemy == "medium")
			enemyHealth = 20;
		else if (enemy == "big")
			enemyHealth = 50;
		else if (enemy == "boss")
			enemyHealth = 100;
		return enemyHealth;
	}
	
	private int enemyDamage() {
		int enemyDamage = 0;
		if (enemy == "small")
			enemyDamage = 5;
		else if (enemy == "medium")
			enemyDamage = 10;
		else if (enemy == "big")
			enemyDamage = 20;
		else if (enemy == "boss")
			enemyDamage = 35;
		return enemyDamage;
	}
	
	private int enemyScorePoints() {
		if (enemy == "small")
			return 2;
		else if (enemy == "medium")
			return 5;
		else if (enemy == "big")
			return 10;
		else if (enemy == "boss")
			return 50;
		return 0;
	}

	/* Bewegung der Gegner
	 Richtungswechsel bei Kollision mit dem Rand */
	
	public void bewegung() {
		xMove = 0;
		xMove += getRichtung();
		if (x >= 440) {
			richtungLinks();
		}
		if (x <= 0) {
			richtungRechts();
		}
	}


	public EnemyShot getSchuss() {
		return schuss;

	}
	
	public void setSchuss(EnemyShot schuss) {
		this.schuss = schuss;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	public int getWidth() {
		return width;
	}

	public boolean getAlive() {
		return alive;
	}
	
	public int getScorePoints() {
		return scorePoints;
	}	
}
