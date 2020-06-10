package states;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import game.Game;
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

public class GameState extends State {

	/*
	 *  Initalisierung von Booleans, um festzustellen, ob ein Level aktiv ist, geschafft wurde, das Spiel gewonnen wurde oder verloren wurde. 
	 */
	private boolean levelIsActive = false;
	private boolean levelDone = false;
	private boolean gameWon = false;
	private boolean gameLose = false;
	private boolean gate = false;

	private Player player;
	private Background background;
	private MenuState menuState;
	private Fleet fleet;


	private Level_1 level1;
	private Level_2 level2;
	private Level_3 level3;
	private Level_4 level4;
	private Level_5 level5;
	private Level_6 level6;
	private Level_7 level7;
	
	HashMap<Integer, String> scoreboard = new HashMap<Integer, String>();
	TreeMap<Integer, String> sortedScoreboard = new TreeMap<Integer, String>(Collections.reverseOrder());
	String memory;
	int num;

	public GameState(Game game, MenuState menuState, Fleet fleet, Player player) {
		super(game);
		this.menuState = menuState;
		this.fleet = fleet;
		this.player = player;
		background = new Background(game);
		initLevel();
		initButtons();

	}


	@Override
	public void update() {
		
		/*
		 * Wenn ESC gedrueckt wird, waehrend sich das Spiel im GameState befindet, wird auf MenuState gewechselt, das Menue sichtbar gemacht
		 * und das Scoreboard versteckt		
		 */
		if (game.getKeyManager().statusTasten.contains(KeyEvent.VK_ESCAPE) ) {
			if (State.getState() == this) {
				State.setState(menuState);
				game.getWindow().menuVisible(true);
				game.getWindow().scoreboardVisible(false);
			}
		}
		
		// Wenn das Spiel verloren wurde, wird die Methode gameLost() aufgerufen.
		if (gameLose == true) {
			gameLost();
		}


		if (game.getFleet().getEnemys().size() == 0 && levelIsActive == true) {

			levelIsActive = false;
			levelDone();
		}
		game.getFleet().update();

		// Aufruf des Updates des Hintergrunds
		background.update();

		/* 
		 * Wenn derzeit das Level aktiv ist, dann wird der Spieler und das aktive Level (je nach Levelnummer) geupdatet.
		 */
		if (levelIsActive) {
			player.update();
			if (game.getStatistics().getLevelNo() == 1)
				level1.update();
			else if (game.getStatistics().getLevelNo() == 2)
				level2.update();
			else if (game.getStatistics().getLevelNo() == 3)
				level3.update();
			else if (game.getStatistics().getLevelNo() == 4)
				level4.update();
			else if (game.getStatistics().getLevelNo() == 5)
				level5.update();
			else if (game.getStatistics().getLevelNo() == 6)
				level6.update();
			else if (game.getStatistics().getLevelNo() == 7)
				level7.update();
		}
		
		
	}

	@Override
	public void render(Graphics graphics) {
		
		//Funktion wie die Update-Methode
		
		background.render(graphics);
		if (levelIsActive) {
			player.render(graphics);
			if (game.getStatistics().getLevelNo() == 1)
				level1.render(graphics);
			else if (game.getStatistics().getLevelNo() == 2)
				level2.render(graphics);
			else if (game.getStatistics().getLevelNo() == 3)
				level3.render(graphics);
			else if (game.getStatistics().getLevelNo() == 4)
				level4.render(graphics);
			else if (game.getStatistics().getLevelNo() == 5)
				level5.render(graphics);
			else if (game.getStatistics().getLevelNo() == 6)
				level6.render(graphics);
			else if (game.getStatistics().getLevelNo() == 7)
				level7.render(graphics);
		}

		//Wird nur gerendert, wenn das Level geschafft wurde.
		if (levelDone) {
			graphics.drawImage(Assets.levelDone, (int) 80, (int) 80, 256, 84, null);
		}
		
		//Wird nur gerendert, wenn das Level gewonnen wurde.
		else if (gameWon) {
			graphics.drawImage(Assets.gameWon, (int) 80, (int) 80, 380, 256, null);
		}
		
		// Wird nur gerendert, wenn das Spiel verloren wurde.
		if (gameLose) {
			graphics.drawImage(Assets.gameOver, (int) 30, (int) 90, 461, 401, null);
//			graphics.drawImage(Assets.enterScore, (int) 30, (int) 100, 461, 151, null);
		}
		
		
	}

	/* Wenn derzeit kein Level aktiv ist, wird die Nummer des Levels hochgesetzt und
	 ein neues Level wird initialisiert */
	private void checkLevel() {
		if (!levelIsActive) {
			game.getStatistics().setLevelNo((game.getStatistics().getLevelNo() + 1));
			game.getWindow().lbllevel.setText("Level: " + game.getStatistics().getLevelNo() + "|7");
			initLevel();
		}
	}

	
	/*
	 * Die Methode wird aufgerufen, wenn das Level geschafft wurde.
	 */
	private void levelDone() {
		if (game.getStatistics().getLevelNo() == 7) {
			gameWon();
			return;
		}
		else {
			game.getWindow().boosterVisible(true);
		}
		levelDone = true;
		
	}

	private void initLevel() {

		if (game.getStatistics().getLevelNo() == 1) {
			level1 = new Level_1(game, player);
		}
		if (game.getStatistics().getLevelNo() == 2) {
			level2 = new Level_2(game, player);
		}
		if (game.getStatistics().getLevelNo() == 3) {
			level3 = new Level_3(game, player);
		}
		if (game.getStatistics().getLevelNo() == 4) {
			level4 = new Level_4(game, player);
		}
		if (game.getStatistics().getLevelNo() == 5) {
			level5 = new Level_5(game, player);
		}
		if (game.getStatistics().getLevelNo() == 6) {
			level6 = new Level_6(game, player);
		}
		if (game.getStatistics().getLevelNo() == 7) {
			level7 = new Level_7(game, player);
		}

		for (int hE = 0; hE < player.getFlyingShots().size(); hE++) {
			player.getFlyingShots().remove(hE);
		}
		for (int j = 0; j < game.getFleet().getShooting().size(); j++) {
			game.getFleet().getShooting().remove(j);

		}
		
		fleet.getShooting().clear();
		player.getFlyingShots().clear();
		
		levelIsActive = true;
		levelDone = false;
	}

//Funktion der Booster-Buttons
	private void initButtons() {

		game.getWindow().btschaden.addActionListener(e -> {
			game.getStatistics().setDamage(game.getStatistics().getDamage() + 10);
			game.getWindow().lblschaden.setText("Schaden: " + game.getStatistics().getDamage());
			game.getWindow().boosterVisible(false);
			checkLevel();

		});
		game.getWindow().btleben.addActionListener(e -> {
			game.getStatistics().setHealth(game.getStatistics().getHealth() + 20);
			game.getWindow().lblleben.setText("Leben: " + game.getStatistics().getHealth() + "|" + game.getStatistics().getMaxHealth());
			game.getWindow().boosterVisible(false);
			checkLevel();

		});
		game.getWindow().btKanonen.addActionListener(e -> {
			game.getStatistics().setAttackSpeed(game.getStatistics().getAttackSpeed() + 0.2d);
			game.getWindow().lblKanonen.setText("Feuerrate: " + (game.getStatistics().getAttackSpeed()));
			game.getWindow().boosterVisible(false);
			checkLevel();

		});
	}

	

	/*
	 * Die Methode sorgt dafuer, dass das derzeitig aktive Level nicht weiterlaeuft, indem es levelIsActive auf false setzt
	 */
	
	private void gameLost() {
	    levelIsActive = false;
	    game.getStatistics().setLevelNo(1);
	    
	    try {
			readCSV();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    if (!gate) createScoreboard();
	}
	
	
	/*
	 * 
	 */
	
	private void readCSV() throws FileNotFoundException {
		// Scannt CSV und uebertraegt Inhalt in HashMap scoreboard
				Scanner scanner = new Scanner(new File("scores.csv"));
		        scanner.useDelimiter("\\n");
		        while(scanner.hasNext()){
		        	memory = scanner.next();
		            String[] parts = memory.split(",");
		            num = Integer.parseInt(parts[0]);
		            scoreboard.put(num, parts[1]);   
		        }
		        scanner.close();
	}
	
	private void createScoreboard() {
		
		int i = 1;
		
		sortedScoreboard.putAll(scoreboard);
		
		for (Map.Entry<Integer, String> entry : sortedScoreboard.entrySet()) {
			
				switch (i) {
				case 5:
					game.getWindow().lblFuenfterScore.setText(entry.getKey().toString());
					game.getWindow().lblFuenfterName.setText(entry.getValue());
					break;
					
				case 4:
					game.getWindow().lblVierterScore.setText(entry.getKey().toString());
					game.getWindow().lblVierterName.setText(entry.getValue());
					break;
				
				case 3: 
					game.getWindow().lblDritterScore.setText(entry.getKey().toString());
					game.getWindow().lblDritterName.setText(entry.getValue());
					break;
					
				case 2:
					game.getWindow().lblZweiterScore.setText(entry.getKey().toString());
					game.getWindow().lblZweiterName.setText(entry.getValue());
					break;
					
				case 1:
					game.getWindow().lblErsterScore.setText(entry.getKey().toString());
					game.getWindow().lblErsterName.setText(entry.getValue());
					break;
				}
				
				i++;
		}
		// Endscreen:
	    game.getWindow().gameOverVisible();
	    gate = true;
	}

	/*
	 * Alle Stats und Listen werden zurueckgesetzt, um vom Neuen beginnen zu koennen
	 */
	public void reset() {
		
		fleet.getEnemys().clear();
		fleet.getShooting().clear();
		player.getFlyingShots().clear();
		gameLose = false;
		gameWon = false;
		levelDone = false;
		game.getStatistics().setHealth(100);
		game.getStatistics().setLevelNo(1);
		game.getStatistics().setAttackSpeed(0.2);
		game.getStatistics().setDamage(5);
		game.getStatistics().setScore(0);
		game.getWindow().lblschaden.setText("Schaden: " + game.getStatistics().getDamage());
		game.getWindow().lblleben.setText("Leben: " + game.getStatistics().getHealth() + "|" + game.getStatistics().getMaxHealth());
		game.getWindow().lblKanonen.setText("Feuerrate: " + game.getStatistics().getAttackSpeed());
		game.getWindow().lbllevel.setText("Level: " + game.getStatistics().getLevelNo() + "|7");
		game.getWindow().lblScoreAnzeige.setText("" + game.getStatistics().getScore());
	}
	
	private void gameWon() {
		gameWon = true;
	}

	public void setGameLose(boolean gamelose) {
		gameLose = gamelose;
	}
	
	public void writeCSV() throws FileNotFoundException {
	
		
		// SCHREIBEN DER CSV
		
		try (PrintWriter writer = new PrintWriter(new File("scores.csv"))) {
			
			StringBuilder sb = new StringBuilder();
			
			/*
			 * CSV Inhalt bilden
			 */
			
			for (Map.Entry me :scoreboard.entrySet()) {
				sb.append(me.getKey());
				sb.append(",");	
				sb.append(me.getValue());
				sb.append("\n");
			}
			
			// Konstruiere String für CSV
			sb.append(game.getWindow().lblGameOverScore.getText().toString());	
			sb.append(",");														
			sb.append(game.getWindow().name.getText().toString());			
			sb.append("\n");
			
			// CSV beschreiben
			writer.write(sb.toString());
			
			// Für Scoreboard 
			scoreboard.put(Integer.parseInt(game.getWindow().lblGameOverScore.getText()), game.getWindow().name.getText().toString());
			
			
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
		game.getWindow().btSubmitName.setVisible(false);
		game.getWindow().name.setEditable(false);
		
	}
	
}
