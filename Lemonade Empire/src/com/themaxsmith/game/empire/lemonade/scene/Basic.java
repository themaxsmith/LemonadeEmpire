package com.themaxsmith.game.empire.lemonade.scene;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import com.themaxsmith.game.empire.lemonade.engine.GameFrame;
import com.themaxsmith.game.empire.lemonade.logic.Bot;
import com.themaxsmith.game.empire.lemonade.logic.SceneHandler;
import com.themaxsmith.game.empire.lemonade.render.HitBox;
import com.themaxsmith.game.empire.lemonade.render.Screen;

public class Basic extends Scene {
	private int x, imgx;
	private BufferedImage level;

	

	public Basic( SceneHandler handler, String path) {
		super(handler,path);

		

	}
	

	public void render(Screen screen) {
	}
	
	public void tick(){

	}

	

	@Override
	public void renderOverlay(Graphics g) {
		g.setColor(Color.blue);
		g.drawRect(0, 0, 100, 100);
	
	}
} 