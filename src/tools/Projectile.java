package tools;

import army.Weapon;

public class Projectile {

	public Weapon weaponType;
	public Position origin;
	public Position currentPosition;
	public Position target;
	public Position step;
	public boolean remove = false;
	int counter = 1;
	
	
	public Projectile(Position origin, Position target, Weapon w){
		currentPosition = new Position(origin.x, origin.y);
		this.origin = new Position(origin.x, origin.y);
		this.target = new Position(target.x, target.y);
		this.step = new Position(0,0);
		weaponType = w;
	}
	
	public void action(){
		counter--;
		step = target.minus(origin).normalize().times(weaponType.speed);
		if(counter <= 0){remove = true;}
	}
}
