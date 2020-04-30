package states;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import game.Game;
import game.Window;
import graphics.Animation;
import graphics.Assets;
import graphics.Background;

public class MenuState extends State{

	
	private State gameState;
	private Window window; 
	private Background background;
	private JFrame frame;
	
	private JButton btnstart = new JButton();
	private JButton btnverlassen = new JButton();
	
	
	public MenuState(Game game) {
		super(game);
		background = new Background(game);
		window = game.getWindow();
		frame = window.getFrame();	

	}
	
	public void mousePressed(MouseEvent e) {
		int mx= e.getX();
		int my = e.getY();

			if(mouseOver(mx, my,260, 140, 210, 64)) {
				
				System.exit(0);
				
			}
			
		}
	
	
	public boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
		if(mx > x && mx < x + width) {
			if(my > y && my < y + height) {
				return true;
			}else return false;
		}else return false;
	}
	
	public void mouseMoved(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
	}
	
	
	private void openMenu() {	
		// Einfügen der Bilder in Buttons
		Icon start = new ImageIcon(Assets.menu[0]);
		Icon verlassen = new ImageIcon(Assets.menu[1]);
		
		
		btnstart = new JButton(start);
		btnstart.setBorderPainted(false);
		btnverlassen = new JButton(verlassen);
		btnverlassen.setBorderPainted(false);
		
		btnstart.setBounds(692, 290, 50, 50);
		frame.add(btnstart);
		btnverlassen.setBounds(692, 290, 50, 50);
		frame.add(btnverlassen);
		frame.repaint();
		btnstart.addActionListener(e -> {
			gameState = new GameState(game);
			State.setState(gameState);
		});

	}

	@Override
	public void update() {
		background.update();
	}

	@Override
	public void render(Graphics graphics) {
		if(State.getState() !=gameState) {
			Font fnt0 = new Font("Bookman Old Style", Font.BOLD, 50);
			background.render(graphics);
			graphics.setFont(fnt0);
			graphics.setColor(Color.white);
			graphics.drawString("JAVARRR", game.width/4, 100);
			
			
			graphics.drawRect(260, 140, 210, 64);
			graphics.drawString("Spielen", 270, 190);
			
			
			
			
				
		}
		

		
		

		
	}
	
	

}