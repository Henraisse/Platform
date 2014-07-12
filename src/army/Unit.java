package army;

import game.Square;

import java.util.ArrayList;

import tools.Effect;
import tools.Position;
import tools.Projectile;
import tools.Visual_Effect;

public class Unit {
	public Position pos;
	public int health;
	public int initialhealth;
	public int armor;
	public double speed;
	public double lineOfSight;
	
	public boolean isDead = false;
	public boolean isMoving = false;
	public boolean isFiring = false;
	public boolean targetFound = false;
	
	public int allegiance;
	public int appearence;
	public Weapon armament;
	public Battle_Setup battle_setup;
	
	public Soldier targetUnit;
	public Position target;
	Visual_Effect idleEffect;
	Visual_Effect walkEffect;
	Visual_Effect fireEffect;
	Visual_Effect deathEffect;
	
	public int fireimagecounter = 0;
	public int walkimagecounter = 0;
	public int idleimagecounter = 0;
	public int imgCount = 0;
	
	int size;
	int sight = 10000;
	
	public Unit(){}
	
	public ArrayList<Soldier> scanVicinity(){
		ArrayList<Soldier> content = new ArrayList<Soldier>();
		//läs av vilken ruta vi står på
			//ta alla rutor
			for(Square[] s: battle_setup.map.dwellers){
				for(Square r: s){
					//räkna ut avstånd
					double distance = new Position(
							(r.index.x+0.5)*battle_setup.map.squareSize, 
							(r.index.y+0.5)*battle_setup.map.squareSize)
					.distance(pos);
					
					if(distance < sight){
						//if(r.squareContent.size() > 0){System.out.println("size is :" + r.squareContent);}
						content.addAll(r.squareContent);
					}
				}
			}
			//avgör vilka rutor som har rätt avstånd
		for(int i = content.size()-1; i >= 0; i--){
			if(content.get(i).equals(this) || content.get(i).allegiance == allegiance || content.get(i).isDead){
				content.remove(i);
				i = -1;
			}
		}
		return content;
	}
	
	public boolean findClosestEnemy(){

		//skapa en lista på alla fiender som enheten ser
		ArrayList<Soldier> closest = scanVicinity();

		//titta igenom hela listan
		for(Soldier s: closest){

			if(validateTarget(s)){
				targetUnit = s;
			}
			
		}
		if(targetUnit == null || targetUnit.allegiance == allegiance || targetUnit.isDead){
			targetUnit = null;
			return false;
		}
		setTarget();
		return true;
	}
	
	public void setTarget(){
		if(targetUnit == null){return;}
		target = new Position(targetUnit.pos.x, targetUnit.pos.y);
	}
	
	public void walkTowardTarget(double pace){
		if(target == null){return;}
		//ta bort från nuvarande ruta 
		
		removeFromMapGrid();		
		pos.add(target.minus(pos).normalize().times(speed * pace));
		addToMapGrid();
		//lägg till i ny ruta
	}
	
	
	public void attackTarget(){
		boolean found = true;
		if(targetUnit.health <= 0){
			found = findClosestEnemy();						
		}
		if(found == true && targetUnit.health > 0){
			setTarget();
			if(target.distance(pos) <= armament.range){
				targetUnit.takeDamage(armament);
				isFiring = true;
				battle_setup.projectiles.add(new Projectile(pos, target, armament));
			}
			else{isFiring = false;}
		}
		else{isFiring = false;}
	}
	
	public boolean validateTarget(Soldier s){
		if(s.allegiance == allegiance){return false;}
		if(s.isDead){return false;}
		if(targetUnit != null){
			if(s.pos.distance(pos) >= targetUnit.pos.distance(pos)){		
				if(targetUnit.isDead == false){return false;}
			}
		}
		return true;
	}
	
	public void takeDamage(Weapon w){
		if(w.damage > armor){
			health -= (w.damage - armor);
		}
		else{
			//DO NOTHING. NOT HURT BY THIS WEAPON.
		}
	}
	
	public void liveOrDie(){
		if(health <= 0 && isDead == false){
			isDead = true;
			battle_setup.effects.add(new Effect(pos.x, pos.y, deathEffect.name, battle_setup));
		}
	}
	
	public boolean targetIsWithinRange(){
		if(targetUnit == null){return false;}
		if(targetUnit.pos.distance(pos) > armament.range){return false;}
		return true;
	}
	
	public void removeFromMapGrid(){
		battle_setup.map.dwellers
		[(int) (pos.x/battle_setup.map.squareSize)]
		[(int) (pos.y/battle_setup.map.squareSize)]
		.squareContent.remove(this);
	}
	
	public void addToMapGrid(){
		if(pos.x >= 0 && pos.y >= 0 && 
				pos.x < battle_setup.map.dimension.x && 
				pos.y < battle_setup.map.dimension.y){
		battle_setup.map.dwellers
		[(int) (pos.x/battle_setup.map.squareSize)]
		[(int) (pos.y/battle_setup.map.squareSize)]
		.squareContent.add((Soldier) this);
		}
		
	}
	
	public boolean isWithinBounds(Position a, Position b){
		if((pos.x < a.x && pos.x > b.x) && (pos.y < a.y && pos.y > b.y)){return true;}
		if((pos.x < a.x && pos.x > b.x) && (pos.y < b.y && pos.y > b.y)){return true;}
		if((pos.x < b.x && pos.x > a.x) && (pos.y < a.y && pos.y > b.y)){return true;}
		if((pos.x < b.x && pos.x > a.x) && (pos.y < b.y && pos.y > a.y)){return true;}
				
		return false;
	}
	
}






