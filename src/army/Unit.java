package army;

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
	
	public int allegiance;
	public int appearence;
	public Weapon armament;
	public Battle_Setup battle_setup;
	
	public Soldier targetUnit;
	public Position target;
	Visual_Effect deathEffect;
	int size = 20;
	
	public Unit(){}
	
	
	public boolean findClosestEnemy(){
		if(targetUnit == null){targetUnit = new Soldier(-999999,-999999,battle_setup,-1,"ZOMBIE");}
		for(Soldier s: battle_setup.soldiers){
			//System.out.println((int)s.pos.x + ", " + (int)s.pos.y);
			if(s.pos.distance(pos) < targetUnit.pos.distance(pos)){
				if(s.allegiance != allegiance && s.health > 0){
					//System.out.println();
					targetUnit = s;
				}
			}
		}
		if(targetUnit.pos.x == -999999 && targetUnit.pos.y == -999999){
			targetUnit = null;
			return false;
		}
		return true;
	}
	
	public void setTarget(double x, double y){
		target = new Position(x,y);
	}
	
	public void walkTowardTarget(double pace){
		pos.add(target.minus(pos).normalize().times(speed * pace));
	}
	
	public void attackTarget(){
		boolean found = true;
		if(targetUnit.health <= 0){
			found = findClosestEnemy();						
		}
		if(found == true && targetUnit.health > 0){
			setTarget(targetUnit.pos.x, targetUnit.pos.y);
			if(target.distance(pos) <= armament.range){
				targetUnit.takeDamage(armament);
				battle_setup.projectiles.add(new Projectile(pos, target, armament));
			}
		}
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
	

}






