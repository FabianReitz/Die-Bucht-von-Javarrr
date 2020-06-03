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
import units.Fleet;
import units.Gegner;
import units.Player;
import units.PlayerShot;

public class GameState extends State {

	private boolean levelIsActive = false;
	private boolean levelDone = false;
	private boolean gameWon = false;
	private static boolean gameLose = false;

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
		
		// Wenn das Spiel verloren wurde, wird die Methode gameLost() aufgerufen.
		if (gameLose == true) {
			gameLost();
		}

		if (game.getFleet().getEnemys().size() == 0 && levelIsActive == true) {
			levelIsActive = false;
			levelDone();
		}
		game.getFleet().update();

//		if(levelDone) {
//			levelDone();
//		}

//		player.update();
		background.update();
//		checkLevel();
		if (levelIsActive) {
			player.update();
			if (Statistics.getLevelNo() == 1)
				level1.update();
			else if (Statistics.getLevelNo() == 2)
				level2.update();
			else if (Statistics.getLevelNo() == 3)
				level3.update();
			else if (Statistics.getLevelNo() == 4)
				level4.update();
			else if (Statistics.getLevelNo() == 5)
				level5.update();
			else if (Statistics.getLevelNo() == 6)
				level6.update();
			else if (Statistics.getLevelNo() == 7)
				level7.update();
		}

	}

	@Override
	public void render(Graphics graphics) {
		background.render(graphics);
//		player.render(graphics);

		if (levelIsActive) {
			player.render(graphics);
			if (Statistics.getLevelNo() == 1)
				level1.render(graphics);
			else if (Statistics.getLevelNo() == 2)
				level2.render(graphics);
			else if (Statistics.getLevelNo() == 3)
				level3.render(graphics);
			else if (Statistics.getLevelNo() == 4)
				level4.render(graphics);
			else if (Statistics.getLevelNo() == 5)
				level5.render(graphics);
			else if (Statistics.getLevelNo() == 6)
				level6.render(graphics);
			else if (Statistics.getLevelNo() == 7)
				level7.render(graphics);
		}

		if (levelDone) {
			graphics.drawImage(Assets.levelDone, (int) 80, (int) 80, 256, 84, null);
		}

	}

	// Wenn derzeit kein Level aktiv ist, wird die Nummer des Levels hochgesetzt und
	// ein neues Level wird initialisiert
	private void checkLevel() {
		if (!levelIsActive) {
			Statistics.setLevelNo((Statistics.getLevelNo() + 1));
			game.getWindow().lbllevel.setText("Level: " + Statistics.getLevelNo() + "|7");
			initLevel();
		}
	}

	
	
	private void levelDone() {
		levelDone = true;
		if (Statistics.getLevelNo() == 7)
			gameWon();
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
			level3 = new Level_3(game);
		}
		if (Statistics.getLevelNo() == 4) {
			level4 = new Level_4(game);
		}
		if (Statistics.getLevelNo() == 5) {
			level5 = new Level_5(game);
		}
		if (Statistics.getLevelNo() == 6) {
			level6 = new Level_6(game);
		}
		if (Statistics.getLevelNo() == 7) {
			level7 = new Level_7(game);
		}

		for (int hE = 0; hE < Player.getFlyingShots().size(); hE++) {
			Player.getFlyingShots().remove(hE);
		}
		for (int j = 0; j < game.getFleet().getShooting().size(); j++) {
			game.getFleet().getShooting().remove(j);
		}

		levelIsActive = true;
		levelDone = false;
	}

//Funktion der Booster-Buttons
	private void initButtons() {

		game.getWindow().btschaden.addActionListener(e -> {
			Statistics.setDamage(Statistics.getDamage() + 10);
			game.getWindow().lblschaden.setText("Schaden: " + Statistics.getDamage());
			game.getWindow().boosterUnsichtbar();
			checkLevel();

		});
		game.getWindow().btleben.addActionListener(e -> {
			Statistics.setHealth(Statistics.getHealth() + 20);
			game.getWindow().lblleben.setText("Leben: " + Statistics.getHealth() + "|" + Statistics.getMaxHealth());
			game.getWindow().boosterUnsichtbar();
			checkLevel();

		});
		game.getWindow().btKanonen.addActionListener(e -> {
			Statistics.setAttackSpeed(Statistics.getAttackSpeed() - 100);
			game.getWindow().lblKanonen.setText("Kanonen: " + Statistics.getAttackSpeed());
			game.getWindow().boosterUnsichtbar();
			checkLevel();

		});
	}

	private void gameWon() {

	}

	public static void setGameLose(boolean gamelose) {
		gameLose = gamelose;
	}

	/*
	 * Die Methode sorgt dafuer, dass das derzeitig aktive Level nicht weiterlaeuft, indem es levelIsActive auf false setzt
	 */
	
	private void gameLost() {
		
	    levelIsActive = false;
		Statistics.setLevelNo(1);
	}

}
