import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

public class Window extends JFrame {

	public Window() {

		Container contentPane = this.getContentPane();

		// Killt Prozess, wenn Anwendung beendet wird
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel background = new JLabel();
		File waterBackgroundFile = new File("assets/water2.png");
		BufferedImage waterBackground = ImageIO.read(waterBackgroundFile);
		background.setIcon(waterBackground);
	}

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
