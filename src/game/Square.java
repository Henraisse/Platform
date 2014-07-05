package game;

import java.util.ArrayList;

import tools.Position;
import army.Soldier;

public class Square {
	public ArrayList<Soldier> squareContent = new ArrayList<Soldier>();
	public Position index;
	public Square(int i, int j){
		index = new Position(i,j);
	}
}
