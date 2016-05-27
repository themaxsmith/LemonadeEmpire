package com.themaxsmith.game.empire.lemonade.render;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;

import com.themaxsmith.game.empire.lemonade.logic.Timer;

public class TextureHandler {
private Map<String,Texture> textures = new HashMap<String,Texture>();
private String[] res = new String[]{"arrow","loading","person1","person2","person3","question","start","store1","store4","store3","store5"}; 
public TextureHandler(){
	initTextures();
}
private void initTextures() {
	
	Timer loadTime = new Timer();
	loadTime.start();
	

	try {
	   
	    	File x  = new File("LemonadeEmpire");
	    	if (!(x.exists())){
	    		x.mkdir();
	    	}
	    	File[] files = new File("LemonadeEmpire").listFiles();
	    	if (files.length != 11){
	    		for (String h : res){
	    			URL dnl = new URL("http://themaxsmith.com/LE/"+h+".png");
	    		
						ImageIO.write(ImageIO.read(dnl), "png", new File("LemonadeEmpire/"+h+".png"));
	    			
	    		}
	    		 files = new File("LemonadeEmpire").listFiles();
	    	}
	    	
	    	for (File file : files) {
	    	    if (file.isFile()) {
	    	    
	    	       textures.put(file.getName(), new Texture(file.getName()));
	    	       System.out.print(" Cached: "+file.getName());
	    	    }
	    	}
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   
	    
	loadTime.end();
	 System.out.println("\nLoaded  Textures in "+loadTime.getTimeTook()+" secounds");

}
public Texture getTexture(String path){
	Texture y = textures.get(path);

		return y;

}
}
