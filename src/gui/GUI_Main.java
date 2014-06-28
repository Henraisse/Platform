package gui;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JButton;

import army.Battle_Setup;


public class GUI_Main {
		Image_Pack im;
		Fullscreen_Window vm;
		Window_Content wc;
		
		public GUI_Main(Battle_Setup bs, Image_Pack im){
			vm = new Fullscreen_Window();					//create a new window for our program
			wc = new Window_Content(vm, im, bs);			//create content and add to the window
		}
	
}


//		Vi behöver en config-fil, där man kan snabbstyra saker och ting
//		en importmapp, där den importerar filer och dylikt