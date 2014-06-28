package tools;

import java.awt.Image;
import java.util.Random;

import army.Battle_Setup;

public class Effect {
	public int count;
	public int timer;
	public int type;
	public Image img;
	public boolean remove = false;
	public int width;
	public int height;
	
	public Position pos;
	
	public Effect(double x, double y, String effectName, Battle_Setup bs){
		pos = new Position(x,y);
		getEffectData(effectName, bs);
	}
	
	public void getEffectData(String effectName, Battle_Setup bs){
		//System.out.println("we wish to find image named " + effectName);
		for(Visual_Effect ve: bs.variables.effectTypes){
			if(ve.name.equals(effectName)){
				try{
					img = ve.image.getImage();
				}
				catch(NullPointerException e){
					//System.out.println("couldn't find image " + ve.name);
				}
				
				timer = ve.counter;
				count = ve.counter;
				type = 0;
				if(ve.types > 0){
					type = new Random().nextInt(ve.types)+1;
				}
				width = ve.width;
				height = ve.height;
			}
		}
	}
	
	public void action(){
		timer--;
		if(timer <= 0){remove = true;}
	}
	
}
