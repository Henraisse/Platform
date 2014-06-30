package main;

import settings.Variables;
import tools.Position;
import army.Battle_Setup;
import army.Soldier;
import gui.*;
public class Main {

	public static void main(String[] args){
		
		String path = "C:\\Users\\Henrik\\Desktop\\Military_school";
		
		Game game = new Game(path);		
		GUI_Main gui = new GUI_Main(game.bs, game.imp);		//creates the GUI, and ties the battle_setup to it.
		
	}

}
