package com.themaxsmith.game.empire.lemonade.logic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

public class SaveLoadGame {
private static File loc = new File("save.dat");
public static void Save(SceneHandler hand){

	String data = hand.getGame().getCash()+"/"+hand.getGame().getPopularity();
//	for (int x = 0; x < hand.getStores().size(); x++){
//		if (x > 0);
//			data+= "|";
//		data+=hand.getStores().get(x).g
//	}
	FileOutputStream x;
	try {
		x = new FileOutputStream(loc);

	x.write((data).getBytes());
	x.close();
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
public static void Load(SceneHandler hand){
	try {
		if (loc.exists()){
		String output = "";
	
			BufferedReader h = Files.newBufferedReader(loc.toPath());
			boolean run = true;
				while (run){
					String t = h.readLine();
					if (t != null){
					output += t;
					
					}else{
						run=false;
					}
					}
		String[] dx = output.split("/");
		hand.getGame().setCash(Integer.parseInt(dx[0]));
		hand.getGame().setPopularity(Integer.parseInt(dx[1]));
	
}
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}
