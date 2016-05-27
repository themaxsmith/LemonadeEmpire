package com.themaxsmith.game.empire.lemonade.scene;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import com.themaxsmith.game.empire.lemonade.logic.SceneHandler;
import com.themaxsmith.game.empire.lemonade.render.Screen;

public class Basic extends Scene {
	private int x, imgx;
	private BufferedImage level;

	

	public Basic( SceneHandler handler, String path) {
		super(handler,path);

		

	}
	

	@Override
	public void render(Screen screen) {
	}
	
	@Override
	public void tick(){

	}

	

	@Override
	public void renderOverlay(Graphics g) {
		g.setColor(Color.blue);
		g.drawRect(0, 0, 100, 100);
	
	}
} 