package game;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import graphics.Assets;
import states.GameState;
import states.MenuState;
import states.State;
import units.Fleet;
import units.Player;

public class Game implements Runnable {
	
	private Window window;
	private Statistics statistics;
	private Fleet fleet;
	private Player player;

	private boolean running = false;
	public boolean consoleFPS = false;

	private Thread thread;
	
	// Grafik 
	private BufferStrategy bs;
	private Graphics graphics;

	// Status
	private MenuState menuState;
	private GameState gameState;
	
	//Input
	private KeyManager keyManager;
	
	// Konstruktor
	
	public String title;
	public int width, height;

	public Game(String title, int width, int height){
		this.width = width;
		this.height = height;
		this.title = title;
		keyManager = new KeyManager();
	}

	// Start und Stop des Spiels
	public synchronized void start() {
		//Wenn Spiel bereits laeuft, werden Befehle nicht ausgefuehrt
		if(running) return;
		
		running = true;
		thread = new Thread(this);
		thread.start();

	}

	
	public synchronized void stop() {
		//Wenn Spiel nicht laueft, werden die Befehle nicht ausgefuehrt
		if(!running) return;
		
		running = false;
		try {
			thread.join();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	private void init() {
		//Initialisierung von den Spielerstatistiken, den Spieler selbst, das Fenster des Spiel und der Klasse Fleet, wo sich die Listen der Gegner etc. befinden
		statistics = new Statistics();
		window = new Window(this, title, width, height);
		player = new Player(this, 256, 450);
		fleet = new Fleet(this, player);

		//Initialisierung der Sprites
		Assets.init();
		
		//Starten der Musik in der Dauerschleife
		Musik.music("assets/Musik/Musik.wav","loop");	

		//Schafft einen neuen MenuState, welcher als aktiver State gesetzt wird, sodass das Menue angezeigt wird
		menuState = new MenuState(this, fleet, player);
		State.setState(menuState);
	}
	
	// Game Loop: Update/Render aller Variablen, Objekten, etc und Grafiken werden gerendert

	private void update() {
		keyManager.update();
		sound();
		
		//Wenn ein State aktiv ist, wird dessen Updatemethode aufgerufen
		if(State.getState() != null) 
			State.getState().update();
	}
	

	
	private void render() {
		bs = window.getCanvas().getBufferStrategy();
		if(bs == null) {
			window.getCanvas().createBufferStrategy(3);
			return;
		}
		graphics = bs.getDrawGraphics();
		// Bildschirm clearen
		graphics.clearRect(0, 0, width, height);

		//Wenn ein State aktiv ist, wird dessen Rendermethode aufgerufen
		if(State.getState() != null) 
			State.getState().render(graphics);
		
		// Ende des Zeichnens
		graphics.dispose();
		bs.show();

		}


	
	public void run() {

		init();
		
		//Initialisierung wichtiger Variablen fuer den Timer
		int fps = 60;
		double timePerTick = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();

		/*
		 * Solange das Spiel laueft (running = true), wird der aktuelle Zeitpunkt - dem letzten Zeitpunkt gesetzt. Dieses wird durch timePerTick geteilt,
		 * welches durch die FPS bestimmt wurde. Solange die Summe + Delta ueber 1 ist, werden die Methoden update() und render() ausgefuehrt. Dem Delta wird 1 abgezogen. 
		 */
		while(running){
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			lastTime = now;
			
			if(delta >= 1){
				update();
				render();
				delta--;
			}
		}
		
		stop();
			

	}
	
	//Weist den Tasten 1-4 mit Hilfe des KeyManagers Funktionen zu, um die Musik einzustellen
	private void sound() {
			
			if (getKeyManager().controllState.contains(KeyEvent.VK_1) ) {
				Musik.restart();
			}
			
			if (getKeyManager().controllState.contains(KeyEvent.VK_2) ) {
				Musik.stop();
			}
			
			if (getKeyManager().controllState.contains(KeyEvent.VK_3) ) {
				Musik.lesser();
			}
			
			if (getKeyManager().controllState.contains(KeyEvent.VK_4) ) {
				Musik.louder();
			}
	}


	public Fleet getFleet() {
	return fleet;
}
	
	public Statistics getStatistics() {
		return statistics;
	}

	public Window getWindow() {
		return window;
	}
	
	public void setMenuState() {
		State.setState(menuState);
	}

	public GameState getGameState() {
		return gameState;
	}

	public void setGameState(GameState gameState) {
		this.gameState = gameState;
		State.setState(gameState);
	}
	
	public KeyManager getKeyManager(){
		return keyManager;
	}
	
}
