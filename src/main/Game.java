package main;

import game.Map;
import gui.Image_Pack;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;

import army.Battle_Setup;
import army.Soldier;
import team.Player;
import tools.Position;
import tools.Statics;

public class Game {
	
	public int loopSleepTime = Statics.loopSleepTime;
	public Image_Pack imp;
	public Battle_Setup bs;
	public ArrayList<Player> players = new ArrayList<Player>();
	public Map map = new Map(1600, 1000, 50);
	
	public Game(String filepath){
		imp = new Image_Pack(filepath);
		bs = new Battle_Setup(this, imp, map, filepath);
		
		setupTestCase001();
	}
	
	
	
	public void addPlayer(Player p){
		if(players.contains(p) == false){
			players.add(p);
		}
	}
	
	
	public void setupTestCase001(){
		bs.soldiers.add(new Soldier(390,500, bs, 0, "MAMMA"));		
		addPlayer(new Player(1));
		for(int i = 0; i < 1000; i++){
			
			Position zPos = new Position(0,0);
			zPos.randomize(map.dimension.x, map.dimension.y);
			bs.soldiers.add(new Soldier(zPos.x,zPos.y, bs, 1, "ZOMBIE"));
		}
	}
	
	public void playersAction(){
		int i = 1;
		Player p = players.get(0);
		
		//IF PLAYER HAS DONE AN ACTION
		if(p.mouseDown == true && p.mouseUp == true){
			p.mouseSelection = false;
			p.mouseDown = false;
			p.mouseUp = false;			
			
			//IF WE SELECT A CERTAIN AREA
			if(p.mouseDownButton == MouseEvent.BUTTON1 && p.mouseDownPos.distance(p.mouseUpPos) > 4){
				p.mouseSelection = true;
				//selection of soldiers is done
				for(Soldier s: bs.soldiers){
					if(i == s.allegiance){
						if(s.isWithinBounds(p.mouseDownPos, p.mouseUpPos)){s.selected = true;}
						else{s.selected = false;}
					}
				}				
			}
			//IF PLAYER CLICKED SOMEWHERE
			else{
				 induceUnitAction();
			}
		}
	}
	
	public void induceUnitAction(){
		int currentAction = 0;
		//vi har markerat enheter, och klickar nu någonstans. Vad händer?
		
		//action: none
			//select unit within position bounds
		
		
		//action: attack
			//attack object at position bounds
		
		//action: move
			//move to position or object
		
		//action: create
			//create object at position
	}	
	
	
}









