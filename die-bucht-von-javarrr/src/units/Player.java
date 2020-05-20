package units;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import game.*;
import graphics.Animation;
import graphics.Assets;


public class Player extends Unit{

    private Game game;
    private int damage, kanonen, maxLeben;
    private static float pXCoord;
    
    //Animation
    private Animation shoot;
    
    //Attacktimer   
    private long lastShootTimer, shootCooldown = 800,  shootTimer = shootCooldown;

    public Player(Game game, float x, float y) {
        super(x,y);
        this.game = game;
        damage = 1;
        kanonen = 1;
        maxLeben = 100;
        
        //Animation
    }
    
    private void shoot() {
    	shootTimer += System.currentTimeMillis()- lastShootTimer;
    	lastShootTimer = System.currentTimeMillis();
    	if(shootTimer < shootCooldown) return;
    	

    	shootTimer = 0;
	
    }

	
	public int getLeben() {
		return maxLeben;
	}


	// Schusscooldown
	public long reloadStart;								// Zeitpunkt des letzten Schusses
						// Zeit in ms, die vergehen muss, bis der Spieler wieder schießen kann.
	private boolean reloading = false;						// Boolean, die auf true steht, wenn der Spieler gerade nachlädt.

	// Methode zum Steuern der Schüsse des Spielers:
	public void shot() {
		
		// Wird die Leertaste gedrückt und der Spieler muss nicht nachladen...
		if (game.getKeyManager().statusTasten.contains(KeyEvent.VK_SPACE) && !reloading) {
			System.out.println("FIRE");						// ... gib einen Schuss ab.
			reloading = true;								// ... setze Nachladen auf true.
			reloadStart = System.currentTimeMillis();		// ... bestimme den Zeitpunkt des Nachladens.
		}
		
		// Muss der Spieler nachladen und es ist eine Zeit von mindestens "shootCooldown" vergangen...
		if (reloading && ((System.currentTimeMillis() - reloadStart) >= shootCooldown)) {
			reloading = false;								// ... setze Nachladen auf false.
		}
	}
	
	// Methode zum Erschaffen eines Projektils:
	public void shootProjectile() {
		
	}

	// Methode zum Bewegen des Spielers:
	private void getInput() {
		xMove = 0;											// Setze die relative Position auf 0.

		// Wird die Taste "A" gedrückt und die Position des Spielers ist innerhalb des Fensters...
		if (game.getKeyManager().statusTasten.contains(KeyEvent.VK_A) && x > 1) {
			xMove = -movespeed;								// ... bewege den Spieler nach links.

		}
		// Wird die Taste "D" gedrückt und die Position des Spielers ist innerhalb des Fensters...
		if (game.getKeyManager().statusTasten.contains(KeyEvent.VK_D) && x < 436) {
			xMove = movespeed;								// ... bewege den Spieler nach rechts.
		}
	}

	@Override
	public void update() {

		getInput();
		move();
		shot();
	}

	@Override
	public void render(Graphics graphics) {
		graphics.drawImage(Assets.player, (int) x, (int) y, Unit.STANDARD_UNIT_WIDTH, Unit.STANDARD_UNIT_HEIGHT, null);

	}


    }
    
