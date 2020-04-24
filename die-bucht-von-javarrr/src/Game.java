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
	
	private void update() {
		
	}
	
	private void render() {
		bs = window.getCanvas().getBufferStrategy();
		if(bs == null) {
			bs = window.getCanvas().createBufferStrategy(3);
			return;
		}
		graphics = bs.getDrawGraphics();
		
		graphics.fillRect(0, 0, width, height);
		graphics.dispose();
		bs.show();

		}
	
	private void init() {
		window = new Window(title, width, height);
	}


	public void run() {
		
		init();
		
		while(running) {
			update();
			render();
		}
		stop();
			

	}

	


}
