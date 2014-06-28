package settings;

import tools.Visual_Effect;
import army.Weapon;

public class UnitConfiguration {
	public String name;
	public int health;
	public int armor;
	public double speed;
	public int lineOfSight;
	public int appearence;
	public Weapon armament;
	public Visual_Effect deathEffect;
	

	public UnitConfiguration(String name, int health, int armor, double speed, int lineOfSight, int appearence, Weapon armament, Visual_Effect deathEffect){
		this.name = name;
		this.health = health;
		this.armor = armor;
		this.speed = speed;
		this.lineOfSight = lineOfSight;
		this.appearence = appearence;
		this.armament = armament;
		this.deathEffect = deathEffect;
	}
}
