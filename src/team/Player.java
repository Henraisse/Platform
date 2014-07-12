package team;

import java.awt.Color;
import java.util.ArrayList;

import tools.Position;
import army.Soldier;

public class Player {
	public int number;
	public Color teamColor;
	public String IP_ADRESS;
	public ArrayList<Soldier> selectedUnits = new ArrayList<Soldier>();
	
	public boolean mouseDown = false;
	public boolean mouseUp = false;
	public Position mouseDownPos = new Position(0,0);
	public Position mouseUpPos = new Position(0,0);
	public int mouseDownButton = -9999;
	
	public Player(int i){
		number = i;
	}
	
	
}
