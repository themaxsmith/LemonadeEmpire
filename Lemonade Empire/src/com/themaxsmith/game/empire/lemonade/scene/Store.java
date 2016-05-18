package com.themaxsmith.game.empire.lemonade.scene;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import com.sun.swing.internal.plaf.synth.resources.synth;
import com.themaxsmith.game.empire.lemonade.engine.GameFrame;
import com.themaxsmith.game.empire.lemonade.engine.Main;
import com.themaxsmith.game.empire.lemonade.logic.Bot;
import com.themaxsmith.game.empire.lemonade.logic.Button;
import com.themaxsmith.game.empire.lemonade.logic.Mob;
import com.themaxsmith.game.empire.lemonade.logic.MouseMissed;
import com.themaxsmith.game.empire.lemonade.logic.NavArrows;
import com.themaxsmith.game.empire.lemonade.logic.SceneHandler;
import com.themaxsmith.game.empire.lemonade.logic.MenuGUI;
import com.themaxsmith.game.empire.lemonade.render.HitBox;
import com.themaxsmith.game.empire.lemonade.render.Screen;

public class Store extends Scene {
	private int  imgx;
	private BufferedImage level;
	private NavArrows nav;
	private int localCash=0;
	private MenuGUI upmenu;
	private boolean inStore=true;
	
	private ArrayList<Mob> mobs = new ArrayList<Mob>();
	private ArrayList<HitBox> store = new ArrayList<HitBox>();
	private int alpha =255;


	public Store(SceneHandler handler, String path, int storenum) {
		super(handler,path);
		this.setStorenum(storenum);
		
		upmenu = new MenuGUI(this);
		nav = new NavArrows(this,250);
	}

	public void addCash(int cash){
		localCash+=cash;
	}
	

	public void render(Screen screen) {
		nav.render(screen);
		synchronized (getMobs()) {
		for (Mob bot : getMobs()){
		bot.render(screen);
		}
		}
	}
	public synchronized ArrayList<Mob> getMobs(){
		return mobs;
		
	}
	
	public void tick(){
		synchronized (getMobs()) {
		for (Mob bot : getMobs()){
			bot.tick();
		}
	
		for(int x= getMobs().size()-1; x >= 0; x--){
			
			if(getMobs().get(x).isRemove()){
				getMobs().remove(x);
				
			}
		}
		}
		if (alpha > 5){
			alpha-=5;
		}else{
			alpha=0;
		}
	}

	public void spawnPeople(){
		int color = (int)(Math.random() * 3)+1;
			int y = (int)( Math.random()*50);
			boolean placed = false;
			for (int x = 0; x <  getMobs().size();  x++){
			if (getMobs().get(x).getY() > y+320){
				placed =true;
			if ((int)((Math.random()+1) * 2) == 2){
			getMobs().add(x, new Bot(this, "person"+color+".png", 1,y));
			break;
			}else{
				getMobs().add(x, new Bot(this, "person"+color+".png", -1,y));
				break;
			}}}
			if (!(placed)){
				
				getMobs().add( new Bot(this, "person"+color+".png", 1,y));
			}
			
		
	}

	@Override
	public void renderOverlay(Graphics g) {

		g.setColor(new Color(0, 0, 0, alpha ));
		g.fillRect(0, 0, Main.WIDTH, Main.HEIGHT);
		g.setColor(Color.BLACK);
		g.setFont(new Font("Arial", Font.BOLD, 20));
		g.drawString("Store #"+getHandler().getCurrentStoreID(), 700, 20);
		for (Mob bot : getMobs()){
			bot.renderOverlay(g);
		}
		upmenu.renderOverlay(g);
	}
	public int getCash() {
		// TODO Auto-generated method stub
		return localCash;
	}
	public void onClick(MouseEvent e) {
		if(upmenu.isActive()){
			upmenu.onClick(e);
		}else{
		synchronized (getHitBoxes()) {
		boolean missed=true;
		for(HitBox box : getHitBoxes()){
			if(box.didClick(e)){
				missed=false;
				box.onHit();
			}
	
		}
		if(missed){
			Store store = ((Store) this);
			synchronized (store.getMobs()) {
		    store.getMobs().add(new MouseMissed("MouseMissed", this, -2, e.getX(), e.getY()));
		}}}}
	}
	public boolean onHover(MouseEvent e) {
		if(upmenu.isActive()){
			return upmenu.onHover(e);
		}else
		for(HitBox box : getHitBoxes()){
			if (box.isHovering(e))
				return true;
		}
		return false;
	}
	
	
} 