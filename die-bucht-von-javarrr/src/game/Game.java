package game;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JLabel;

import graphics.Assets;
import scoreboard.scoreboard;
import states.GameState;
import states.MenuState;
import states.State;

public class Game implements Runnable {
	
	private Window window;
	
	private boolean running = false;
	public boolean consoleFPS = false;
	

	private Thread thread;
	
	// Grafik 
	private BufferStrategy bs;
	private Graphics graphics;

	// Status
	private State gameState;
	private State menuState;
	
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

	private void update() {
		keyManager.update();
		
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

		
		
		graphics.drawImage(Assets.background1, (int) 0, (int) 0, null);
		graphics.drawImage(Assets.scoreboard, (int) 512, (int) 0, null);
		
		if(State.getState() != null) 
			State.getState().render(graphics);
		
		// Ende des Zeichnens
		graphics.dispose();
		bs.show();

		}
	
	public KeyManager getKeyManager(){
		return keyManager;
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
				if (consoleFPS) System.out.println("FPS: " + ticks);
				ticks = 0;
				timer = 0;
			}
		}
		
		stop();
			

	}



}
