package army;

import java.util.Random;

import settings.UnitConfiguration;
import settings.Variables;
import tools.Position;
import tools.Statics;
import tools.Visual_Effect;

public class Soldier extends Unit{
		public String type;
		int team = -1;
		public boolean selected = false;
	
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
		
		if((armament.rateOfFire*(1000/battle_setup.game.loopSleepTime)) < 1){
			armament.rateOfFire = ((double)Statics.loopSleepTime / (double)1000);
		}
		
		clipAmmo = armament.clipCapacity;
		idleEffect = unit.idleEffect;
		walkEffect = unit.walkEffect;
		fireEffect = unit.fireEffect;
		deathEffect = unit.deathEffect;
		
		sp = battle_setup.sp;
		
	}

	/**
	 * Makes this soldier take action, and move, look, shoot, and do other things.
	 */
	public void action(int counter){
		if(isDead == true){return;}
		liveOrDie(type);
		
		//LITE DÅ OCH DÅ SKA ENHETERNA LETA EFTER ETT MÅL
		if(counter%5 == 0 || (targetUnit != null && targetUnit.isDead)){
			findClosestEnemy();
			if(counter%50 == 0){
				findClosestEnemy();
				randomizeImages();
			}
		}
		
		
		if(targetIsWithinRange()){
			fireimagecounter++;
			walkimagecounter = 0;
			idleimagecounter = 0;
			imgCount = fireimagecounter % fireEffect.counter;
			if(isReloading == false){
				attackCounter++;
				if(attackCounter% (int)(armament.rateOfFire*(1000/battle_setup.game.loopSleepTime)) == 0){
					isFiring = true;
					isMoving = false;
					attackTarget(counter);
					
					attackCounter = 0;
				}
			}
			else{
				reload(counter - counterStart);
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
			walkimagecounter = 0;
			fireimagecounter = 0;
			idleimagecounter++;
			imgCount = idleimagecounter % idleEffect.counter;
			
			isFiring = false;
			isMoving = false;
		}

		
	}


	private void randomizeImages() {
		if(new Random().nextInt(10) == 0){
			fireimagecounter++;
			idleimagecounter++;
			idleimagecounter++;
		}
	}
}
