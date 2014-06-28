package army;


public class Weapon {
	public String name;
	public double rateOfFire;
	public double reloadTime;
	public double weight;
	public int clipCapacity;
	public Ammo ammo;
	public double barrellength;
	public double accuracy;
	public int range;
	public boolean automatic;
	public boolean semiauto;
	public int damage;
	public int speed;
	
	
	public Weapon(String name, double ROF, double reload, double weight, int clip, Ammo ammo, double barrel, double accuracy, int range, boolean auto, boolean semi){
		this.name = name;
		this.rateOfFire = ROF;
		this.reloadTime = reload;
		this.weight = weight;
		this.clipCapacity = clip;
		this.ammo = ammo;
		this.barrellength = barrel;
		this.accuracy = accuracy;
		this.range = range;
		this.automatic = auto;
		this.semiauto = semi;
	}
	
	public void setDamage(){
		
		speed = ammo.getBulletSpeed(barrellength);
		int joule = (int)(ammo.weight * speed * speed * 0.5);		
		damage = (int)(joule * 0.05);
		
	}
}















