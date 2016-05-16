package com.themaxsmith.game.empire.lemonade.logic;

import java.awt.Color;
import java.awt.Graphics;

import com.themaxsmith.game.empire.lemonade.render.Screen;
import com.themaxsmith.game.empire.lemonade.scene.Scene;

public class Button extends Mob {
	private int width, height;
	public Button(Scene level, String text, int x, int y, int width, int height) {
		super(text, level, null, x, y);
		this.width = width;
		this.height = height;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void render(Screen screen) {
	}

	@Override
	public void tick() {
	}

	@Override
	public void renderOverlay(Graphics g) {
	//	int width = g.getFontMetrics().stringWidth(getName());
	//	System.out.println(width);
		g.setColor(new Color(0, 0, 0, 170));
		g.fillRect(getX(), getY(), width, height);
		g.setColor(Color.WHITE);
		g.drawString("Upgrade", getX()+10, getY()+20);
	}

	
}
