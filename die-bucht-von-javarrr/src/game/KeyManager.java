package game;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Set;

// Überarbeitete Steuerung:

public class KeyManager  {
	
	public Set<Integer> statusTasten = new HashSet<Integer>();
	
	public void update() {
		Controlls();
	}
	
	public void Controlls() {
		
		KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {
			
			@Override
			public boolean dispatchKeyEvent(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_D
						|| e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_1 
						|| e.getKeyCode() == KeyEvent.VK_2 || e.getKeyCode() == KeyEvent.VK_3 || e.getKeyCode() == KeyEvent.VK_4) {
					if (e.getID() == KeyEvent.KEY_PRESSED) {
						statusTasten.add(e.getKeyCode());
					} else if (e.getID() == KeyEvent.KEY_RELEASED) {
						statusTasten.remove(e.getKeyCode());
					}
					return true;
				}
				return false;
			}
		});
	}
	
}