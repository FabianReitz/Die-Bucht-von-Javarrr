import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuBar {

	public static void main(String[] args) {

		// Erzeugt Menueleiste
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

		Window frame = new Window();
		frame.setVisible(true);

		// Setzt die Größe des Fensters
		frame.setPreferredSize(new Dimension(512, 512));

		// MenuBar anzeigen
		frame.setJMenuBar(mbar);

		frame.pack();

	}

}
