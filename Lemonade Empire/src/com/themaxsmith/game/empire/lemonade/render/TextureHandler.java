package com.themaxsmith.game.empire.lemonade.render;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.themaxsmith.game.empire.lemonade.logic.Timer;

public class TextureHandler {
private Map<String,Texture> textures = new HashMap<String,Texture>();
public TextureHandler(){
	initTextures();
}
private void initTextures() {
	Timer loadTime = new Timer();
	loadTime.start();
	File[] files = new File("res").listFiles();
	for (File file : files) {
	    if (file.isFile()) {
	    	
	       textures.put(file.getName(), new Texture(file.getName()));
	       System.out.print(" Cached: "+file.getName());
	    }
	}
	loadTime.end();
	 System.out.println("\nLoaded: "+files.length+" Textures in "+loadTime.getTimeTook()+" secounds");
}
public Texture getTexture(String path){
	
	return textures.get(path);
}
}
