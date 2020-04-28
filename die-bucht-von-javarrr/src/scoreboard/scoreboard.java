package scoreboard;


import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.io.File;
import java.io.IOException;

import javax.imageio.*;
import javax.swing.*;

public class scoreboard extends JFrame {
   
	private JLabel hintergrund;
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
     
    public scoreboard() {
    	
    	Icon herz = new ImageIcon(getClass().getResource("Button.png"));
    	Icon kanone = new ImageIcon(getClass().getResource("KanoneW.png"));
    	
        lblstats = new JLabel("Attribute:");
        lblschaden = new JLabel("Schaden: " + schaden);
        lblleben = new JLabel("Leben: " + leben+"|"+maxLeben);
        lblKanonen = new JLabel("Kanonen: " + kanonen);
        lbllevel = new JLabel("Level: " + level +"|10");
        lblscore = new JLabel("Punktestand");
        lblScoreAnzeige = new JLabel("" +punkte);
        btschaden = new JButton("DMG+");
        btschaden.setBorderPainted(false);
        btleben = new JButton(herz);
        btleben.setBorderPainted(false);
        btKanonen = new JButton(kanone);
        btKanonen.setBorderPainted(false);

        
        Container contentPane = this.getContentPane();
        
        
        Font d = new Font("Bookman Old Style", Font.PLAIN, 20);
        Font f = new Font("Bookman Old Style", Font.PLAIN, 30);
       
        
        lblstats.setFont(f);
        lblschaden.setFont(d);
        lblleben.setFont(d);
        lblKanonen.setFont(d);
        lbllevel.setFont(f);
        lblscore.setFont(f);
        lblScoreAnzeige.setFont(f);
        
        
        contentPane.add(lblstats);
        lblstats.setBounds(30, 130, 150, 30);        
        contentPane.add(lblschaden);
        lblschaden.setBounds(30, 200, 150, 30);        
        contentPane.add(lblleben);
        lblleben.setBounds(30, 170, 200, 30);        
        contentPane.add(lblKanonen);
        lblKanonen.setBounds(30, 230, 350, 30);  
        contentPane.add(lbllevel);
        lbllevel.setBounds(40, 10, 200, 35);
        contentPane.add(btschaden);
        btschaden.setBounds(20, 290, 50, 50);
        contentPane.add(btKanonen);
        btKanonen.setBounds(100, 275, 50, 50);
        contentPane.add(btleben);
        btleben.setBounds(180, 290, 50, 50);
        contentPane.add(lblscore);
        lblscore.setBounds(30, 40, 200, 50);
        contentPane.add(lblScoreAnzeige);
        lblScoreAnzeige.setBounds(110, 70, 100, 50);
        
       
        ImageIcon img = new ImageIcon(getClass().getResource("Hintergrund.png"));
        hintergrund = new JLabel("",img,JLabel.CENTER);
        hintergrund.setBounds(0, 0, 256, 512);
        contentPane.add(hintergrund);
        
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
        
        
        contentPane.setLayout(null);
    

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
             
    }
    public static void main(String[] args)  {
        scoreboard frame = new scoreboard();
        frame.setVisible(true);
        frame.setPreferredSize(new Dimension(256, 512));
        frame.pack();
        frame.getContentPane().setBackground(Color.GRAY);
       
    
    }
            
}
