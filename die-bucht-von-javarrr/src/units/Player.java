package units;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import game.*;
import graphics.Animation;
import graphics.Assets;


public class Player extends Unit{

    private Game game;
    private int damage, kanonen, maxLeben;
    private static float pXCoord;
    
    
    
    // Schusscooldown
 	public long reloadStart;								// Zeitpunkt des letzten Schusses  
    private long shootCooldown = 800;						// Zeit in ms, die vergehen muss, bis der Spieler wieder schiessen kann.
    private boolean reloading = false;						// Boolean, die auf true steht, wenn der Spieler gerade nachlaedt.
    
	private static ArrayList<PlayerShot> flyingShots = new ArrayList<PlayerShot>();
    

	public PlayerShot playerShot;
    
    public Player(Game game, float x, float y) {
        super(x,y);
        this.game = game;
        damage = 1;
        kanonen = 1;
        maxLeben = 100;
    }
    
    
    
	public int getLeben() {
		return maxLeben;
	}
	
	public static ArrayList<PlayerShot> getFlyingShots() {
		return flyingShots;
	}

	// Methode zum Steuern der Schüsse des Spielers:
	public void shot() {
		
		// Wird die Leertaste gedrückt und der Spieler muss nicht nachladen...
		if (game.getKeyManager().statusTasten.contains(KeyEvent.VK_SPACE) && !reloading) {
			System.out.println("FIRE");						// ... gib einen Schuss ab.
			playerShot = new PlayerShot(game, x, y);
			flyingShots.add(playerShot);
			reloading = true;								// ... setze Nachladen auf true.
			reloadStart = System.currentTimeMillis();		// ... bestimme den Zeitpunkt des Nachladens.
		}
		
		// Muss der Spieler nachladen und es ist eine Zeit von mindestens "shootCooldown" vergangen...
		if (reloading && ((System.currentTimeMillis() - reloadStart) >= shootCooldown)) {
			reloading = false;								// ... setze Nachladen auf false.
		}
	}
	

	// Methode zum Bewegen des Spielers:
	private void getInput() {
		xMove = 0;											// Setze die relative Position auf 0.

		// Wird die Taste "A" gedrueckt und die Position des Spielers ist innerhalb des Fensters...
		if (game.getKeyManager().statusTasten.contains(KeyEvent.VK_A) && x > 1) {
			xMove = -movespeed;								// ... bewege den Spieler nach links.

		}
		// Wird die Taste "D" gedrückt und die Position des Spielers ist innerhalb des Fensters...
		if (game.getKeyManager().statusTasten.contains(KeyEvent.VK_D) && x < 436) {
			xMove = movespeed;								// ... bewege den Spieler nach rechts.
		}
	}
	//Es wird ueberpueft, ob ein Gegner getroffen wurde
	//Ist das der Fall wird diesem leben angezogen
	//Falls er stirbt wird dieser entfernt
	public void hit() {
		for (int hE = 0; hE < Player.getFlyingShots().size(); hE++) {
			for (int e = 0; e < Gegner.getEnemys().size(); e++) {
				if (Player.getFlyingShots().size() > 0) {
				if (((Player.getFlyingShots().get(hE).getSX() + 20) > Gegner.getEnemys().get(e).getX()) &&
						(Player.getFlyingShots().get(hE).getSX() < (Gegner.getEnemys().get(e).getX() + Gegner.getEnemys().get(e).getWidth())) &&
						Player.getFlyingShots().get(hE).getSY() < (Gegner.getEnemys().get(e).getY() )) 
				{
					Player.getFlyingShots().remove(hE);
			
					game.getWindow().lblScoreAnzeige.setText("" + Statistics.getScore());
					Gegner.getEnemys().get(e).setHealth(Gegner.getEnemys().get(e).getHealth() - Statistics.getDamage());
					if(Gegner.getEnemys().get(e).getHealth() <= 0){
						Gegner.getEnemys().get(e).setAlive(false);
						Gegner.getEnemys().remove(e);
						Statistics.setScore(Statistics.getScore() + Gegner.getScorePoints());
						game.getWindow().lblScoreAnzeige.setText("" + Statistics.getScore());
					}
				}
			}
		}
		}
	}
	
	public void despawnShot() {
		for (int hE = 0; hE < Player.getFlyingShots().size(); hE++) {
			if(Player.getFlyingShots().get(hE).getSY() <= 0)
				Player.getFlyingShots().remove(hE);
		}
	}

	@Override
	public void update() {
		getInput();
		move();
		shot();
		hit();
		despawnShot();
		
	}

	@Override
	public void render(Graphics graphics) {
		graphics.drawImage(Assets.player, (int) x, (int) y, Unit.STANDARD_UNIT_WIDTH, Unit.STANDARD_UNIT_HEIGHT, null);
		
	}


    }
    
