package game;
import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {

	private static final long serialVersionUID = -8702099552366320139L;
	public  JFrame frame;
	private Canvas canvas;
	

	private String title;
	private int width, height;
	
	
	private JLabel scoreboard;
	private ImageIcon icon;
    private JLabel lblstats, lblschaden, lblleben, lblKanonen;
	public JLabel lbllevel;
    private JLabel lblscore, lblScoreAnzeige, lblMusicOn, lblMusicOff, lblMusicUp, lblMusicDown;
    
    private JLabel lblBreak;
    public JButton btnBreak;
    
    private JLabel lblKeybindings, lblMovement, lblShoot;
    private JButton btschaden, btleben, btKanonen;
    
    
    public JMenuItem start, highScores, exit;
    
    private JLabel lblname;
    public JButton btStartSpiel, btVerlassenSpiel;

    
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

		// Groesse des Fensters kann nicht durch Benutzer geaendert werden
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
		exit = new JMenuItem("Spiel verlassen");
		

		// Einfuegen der Bilder in Buttons
		Icon herz = new ImageIcon("assets/sprites/Javarrr_booster_hp-up_002.png");
    	Icon kanone = new ImageIcon("assets/sprites/Javarrr_booster_cannon_001.png");
    	Icon bombe = new ImageIcon("assets/sprites/Javarrr_booster_damage_001.png");
    
    	
    	Icon startSpiel = new ImageIcon("assets/sprites/Javarrr_menu_start.png");
    	Icon verlassenSpiel = new ImageIcon("assets/sprites/Javarrr_menu_verlassen.png");
    	
    	Icon resumeGame = new ImageIcon("assets/sprites/Javarrr_menu_start.png");
    	
    	
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
        
        
        lblKeybindings = new JLabel("Steuerung");
        lblMovement = new JLabel("Links: A | Rechts: D");
        lblShoot = new JLabel("Schiessen: Leertaste");
        lblMusicOn = new JLabel("Musik an: 1");
        lblMusicOff = new JLabel("Musik aus: 2");
        lblMusicUp = new JLabel("Musik lauter: 3");
        lblMusicDown = new JLabel("Musik leiser: 4");


        // Menu erstellen und Funktion der Buttons 
        Font g = new Font("Bookman Old Style", Font.PLAIN, 45);
        lblname = new JLabel("" + title);
        lblname.setFont(g);
        lblname.setBounds(120, 80, 505, 55);
        lblname.setBackground(new Color(229, 178, 129));
        lblname.setOpaque(true);
        frame.add(lblname);
        
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
        
		 //Breakpoint
        lblBreak = new JLabel(" Geschafft! Weiter so!");
        lblBreak.setFont(g);
        lblBreak.setBounds(280, 80, 320, 55);
        lblBreak.setBackground(new Color(229, 178, 129));
        lblBreak.setOpaque(true);
        frame.add(lblBreak);
        btnBreak = new JButton(resumeGame);
        frame.add(btnBreak);
        btnBreak.setBounds(120, 180, 157, 65);
        btnBreak.setFocusable(false);
		breakInvisible();
		
        //Festlegen Schriftart
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
        lblBreak.setFont(f);
        
        //Buttons und Labels dem Frame hinzufuegen und in Position bringen
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
        lblMusicOn.setVisible(false);
        lblMusicOff.setVisible(false);
        lblMusicUp.setVisible(false);
        lblMusicDown.setVisible(false);
        lblKeybindings.setVisible(false);
        lblMovement.setVisible(false);
        lblShoot.setVisible(false);
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
        lblMusicOn.setVisible(true);
        lblMusicOff.setVisible(true);
        lblMusicUp.setVisible(true);
        lblMusicDown.setVisible(true);
        lblScoreAnzeige.setVisible(true);
        lblKeybindings.setVisible(true);
        lblMovement.setVisible(true);
        lblShoot.setVisible(true);
	}
	
	public void menuSichtbar() {
		 lblname.setVisible(true);
		 btStartSpiel.setVisible(true);
	     btVerlassenSpiel.setVisible(true);
	}
	
	public void menuUnsichtbar() {
		 lblname.setVisible(false);
		 btStartSpiel.setVisible(false);
	     btVerlassenSpiel.setVisible(false);
	}
	
	public void breakVisible() {
		lblBreak.setVisible(true);
		btnBreak.setVisible(true);
	}
	
	public void breakInvisible() {
		lblBreak.setVisible(false);
		btnBreak.setVisible(false);
	}
	

	// Getter
	
	public Canvas getCanvas() {
		return canvas;
		
	}
	
	public JFrame getFrame() {
		return frame;
	}
	

}



//package game;
//import javax.swing.*;
//import java.awt.*;
//
//public class Window extends JFrame {
//
//	private static final long serialVersionUID = -8702099552366320139L;
//	public  JFrame frame;
//	private Canvas canvas;
//	
//
//	private String title;
//	private int width, height;
//	
//	
//	private JLabel scoreboard;
//	private ImageIcon icon;
//    private JLabel lblstats, lblschaden, lblleben, lblKanonen, lbllevel;
//    private JLabel lblscore, lblScoreAnzeige, lblMusicOn, lblMusicOff, lblMusicUp, lblMusicDown;
//    
//    private JLabel lblKeybindings, lblMovement, lblShoot;
//    private JButton btschaden, btleben, btKanonen;
//    
//    
//    public JMenuItem start, highScores, exit;
//    
//    private JLabel lblname;
//    public JButton btStartSpiel, btVerlassenSpiel;
//
//    
//	public Window(String title, int width, int height) {
//		this.title = title;
//		this.width = width;
//		this.height = height;
//
//		startWindow();
//	}
//
//	private void startWindow() {
//		frame = new JFrame(title);
//
//		// Setzt die Groesse des Fensters
//		frame.setPreferredSize(new Dimension(width, height));
//		frame.setMaximumSize(new Dimension(width, height));
//		frame.setMinimumSize(new Dimension(width, height));
//
//		// Killt Prozess, wenn Anwendung beendet wird
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//		// Groesse des Fensters kann nicht durch Benutzer ge�ndert werden
//		frame.setResizable(false);
//
//		// Fenster erscheint in der Mitte des Bildschirms
//		frame.setLocationRelativeTo(null);
//
//		frame.setVisible(true);
//		
//		
//		// Erzeugt Menueleiste
//		JMenuBar mbar = new JMenuBar();
//
//		// Erzeugt Menues
//		JMenu game = new JMenu("Spiel");
//
//		// Menuepunkte erzeugen
//		start = new JMenuItem("Neues Spiel");
//		highScores = new JMenuItem("Scoreboard");
//		exit = new JMenuItem("Spiel verlassen");
//		
//
//		// Einf�gen der Bilder in Buttons
//		Icon herz = new ImageIcon("assets/sprites/Javarrr_booster_hp-up_002.png");
//    	Icon kanone = new ImageIcon("assets/sprites/Javarrr_booster_cannon_001.png");
//    	Icon bombe = new ImageIcon("assets/sprites/Javarrr_booster_damage_001.png");
//    
//    	
//    	Icon startSpiel = new ImageIcon("assets/sprites/Javarrr_menu_start.png");
//    	Icon verlassenSpiel = new ImageIcon("assets/sprites/Javarrr_menu_verlassen.png");
//
//
//    	
//    	//Erzeugen der Buttons und Label
//        lblstats = new JLabel("Attribute:");
//        lblschaden = new JLabel("Schaden: " + Statistics.getDamage());
//        lblleben = new JLabel("Leben: " + Statistics.getHealth() +"|"+ Statistics.getMaxHealth());
//        lblKanonen = new JLabel("Kanonen: " + Statistics.getAttackSpeed());
//        lbllevel = new JLabel("Level: " + Statistics.getLevelNo() +"|10");
//        lblscore = new JLabel("Punktestand");
//        lblScoreAnzeige = new JLabel("" + Statistics.getScore());
//        btschaden = new JButton(bombe);
//        btschaden.setBorderPainted(false);
//        btleben = new JButton(herz);
//        btleben.setBorderPainted(false);
//        btKanonen = new JButton(kanone);
//        btKanonen.setBorderPainted(false);
//        
//        
//        lblKeybindings = new JLabel("Steuerung");
//        lblMovement = new JLabel("Links: A | Rechts: D");
//        lblShoot = new JLabel("Schiessen: Leertaste");
//        lblMusicOn = new JLabel("Musik an: 1");
//        lblMusicOff = new JLabel("Musik aus: 2");
//        lblMusicUp = new JLabel("Musik leiser: 3");
//        lblMusicDown = new JLabel("Musik lauter: 4");
//        
//        
//        
//        
//        
//        
//        // Menu erstellen und Funktion der Buttons 
//        Font g = new Font("Bookman Old Style", Font.PLAIN, 45);
//        lblname = new JLabel("" + title);
//        lblname.setFont(g);
//        lblname.setBounds(120, 80, 505, 55);
//        lblname.setBackground(new Color(229, 178, 129));
//        lblname.setOpaque(true);
//        frame.add(lblname);
//        
//        btStartSpiel = new JButton(startSpiel);
//        btVerlassenSpiel = new JButton(verlassenSpiel);
//        frame.add(btStartSpiel);
//        btStartSpiel.setBounds(280, 200, 157, 65);
////        btStartSpiel.setBorderPainted(false);
//        frame.add(btVerlassenSpiel);
//        btVerlassenSpiel.setBounds(280, 300, 157, 65);
////        btVerlassenSpiel.setBorderPainted(false);
//
//        btStartSpiel.setFocusable(false);
//		btVerlassenSpiel.setFocusable(false);
//              
//        //Festlegen Schriftart
//		Font c = new Font("Bookman Old Style", Font.PLAIN, 15);
//        Font d = new Font("Bookman Old Style", Font.PLAIN, 20);
//        Font f = new Font("Bookman Old Style", Font.PLAIN, 30);
//        lblstats.setFont(f);
//        lblschaden.setFont(d);
//        lblleben.setFont(d);
//        lblKanonen.setFont(d);
//        lbllevel.setFont(f);
//        lblscore.setFont(f);
//        lblScoreAnzeige.setFont(d);
//        lblMusicOn.setFont(c);
//        lblMusicOff.setFont(c);
//        lblMusicUp.setFont(c);
//        lblMusicDown.setFont(c);
//        lblKeybindings.setFont(d);
//        lblMovement.setFont(c);
//        lblShoot.setFont(c);
//        
//        //Buttons und Labels dem Frame hinzuf�gen und in Position bringen
//        frame.add(lblstats);
//        lblstats.setBounds(542, 130, 150, 30);        
//        frame.add(lblschaden);
//        lblschaden.setBounds(542, 200, 150, 30);        
//        frame.add(lblleben);
//        lblleben.setBounds(542, 170, 200, 30);        
//        frame.add(lblKanonen);
//        lblKanonen.setBounds(542, 230, 350, 30);  
//        frame.add(lbllevel);
//        lbllevel.setBounds(552, 10, 200, 35);
//        frame.add(btschaden);
//        btschaden.setBounds(532, 290, 50, 50);
//        frame.add(btKanonen);
//        btKanonen.setBounds(612, 290, 50, 50);
//        frame.add(btleben);
//        btleben.setBounds(692, 290, 50, 50);
//        frame.add(lblscore);
//        lblscore.setBounds(542, 40, 200, 50);
//        frame.add(lblScoreAnzeige);
//        lblScoreAnzeige.setBounds(622, 70, 100, 50);
//        frame.add(lblKeybindings);
//        lblKeybindings.setBounds(542, 370, 150, 50);
//        frame.add(lblMovement);
//        lblMovement.setBounds(542, 405, 200, 15);
//        frame.add(lblShoot);
//        lblShoot.setBounds(542, 420, 200, 15);
//        frame.add(lblMusicOn);
//        lblMusicOn.setBounds(542, 435, 150, 15);
//        frame.add(lblMusicOff);
//        lblMusicOff.setBounds(542, 450, 150, 15);
//        frame.add(lblMusicUp);
//        lblMusicUp.setBounds(542, 465, 150, 15);
//        frame.add(lblMusicDown);
//        lblMusicDown.setBounds(542, 480, 150, 15);
//
//        //Grafik des Scoreboard
//		icon = new ImageIcon("assets/sprites/Javarrr_Treasure-Map_002.png");
//		scoreboard = new JLabel(icon);
//		frame.add(scoreboard);
//		scoreboard.setBounds(512,0,256,512);
//
//		
//		// Focus bleibt auf Player
//		btschaden.setFocusable(false);
//		btleben.setFocusable(false);
//		btKanonen.setFocusable(false);
//
//		//Funktion der Buttons
//        btschaden.addActionListener( e -> {
//        	Statistics.setDamage(Statistics.getDamage() + 1);
//        lblschaden.setText("Schaden: " + Statistics.getDamage());
//        boosterUnsichtbar();
//        });
//        btleben.addActionListener( e -> {
//        	Statistics.setHealth(Statistics.getHealth() + 20);
//        lblleben.setText("Leben: "+ Statistics.getHealth() +"|"+ Statistics.getMaxHealth());
//        boosterUnsichtbar();
//        });     
//        btKanonen.addActionListener( e -> {
//        	Statistics.setAttackSpeed(Statistics.getAttackSpeed() + 1);
//        lblKanonen.setText("Kanonen: " + Statistics.getAttackSpeed());
//        boosterUnsichtbar();
//        });
//        
//
//		// Unterpunkte einfuegen
//		game.add(start);
//		game.add(highScores);
//		game.add(exit);
//
//		mbar.add(game);
//
//		// MenuBar anzeigen
//		frame.setJMenuBar(mbar);
//
//		canvas = new Canvas();
//		canvas.setPreferredSize(new Dimension(width, height));
//		canvas.setMaximumSize(new Dimension(width, height));
//		canvas.setMinimumSize(new Dimension(width, height));
//		
//		// Fix: Focusverlust 
//		canvas.setFocusable(false);
//		
//		frame.add(canvas);
//
//		// Passt die Groesse an
//		frame.pack();
//		
//
//		scoreboardUnsichtbar();
//	}
//	
//	
//	public void boosterUnsichtbar() {
//        btschaden.setVisible(false);
//        btleben.setVisible(false);
//        btKanonen.setVisible(false);
//	}
//	
//	public void boosterSichtbar() {
//        btschaden.setVisible(true);
//        btleben.setVisible(true);
//        btKanonen.setVisible(true);
//
//	}
//	
//	public void scoreboardUnsichtbar() {	
//		scoreboard.setVisible(false);
//		lblstats.setVisible(false);
//		lblschaden.setVisible(false);
//		lblleben.setVisible(false);
//        lblKanonen.setVisible(false);
//        lbllevel.setVisible(false);
//        lblscore.setVisible(false);
//        lblScoreAnzeige.setVisible(false);
//        lblMusicOn.setVisible(false);
//        lblMusicOff.setVisible(false);
//        lblMusicUp.setVisible(false);
//        lblMusicDown.setVisible(false);
//        lblKeybindings.setVisible(false);
//        lblMovement.setVisible(false);
//        lblShoot.setVisible(false);
//        boosterUnsichtbar();
//
//	}
//	
//	public void scoreboardSichtbar() {
//		scoreboard.setVisible(true);
//		lblstats.setVisible(true);
//		lblschaden.setVisible(true);
//		lblleben.setVisible(true);
//        lblKanonen.setVisible(true);
//        lbllevel.setVisible(true);
//        lblscore.setVisible(true);
//        lblMusicOn.setVisible(true);
//        lblMusicOff.setVisible(true);
//        lblMusicUp.setVisible(true);
//        lblMusicDown.setVisible(true);
//        lblScoreAnzeige.setVisible(true);
//        lblKeybindings.setVisible(true);
//        lblMovement.setVisible(true);
//        lblShoot.setVisible(true);
//	}
//	
//	public void menuSichtbar() {
//		 lblname.setVisible(true);
//		 btStartSpiel.setVisible(true);
//	     btVerlassenSpiel.setVisible(true);
//	}
//	
//	public void menuUnsichtbar() {
//		 lblname.setVisible(false);
//		 btStartSpiel.setVisible(false);
//	     btVerlassenSpiel.setVisible(false);
//	}
//	
//
//	// Getter
//	
//	public Canvas getCanvas() {
//		return canvas;
//		
//	}
//	
//	public JFrame getFrame() {
//		return frame;
//	}
//	
//
//}
