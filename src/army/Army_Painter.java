package army;

import gui.Combat_Screen;

import java.awt.Color;
import java.awt.Graphics;

import tools.Position;
import tools.Statics;
import tools.Visual_Effect;

public class Army_Painter {
	Battle_Setup bs;
	
	public Army_Painter(Battle_Setup bs){
		this.bs = bs;
	}
	
	public void paint(Graphics g, Combat_Screen parent) {
		

		
		
		//g.drawLine(100,100, 200, 200);
		g.setColor(new Color(100, 100, 100));
		g.fillRect(0, 0, 2000, 2000);
		
		
		for(int i = 0; i < bs.projectiles.size(); i++){
			
			//DRAW GENERIC GUNSHOTS
			g.setColor(Color.RED);
			g.drawLine(
					(int)bs.projectiles.get(i).currentPosition.x, 
					(int)bs.projectiles.get(i).currentPosition.y, 
					(int)bs.projectiles.get(i).target.x, 
					(int)bs.projectiles.get(i).target.y);
		}
		
		//DRAW UNIT SELECTIONS
		for(int i = 0; i < bs.soldiers.size(); i++){
			if(bs.soldiers.get(i).isDead == false && bs.soldiers.get(i).selected == true){
			Soldier cs = bs.soldiers.get(i);
			Position leftTop = cs.pos.plus(new Position(-(cs.size/2),-(cs.size/2)));
			Position rightTop = cs.pos.plus(new Position((cs.size/2),-(cs.size/2)));
			Position leftBottom = cs.pos.plus(new Position(-(cs.size/2),(cs.size/2)));
			Position rightBottom = cs.pos.plus(new Position((cs.size/2),(cs.size/2)));
			
			Position widthInc = new Position((cs.size/5),0);
			Position heightInc = new Position(0,(cs.size/5));
			Position hBar = new Position(0, 5);
			
			g.setColor(new Color(255,255,255));
			
			g.drawLine((int)rightTop.x, (int)rightTop.y, (int)rightTop.minus(widthInc).x, (int)rightTop.minus(widthInc).y);
			g.drawLine((int)leftTop.x, (int)leftTop.y, (int)leftTop.plus(widthInc).x, (int)leftTop.plus(widthInc).y);						
			
			g.drawLine((int)rightBottom.x, (int)rightBottom.y, (int)rightBottom.minus(widthInc).x, (int)rightBottom.minus(widthInc).y);
			g.drawLine((int)leftBottom.x, (int)leftBottom.y, (int)leftBottom.plus(widthInc).x, (int)leftBottom.plus(widthInc).y);
			
			g.drawLine((int)rightTop.x, (int)rightTop.y, (int)rightTop.plus(heightInc).x, (int)rightTop.plus(heightInc).y);
			g.drawLine((int)rightBottom.x, (int)rightBottom.y, (int)rightBottom.minus(heightInc).x, (int)rightBottom.minus(heightInc).y);		
			
			g.drawLine((int)leftTop.x, (int)leftTop.y, (int)leftTop.plus(heightInc).x, (int)leftTop.plus(heightInc).y);
			g.drawLine((int)leftBottom.x, (int)leftBottom.y, (int)leftBottom.minus(heightInc).x, (int)leftBottom.minus(heightInc).y);		
			
			g.drawRect((int)leftTop.x, (int)leftTop.y-(Statics.healthBarThickness+1), cs.size, Statics.healthBarThickness+1);
			g.setColor(getHealthColor(cs));
			g.fillRect((int)leftTop.x+1, (int)(leftTop.y-Statics.healthBarThickness), (int)((double)(cs.size-1)*getHealthBarLength(cs)), Statics.healthBarThickness);
			g.setColor(Color.BLACK);
			g.fillRect((int) ((int)leftTop.x+1+(double)(cs.size-1)*getHealthBarLength(cs)), (int)(leftTop.y-Statics.healthBarThickness), (int)((double)(cs.size-1)-(cs.size-1)*getHealthBarLength(cs)), Statics.healthBarThickness);
			//rita upp en box runtom
			//rita upp health bar
			}
		}
		
		for(int i = 0; i < bs.soldiers.size(); i++){
			Visual_Effect ef = null;
			try{
			Soldier s = bs.soldiers.get(i);
			if(s.isDead == false){
				
				double cos = 0.0;
				double sin = -1.0;
				
				if(s.targetUnit != null){
					cos = s.targetUnit.pos.minus(s.pos).unitDotProduct(new Position(1,0));	// 1 to -1
					sin = s.targetUnit.pos.minus(s.pos).unitDotProduct(new Position(0,-1));	// 1 to -1
				}
				
				else if(s.target != null){
					cos = s.target.minus(s.pos).unitDotProduct(new Position(1,0));	// 1 to -1
					sin = s.target.minus(s.pos).unitDotProduct(new Position(0,-1));	// 1 to -1
				}
				//double degrees = (dir-1.0)*(-90.0);		//0 to 180
				//if(dir2 > 0){degrees = 360-degrees;}
				//
				
				double degrees = Math.toDegrees(Math.acos(cos));
				if(sin > 0){
					degrees = 360-degrees;
				}
				ef = s.idleEffect;
				if(s.isMoving){ef = s.walkEffect;}
				else if(s.isFiring){ef = s.fireEffect;}
//				if(ef.name.equals("SOLDIER_01_IDLE")){
//					System.out.println("is idling");
//				}
		
				int cakePartSize = 360/ef.directions;
				degrees += cakePartSize/2;
				if(degrees > 360){
					degrees -= 360;
				}
				else if(degrees < 0){
					degrees += 360;
				}
				int part = 0;
				int quadrant = 0;
				
				while(part < degrees){
					part += cakePartSize;
					quadrant++;
				}
				
				//System.out.println("quadrant: " + quadrant);
				quadrant--;
				quadrant = quadrant % ef.directions;
				
				
				g.drawImage(ef.image.getImage(), 
						(int)(1+s.pos.x-(ef.width/2)), 
						(int)(1+s.pos.y-(ef.height/2)),
						(int)(1+s.pos.x+(ef.width/2)), 
						(int)(1+s.pos.y+(ef.height/2)),
				1+((s.imgCount)*ef.width), 
				1+(quadrant*ef.height), 
				ef.width+((s.imgCount)*ef.width)-1, 
				ef.height+(quadrant * ef.height)-1,	
				parent
				);
				
			}
			}
			catch(ArithmeticException e){
				System.out.println("PAINT ERROR: Perhaps no instance of some certain " + ef.name + " present in rules.ini?");
			}
		}
		

		
		
		//DRAW GENERIC UNITS
//		for(int i = 0; i < bs.soldiers.size(); i++){	
//			if(bs.soldiers.get(i).isDead == false){
//				g.setColor(new Color(0,0,0));
//				g.drawRect((int)bs.soldiers.get(i).pos.x, (int)bs.soldiers.get(i).pos.y, 3, 3);			
//			}
//			else{
//				g.setColor(new Color(255,0,0));
//				g.drawRect((int)bs.soldiers.get(i).pos.x+1, (int)bs.soldiers.get(i).pos.y+1, 1, 1);
//				g.drawRect((int)bs.soldiers.get(i).pos.x, (int)bs.soldiers.get(i).pos.y, 3, 3);	
//				g.drawRect((int)bs.soldiers.get(i).pos.x-1, (int)bs.soldiers.get(i).pos.y-1, 5, 5);	
//			}
//		}
		
		//DRAW EFFECTS
		for(int i = 0; i < bs.effects.size(); i++){
			g.drawImage(bs.effects.get(i).img, 
					(int)(1+bs.effects.get(i).pos.x-(bs.effects.get(i).width/2)), 
					(int)(1+bs.effects.get(i).pos.y-(bs.effects.get(i).height/2)),
					(int)(1+bs.effects.get(i).pos.x+bs.effects.get(i).width-(bs.effects.get(i).width/2)), 
					(int)(1+bs.effects.get(i).pos.y+bs.effects.get(i).height-(bs.effects.get(i).height/2)),
			1+((bs.effects.get(i).count-bs.effects.get(i).timer)*bs.effects.get(i).width), 
			1+((bs.effects.get(i).type-1)*bs.effects.get(i).height), 
			bs.effects.get(i).width+((bs.effects.get(i).count-bs.effects.get(i).timer)*bs.effects.get(i).width)-1, 
			bs.effects.get(i).height+((bs.effects.get(i).type-1)*bs.effects.get(i).height)-1,	
			parent
			);

		}
		
		//DRAW SELECTIONS
		

		
	}

	public double getHealthBarLength(Soldier s){
		double healthQuota = ((double)s.health/(double)s.initialhealth);
		if(healthQuota < 0){healthQuota = 0;}
		return healthQuota;
	}
	
	public Color getHealthColor(Soldier s){
		double healthQuota = ((double)s.health/(double)s.initialhealth);
		
		     if(healthQuota >= 0.5){return Color.GREEN;}
		else if(healthQuota < 0.25){return Color.RED;}
		return Color.YELLOW;
		
	}
	
	public void getPaintData(){}
	//Takes a Battle_Setup and converts it to painted things.
	
	
}
