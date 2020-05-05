package units;
import java.awt.Graphics;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

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
    public long shootCooldown = 800;
    private boolean reloading = false;
    Timer cooldown;

    public Player(Game game, float x, float y) {
        super(x,y, Unit.STANDARD_UNIT_WIDTH, Unit.STANDARD_UNIT_HEIGHT);
        this.game = game;
        damage = 1;
        kanonen = 1;
        maxLeben = 100;
        
        //Animation
    }
    
    
    public void shoot() {
		cooldown = new Timer();
		cooldown.schedule(new TimerTask() {
			@Override
			public void run() {
				if (game.getKeyManager().shoot) {
		    		System.out.println("FIRE");
		    		}
			}	
		}, 0, shootCooldown);
	}

    private void getInput() {
        xMove = 0;

        if (game.getKeyManager().left && x > 1) {
            xMove = -movespeed;

        }

        if (game.getKeyManager().right && x < 436) {
            xMove = movespeed;
        }
    }

    @Override
    public void update() {

        getInput();
        move();
        shoot();

    }

    @Override
    public void render(Graphics graphics) {
        graphics.drawImage(Assets.player, (int) x, (int) y, width, height, null);

    }
    
}