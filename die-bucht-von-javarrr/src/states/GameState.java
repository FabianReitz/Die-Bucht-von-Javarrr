package states;


import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

import game.Game;
import game.Statistics;
import graphics.Background;
import units.EnemyShot;
import units.Gegner;
import units.Player;
import units.PlayerShot;

public class GameState extends State{

	private Player player;
	private Background background;
    public long reloadStart, shootCooldown = 400;
    private boolean reloading = false;
    private int i;
	private ArrayList<Gegner> enemy = new ArrayList<Gegner>();
	private ArrayList<Gegner> canShoot = new ArrayList<Gegner>();
	private ArrayList<Gegner> shooting = new ArrayList<Gegner>();
	private ArrayList<Gegner> cooldown = new ArrayList<Gegner>();
	
	private boolean newLevel = false;
	


//	public GameState(Game game) {
//		super(game);
//		player = new Player(game, 256, 450);
//		background = new Background(game);
//
//		for (int i = 0; i < 4; i++) {
//			for (int j = 0; j < 4; j++) {
//			Gegner gegner = new Gegner(game, 20 + 100 * j, 20 + 75 * i, "medium");
//			enemy.add(gegner);
//			canShoot.add(gegner);
//			 
//			}
//		}
//		
//
//	}

	public GameState(Game game) {
		super(game);
		player = new Player(game, 256, 450);
		background = new Background(game);
		initBreak();
		initLevel();
	}
	
	

//	public Player getPlayer() {
//		return player;
//	}
//
//
//	@Override
//	public void update() {
//		player.update();
//		background.update();
//
//		shoot();
//		hit();
//		if(shooting.size() > 0) {
//		removeShot();
//		}
//		for(Gegner gegner : enemy) {
//		gegner.update();
//		}
//		for(Gegner gegner : shooting) {
//		gegner.schuss.update();
//		}
//		
//		for (PlayerShot playerShot : Player.getFlyingShots()) {
//			playerShot.update();
//		}
//		
//		
//	}
//	
//
//	@Override
//	public void render(Graphics graphics) {
//		background.render(graphics);
//		player.render(graphics);
//		if (player.playerShot.sichtbar) player.playerShot.render(graphics);
//
//		for(Gegner gegner : enemy) {
//			gegner.render(graphics);
//		}	
//		for(Gegner gegner : shooting) {
//			gegner.schuss.render(graphics);
//		}
//	
//		for (PlayerShot playerShot : Player.getFlyingShots()) {
//			playerShot.render(graphics);
//		}
//		
//			
//	}
//    public void shoot() {
//        if (!reloading) {
//        	chooseEnemy();
//            fire();
//            if(cooldown.size() > 0) 
//            {
//            cooldown.get(0).schuss = new EnemyShot(game, cooldown.get(0).getX(), cooldown.get(0).getY());
//            canShoot.add(cooldown.get(0));
//            cooldown.remove(0);
//            }   
//            reloading = true;
//            reloadStart = System.currentTimeMillis();
//        }
//        if (reloading && ((System.currentTimeMillis() - reloadStart) >= shootCooldown)) {
//            reloading = false;
//        }
//    }	
//	public void fire() {
//	if(canShoot.size() > 0) {
//	shooting.add(canShoot.get(i));
//	canShoot.remove(i);
//	}
//	}
//	public void hit() {
//		for(int z = 0;z < shooting.size();z++ ) {
//			if(((shooting.get(z).schuss.getSX() > player.getX()) || 
//					(shooting.get(z).schuss.getSX() + 20) > player.getX()) && 
//				(shooting.get(z).schuss.getSX() < (player.getX() + 72)) &&
//				shooting.get(z).schuss.getSY() > (player.getY() )) {
//			cooldown.add(shooting.remove(z));
//		
//			}
//		}
//	}
//	
//	 public void removeShot() {
//		for(int j = 0; j < shooting.size(); j++) {
//		if(shooting.get(j).schuss.getSY() > 512) {
//			cooldown.add(shooting.remove(j));	
//		}
//		}
//		}
//	  
//	 public void chooseEnemy() {
//		 Random random = new Random();
//		 i = random.nextInt(canShoot.size());
//	 }
//
//
//
//	 
//}
	
	private void initBreak() {
		game.getWindow().btnBreak.addActionListener( e -> {
			System.out.print(Statistics.getLevelNo());
			initLevel();
			 game.getWindow().breakInvisible();
			 game.getWindow().scoreboardSichtbar();
	
	        });
	}
	

	

	public Player getPlayer() {
		return player;
	}


	@Override
	public void update() {
		player.update();
		background.update();
		nextLevel();		
		hit();

		if (enemy.size() > 0) {
			shoot();
			for(Gegner gegner : enemy) {
				gegner.update();
				if (gegner.getHealth() <= 0)
				{
					enemy.remove(enemy.indexOf(gegner));					
				}
			}
		}
		
		if (shooting.size() > 0) {
			removeShot();
			for(Gegner gegner : shooting) {
				gegner.schuss.update();
			}
		}
		
		if (Player.getFlyingShots().size() > 0) {
			for (PlayerShot playerShot : Player.getFlyingShots()) {
				playerShot.update();
			}
		}
		
	}
	

	@Override
	public void render(Graphics graphics) {
		background.render(graphics);
		player.render(graphics);
		
		if (player.playerShot.sichtbar) player.playerShot.render(graphics);

		if (enemy.size() > 0) {
			for(Gegner gegner : enemy) {
				gegner.render(graphics);
			}	
		}
		
		if (shooting.size() > 0) {
			for(Gegner gegner : shooting) {
				gegner.schuss.render(graphics);
			}
		}
		
		if (Player.getFlyingShots().size() > 0) {
			for (PlayerShot playerShot : Player.getFlyingShots()) {
				playerShot.render(graphics);
			}
		}
	}
		
	
    public void shoot() {
        if (!reloading) {
        	chooseEnemy();
            fire();
            if(cooldown.size() > 0) 
            {
            cooldown.get(0).schuss = new EnemyShot(game, cooldown.get(0).getX(), cooldown.get(0).getY());
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
			if(((shooting.get(z).schuss.getSX() > player.getX()) || 
					(shooting.get(z).schuss.getSX() + 20) > player.getX()) && 
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

	 private void nextLevel() {

			 if (enemy.size() == 0) {
				if(!newLevel) {
				 Statistics.setLevelNo((Statistics.getLevelNo() + 1));
				 game.getWindow().lbllevel.setText("Level: " + Statistics.getLevelNo() +"|10");
				 levelDone(); 
				 newLevel= true;
				}
		 }
	 }
	 
	 private void levelDone() {
		 game.getWindow().scoreboardUnsichtbar(); 
		 game.getWindow().breakVisible();	 
	 }
	 
	 
	 private void initLevel() {
			if (Statistics.getLevelNo() == 1) {
				System.out.print(Statistics.getLevelNo());
				for (int i = 0; i < 4; i++) {
					for (int j = 0; j < 4; j++) {
					Gegner gegner = new Gegner(game, 20 + 100 * j, 20 + 75 * i, "small");
					enemy.add(gegner);
					canShoot.add(gegner);
					}
				}
			}
			
			if (Statistics.getLevelNo() == 2) {
				for (int i = 0; i < 4; i++) {
					for (int j = 0; j < 4; j++) {
					Gegner gegner = new Gegner(game, 20 + 100 * j, 20 + 75 * i, "medium");
					enemy.add(gegner);
					canShoot.add(gegner);
					}
				}
			}
			
			if (Statistics.getLevelNo() == 3) {
				for (int i = 0; i < 4; i++) {
					for (int j = 0; j < 4; j++) {
					Gegner gegner = new Gegner(game, 20 + 100 * j, 20 + 75 * i, "big");
					enemy.add(gegner);
					canShoot.add(gegner);
					}
				}
			}
			newLevel = false;
	 }


	 
}
