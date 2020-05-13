package game;
import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {

	private static final long serialVersionUID = -8702099552366320139L;
	private JFrame frame;
	private Canvas canvas;

	private String title;
	private int width, height;
	
	
	private JLabel scoreboard;
	private ImageIcon icon;
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
    
    
    public JMenuItem start, highScores, settings, exit;
    
    private JLabel lblname;
    
    // Startscreen Buttons
    public JButton btStartSpiel, btVerlassenSpiel, btMusikAn, btMusikAus, btMusikLauter, btMusikLeiser;
   



    
    
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
		start = new JMenuItem("Neues Spiel");
		highScores = new JMenuItem("Scoreboard");
		settings = new JMenuItem("Einstellungen");
		exit = new JMenuItem("Spiel verlassen");
		

		// Einf�gen der Bilder in Buttons
		Icon herz = new ImageIcon("assets/sprites/Javarrr_booster_hp-up_002.png");
    	Icon kanone = new ImageIcon("assets/sprites/Javarrr_booster_cannon_001.png");
    	Icon bombe = new ImageIcon("assets/sprites/Javarrr_booster_damage_001.png");
    	Icon startSpiel = new ImageIcon("assets/sprites/Javarrr_menu_start.png");
    	Icon verlassenSpiel = new ImageIcon("assets/sprites/Javarrr_menu_verlassen.png");
    	Icon musikAn = new ImageIcon("assets/sprites/Javarrr_menu_entmute_64x64.png");
    	Icon musikAus = new ImageIcon("assets/sprites/Javarrr_menu_mute_64x64.png");
    	Icon musikLauter = new ImageIcon("assets/sprites/Javarrr_menu_lauter_64x64.png");
    	Icon musikLeiser = new ImageIcon("assets/sprites/Javarrr_menu_leiser_64x64.png");


    	
    	//Erzeugen der Buttons und Label
        lblstats = new JLabel("Attribute:");
        lblschaden = new JLabel("Schaden: " + Statistics.getDamage());
        lblleben = new JLabel("Leben: " + Statistics.getHealth() +"|"+ Statistics.getMaxHealth());
        lblKanonen = new JLabel("Kanonen: " + Statistics.getAttackSpeed());
        lbllevel = new JLabel("Level: " + Statistics.getLevelNo() +"|10");
        lblscore = new JLabel("Punktestand");
        lblScoreAnzeige = new JLabel("" + Statistics.getScore());
        btschaden = new JButton(bombe);
        btschaden.setBorderPainted(false);
        btleben = new JButton(herz);
        btleben.setBorderPainted(false);
        btKanonen = new JButton(kanone);
        btKanonen.setBorderPainted(false);
        
        
        // Menu erstellen und Funktion der Buttons 
        Font c = new Font("Bookman Old Style", Font.PLAIN, 45);
        lblname = new JLabel("" + title);
        lblname.setFont(c);
        lblname.setBounds(120, 80, 505, 55);
        lblname.setBackground(new Color(229, 178, 129));
        lblname.setOpaque(true);
        frame.add(lblname);
        
        btStartSpiel = new JButton(startSpiel);
        btVerlassenSpiel = new JButton(verlassenSpiel);
        frame.add(btStartSpiel);
        btStartSpiel.setBounds(280, 200, 157, 65);
        frame.add(btVerlassenSpiel);
        btVerlassenSpiel.setBounds(280, 300, 157, 65);
        btStartSpiel.setFocusable(false);
		btVerlassenSpiel.setFocusable(false);
        
        // Buttons fuer Musik 
	     btMusikAn = new JButton(musikAn);
	     btMusikAus = new JButton(musikAus);
	     btMusikLauter = new JButton(musikLauter);
	     btMusikLeiser = new JButton(musikLeiser);
	     frame.add(btMusikAn);
	     frame.add(btMusikAus);
	     frame.add(btMusikLauter);
	     frame.add(btMusikLeiser);
	     btMusikAn.setBounds(700, 280, 64, 64);
	     btMusikAus.setBounds(700, 280, 64, 64);
	     btMusikLauter.setBounds(700, 360, 64, 64);
	     btMusikLeiser.setBounds(700, 440, 64, 64);
	     btMusikAn.setFocusable(false);
	     btMusikAus.setFocusable(false);
	     btMusikLauter.setFocusable(false);
	     btMusikLeiser.setFocusable(false);
	     btMusikAn.setVisible(false);

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
		icon = new ImageIcon("assets/sprites/Javarrr_Treasure-Map_002.png");
		scoreboard = new JLabel(icon);
		frame.add(scoreboard);
		scoreboard.setBounds(512,0,256,512);

		
		// Focus bleibt auf Player
		btschaden.setFocusable(false);
		btleben.setFocusable(false);
		btKanonen.setFocusable(false);

        //Funktion der Buttons
        btschaden.addActionListener( e -> {
        	Statistics.setDamage(Statistics.getDamage() + 1);
        lblschaden.setText("Schaden: " + Statistics.getDamage());
        boosterUnsichtbar();
        });
        btleben.addActionListener( e -> {
        	Statistics.setHealth(Statistics.getHealth() + 20);
        lblleben.setText("Leben: "+ Statistics.getHealth() +"|"+ Statistics.getMaxHealth());
        boosterUnsichtbar();
        });     
        btKanonen.addActionListener( e -> {
        	Statistics.setAttackSpeed(Statistics.getAttackSpeed() + 1);
        lblKanonen.setText("Kanonen: " + Statistics.getAttackSpeed());
        boosterUnsichtbar();
        });
        
        

		// Unterpunkte einfuegen
		game.add(start);
		game.add(highScores);
		game.add(settings);
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
		

		scoreboardUnsichtbar();
	}
	
	public void boosterUnsichtbar() {
        btschaden.setVisible(false);
        btleben.setVisible(false);
        btKanonen.setVisible(false);
	}
	
	public void boosterSichtbar() {
        btschaden.setVisible(true);
        btleben.setVisible(true);
        btKanonen.setVisible(true);

	}
	
	public void scoreboardUnsichtbar() {	
		scoreboard.setVisible(false);
		lblstats.setVisible(false);
		lblschaden.setVisible(false);
		lblleben.setVisible(false);
        lblKanonen.setVisible(false);
        lbllevel.setVisible(false);
        lblscore.setVisible(false);
        lblScoreAnzeige.setVisible(false);
        boosterUnsichtbar();

	}
	
	public void scoreboardSichtbar() {
		scoreboard.setVisible(true);
		lblstats.setVisible(true);
		lblschaden.setVisible(true);
		lblleben.setVisible(true);
        lblKanonen.setVisible(true);
        lbllevel.setVisible(true);
        lblscore.setVisible(true);
        lblScoreAnzeige.setVisible(true);
	}
	
	public void menuSichtbar() {
		 lblname.setVisible(true);
		 btStartSpiel.setVisible(true);
	     btVerlassenSpiel.setVisible(true);
	     btMusikAn.setVisible(true);
	     btMusikAus.setVisible(true);
	     btMusikLauter.setVisible(true);
	     btMusikLeiser.setVisible(true);
	}
	
	public void menuUnsichtbar() {
		 lblname.setVisible(false);
		 btStartSpiel.setVisible(false);
	     btVerlassenSpiel.setVisible(false);
	     btMusikAn.setVisible(false);
	     btMusikAus.setVisible(false);
	     btMusikLauter.setVisible(false);
	     btMusikLeiser.setVisible(false);
	}
	

	// Getter
	
	public Canvas getCanvas() {
		return canvas;
		
	}
	
	public JFrame getFrame() {
		return frame;
	}

}