package states;


import java.awt.Graphics;

import game.Game;
import game.Statistics;
import graphics.Assets;
import graphics.Background;
import levels.Level_1;
import levels.Level_2;
import levels.Level_3;
import levels.Level_4;
import levels.Level_5;
import levels.Level_6;
import levels.Level_7;
import units.Gegner;
import units.Player;
import units.PlayerShot;



public class GameState extends State{

	
	private boolean levelIsActive = false;
	private boolean levelDone = false;
	private boolean gameWon = false;
	private static Player player;
	private Background background;

	private Level_1 level1;
	private Level_2 level2;
	private Level_3 level3;
	private Level_4 level4;
	private Level_5 level5;
	private Level_6 level6;
	private Level_7 level7;
	
	
	public GameState(Game game) {
		super(game);
		player = new Player(game, 256, 450);
		background = new Background(game);
		initLevel();
		initButtons();
		
		}
		

	public static Player getPlayer() {
		return player;
	}

	@Override
	public void update() {
		if (Gegner.getEnemys().size() == 0) {
			levelDone = true;
			levelIsActive = false; 	
		}
		if(levelDone) {
			levelDone();
		}
		
		player.update();
		background.update();
//		checkLevel();
		if (levelIsActive) {
			if(Statistics.getLevelNo() == 1) level1.update();
			else if(Statistics.getLevelNo() == 2) level2.update();
			else if(Statistics.getLevelNo() == 3) level3.update();
			else if(Statistics.getLevelNo() == 4) level4.update();
			else if(Statistics.getLevelNo() == 5) level5.update();
			else if(Statistics.getLevelNo() == 6) level6.update();
			else if(Statistics.getLevelNo() == 7) level7.update();
		}
		
		
		
	}
	
	@Override
	public void render(Graphics graphics) {
		background.render(graphics);
		player.render(graphics);
		
		if(levelIsActive) {
			if(Statistics.getLevelNo() == 1) level1.render(graphics);
			else if(Statistics.getLevelNo() == 2) level2.render(graphics);
			else if(Statistics.getLevelNo() == 3) level3.render(graphics);
			else if(Statistics.getLevelNo() == 4) level4.render(graphics);
			else if(Statistics.getLevelNo() == 5) level5.render(graphics);
			else if(Statistics.getLevelNo() == 6) level6.render(graphics);
			else if(Statistics.getLevelNo() == 7) level7.render(graphics);
		}
		
		if (levelDone) {
			graphics.drawImage(Assets.levelDone, (int) 80, (int) 80, 256, 84, null);
		}

	}
	
	
	// Wenn derzeit kein Level aktiv ist, wird die Nummer des Levels hochgesetzt und ein neues Level wird initialisiert
	private void checkLevel() {
		if (!levelIsActive) {
			 Statistics.setLevelNo((Statistics.getLevelNo() + 1));
			 game.getWindow().lbllevel.setText("Level: " + Statistics.getLevelNo() +"|10");
			 initLevel(); 
			}
	 }

	

private void levelDone() {
	if (Statistics.getLevelNo() == 10) gameWon();
	else {
		game.getWindow().boosterSichtbar();
	}
}


private void initLevel() {
	

		if (Statistics.getLevelNo() == 1) {
			level1 = new Level_1(game);
		}
		if (Statistics.getLevelNo() == 2) {
			level2 = new Level_2(game);
		}
		if (Statistics.getLevelNo() == 3) {
			level3= new Level_3(game);
		}
		if (Statistics.getLevelNo() == 4) {
			level4= new Level_4(game);
		}
		if (Statistics.getLevelNo() == 5) {
			level5= new Level_5(game);
		}
		if (Statistics.getLevelNo() == 6) {
			level6= new Level_6(game);
		}
		if (Statistics.getLevelNo() == 7) {
			level7= new Level_7(game);
		}
		
		for (int hE = 0; hE < Player.getFlyingShots().size(); hE++) {
			Player.getFlyingShots().remove(hE);
		}
		for (int j = 0; j < Gegner.getShooting().size(); j++) {
			Gegner.getShooting().remove(j);
		}

		levelIsActive = true;
		levelDone = false;
}

private void initButtons() {
	
	//Funktion der Booster-Buttons
  game.getWindow().btschaden.addActionListener( e -> {
  	Statistics.setDamage(Statistics.getDamage() + 1);
  	game.getWindow().lblschaden.setText("Schaden: " + Statistics.getDamage());
  	game.getWindow().boosterUnsichtbar();
  	checkLevel();

  	
  	
  });
  game.getWindow().btleben.addActionListener( e -> {
  	Statistics.setHealth(Statistics.getHealth() + 20);
  	game.getWindow().lblleben.setText("Leben: "+ Statistics.getHealth() +"|"+ Statistics.getMaxHealth());
  	game.getWindow().boosterUnsichtbar();
  	checkLevel();

  });     
  game.getWindow().btKanonen.addActionListener( e -> {
  	Statistics.setAttackSpeed(Statistics.getAttackSpeed() + 1);
  	game.getWindow().lblKanonen.setText("Kanonen: " + Statistics.getAttackSpeed());
  	game.getWindow().boosterUnsichtbar();
  	checkLevel();

  });
}

private void gameWon() {
	
}

	 
}
