package com.themaxsmith.game.empire.lemonade.logic;

import java.awt.Graphics;

import com.themaxsmith.game.empire.lemonade.render.Screen;
import com.themaxsmith.game.empire.lemonade.scene.Scene;
import com.themaxsmith.game.empire.lemonade.scene.Store;

public class MouseMissed extends Mob {
	private int anim=0, mon;
	public MouseMissed(String name, Scene level, int mon, int x, int y) {
		super(name, level, null, x, y);
		this.mon = mon;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void render(Screen screen) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tick() {
		anim++;
		if(anim > 70){
			//DELETE
			((Store) getLevel()).addCash(-2);
			setRemove(true);
		}
	}

	@Override
	public void renderOverlay(Graphics g) {
		g.drawString(((Object) mon).toString(), getX()-10, getY()-(anim));
		
		
	}

}
