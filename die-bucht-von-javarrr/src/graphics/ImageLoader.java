package graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageLoader {

	/*
	 * Der ImageLoader wird von der Klasse Assets aufgerufen, wo die Grafiken geladen. Um Zugriff zu erleichtern, ist die Methode statisch. 
	 * In der Methode wird versucht das Bild zu lesen, wofuer der Pfad der Datei uebergeben wird. Klappt dies, wird das Bild als BufferedImage
	 * uebergeben, wenn nicht wird der Vorgang abgebrochen und das System wird beendet.
	 */
	public static BufferedImage loadImage(String path) {
		try {
		return ImageIO.read(new File(path));		
		}
		catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		return null;
	}
}
