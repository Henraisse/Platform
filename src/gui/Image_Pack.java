package gui;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Image_Pack {

	String mainPath;
	ArrayList<ImageIcon> images = new ArrayList<ImageIcon>();
	ImageIcon error;

	
	public Image_Pack(String main_path){
		mainPath = main_path;
		getImages(mainPath);
	}

	public void getImages(String path) {
		error  = new ImageIcon(mainPath+"\\gfx\\default\\error.png", "error");
		
		ArrayList<File> files = new ArrayList<File>();
		listf(path, files);
		
		for(File f: files){
			//if the file is a png image
			if(f.toString().split("\\\\")[f.toString().split("\\\\").length-1].split("\\.")[1].equals("png")){
				//System.out.println("importing image at " + f.toString());
				importImage(f.toString());
			}
		}
	}

	
	public void importImage(String path){
		ImageIcon newImage = new ImageIcon(path, "");
		String p = path.split("\\\\")[path.split("\\\\").length-1].split("[.]")[0];
		newImage.setDescription(p);
		
		images.add(newImage);
	}
	
	
	public ImageIcon getImage(String name) {
		for(ImageIcon i: images){
			if(i.getDescription().equals(name)){
				return i;
			}
		}
		return error;
	}
	
	public void listf(String directoryName, ArrayList<File> files) {
	    File directory = new File(directoryName);				//den mappen vi är i nu

	    // get all the files from a directory
	    File[] fList = directory.listFiles();					
	    for (File file : fList) {								
	        if (file.isFile()) {
	            files.add(file);
	        } else if (file.isDirectory()) {
	            listf(file.getAbsolutePath(), files);
	        }
	    }
	}
	
}
