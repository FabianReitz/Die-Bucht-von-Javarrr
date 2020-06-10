package states;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import game.Game;
import graphics.Assets;
import graphics.Background;
import units.Fleet;
import units.Gegner;
import units.Player;
import units.PlayerShot;

public class GameState extends State {

	/*
	 * Initalisierung von Booleans, um festzustellen, ob ein Level aktiv ist,
	 * geschafft wurde, das Spiel gewonnen wurde oder verloren wurde.
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



	
	HashMap<Integer, String> scoreboard = new HashMap<Integer, String>();
	TreeMap<Integer, String> sortedScoreboard = new TreeMap<Integer, String>(Collections.reverseOrder());
	String memory;
	int num;


	public GameState(Game game, MenuState menuState, Fleet fleet, Player player) {
		super(game);
		this.menuState = menuState;
		this.fleet = fleet;
		this.player = player;
		background = new Background();
		initLevel();
		initButtons();

	}

	@Override
	public void update() {

		/*
		 * Wenn ESC gedrueckt wird, waehrend sich das Spiel im GameState befindet, wird
		 * auf MenuState gewechselt, das Menue sichtbar gemacht und das Scoreboard
		 * versteckt
		 */
		if (game.getKeyManager().controllState.contains(KeyEvent.VK_ESCAPE)) {
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

		// Wenn keine Gegner auf dem Spielfeld sind, ein Level jedoch aktiv ist, gilt
		// das Level als geschafft und das Level wird deaktiviert.
		if (game.getFleet().getEnemys().size() == 0 && levelIsActive == true) {
			levelIsActive = false;
			levelDone();
		}

		// Ruft die Update-Methode der Klasse Fleet auf, wo die Schuesse der Gegner
		// verwaltet werden.
		game.getFleet().update();

		// Aufruf des Updates des Hintergrunds
		background.update();

		/*
		 * Wenn derzeit das Level aktiv ist, dann wird der Spieler und die Listen geupdatet.
		 */
		if (levelIsActive) {
			player.update();
			for (Gegner gegner : game.getFleet().getEnemys()) {
				gegner.update();
			}
			for (Gegner gegner : game.getFleet().getShooting()) {
				gegner.getSchuss().update();
			}

			for (PlayerShot playerShot : player.getFlyingShots()) {
				playerShot.update();
			}
		}

	}

	@Override
	public void render(Graphics graphics) {

		// Funktionen wie die Update-Methode

		background.render(graphics);
		if (levelIsActive) {
			player.render(graphics);
			for (Gegner gegner : game.getFleet().getEnemys()) {
				gegner.render(graphics);
			}
			for (Gegner gegner : game.getFleet().getShooting()) {
				gegner.getSchuss().render(graphics);

			}

			for (PlayerShot playerShot : player.getFlyingShots()) {
				playerShot.render(graphics);
			}
		}

		// Wird nur gerendert, wenn das Level geschafft wurde.
		if (levelDone) {
			graphics.drawImage(Assets.levelDone, (int) 80, (int) 80, 256, 84, null);
		}

		
		//Wird nur gerendert, wenn das Level gewonnen wurde.
		else if (gameWon) {
			graphics.drawImage(Assets.gameOver, (int) 30, (int) 90, 461, 401, null);
		}
		
		// Wird nur gerendert, wenn das Spiel verloren wurde.
		if (gameLose) {
			graphics.drawImage(Assets.gameOver, (int) 30, (int) 90, 461, 401, null);
		}
		
		

	}

	/*
	 * Wenn derzeit kein Level aktiv ist, wird die Nummer des Levels hochgesetzt und
	 * ein neues Level wird initialisiert
	 */
	private void checkLevel() {
		if (!levelIsActive) {
			game.getStatistics().setLevelNo((game.getStatistics().getLevelNo() + 1));
			game.getWindow().lbllevel.setText("Level: " + game.getStatistics().getLevelNo() + "|7");
			initLevel();
		}
	}

	/*
	 * Die Methode wird aufgerufen, wenn das Level geschafft wurde. Falls der
	 * Spieler sich in Level 7 befindet und die Gegner besiegt hat, hat der Spieler
	 * gewonnen.
	 */
	private void levelDone() {
		if (game.getStatistics().getLevelNo() == 7) {
			gameWon();
			return; // die weiteren Befehle der Methode sollen nicht ausgefuehrt werden, wenn das
					// Spiel gewonnen wurde
		} else {
			game.getWindow().boosterVisible(true);
		}
		levelDone = true;

	}

	// Initialisiert die Level. Je nach derzeitiger Levelnummer werden der Liste
	// andere/neue Gegner hinzugefuegt
	private void initLevel() {

		if (game.getStatistics().getLevelNo() == 1) {
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					Gegner gegner = new Gegner(game, 20 + 110 * j, 20 + 75 * i, "small");
					game.getFleet().getEnemys().add(gegner);
					game.getFleet().getCanShoot().add(gegner);
				}
			}
		}
		else if (game.getStatistics().getLevelNo() == 2) {
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 3; j++) {

					Gegner gegner = new Gegner(game, 20 + 100 * i, 20 + 75 * j, "small");
					game.getFleet().getEnemys().add(gegner);
					game.getFleet().getCanShoot().add(gegner);

				}
			}
		}
		else if (game.getStatistics().getLevelNo() == 3) {
			for (int i = 0; i < 1; i++) {
				for (int j = 0; j < 4; j++) {

					Gegner gegner = new Gegner(game, 20 + 100 * j, 20 + 75 * i, "medium");
					game.getFleet().getEnemys().add(gegner);
					game.getFleet().getCanShoot().add(gegner);

				}
			}
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 4; j++) {

					Gegner gegner = new Gegner(game, 20 + 100 * j, 95 + 75 * i, "small");
					game.getFleet().getEnemys().add(gegner);
					game.getFleet().getCanShoot().add(gegner);

				}
			}
		}
		else if (game.getStatistics().getLevelNo() == 4) {
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {

					Gegner gegner = new Gegner(game, 20 + 100 * j, 20 + 75 * i, "medium");
					game.getFleet().getEnemys().add(gegner);
					game.getFleet().getCanShoot().add(gegner);

				}
			}
		}
		else if (game.getStatistics().getLevelNo() == 5) {
			for (int i = 0; i < 1; i++) {
				for (int j = 0; j < 4; j++) {

					Gegner gegner = new Gegner(game, 20 + 100 * j, 20 + 75 * i, "big");
					game.getFleet().getEnemys().add(gegner);
					game.getFleet().getCanShoot().add(gegner);

				}
			}

			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 4; j++) {

					Gegner gegner = new Gegner(game, 20 + 100 * j, 95 + 75 * i, "medium");
					game.getFleet().getEnemys().add(gegner);
					game.getFleet().getCanShoot().add(gegner);

				}
			}
		}
		else if (game.getStatistics().getLevelNo() == 6) {
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {

					Gegner gegner = new Gegner(game, 20 + 100 * j, 20 + 75 * i, "big");
					game.getFleet().getEnemys().add(gegner);
					game.getFleet().getCanShoot().add(gegner);

				}
			}
		}
		else if (game.getStatistics().getLevelNo() == 7) {
			Gegner gegner = new Gegner(game, 160, 10, "boss");
			game.getFleet().getEnemys().add(gegner);
			game.getFleet().getCanShoot().add(gegner);
			for (int i = 0; i < 1; i++) {
				for (int j = 0; j < 4; j++) {
					gegner = new Gegner(game, 20 + 100 * j, 245 + 75 * i, "medium");
					game.getFleet().getEnemys().add(gegner);
					game.getFleet().getCanShoot().add(gegner);

				}
			}
			for (int i = 0; i < 2; i++) {
				for (int j = 0; j < 4; j++) {

					gegner = new Gegner(game, 20 + 100 * j, 95 + 75 * i, "big");
					game.getFleet().getEnemys().add(gegner);
					game.getFleet().getCanShoot().add(gegner);

				}
			}
		}

		// Schuesse(Gegner und Spieler) werden entfernt
		fleet.getShooting().clear();
		player.getFlyingShots().clear();

		levelIsActive = true;
		levelDone = false;
	}

//Funktion der Booster-Buttons
	private void initButtons() {

		game.getWindow().btschaden.addActionListener(e -> { // wenn auf den Button geklickt wird, werden folgende
															// Befehle ausgefuehrt
			game.getStatistics().setDamage(game.getStatistics().getDamage() + 10); // Schaden wird hochgesetzt
			game.getWindow().lblschaden.setText("Schaden: " + game.getStatistics().getDamage()); // aendert Text des
																									// Labels
			game.getWindow().boosterVisible(false);
			checkLevel();

		});
		game.getWindow().btleben.addActionListener(e -> {
			game.getStatistics().setHealth(game.getStatistics().getHealth() + 20); // Leben wird hochgesetzt
			game.getWindow().lblleben
					.setText("Leben: " + game.getStatistics().getHealth() + "|" + game.getStatistics().getMaxHealth());
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
	 * Die Methode sorgt dafuer, dass das derzeitig aktive Level nicht weiterlaeuft,
	 * indem es levelIsActive auf false setzt
	 */

	private void gameLost() {

	    levelIsActive = false;
	    game.getStatistics().setLevelNo(1);
	    
	    try {
			readCSV();
		} catch (FileNotFoundException e) {
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
		game.getWindow().lblleben
				.setText("Leben: " + game.getStatistics().getHealth() + "|" + game.getStatistics().getMaxHealth());
		game.getWindow().lblKanonen.setText("Feuerrate: " + game.getStatistics().getAttackSpeed());
		game.getWindow().lbllevel.setText("Level: " + game.getStatistics().getLevelNo() + "|7");
		game.getWindow().lblScoreAnzeige.setText("" + game.getStatistics().getScore());
	}

	// Wenn das Spiel gewonnen wurde, wir die Variable gameWon auf true gesetzt
	private void gameWon() {
		gameWon = true;
		
		game.getWindow().btSubmitName.addActionListener(a -> {

			try {
				game.getGameState().writeCSV();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}});
		
		try {
			readCSV();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	    
	    if (!gate) createScoreboard();
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
