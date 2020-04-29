package game;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Musik {
	
	public static synchronized void music(String musik) {
		final String liedname = musik;
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				while(true) {
					
					try {
						
						Clip clip = AudioSystem.getClip();
						AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(liedname));
						clip.open(inputStream);
						clip.loop(clip.LOOP_CONTINUOUSLY);
						
						Thread.sleep(clip.getMicrosecondLength()/1000);
			
					} catch(Exception e) {
						e.printStackTrace(); 
					}
				}
			}
		}).start();
	}
}
