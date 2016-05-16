package com.themaxsmith.game.empire.lemonade.render;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Texture {
	public BufferedImage rawimage;
	public int[] texture;
	public int width,height;
public Texture(String file){
	try {
		rawimage = ImageIO.read(new File("res/"+file));
		width = rawimage.getWidth();
		height = rawimage.getHeight();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	texture = new int[rawimage.getHeight() * rawimage.getWidth()];

	for (int y = 0; y < ((rawimage.getHeight())); y++) {
		for (int x = 0; x < ((rawimage.getWidth())); x++) {

			texture[((x) + ((y) * rawimage.getWidth()))] = rawimage.getRGB((int) ((x)),(int) ((y)));
			
		}
	}
	
}
}
