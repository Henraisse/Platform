package gui;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;

import army.Battle_Setup;

/**
 * This class contains every button, window, screen, slider, checkbox, label and other JComponents we wish to cram down into the
 * fullscreen window. Every setting to these components are also found here. This is to simplify code, and make it more easily navigated.
 * @author Henrik Rönnholm (henraisse@gmail.com)
 * @version 1.0
 * @since 2014-06-13
 */
public class Window_Content {

	Image_Pack images;
	
	
	public Window_Content(Fullscreen_Window fw, Image_Pack images, Battle_Setup bs){
		this.images = images;
		addAllComponents(fw, bs);
	}
	
	
	public void addAllComponents(Fullscreen_Window fw, Battle_Setup bs){
		JButton jb1 = new JButton("Hej");
		jb1.addActionListener(fw);
		jb1.setAlignmentX( Component.CENTER_ALIGNMENT );//0.0
		jb1.setMaximumSize(new Dimension(80, 25));
		jb1.setPreferredSize(new Dimension(80, 25));
        //fw.add(jb1, BorderLayout.CENTER);
		Combat_Screen cs = new Combat_Screen(images, bs);
		fw.add(cs);
        fw.setVisible(true);
        
        
        
        while(true){
        	try {
        	    Thread.sleep(bs.game.loopSleepTime);
        	} catch(InterruptedException ex) {
        	    Thread.currentThread().interrupt();
        	}
        	cs.update();
        	}
	}
	
	
	
}







