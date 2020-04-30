
package game;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window extends JFrame {

	private static final long serialVersionUID = -8098886874372096887L;
	private JFrame frame;
	private Canvas canvas;
	private String title;
	private int width, height;
	
	public Window(String title, int width, int height){
		this.title = title;
		this.width = width;
		this.height = height;
		
		startWindow();
	}
	
	private void startWindow() {
		frame = new JFrame(title);
		
		// Setzt die Groesse des Fensters
		frame.setPreferredSize(new Dimension(width, height));
		frame.setMaximumSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(width, height));
		
		// Killt Prozess, wenn Anwendung beendet wird
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Groesse des Fensters kann nicht durch Benutzer geändert werden
		frame.setResizable(false);
		
		//Fenster erscheint in der Mitte des Bildschirms
		frame.setLocationRelativeTo(null);
		
		frame.setVisible(true);
		
		Container contentPane = this.getContentPane();
		// Erzeugt Menueleiste
<<<<<<< Updated upstream
				JMenuBar mbar = new JMenuBar();

				// Erzeugt Menues
				JMenu game = new JMenu("Spiel");

				// Menuepunkte erzeugen
				JMenuItem start = new JMenuItem("Neues Spiel");
				JMenuItem highScores = new JMenuItem("Scoreboard");
				JMenuItem exit = new JMenuItem("Spiel verlassen");

				// Spiel verlassen
				exit.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						System.exit(0);
					}
				});

				// Unterpunkte einfuegen
				game.add(start);
				game.add(highScores);
				game.add(exit);

				mbar.add(game);

				// MenuBar anzeigen
				frame.setJMenuBar(mbar);
				
				canvas = new Canvas();
				canvas.setPreferredSize(new Dimension(width, height));
				canvas.setMaximumSize(new Dimension(width, height));
				canvas.setMinimumSize(new Dimension(width, height));
				frame.add(canvas);
				
				// Passt die Groesse an
				frame.pack();
				
//				Music.music("music/track1.wav");
=======
		JMenuBar mbar = new JMenuBar();

		// Erzeugt Menues
		JMenu game = new JMenu("Spiel");

		// Menuepunkte erzeugen
		JMenuItem start = new JMenuItem("Neues Spiel");
		JMenuItem highScores = new JMenuItem("Scoreboard");
		JMenuItem exit = new JMenuItem("Spiel verlassen");
		
		

		// Spiel verlassen
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		// Unterpunkte einfuegen
		game.add(start);
		game.add(highScores);
		game.add(exit);

		mbar.add(game);

		// MenuBar anzeigen
		frame.setJMenuBar(mbar);

		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(width, height));
		canvas.setMaximumSize(new Dimension(width, height));
		canvas.setMinimumSize(new Dimension(width, height));
		frame.add(canvas);

		// Passt die Groesse an
		frame.pack();
		
		//Öffnet Musik
		Musik.music("assets/Musik/Musik.wav");
>>>>>>> Stashed changes
	}
	
	public Canvas getCanvas() {
		return canvas;
	}
	
	public JFrame getFrame() {
		return frame;
	}

}
