import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Game implements Runnable {
	
	private Window window;
	
	private boolean running = false;
	private Thread thread;
	private BufferStrategy bs;
	private Graphics graphics;

	private BufferedImage background;
	
	
	public String title;
	public int width, height;

	public Game(String title, int width, int height){
		this.width = width;
		this.height = height;
		this.title = title;
	}


	
	
	public synchronized void start() {
		//Wenn Spiel bereits läuft, werden Befehle nicht ausgeführt
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
		// Bildschirm clearen
		graphics.clearRect(0, 0, width, height);
		
		//Bild zeichnen
		graphics.drawImage(background, 20, 20,  null);
		
		
		// Ende des Zeichnens
		graphics.dispose();
		bs.show();

		}
	
	private void init() {
		window = new Window(title, width, height);
		background = ImageLoader.loadImage("water2.png");
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
