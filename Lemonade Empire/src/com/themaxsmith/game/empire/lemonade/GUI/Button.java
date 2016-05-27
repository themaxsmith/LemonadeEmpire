package com.themaxsmith.game.empire.lemonade.GUI;

import java.awt.Color;
import java.awt.Graphics;

import com.themaxsmith.game.empire.lemonade.logic.HitBoxHandler;
import com.themaxsmith.game.empire.lemonade.logic.Mob;
import com.themaxsmith.game.empire.lemonade.logic.SceneHandler;
import com.themaxsmith.game.empire.lemonade.render.HitBox;
import com.themaxsmith.game.empire.lemonade.render.HitBoxParent;
import com.themaxsmith.game.empire.lemonade.render.Screen;
import com.themaxsmith.game.empire.lemonade.render.onHit;
import com.themaxsmith.game.empire.lemonade.scene.Scene;

public abstract class Button extends Mob implements onHit, HitBoxParent{
	private int width, height;
	private int xtex,ytex;
	private boolean centered = false;
	private HitBox hit;
	public static enum Layout {
		SMALL_CENTERED, MED_CENTERED, MED_LEFT, LARGE_LEFT 
	}
	
	public Button(SceneHandler sceneHandler, HitBoxHandler hand,String text, int x, int y, Layout l) {
		super(text, sceneHandler.getCurrentView(), null, x, y);
		setupLayout(l); 
		hit = new HitBox(this,getX(), getY(), width, height) {@Override
		public void onHit() {hit();}};
		hand.initHitBox(hit);
	}
	public void hit(){
		
		onHit();
	}
	@Override
	public void render(Screen screen) {}
	
	@Override
	public void tick() {}

	@Override
	public void renderOverlay(Graphics g) {
		if (centered){
			int widtht = g.getFontMetrics().stringWidth(getName());
			xtex = (width-widtht)/2;
			centered=false;
		}
	//	int width = g.getFontMetrics().stringWidth(getName());
	//	System.out.println(width);
		g.setColor(new Color(0, 0, 0, 170));
		g.fillRect(getX(), getY(), width, height);
		g.setColor(Color.WHITE);
		g.drawString(getName(), getX()+xtex, getY()+ytex);
		g.setColor(Color.BLACK);
		g.drawRect(getX(), getY(), width, height);
	}

	public HitBox getHitBox(){
		return hit;
		
	}
	public void setupLayout(Layout l){
		switch(l){
		case SMALL_CENTERED:
			centered=true;
			width= 100;
			height=30;
			ytex=20;
		break;
		case MED_CENTERED:
			width=200;
			height=40;
			centered=true;
			ytex=30;
			break;
		case MED_LEFT:
			width=200;
			height=40;
			xtex=10;
			ytex=30;
			break;
		case LARGE_LEFT:
			width=400;
			height=40;
			xtex=10;
			ytex=30;
			break;
		default:
			break;	
		
		}

	}
	
}
