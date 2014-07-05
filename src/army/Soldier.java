package army;

import settings.UnitConfiguration;
import settings.Variables;
import tools.Position;
import tools.Visual_Effect;

public class Soldier extends Unit{
		public String type;
		int team = -1;
	
	public Soldier(double x, double y, Battle_Setup bs, int team, String type){
		this.type = type;
		this.team = team;
		this.allegiance = team;
		this.battle_setup = bs;
		pos = new Position(x,y);
		addToMapGrid();
		//targetUnit = new Soldier(pos.x, -99999, bs, -1, "ZOMBIE");
		//starget = new Position(pos.x, -99999);
		
		setSpecs(type);
	}
	
	private void setSpecs(String type) {
		UnitConfiguration unit = battle_setup.variables.getUnitType(type);

		
		
		health = unit.health;
		initialhealth = health;
		armor = unit.armor;
		speed = unit.speed;
		size = unit.size;
		lineOfSight = unit.lineOfSight;			
		appearence = unit.appearence;
		armament = unit.armament;	
		idleEffect = unit.idleEffect;
		walkEffect = unit.walkEffect;
		fireEffect = unit.fireEffect;
		deathEffect = unit.deathEffect;
		
	}

	/**
	 * Makes this soldier take action, and move, look, shoot, and do other things.
	 */
	public void action(int counter){
		if(isDead == true){return;}
		liveOrDie();
		
		//LITE DÅ OCH DÅ SKA ENHETERNA LETA EFTER ETT MÅL
		if(counter%5 == 0){
			findClosestEnemy();
		}
		
		if(targetIsWithinRange()){
			fireimagecounter++;
			walkimagecounter = 0;
			idleimagecounter = 0;
			imgCount = fireimagecounter % fireEffect.counter;
			
			if(counter%5 == 0){
				isFiring = true;
				isMoving = false;
				attackTarget();
			}
		}
		else if(targetUnit != null){
			walkTowardTarget(1.0);
			walkimagecounter++;
			fireimagecounter = 0;
			idleimagecounter = 0;
			isFiring = false;
			isMoving = true;
			imgCount = walkimagecounter % walkEffect.counter;
		}
		
		else{
			isFiring = false;
			isMoving = false;
		}

		
	}
}
