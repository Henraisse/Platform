package army;


import game.Map;
import gui.Image_Pack;

import java.util.ArrayList;

import main.Game;
import settings.Variables;
import tools.Effect;
import tools.Projectile;

public class Battle_Setup {
	
	
	public Variables variables;
	public Map map;
	public Game game;
	
	int[] teams = new int[2];
	public ArrayList<Soldier> soldiers;
	public ArrayList<Projectile> projectiles;
	public ArrayList<Effect> effects;
	public String path;
	
	public Battle_Setup(Game game, Image_Pack imp, Map mappi, String path){		
		this.path = path;
		variables = new Variables(this, imp);
		soldiers = new ArrayList<Soldier>();
		projectiles = new ArrayList<Projectile>();
		effects = new ArrayList<Effect>();
		map = mappi;
		this.game = game;
		
	}
	
	public void simulationStep(int counter){
		
		int removeCount = 0;
		for(int i = projectiles.size()-1; i >= 0; i--){
			projectiles.get(i).action();
			if(projectiles.get(i).remove == true){
				projectiles.remove(i);
				removeCount++;
			}
		}
		
		for(int i = effects.size()-1; i >= 0; i--){
			effects.get(i).action();
			if(effects.get(i).remove == true){
				effects.remove(i);
				removeCount++;
			}
		}
		
		for(int i = 0; i < soldiers.size(); i++){
			soldiers.get(i).action(counter);
		}		
	}
	//This class is to be filled with everything that one can paint or modify in simulation.
	//Soldiers, tanks, squad leaders, commanders, resources, and so on
	
}
