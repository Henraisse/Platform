package main;

import game.Map;
import gui.Image_Pack;

import java.util.ArrayList;
import java.util.Random;

import army.Battle_Setup;
import army.Soldier;
import team.Player;
import tools.Position;

public class Game {
	
	
	public Image_Pack imp;
	public Battle_Setup bs;
	public ArrayList<Player> players = new ArrayList<Player>();
	public Map map = new Map(1600, 1000);
	
	public Game(String filepath){
		imp = new Image_Pack(filepath);
		bs = new Battle_Setup(imp);
		
		setupTestCase001();
	}
	
	
	
	public void addPlayer(Player p){
		if(players.contains(p) == false){
			players.add(p);
		}
	}
	
	
	public void setupTestCase001(){
		bs.soldiers.add(new Soldier(390,500, bs, 0, "SOLDIER"));		
		
		for(int i = 0; i < 30; i++){
			
			Position zPos = new Position(0,0);
			zPos.randomize(map.dimension.x, map.dimension.y);
			bs.soldiers.add(new Soldier(zPos.x,zPos.y, bs, 1, "ZOMBIE"));
		}
	}
	
	
}
