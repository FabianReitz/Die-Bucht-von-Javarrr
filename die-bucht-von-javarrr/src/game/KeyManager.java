package game;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

// WIP

public class KeyManager implements KeyListener {


	private boolean[] keys;
	public boolean left, right, shoot;
	
	public KeyManager(){
		keys = new boolean[256];
	}
	
	public void update(){
		left = keys[KeyEvent.VK_A];
		right = keys[KeyEvent.VK_D];
		shoot = keys[KeyEvent.VK_SPACE];
	}

	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}
}