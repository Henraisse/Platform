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

		
		setSpecs(type);
	}
	
	private void setSpecs(String type) {
		UnitConfiguration unit = battle_setup.variables.getUnitType(type);

		health = unit.health;
		initialhealth = health;
		armor = unit.armor;
		speed = unit.speed;
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
		if(counter%50 == 0){
			findClosestEnemy();
			setTarget(targetUnit.pos.x, targetUnit.pos.y);
		}
		//ZOMBIER SKA GÅ EMOT SOLDATERNA
		if(type == "ZOMBIE"){

			walkTowardTarget(1.0);
		}
		//SOLDATERNA SKA SKJUTA OM DE ÄR INOM SKOTTHÅLL
		if(type == "SOLDIER" && counter%15 == 0){
			attackTarget();
		}
		
	}
}
