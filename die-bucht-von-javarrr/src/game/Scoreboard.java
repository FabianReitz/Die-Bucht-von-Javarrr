package game;

import java.awt.Font;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import states.GameState;
import units.Player;

public class Scoreboard {

	private Game game;
	private JFrame frame;
	private Window window;
	private Player player;
	private GameState gamestate;

	private JLabel lblstats;
	private JLabel lblschaden;
	private JLabel lblleben;
	private JLabel lblKanonen;
	private JLabel lbllevel;
	private JLabel lblscore;
	private JLabel lblScoreAnzeige;
	private JButton btschaden, btleben, btKanonen;

	private int level = 1;
	private int punkte = 0;

	public Scoreboard(Game game, GameState gamestate) {
		this.game = game;
		this.gamestate= gamestate;
		window = game.getWindow();
		frame = window.getFrame();
		player = gamestate.getPlayer();
		initScoreboard();
	}

	private void initScoreboard() {
		
		// Einfügen der Bilder in Buttons
		Icon herz = new ImageIcon("assets/sprites/Javarrr_booster_hp-up_002.png");
		Icon kanone = new ImageIcon("assets/sprites/Javarrr_booster_cannon_001.png");
		Icon bombe = new ImageIcon("assets/sprites/Javarrr_booster_damage_001.png");

		// Erzeugen der Buttons und Label
		lblstats = new JLabel("Attribute:");
		lblschaden = new JLabel("Schaden: " + player.getDamage());
		lblleben = new JLabel("Leben: " + player.getHealth() + "|" + player.getMaxhealth());
		lblKanonen = new JLabel("Kanonen: " + player.getKanonen());
		lbllevel = new JLabel("Level: " + level + "|10");
		lblscore = new JLabel("Punktestand");
		lblScoreAnzeige = new JLabel("" + punkte);
		btschaden = new JButton(bombe);
		btschaden.setBorderPainted(false);
		btleben = new JButton(herz);
		btleben.setBorderPainted(false);
		btKanonen = new JButton(kanone);
		btKanonen.setBorderPainted(false);

		// Festlegen Schriftart
		Font d = new Font("Bookman Old Style", Font.PLAIN, 20);
		Font f = new Font("Bookman Old Style", Font.PLAIN, 30);
		lblstats.setFont(f);
		lblschaden.setFont(d);
		lblleben.setFont(d);
		lblKanonen.setFont(d);
		lbllevel.setFont(f);
		lblscore.setFont(f);
		lblScoreAnzeige.setFont(f);

		// Buttons und Labels dem Frame hinzufuegen und in Position bringen
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

		// Grafik des Scoreboard
		ImageIcon icon = new ImageIcon("assets/sprites/Javarrr_Treasure-Map_002.png");
		JLabel scoreboard = new JLabel(icon);
		frame.add(scoreboard);
		scoreboard.setBounds(512, 0, 256, 512);

		// Funktion der Buttons
		btschaden.addActionListener(e -> {
			player.setDamage(player.getDamage()+ 1);
			lblschaden.setText("Schaden: " + player.getDamage());
			btschaden.setVisible(false);
			btleben.setVisible(false);
			btKanonen.setVisible(false);
		});
		btleben.addActionListener(e -> {
			player.setMaxhealth(player.getMaxhealth() + 20);
			lblleben.setText("Leben: " + player.getHealth() + "|" + player.getMaxhealth());
			btschaden.setVisible(false);
			btleben.setVisible(false);
			btKanonen.setVisible(false);
		});
		btKanonen.addActionListener(e -> {
			player.setKanonen(player.getKanonen() + 1);
			lblKanonen.setText("Kanonen: " + player.getKanonen());
			btschaden.setVisible(false);
			btleben.setVisible(false);
			btKanonen.setVisible(false);
		});
		
		frame.pack();
		frame.revalidate();
	


	}
}
