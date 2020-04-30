package game;
import javax.swing.*;

import graphics.Assets;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import game.*;

public class Window extends JFrame {

	private JFrame frame;
	private Canvas canvas;

	private String title;
	private int width, height;
	
    private JLabel lblstats;
    private JLabel lblschaden;
    private JLabel lblleben;
    private JLabel lblKanonen;
    private JLabel lbllevel;
    private JLabel lblscore;
    private JLabel lblScoreAnzeige;
    private JButton btschaden;
    private JButton btleben;
    private JButton btKanonen;
    private int schaden = 1;
    private int leben = 100;
    private int maxLeben = 100;
    private int kanonen = 1;
    private int level = 1;
    private int punkte = 1;
    
    
	public Window(String title, int width, int height) {
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

		// Groesse des Fensters kann nicht durch Benutzer ge�ndert werden
		frame.setResizable(false);

		// Fenster erscheint in der Mitte des Bildschirms
		frame.setLocationRelativeTo(null);

		frame.setVisible(true);
		
		
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
		



		// Einf�gen der Bilder in Buttons
		Icon herz = new ImageIcon("assets/sprites/Javarrr_booster_hp-up_002.png");
    	Icon kanone = new ImageIcon("assets/sprites/Javarrr_booster_cannon_001.png");
    	Icon bombe = new ImageIcon("assets/sprites/Javarrr_booster_damage_001.png");


    	
    	//Erzeugen der Buttons und Label
        lblstats = new JLabel("Attribute:");
        lblschaden = new JLabel("Schaden: " + schaden);
        lblleben = new JLabel("Leben: " + leben+"|"+maxLeben);
        lblKanonen = new JLabel("Kanonen: " + kanonen);
        lbllevel = new JLabel("Level: " + level +"|10");
        lblscore = new JLabel("Punktestand");
        lblScoreAnzeige = new JLabel("" +punkte);
        btschaden = new JButton(bombe);
        btschaden.setBorderPainted(false);
        btleben = new JButton(herz);
        btleben.setBorderPainted(false);
        btKanonen = new JButton(kanone);
        btKanonen.setBorderPainted(false);
        
        
    
        
        //Festlegen Schriftart
        Font d = new Font("Bookman Old Style", Font.PLAIN, 20);
        Font f = new Font("Bookman Old Style", Font.PLAIN, 30);
        lblstats.setFont(f);
        lblschaden.setFont(d);
        lblleben.setFont(d);
        lblKanonen.setFont(d);
        lbllevel.setFont(f);
        lblscore.setFont(f);
        lblScoreAnzeige.setFont(f);
        
        //Buttons und Labels dem Frame hinzuf�gen und in Position bringen
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
        
        //Grafik des Scoreboard
		ImageIcon icon = new ImageIcon("assets/sprites/Javarrr_Treasure-Map_002.png");
		JLabel scoreboard = new JLabel(icon);
		frame.add(scoreboard);
		scoreboard.setBounds(512,0,256,512);
		
		// Focus bleibt auf Player
		btschaden.setFocusable(false);
		btleben.setFocusable(false);
		btKanonen.setFocusable(false);

        //Funktion der Buttons
        btschaden.addActionListener( e -> {
        schaden ++;
        lblschaden.setText("Schaden: " + schaden);
        btschaden.setVisible(false);
        btleben.setVisible(false);
        btKanonen.setVisible(false);
        });
        btleben.addActionListener( e -> {
        maxLeben = maxLeben+ 20;
        lblleben.setText("Leben: "+ leben +"|"+maxLeben);
        btschaden.setVisible(false);
        btleben.setVisible(false);
        btKanonen.setVisible(false);
        });     
        btKanonen.addActionListener( e -> {
        kanonen ++;
        lblKanonen.setText("ASpeed: " + kanonen);
        btschaden.setVisible(false);
        btleben.setVisible(false);
        btKanonen.setVisible(false);
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
		
		// Fix: Focusverlust 
		canvas.setFocusable(false);
		
		frame.add(canvas);

		// Passt die Groesse an
		frame.pack();
		
		
	}

	// Getter
	
	public Canvas getCanvas() {
		return canvas;
		
	}
	
	public JFrame getFrame() {
		return frame;
	}

}
