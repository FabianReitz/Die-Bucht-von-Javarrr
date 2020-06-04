package game;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {

	private static final long serialVersionUID = -8702099552366320139L;
	public JFrame frame;
	private Canvas canvas;
	private Game game;

	private String title;
	private int width, height;

	private JLabel scoreboard;
	private ImageIcon icon;
	private JLabel lblstats;
	public JLabel lblschaden;
	public JLabel lblleben;
	public JLabel lblKanonen;
	public JLabel lbllevel;
	public JLabel lblscore, lblScoreAnzeige, lblMusicOn, lblMusicOff, lblMusicUp, lblMusicDown;

	private JLabel lblKeybindings, lblMovement, lblShoot;
	public JButton btschaden, btleben, btKanonen;

	public JMenuItem start, highScores, exit;
	
	public JButton btStartSpiel, btVerlassenSpiel;

	public Window(Game game, String title, int width, int height) {
		this.title = title;
		this.width = width;
		this.height = height;
		this.game = game;

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

		// Groesse des Fensters kann nicht durch Benutzer ge�ndert werden
		frame.setResizable(false);

		// Fenster erscheint in der Mitte des Bildschirms
		frame.setLocationRelativeTo(null);

		frame.setVisible(true);

		// Erzeugt Menueleiste
		JMenuBar mbar = new JMenuBar();

		// Erzeugt Menues
		JMenu gameMenu = new JMenu("Spiel");

		// Menuepunkte erzeugen
		start = new JMenuItem("Neues Spiel");
		highScores = new JMenuItem("Scoreboard");
		exit = new JMenuItem("Spiel verlassen");

		// Einf�gen der Bilder in Buttons
		Icon herz = new ImageIcon("assets/sprites/Javarrr_booster_hp-up_002.png");
		Icon kanone = new ImageIcon("assets/sprites/Javarrr_booster_cannon_001.png");
		Icon bombe = new ImageIcon("assets/sprites/Javarrr_booster_damage_001.png");

		Icon startSpiel = new ImageIcon("assets/sprites/Javarrr_menu_start.png");
		Icon verlassenSpiel = new ImageIcon("assets/sprites/Javarrr_menu_verlassen.png");

		// Erzeugen der Buttons und Label
		lblstats = new JLabel("Attribute:");
		lblschaden = new JLabel("Schaden: " + game.getStatistics().getDamage());
		lblleben = new JLabel("Leben: " + game.getStatistics().getHealth() + "|" + game.getStatistics().getMaxHealth());
		lblKanonen = new JLabel("Feuerrate: " + (game.getStatistics().getAttackSpeed()));
		lbllevel = new JLabel("Level: " + game.getStatistics().getLevelNo() + "|7");
		lblscore = new JLabel("Punktestand");
		lblScoreAnzeige = new JLabel("" + game.getStatistics().getScore());
		btschaden = new JButton(bombe);
		btschaden.setBorderPainted(false);
		btleben = new JButton(herz);
		btleben.setBorderPainted(false);
		btKanonen = new JButton(kanone);
		btKanonen.setBorderPainted(false);

		lblKeybindings = new JLabel("Steuerung");
		lblMovement = new JLabel("Links: A | Rechts: D");
		lblShoot = new JLabel("Schiessen: Leertaste");
		lblMusicOn = new JLabel("Musik an: 1");
		lblMusicOff = new JLabel("Musik aus: 2");
		lblMusicUp = new JLabel("Musik leiser: 3");
		lblMusicDown = new JLabel("Musik lauter: 4");

		// Menu erstellen und Funktion der Buttons
		btStartSpiel = new JButton(startSpiel);
		btVerlassenSpiel = new JButton(verlassenSpiel);
		frame.add(btStartSpiel);
		btStartSpiel.setBounds(280, 200, 157, 65);
//        btStartSpiel.setBorderPainted(false);
		frame.add(btVerlassenSpiel);
		btVerlassenSpiel.setBounds(280, 300, 157, 65);
//        btVerlassenSpiel.setBorderPainted(false);

		btStartSpiel.setFocusable(false);
		btVerlassenSpiel.setFocusable(false);

		// Festlegen Schriftart
		Font c = new Font("Bookman Old Style", Font.PLAIN, 15);
		Font d = new Font("Bookman Old Style", Font.PLAIN, 20);
		Font f = new Font("Bookman Old Style", Font.PLAIN, 30);
		lblstats.setFont(f);
		lblschaden.setFont(d);
		lblleben.setFont(d);
		lblKanonen.setFont(d);
		lbllevel.setFont(f);
		lblscore.setFont(f);
		lblScoreAnzeige.setFont(d);
		lblMusicOn.setFont(c);
		lblMusicOff.setFont(c);
		lblMusicUp.setFont(c);
		lblMusicDown.setFont(c);
		lblKeybindings.setFont(d);
		lblMovement.setFont(c);
		lblShoot.setFont(c);

		// Buttons und Labels dem Frame hinzuf�gen und in Position bringen
		frame.add(lblstats);
		lblstats.setBounds(542, 130, 150, 30);
		frame.add(lblschaden);
		lblschaden.setBounds(542, 200, 150, 30);
		frame.add(lblleben);
		lblleben.setBounds(542, 170, 200, 30);
		frame.add(lblKanonen);
		lblKanonen.setBounds(542, 230, 350, 30);
		frame.add(lbllevel);
		lbllevel.setBounds(552, 10, 200, 35);
		frame.add(btschaden);
		btschaden.setBounds(532, 290, 50, 50);
		frame.add(btKanonen);
		btKanonen.setBounds(612, 290, 50, 50);
		frame.add(btleben);
		btleben.setBounds(692, 290, 50, 50);
		frame.add(lblscore);
		lblscore.setBounds(542, 40, 200, 50);
		frame.add(lblScoreAnzeige);
		lblScoreAnzeige.setBounds(622, 70, 100, 50);
		frame.add(lblKeybindings);
		lblKeybindings.setBounds(542, 370, 150, 50);
		frame.add(lblMovement);
		lblMovement.setBounds(542, 405, 200, 15);
		frame.add(lblShoot);
		lblShoot.setBounds(542, 420, 200, 15);
		frame.add(lblMusicOn);
		lblMusicOn.setBounds(542, 435, 150, 15);
		frame.add(lblMusicOff);
		lblMusicOff.setBounds(542, 450, 150, 15);
		frame.add(lblMusicUp);
		lblMusicUp.setBounds(542, 465, 150, 15);
		frame.add(lblMusicDown);
		lblMusicDown.setBounds(542, 480, 150, 15);

		// Grafik des Scoreboard
		icon = new ImageIcon("assets/sprites/Javarrr_Treasure-Map_002.png");
		scoreboard = new JLabel(icon);
		frame.add(scoreboard);
		scoreboard.setBounds(512, 0, 256, 512);

		// Focus bleibt auf Player
		btschaden.setFocusable(false);
		btleben.setFocusable(false);
		btKanonen.setFocusable(false);

		// Unterpunkte einfuegen
		gameMenu.add(start);
		gameMenu.add(highScores);
		gameMenu.add(exit);

		mbar.add(gameMenu);

		// MenuBar anzeigen
		frame.setJMenuBar(mbar);

		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(width, height));
		canvas.setMaximumSize(new Dimension(width, height));
		canvas.setMinimumSize(new Dimension(width, height));

		// Fix: Focusverlust
		canvas.setFocusable(false);

		frame.add(canvas);

		// Passt die Groesse an
		frame.pack();

		scoreboardVisible(false);
		boosterVisible(false);
	}

	
	//Die Sichtbarkeit der drei Booster-Buttons kann hier umgestellt werden
	public void boosterVisible(boolean visible) {
		btschaden.setVisible(visible);
		btleben.setVisible(visible);
		if (game.getStatistics().getAttackSpeed() < 0.8 && visible == true) btKanonen.setVisible(true);
		else btKanonen.setVisible(false);
	}

	//Sichtbarkeit des Scoreboards und seinen Labels/Buttons
	public void scoreboardVisible(boolean visible) {
		scoreboard.setVisible(visible);
		lblstats.setVisible(visible);
		lblschaden.setVisible(visible);
		lblleben.setVisible(visible);
		lblKanonen.setVisible(visible);
		lbllevel.setVisible(visible);
		lblscore.setVisible(visible);
		lblScoreAnzeige.setVisible(visible);
		lblMusicOn.setVisible(visible);
		lblMusicOff.setVisible(visible);
		lblMusicUp.setVisible(visible);
		lblMusicDown.setVisible(visible);
		lblKeybindings.setVisible(visible);
		lblMovement.setVisible(visible);
		lblShoot.setVisible(visible);

	}

	public void menuVisible(boolean visible) {
		btStartSpiel.setVisible(visible);
		btVerlassenSpiel.setVisible(visible);
	}

	// Getter

	public Canvas getCanvas() {
		return canvas;

	}

	public JFrame getFrame() {
		return frame;
	}

}
