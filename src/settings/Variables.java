package settings;

import gui.Image_Pack;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import tools.Visual_Effect;
import army.Ammo;
import army.Battle_Setup;
import army.Weapon;

public class Variables {
	
	String file = "";
	Battle_Setup bs;
	Image_Pack imp;

	public ArrayList<Visual_Effect> effectTypes = new ArrayList<Visual_Effect>();
	public ArrayList<Ammo> ammoTypes = new ArrayList<Ammo>();
	public ArrayList<Weapon> weaponTypes = new ArrayList<Weapon>();
	public ArrayList<UnitConfiguration> unitTypes = new ArrayList<UnitConfiguration>();
	
	public Variables(Battle_Setup bs, Image_Pack imp){
		this.imp = imp;
		this.bs = bs;		
		importConfigFile();
	}
	
	public void importConfigFile() {
		String FILE_NAME = "C:\\Users\\Henrik\\Desktop\\Military_school\\file.txt";
		try {
			file = readLargerTextFile(FILE_NAME);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		interpretEffects();
		interpretAmmo();
		interpretWeapons();
		interpretUnits();
	}
	
	private void interpretEffects() {
		
	String effectsFile = file.split("(\\*){4} EFFECTS (\\*){4}")[1];
	String[] units = effectsFile.split("\\[");
	for(String s: units){
		//FÖR VARJE ENHET	
		String name = "";
		int time = 10;
		int types = 1;
		int height = 100;
		int width = 100;
		int directions = 1;
		if(s != ""){
		
			String[] variables = s.split("[\n]");
			for(String s1: variables){
				if(s1 != ""){
					//MATCH THE NEW UNIT TYPE NAME
					if(s1.matches("\\w*]")){
						name = s1.replaceAll("\\]", "");
						//System.out.println("AMMO TYPE: " + name);
					}

					time = matchIntVariable(time, "time", s1);
					types = matchIntVariable(types, "types", s1);
					width = matchIntVariable(width, "width", s1);
					height = matchIntVariable(height, "height", s1);
					directions = matchIntVariable(directions, "directions", s1);
				}						
			}
		}	
			Visual_Effect config = new Visual_Effect(name, time, types, width, height, directions, imp);		
			//System.out.println(name);
			effectTypes.add(config);
		}
	}

	public void interpretAmmo(){
			

		
		String ammoFile = file.split("(\\*){4} AMMO (\\*){4}")[1].split("(\\*){4} EFFECTS (\\*){4}")[0];
		String[] units = ammoFile.split("\\[");
		for(String s: units){
			//FÖR VARJE ENHET	
			String name = "";
			double weight = -1.0;
			double caliber = 0.0;
			int metersPerSecond = 0;
			int joule = 0;
			if(s != ""){
			
				String[] variables = s.split("[\n]");
				for(String s1: variables){
					if(s1 != ""){
						//MATCH THE NEW UNIT TYPE NAME
						if(s1.matches("\\w*]")){
							name = s1.replaceAll("\\]", "");
							//System.out.println("AMMO TYPE: " + name);
						}

						weight = matchDoubleVariable(weight, "weight", s1);
						metersPerSecond = matchIntVariable(metersPerSecond, "metersPerSecond", s1);
						caliber = matchDoubleVariable(caliber, "caliber", s1);
						joule = matchIntVariable(joule, "joule", s1);
					}						
				}
			}	
				Ammo config = new Ammo(name, weight, metersPerSecond, caliber, joule);
				ammoTypes.add(config);
				//System.out.println("\n");
			}
		}
	
	public void interpretWeapons(){			

		
		String weaponFile = file.split("(\\*){4} WEAPONS (\\*){4}")[1].split("(\\*){4} AMMO (\\*){4}")[0];
		String[] units = weaponFile.split("\\[");
		for(String s: units){
			//FÖR VARJE ENHET		
			String name = "dummy gun";
			double rateOfFire = 1.0;
			int reloadTime = 1;
			double weight = 0.0;
			int clipCapacity = 1;
			String ammo = "";
			double barrellength = 0.0;
			double accuracy = 1.0;
			int damage = -1;
			int range = 5;
			boolean automatic = false;
			boolean semiauto = false;
			if(s != ""){
			
				String[] variables = s.split("[\n]");
				for(String s1: variables){
					if(s1 != ""){
						//MATCH THE NEW UNIT TYPE NAME
						if(s1.matches("\\w*]")){
							name = s1.replaceAll("\\]", "");
							//System.out.println("WEAPON NAME: " + name);
						}

						rateOfFire = matchDoubleVariable(rateOfFire, "ROF", s1);
						reloadTime = matchIntVariable(reloadTime, "reloadTime", s1);
						weight = matchDoubleVariable(weight, "weight", s1);
						clipCapacity = matchIntVariable(clipCapacity, "clipCapacity", s1);
						ammo = matchStringVariable(ammo, "ammo", s1);
						barrellength = matchDoubleVariable(barrellength, "barrellength", s1);
						accuracy = matchDoubleVariable(accuracy, "accuracy", s1);
						range = matchIntVariable(range, "range", s1);
						automatic = matchBoolVariable(automatic, "automatic", s1);
						semiauto = matchBoolVariable(semiauto, "semiauto", s1);
						damage = matchIntVariable(damage, "damage", s1);
					}						
				}
			}				
			
				Weapon config = new Weapon(name, rateOfFire, reloadTime, weight, clipCapacity, getAmmoType(ammo), barrellength, accuracy, range, automatic, semiauto);
				config.damage = damage;
				if(damage == -1){config.setDamage();}
				weaponTypes.add(config);
				//System.out.println("\n");
			}
		}
	
	public void interpretUnits(){
		//importAmmo();				

		
		String unitFile = file.split("(\\*){4} UNITS (\\*){4}")[1].split("(\\*){4} WEAPONS (\\*){4}")[0];
		String[] units = unitFile.split("\\[");
		for(String s: units){
			//FÖR VARJE ENHET			
			String name = "DUMMYUNIT";
			int health = -2;
			int armor = -1;
		double speed = -1;
		int sightRange = -1;
			int appearence = -1;
		String weapon = "";
		String idleEffect = "";
		String walkEffect = "";
		String fireEffect = "";
		String deathEffect = "";
			if(s != ""){
			
				String[] variables = s.split("[\n]");
				for(String s1: variables){
					if(s1 != ""){
						//System.out.println(s1);
						//MATCH THE NEW UNIT TYPE NAME
						if(s1.matches("\\w*]")){
							name = s1.replaceAll("\\]", "");
							//System.out.println("Unit Type name: " + name);
						}

						health = matchIntVariable(health, "health", s1);
						armor = matchIntVariable(armor, "armor", s1);
						appearence = matchIntVariable(appearence, "appearence", s1);
						sightRange = matchIntVariable(sightRange, "sightRange", s1);
						speed = matchDoubleVariable(speed, "speed", s1);
						weapon = matchStringVariable(weapon, "weapon", s1);
						idleEffect = matchStringVariable(idleEffect, "idleeffect", s1);
						walkEffect = matchStringVariable(walkEffect, "walkeffect", s1);
						fireEffect = matchStringVariable(fireEffect, "fireeffect", s1);
						deathEffect = matchStringVariable(deathEffect, "deatheffect", s1);
					}						
				}
			}
				UnitConfiguration config = new UnitConfiguration(name, health, armor, speed, sightRange, appearence, getWeaponType(weapon), 
						getEffectType(idleEffect),
						getEffectType(walkEffect),
						getEffectType(fireEffect),
						getEffectType(deathEffect)
						);
				System.out.println(fireEffect);
				unitTypes.add(config);
			}
		}
		
	public String readLargerTextFile(String aFileName) throws IOException {
		Charset ENCODING = StandardCharsets.UTF_8;
	    Path path = Paths.get(aFileName);
	    try (Scanner scanner =  new Scanner(path, ENCODING.name())){
	    	
	    	StringBuilder stringBuilder = new StringBuilder();

	    	
	      while (scanner.hasNextLine()){
	        //process each line in some way
	    	  stringBuilder.append("\n");
	    	  stringBuilder.append(scanner.nextLine());
	      }
	      return stringBuilder.toString();
	    }
	  }
		
	public Visual_Effect getEffectType(String name){
		//System.out.println("Seeking for: " + name);
		Visual_Effect correct = new Visual_Effect("INVALID AMMO", -1, -1, -1, 0, 0, null);
		for(Visual_Effect ac : effectTypes){
			if(ac.name.equals(name)){return ac;}
		}
		return correct;
	}	
	
	public Ammo getAmmoType(String name){
		//System.out.println("Seeking for: " + name);
		Ammo correct = new Ammo("INVALID AMMO", -1, -1, -1, 0);
		for(Ammo ac : ammoTypes){
			if(ac.type.equals(name)){return ac;}
		}
		return correct;
	}
	
	public Weapon getWeaponType(String name){
		Ammo dummyAmmo = new Ammo("INVALID AMMO", -1, -1, -1, 0);
		Weapon correct = new Weapon("INVALID Weapon", -1, -1, -1, 0, dummyAmmo, 0, 0, 0, false, false);
		for(Weapon ac : weaponTypes){
			if(ac.name.equals(name)){return ac;}
		}
		return correct;
	}
	
	public UnitConfiguration getUnitType(String name){
		//System.out.println("We are seeking for: " + name);
		UnitConfiguration correct = new UnitConfiguration(name, 0, 0, 0, 0, 0, null, null, null, null, null);
		for(UnitConfiguration ac : unitTypes){
			//System.out.println(ac.name);
			if(ac.name.equalsIgnoreCase(name)){return ac;}
		}
		return correct;
	}

	public boolean matchBoolVariable(boolean currentValue, String variable, String text){
		String bool = "no";
		if(currentValue == true){bool = "yes";}

		matchStringVariable("yes", variable, text);
		if(bool.equals("yes")){
			return true;
			}
		return false;
	}
	
	public String matchStringVariable(String currentValue, String variable, String text){
		if(text.matches(variable+"(\\s)*(\\t)*=(\\s)*(\\t)*[A-Za-z0-9_]+(\\s)*(\\t)*")){
			currentValue = text.replaceAll(variable+"(\\s)*(\\t)*=(\\s)*(\\t)*(\\s)*(\t)*", "").replaceAll("\\t", "");
			//System.out.println(variable + " = " + value);
		}
		return currentValue;
	}
	
	public int matchIntVariable(int currentValue, String variable, String text){
		if(text.matches(variable+"(\\s)*(\\t)*=(\\s)*(\\t)*(\\d)+(\\s)*(\\t)*")){
			currentValue = Integer.parseInt(text.replaceAll(variable+"(\\s)*(\\t)*=(\\s)*(\\t)*(\\s)*(\t)*", "").replaceAll("\\t", ""));
			//System.out.println(variable + " = " + value);
		}
		
		return currentValue;
	}
	
	public double matchDoubleVariable(double currentValue, String variable, String text){
		if(text.matches(variable+"(\\s)*(\\t)*=(\\s)*(\\t)*(\\d)+(\\.{1})(\\d)+(\\s)*(\\t)*")){
			currentValue = Double.parseDouble(text.replaceAll(variable+"(\\s)*(\\t)*=(\\s)*(\\t)*(\\s)*(\t)*", "").replaceAll("\\t", ""));
			//System.out.println(variable + " = " + value);			
		}
		return currentValue;
	}
	
	
}
