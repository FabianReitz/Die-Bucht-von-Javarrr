package units;
import java.awt.Graphics;
import game.Game;
import graphics.Assets;

public class Player extends Unit{


    private Game game;

    
    //Animation
    private Animation shoot;
    
    //Attacktimer   
    private long lastShootTimer, shootCooldown = 800,  shootTimer = shootCooldown;

    public Player(Game game, float x, float y) {
        super(x,y, Unit.STANDARD_UNIT_WIDTH, Unit.STANDARD_UNIT_HEIGHT);
        this.game = game;
        damage = 1;
        kanonen = 1;
        health = 100;
        maxhealth = 100;
        
        //Animation
//        shoot = new Animation(500, Assets.shoot);
    }
    
    

//	private void shoot() {
//    	shootTimer += System.currentTimeMillis()- lastShootTimer;
//    	lastShootTimer = System.currentTimeMillis();
//    	if(shootTimer < shootCooldown) return;
//    	
//    	Shoot shoot = new Shoot((int) x,(int) y);
//    	shootTimer = 0;
//	}
	

	private int damage, kanonen, maxLeben;

	private void getInput() {
		xMove = 0;
		
		if(game.getKeyManager().left) {
			xMove = -movespeed;
			
		}
		if(game.getKeyManager().right) {
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
