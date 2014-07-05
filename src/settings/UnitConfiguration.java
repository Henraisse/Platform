package settings;

import tools.Visual_Effect;
import army.Weapon;

public class UnitConfiguration {
	public String name;
	public int health;
	public int armor;
	public double speed;
	public int size;
	public int lineOfSight;
	public int appearence;
	public Weapon armament;
	public Visual_Effect idleEffect;
	public Visual_Effect walkEffect;
	public Visual_Effect fireEffect;
	public Visual_Effect deathEffect;

	public UnitConfiguration(String name, int health, int armor, double speed, int size, int lineOfSight, int appearence, Weapon armament, 
			Visual_Effect idleEffect,
			Visual_Effect walkEffect,
			Visual_Effect fireEffect,
			Visual_Effect deathEffect){
		this.name = name;
		this.health = health;
		this.armor = armor;
		this.speed = speed;
		this.lineOfSight = lineOfSight;
		this.appearence = appearence;
		this.armament = armament;
		this.idleEffect = idleEffect;
		this.walkEffect = walkEffect;
		this.fireEffect = fireEffect;
		this.deathEffect = deathEffect;
		this.size = size;
	}
}
