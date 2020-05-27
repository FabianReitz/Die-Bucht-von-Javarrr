package game;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Musik {

	private static Clip clip;
	private static float masterVolume = 0.6f;
	private static boolean soundRunning = false;
	
	public static synchronized void music(String musik, String schleife) {
		final String liedname = musik;
		Thread thread = new Thread() {
			public void run() {
			try {
				
				clip = AudioSystem.getClip();
				AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(liedname));
				clip.open(inputStream);
				setVolume(masterVolume);
				if (schleife == "loop") {
				clip.loop(Clip.LOOP_CONTINUOUSLY);
				}
				Thread.sleep(clip.getMicrosecondLength()/1000);
	
			} 
			catch(Exception e) {
				e.printStackTrace(); 
			}
			}
		}; thread.start();
		soundRunning = true;
		
	}
	

	// Gibt die Lautstaerke als float zurueck
	public static float getVolume() {
	    FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);        
	    return (float) Math.pow(10f, gainControl.getValue() / 20f);
	}
	
	//Setzt die Lautstaerke auf den uebergebenen float-Wert.
	public static void setVolume(float volume) {
	    FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);        
	    gainControl.setValue(20f * (float) Math.log10(volume));
	}
	
	
	// Die Lautstaerke varriert zwischen 0.0 - 1.0, weshalb jeweils in 0.1er-Schritten die Lautstaerke erhoeht oder verringert wird. 
	// Sobald die Hoechst- oder Mindestgrenze erreicht ist, wird die Methode abgebrochen bzw. ist nicht mehr aufrufbar
	
	public static void lauter() {
		if (getVolume() <=0.9f) {
		float newVolume = getVolume() + 0.1f;
		setVolume(newVolume);
		}
		else return;
	}
	
	public static void leiser() {
		if (getVolume() >=0.11f) {
		float newVolume = getVolume() - 0.1f;
		setVolume(newVolume);
		}
		else return;
	}
	
	
	// Wenn der Musik-Clip laeuft, wird dieser gestoppt
	public static void stop() {
		if (soundRunning) {
			clip.stop();
			soundRunning = false;
		}
	}
	
	// Wenn der Musik-Clip nicht laueft, wird er gestartet
	public static void restart() {
		if (!soundRunning) {
			clip.start();
			soundRunning = true;
		}
	}
	


}
