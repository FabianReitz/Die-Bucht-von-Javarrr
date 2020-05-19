package states;


import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import game.Game;
import game.Musik;
import graphics.Assets;
import graphics.Background;
import units.Gegner;
import units.Player;
import units.Shoot;

public class GameState extends State{
	private Player player;
	private Background background;
    public long reloadStart;
    public long shootCooldown = 400;
    private boolean reloading = false;
    int i;
	private ArrayList<Gegner> enemy = new ArrayList<Gegner>();
	private ArrayList<Gegner> canShoot = new ArrayList<Gegner>();
	private ArrayList<Gegner> shooting = new ArrayList<Gegner>();
	private ArrayList<Gegner> cooldown = new ArrayList<Gegner>();
	
	
	
	public GameState(Game game) {
		super(game);
		player = new Player(game, 256, 450);
		background = new Background(game);
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
			Gegner gegner = new Gegner(game, 20 + 100 * j, 20 + 75 * i);
			enemy.add(gegner);
			canShoot.add(gegner);
			}
		}
	}

	@Override
	public void update() {
		player.update();
		background.update();
		shoot();
		hit();
		if(shooting.size() > 0) {
		removeShot();
		}
		for(Gegner gegner : enemy) {
		gegner.update();
		}
		for(Gegner gegner : shooting) {
		gegner.schuss.update();
		}
		}
	
	@Override
	public void render(Graphics graphics) {
		background.render(graphics);
		player.render(graphics);
		for(Gegner gegner : enemy) {
		gegner.render(graphics);
		}	
		for(Gegner gegner : shooting) {
		gegner.schuss.render(graphics);
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
	
	public void hit() {
		for(int z = 0;z < shooting.size();z++ ) {
			if((shooting.get(z).schuss.getSX() > player.getX()) && 
				(shooting.get(z).schuss.getSX() < (player.getX() + 72)) &&
				shooting.get(z).schuss.getSY() > (player.getY() )) {
			cooldown.add(shooting.remove(z));
		
			}
		}
		

	}
	
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

	 
}
