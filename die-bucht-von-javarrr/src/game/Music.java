package game;
import java.io.File;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioInputStream;

public class Music {

	public static synchronized void music(String track) {
		
		final String trackname = track;
		
		new Thread(new Runnable() {
			

			@Override
			public void run() {
				while(true) {
					try {
						Clip clip = AudioSystem.getClip();
						AudioInputStream inputstream = AudioSystem.getAudioInputStream(new File(trackname));
						clip.open(inputstream);
						clip.loop(clip.LOOP_CONTINUOUSLY);
						
						Thread.sleep(clip.getMicrosecondLength()/1000);
			} catch (Exception e) {
				e.printStackTrace();
				}
				}
			}
			}).start();
		}
}

