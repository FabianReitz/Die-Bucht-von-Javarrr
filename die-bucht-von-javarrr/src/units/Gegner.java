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
	private static ArrayList<Gegner> enemys = new ArrayList<Gegner>();
	public Shoot schuss;
	private double lastFire;
	private String enemy;
	private int width, height;
    public long reloadStart;
    public long shootCooldown = 400;
    private boolean reloading = false;
    private int i;
	private static ArrayList<Gegner> canShoot = new ArrayList<Gegner>();
	private static ArrayList<Gegner> shooting = new ArrayList<Gegner>();
	private static ArrayList<Gegner> cooldown = new ArrayList<Gegner>();

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
			xMove = 0;
			xMove += getRichtung();
			if(x >= 440) {
				richtungLinks();
			}
			if(x <= 0) {
				richtungRechts();
			}
			}
	
	public void kill() {
		if(this.health <= 0) {
			
		}
	}

	@Override
	public void update() {	
		bewegung();
		move();
		shoot();
	//	hit();
		if(shooting.size() > 0) {
		removeShot();
		}
		}
	
	  public void shoot() {
	        if (!reloading) {
	        	chooseEnemy();
	            fire();
	            if(cooldown.size() > 0) 
	            {
	            cooldown.get(0).schuss = new Shoot(game, cooldown.get(0).getX(), cooldown.get(0).getY());
	            canShoot.add(cooldown.get(0));
	            cooldown.remove(0);
	            }   
	            reloading = true;
	            reloadStart = System.currentTimeMillis();
	        }
	        if (reloading && ((System.currentTimeMillis() - reloadStart) >= shootCooldown)) {
	            reloading = false;
	        }
	    }	
		public void fire() {
		if(canShoot.size() > 0) {
		shooting.add(canShoot.get(i));
		canShoot.remove(i);
		}
		}
//		public void hit() {
//			for(int z = 0;z < shooting.size();z++ ) {
//				if(((shooting.get(z).schuss.getSX() > GameState.getPlayer().getX()) || 
//						(shooting.get(z).schuss.getSX() + 20) > GameState.getPlayer().getX()) && 
//					(shooting.get(z).schuss.getSX() < GameState.getPlayer().getX() + 72) &&
//					shooting.get(z).schuss.getSY() > GameState.getPlayer().getY() ) {
//				cooldown.add(shooting.remove(z));
//				
//				}
//			}
//		}
		
		 public void removeShot() {
			for(int j = 0; j < shooting.size(); j++) {
			if(shooting.get(j).schuss.getSY() > 512) {
				cooldown.add(shooting.remove(j));	
			}
			}
			}
		  
		 public void chooseEnemy() {
			 Random random = new Random();
			 i = random.nextInt(canShoot.size());
		 }
		public static ArrayList<Gegner> getCanShoot() {
			return canShoot;
		}
		public static ArrayList<Gegner> getShooting() {
			return shooting;
		}
		public static ArrayList<Gegner> getCooldown() {
			return cooldown;
		}

	public static ArrayList<Gegner> getEnemys(){
		return enemys;
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
