package army;

public class Ammo {
	public String type;
	public double weight;
	public int metersPerSecond;
	public double caliber;
	public int joule;
	
	
	public Ammo(String name, double weight, int mps, double caliber, int joule){
		this.type = name;
		this.weight = weight;
		this.metersPerSecond = mps;
		this.caliber = caliber;
		this.joule = joule;
	}
	
	public int getBulletSpeed(double barrellength){
		//meters per second
		return (int) ((barrellength / 0.5) * 900);
	}	

}
