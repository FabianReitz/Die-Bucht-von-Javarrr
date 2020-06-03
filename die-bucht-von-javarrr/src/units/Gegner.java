package units;


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

	private int kanonen, damage, maxLeben;
	private Game game;

	private static ArrayList<Gegner> enemys = new ArrayList<Gegner>();
	private static ArrayList<Gegner> canShoot = new ArrayList<Gegner>();
	private static ArrayList<Gegner> shooting = new ArrayList<Gegner>();
	private ArrayList<Gegner> cooldown = new ArrayList<Gegner>();
	private double lastFire;
	private String enemy;
	private int width, height;
	private long reloadStart;
	private long shootCooldown = 800;
	private boolean reloading = true;
	private int chosen;
	private EnemyShot schuss;
	private boolean alive;

	public Gegner(Game game, float x, float y, String enemy) {
		super(x, y);
		this.game = game;
		this.enemy = enemy;
		this.width = enemyWidth(enemy);
		this.height = enemyHeight(enemy);
		damage = 1;
		kanonen = 1;
		maxLeben = 100;
		alive = true;
		enemys.add(this);
		canShoot.add(this);
	}
	


	@Override
	public void update() {
		bewegung();
		move();
		shoot();
		hit();
		removeShot();
	}
	

	// Nachdem die Zeit des Reloads abgelaufen ist wird ein neuer Gegner
		// ausgewaehlt, der dann
		// anfaengt zu schiessen.
		// Zudem wird der Gegner, welcher am laengsten nicht geschossen hat wieder zu
		// den Gegnern
		// hinzugefuegt, welche bereit sind zu schiessen
	public void shoot() {
        if (!reloading) {
            if (canShoot.size() > 0) {
                chooseEnemy();
            
                fire();
            }
            reloading = true;
            reloadStart = System.currentTimeMillis();
        }
        if (reloading && ((System.currentTimeMillis() - reloadStart) >= shootCooldown)) {
            reloading = false;
        }
            if (cooldown.size() > 0) {
                if(cooldown.get(0).alive == true) {
                canShoot.add(cooldown.get(0));
                cooldown.remove(0);
                }
                else
                {
                cooldown.remove(0);
                }
            }

    }	
		

		// Der ausgewaehlte Gegner bekommt einen neuen Schuss und wird zu den
		// schiessenden hinzugefuegt
		public void fire() {
			if (canShoot.size() > 0) {
				canShoot.get(chosen).schuss = new EnemyShot(game, canShoot.get(chosen).x, canShoot.get(chosen).y);
				shooting.add(canShoot.remove(chosen));
				
			}
		}

		// Wenn der Schuss mit dem Spieler kollidiert, wird dieser entfernt
		public void hit() {
			for (int z = 0; z < shooting.size(); z++) {
				if (((shooting.get(z).schuss.getSX() + 20) > GameState.getPlayer().x)
						&& (shooting.get(z).schuss.getSX() < GameState.getPlayer().x + 72)
						&& shooting.get(z).schuss.getSY() > GameState.getPlayer().y) {
					cooldown.add(shooting.remove(z));
					Statistics.setHealth(Statistics.getHealth() - 20); 
					game.getWindow().lblleben.setText("Leben: " + Statistics.getHealth() +"|"+ Statistics.getMaxHealth());
					if(Statistics.getHealth() <= 0) {
						System.out.println("you Loose");
					}
				}
			}
		}

		// Wenn der Schuss aus dem Bildschirm fliegt, wird dieser Entfernt und der
		// Gegner
		// wird in die Warteliste fuer den naechsten Schuss gesetzt
		public void removeShot() {
			for (int j = 0; j < shooting.size(); j++) {
				if (shooting.size() > 0 && (shooting.get(j).schuss.getSY() > 485)) {
					cooldown.add(shooting.remove(j));
				}
			}
		}

		// Es wird eine Nummer aus der Liste der gegner, welche bereit sind zu
		// schie�en, bestimmt
		public void chooseEnemy() {
			Random random = new Random();
			chosen = random.nextInt(canShoot.size());
		}
	


	@Override
	public void render(Graphics graphics) {
		if (enemy == "small")
			graphics.drawImage(Assets.enemySmall, (int) x, (int) y, width, height, null);
		else if (enemy == "medium")
			graphics.drawImage(Assets.enemyMedium, (int) x, (int) y, width, height, null);
		else if (enemy == "big")
			graphics.drawImage(Assets.enemyBig, (int) x, (int) y, width, height, null);
		else
			graphics.drawImage(Assets.enemyBoss, (int) x, (int) y, width, height, null);

	}

	// Hier wird je nach Gegnertyp die Breite/Hoehe ausgegeben, um die Grafik
	// skalieren zu koennen

	private int enemyWidth(String enemy) {
		int enemyWidth;
		if (enemy == "small")
			enemyWidth = 45;
		else if (enemy == "medium")
			enemyWidth = 60;
		else if (enemy == "big")
			enemyWidth = 85;
		else
			enemyWidth = 4;
		return enemyWidth;
	}

	private int enemyHeight(String enemy) {
		int enemyHeight;
		if (enemy == "small")
			enemyHeight = 35;
		else if (enemy == "medium")
			enemyHeight = 45;
		else if (enemy == "big")
			enemyHeight = 55;
		else
			enemyHeight = 4;
		return enemyHeight;
	}

	// Bewegung der Gegner
	// Richtungswechsel bei Kollision mit dem Rand
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

	
	public static ArrayList<Gegner> getShooting() {
		return shooting;
	}

	public static ArrayList<Gegner> getEnemys() {
		return enemys;
	}
	public static ArrayList<Gegner> getCanShoot(){
		return canShoot;
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
	public EnemyShot getSchuss() {
		return schuss;
	}
	

	
}
