package com.themaxsmith.game.empire.lemonade.scene;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import com.sun.prism.impl.ps.CachingShapeRep;
import com.themaxsmith.game.empire.lemonade.engine.GameFrame;
import com.themaxsmith.game.empire.lemonade.logic.Bot;
import com.themaxsmith.game.empire.lemonade.logic.HitBoxHandler;
import com.themaxsmith.game.empire.lemonade.logic.MouseMissed;
import com.themaxsmith.game.empire.lemonade.logic.SceneHandler;
import com.themaxsmith.game.empire.lemonade.render.HitBox;
import com.themaxsmith.game.empire.lemonade.render.Screen;

public abstract class Scene implements HitBoxHandler {
	private int x, imgx, storenum;
	private BufferedImage level;
	private SceneHandler handler;
	private boolean menu = false, Background=false;;
	private ArrayList<HitBox> hitboxs = new ArrayList<HitBox>();
	
	public Scene( SceneHandler handler,  String path) {
		x = 0;
		this.handler = handler;
		try {
			level = ImageIO.read(new File("res/"+path));
		} catch (IOException e) {
			e.printStackTrace();
		}
        
		imgx = level.getWidth();


	}
	public synchronized ArrayList<HitBox> getHitBoxes(){
		return hitboxs;
	}
	public void setNewScene(Screen screen, String path){
		x = 0;
		try {
			level = ImageIO.read(new File("res/"+path));
		} catch (IOException e) {
			e.printStackTrace();
		}
        
		imgx = level.getWidth();
		screen.initlvl(level);
		screen.renderlevel( 0, level);

	}
	

	public abstract void render(Screen screen);
	public abstract void tick();
	public abstract void renderOverlay(Graphics g);
	
	public void initHitBox(HitBox x){
		synchronized (getHitBoxes()) {
			getHitBoxes().add(x);
		}
		
		
	}
	
	public void rerenderlevel(Screen screen) {
		screen.renderlevel( x, level);
		
		
	}
	public void setInBackground(boolean x){
		Background =x;
	}
	public boolean inBackground(){
		return Background;
		
	}
	public void onClick(MouseEvent e) {
		synchronized (getHitBoxes()) {
		
				for(HitBox box : getHitBoxes()){
					box.onClick(e);
				}
			
		}
	}
	public boolean isMenu() {
		return menu;
	}
	public SceneHandler getHandler(){
		return handler;
	}
	public void setMenu(boolean menu) {
		this.menu = menu;
	}
	public int getStorenum() {
		return storenum;
	}
	public void setStorenum(int storenum) {
		this.storenum = storenum;
	}
	public void setScreen(){
		handler.getGame().getScreen().initlvl(level);
		handler.getGame().getScreen().renderlevel( 0, level);
	}
	public boolean onHover(MouseEvent e) {
		for(HitBox box : getHitBoxes()){
			if (box.isHovering(e))
				return true;
		}
		return false;
	}


} 