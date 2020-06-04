package units;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import game.*;
import graphics.Assets;


public class Player extends Unit{

    private Game game;
    
    
    // Schusscooldown
 	private long reloadStart;								// Zeitpunkt des letzten Schusses  					
    private boolean reloading = false;						// Boolean, die auf true steht, wenn der Spieler gerade nachlaedt.
    
	private ArrayList<PlayerShot> flyingShots = new ArrayList<PlayerShot>();
    

	private PlayerShot playerShot;
    
    public Player(Game game, float x, float y) {
        super(x,y);
        this.game = game;
    }

	
	public ArrayList<PlayerShot> getFlyingShots() {
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
		if (reloading && ((System.currentTimeMillis() - reloadStart) >= (1000 -(game.getStatistics().getAttackSpeed() * 1000)))) { 
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
		for (int hE = 0; hE < flyingShots.size(); hE++) {
			for (int e = 0; e < game.getFleet().getEnemys().size(); e++) {
				if (flyingShots.size() > 0) {

				if (((flyingShots.get(hE).getSX() + 20) > game.getFleet().getEnemys().get(e).getX()) &&
						(flyingShots.get(hE).getSX() < (game.getFleet().getEnemys().get(e).getX() + game.getFleet().getEnemys().get(e).getWidth())) &&
						flyingShots.get(hE).getSY() < (game.getFleet().getEnemys().get(e).getY() )) 
				{
					flyingShots.remove(hE);
			
					game.getWindow().lblScoreAnzeige.setText("" + game.getStatistics().getScore());
					game.getFleet().getEnemys().get(e).setHealth(game.getFleet().getEnemys().get(e).getHealth() - game.getStatistics().getDamage());
					if(game.getFleet().getEnemys().get(e).getHealth() <= 0){
						game.getFleet().getEnemys().get(e).setAlive(false);
						game.getStatistics().setScore(game.getStatistics().getScore() + game.getFleet().getEnemys().get(e).getScorePoints());
						game.getFleet().getEnemys().remove(e);
						game.getWindow().lblScoreAnzeige.setText("" + game.getStatistics().getScore());

					}
				}
			}
		}
		}
	}
	
	public void despawnShot() {
		for (int hE = 0; hE < flyingShots.size(); hE++) {
			if(flyingShots.get(hE).getSY() <= 0)
				flyingShots.remove(hE);
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
		graphics.drawImage(Assets.player, (int) x, (int) y, 72, 44, null);
		
	}


    }
    
