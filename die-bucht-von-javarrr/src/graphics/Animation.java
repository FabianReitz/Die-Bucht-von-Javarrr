package graphics;

import java.awt.image.BufferedImage;

public class Animation {
	
	private int speed, index;
	private long lastTime, timer;
	private BufferedImage[] frames;
	
	
	//Konstruktor der Animation
	public Animation(int speed, BufferedImage[] frames){
		this.speed = speed;
		this.frames = frames;
		index = 0;
		timer = 0;
		lastTime = System.currentTimeMillis();
	}
	
	public void update(){
		
		timer += System.currentTimeMillis() - lastTime; //Aktueller Zeitpunkt - Letztem Zeitpunkt
		lastTime = System.currentTimeMillis(); //Aktueller Zeitpunkt wird als letzter Zeitpunkt gesetzt
		
		/*Wenn die Zahl des Timers groesser ist als der Schnelligkeit der Anzeige(speed), 
		*dann wird die Indexzahl hochgesetzt, sodass ein anderer Frame zurueckgegeben werden kann
		*/
		if(timer > speed){
			index++;
			timer = 0;
			if(index >= frames.length) //
				index = 0;
		}
	}
	
	//Liefert das Bild ausm Feld zurueck, je nach Wert des Indizes
	public BufferedImage getCurrentFrame(){
		return frames[index];
	}
}
