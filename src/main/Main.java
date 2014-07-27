package main;

import java.io.IOException;

import settings.Variables;
import tools.Position;
import army.Battle_Setup;
import army.Soldier;
import gui.*;
public class Main {

	public static void main(String[] args) throws IOException{
		
		String path = "C:\\Users\\Henraisse\\Desktop\\Military_school";
		
		//s.playSound("c:\\sound.wav");
		
		
		Game game = new Game(path);		
		GUI_Main gui = new GUI_Main(game.bs, game.imp);		//creates the GUI, and ties the battle_setup to it.
		

		
	}

}
