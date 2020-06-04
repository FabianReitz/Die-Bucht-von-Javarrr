package graphics;

import java.awt.image.BufferedImage;

public class Spritesheet {
	
	private BufferedImage sheet;
	
	// Konstruktor
	public Spritesheet(BufferedImage sheet) {
		this.sheet= sheet;
	}
	
	// Die Methode zerteilt das BufferedImage mit den Koordinaten, Breite und Hoehe und gibt das neue Bild zurueck
	public BufferedImage zerteilen(int x, int y, int width, int height) {
		return sheet.getSubimage(x, y, width, height);
	}

}
