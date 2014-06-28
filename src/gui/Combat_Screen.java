package gui;

import java.awt.Graphics;
import java.awt.Image;

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
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		battleSetup.simulationStep(counter);				
		counter++;

		ap.paint(g, this);

		g.drawString("FPS: " + counter, 5, 10);				
	}
	
	public void update(){
		repaint();
	}
}
