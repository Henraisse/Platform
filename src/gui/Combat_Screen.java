package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import army.Army_Painter;
import army.Battle_Setup;
import army.Soldier;

public class Combat_Screen extends JPanel{
	int counter = 0;
	
	Image_Pack images;
	Battle_Setup battleSetup;
	Army_Painter ap;
	
	public Combat_Screen(Image_Pack images, Battle_Setup bs){
		this.images = images;
		battleSetup = bs;
		ap = new Army_Painter(bs);
		addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                //om musen trycks
            	battleSetup.game.players.get(0).mouseDown = true;
            	battleSetup.game.players.get(0).mouseDownPos.x = e.getX();
            	battleSetup.game.players.get(0).mouseDownPos.y = e.getY();
            	battleSetup.game.players.get(0).mouseDownButton = e.getButton();
            	//hämta den nuvarande spelaren
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                //om musen släpps
            	battleSetup.game.players.get(0).mouseUp = true;
            	battleSetup.game.players.get(0).mouseUpPos.x = e.getX();
            	battleSetup.game.players.get(0).mouseUpPos.y = e.getY();
            	
            }
        });
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		battleSetup.simulationStep(counter);		
		battleSetup.game.playersAction();
		counter++;

		ap.paint(g, this);

		g.drawString("FPS: " + counter, 5, 10);				
	}
	
	public void update(){
		repaint();
	}
}
