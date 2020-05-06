package units;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import game.*;
import graphics.Animation;
import graphics.Assets;
import graphics.Shoot;

public class Player extends Unit{

    private Game game;
    private int damage, kanonen, maxLeben;
    
    //Animation
    private Animation shoot;
    
    //Attacktimer   
    private long lastShootTimer, shootCooldown = 800,  shootTimer = shootCooldown;

    public Player(Game game, float x, float y) {
        super(x,y, Unit.STANDARD_UNIT_WIDTH, Unit.STANDARD_UNIT_HEIGHT);
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
    	
    	Shoot shoot = new Shoot((int) x,(int) y);
    	shootTimer = 0;
	
    }

    private void getInput() {
        xMove = 0;

        if(game.getKeyManager().statusTasten.contains(KeyEvent.VK_A) && x > 1) {
            xMove = -movespeed;

        }

        if(game.getKeyManager().statusTasten.contains(KeyEvent.VK_D) && x < 436) {
            xMove = movespeed;
        }
    }

    @Override
    public void update() {

        getInput();
        move();

    }

    @Override
    public void render(Graphics graphics) {
        graphics.drawImage(Assets.player, (int) x, (int) y, width, height, null);

    }
    
}