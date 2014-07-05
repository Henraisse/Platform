package game;

import tools.Position;

public class Map {
	public Position dimension;
	public Square[][] dwellers;
	public int squareSize;
	
	public Map(int x, int y, int squareSize){
		this.squareSize = squareSize;
		dimension = new Position(x, y);
		dwellers = new Square[x/squareSize][y/squareSize];
		
		for(int i = 0; i < dwellers.length; i++){
			for(int j = 0; j < dwellers[j].length; j++){
				dwellers[i][j] = new Square(i,j);
			}
		}
		
	}
}
