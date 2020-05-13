package states;


import java.awt.Graphics;
import java.util.ArrayList;
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
    public long shootCooldown = 800;
    private boolean reloading = false;
    public float coord;
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
		for(Gegner gegner : enemy) {
		gegner.update();
		}
		for(Gegner gegner : shooting) {
		gegner.getSchuss().update();
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
		gegner.getSchuss().render(graphics);
		}
			
	}
	
	public void schuss() {
	if(canShoot.size() > 1) {
	int i = 0;
	canShoot.get(i).schuss = new Shoot(game,canShoot.get(i).getX() + 36, canShoot.get(i).getY() + 40 );
	shooting.add(canShoot.get(i));
	canShoot.remove(i);
	}
	}
	
	 public void schieﬂen() {
		for(int j = 0; j <= shooting.size(); j++) {
		shooting.get(j).schuss.feuer();
		if(shooting.get(j).schuss.getSY() > 512) {
			cooldown.add(shooting.get(j));
			shooting.remove(j);
		}
		}
	  }
	    public void shoot() {
	        if (!reloading) {
	            schuss();
	            if(cooldown.size() > 0) 
	            {
	            canShoot.add(cooldown.get(1));
	            cooldown.remove(1);
	            	System.out.println(cooldown.get(0));
	            }   
	            reloading = true;
	            reloadStart = System.currentTimeMillis();
	        }
	        if (reloading && ((System.currentTimeMillis() - reloadStart) >= shootCooldown)) {
	            reloading = false;
	        }
	    
	  

			
			
	    	
	    }
	 
}
