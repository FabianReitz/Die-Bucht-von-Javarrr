import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Game implements Runnable {
	
	private Window window;
	
	private boolean running = false;
	private Thread thread;
	private BufferStrategy bs;
	private Graphics graphics;

	
	
	public String title;
	public int width, height;

	public Game(String title, int width, int height){
		this.width = width;
		this.height = height;
		this.title = title;
	}


	
	
	public synchronized void start() {
		//Wenn Spiel bereits l�uft, werden Befehle nicht ausgef�hrt
		if(running) return;
		
		running = true;
		thread = new Thread(this);
		thread.start();

	}
	
	public synchronized void stop() {
		if(!running) return;
		
		running = false;
		try {
			thread.join();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
<<<<<<< Updated upstream:die-bucht-von-javarrr/src/Game.java
=======
	
	// Initialisierung des Spiels
	
	private void init() {
		window = new Window(title, width, height);
		Assets.init();
		window.getFrame().addKeyListener(keyManager);
			
		
		gameState = new GameState(this);
		menuState = new MenuState(this);
		State.setState(gameState);
	}
	

	// Game Loop: Update/Render aller Variablen, Objekten, etc und Grafiken werden gerendert
>>>>>>> Stashed changes:die-bucht-von-javarrr/src/game/Game.java

	// Game Loop: Update aller Variablen, Objekten, etc und Grafiken werden gerendert
	int x = 0;
	private void update() {
		x += 1;
	}
	
	private void render() {
		bs = window.getCanvas().getBufferStrategy();
		if(bs == null) {
			window.getCanvas().createBufferStrategy(3);
			return;
		}
		graphics = bs.getDrawGraphics();
<<<<<<< Updated upstream:die-bucht-von-javarrr/src/Game.java
		
		graphics.fillRect(0, 0, width, height);
=======
		// Bildschirm clearen
		graphics.clearRect(0, 0, width, height);

		if(State.getState() != null) 
			State.getState().render(graphics);
		

>>>>>>> Stashed changes:die-bucht-von-javarrr/src/game/Game.java
		
		// Ende des Zeichnens
		graphics.dispose();
		bs.show();

		}
	
	private void init() {
		window = new Window(title, width, height);
	}


	public void run() {
		
		init();
		
		int fps = 60;
		double timePerTick = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;
		
		while(running){
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;
			
			if(delta >= 1){
				update();
				render();
				ticks++;
				delta--;
			}
			
			if(timer >= 1000000000){
				System.out.println("Ticks and Frames: " + ticks);
				ticks = 0;
				timer = 0;
			}
		}
		
		stop();
			

	}



}