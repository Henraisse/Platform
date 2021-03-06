package tools;

import gui.Image_Pack;

import java.awt.Image;

import javax.swing.ImageIcon;

import army.Weapon;

public class Visual_Effect {

	public String name;
	public boolean remove = false;
	public int counter;
	public int types;
	public int width;
	public int height;
	public int directions;
	public ImageIcon image;
	
	
	public Visual_Effect(String name, int time, int types, int width, int height, int directions, Image_Pack images){
		this.name = name;
		counter = time;
		this.types = types;
		this.width = width;
		this.height = height;
		if(images != null){
			setImage(images);
		}
		this.directions = directions;

	}
	
	public void setImage(Image_Pack imp){
		image = imp.getImage(name);
	}
}
