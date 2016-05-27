package com.themaxsmith.game.empire.lemonade.scene;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import com.themaxsmith.game.empire.lemonade.GUI.MenuGUI;
import com.themaxsmith.game.empire.lemonade.engine.GameFrame;
import com.themaxsmith.game.empire.lemonade.engine.Main;
import com.themaxsmith.game.empire.lemonade.logic.Bot;
import com.themaxsmith.game.empire.lemonade.logic.Mob;
import com.themaxsmith.game.empire.lemonade.logic.MouseMissed;
import com.themaxsmith.game.empire.lemonade.logic.NavArrows;
import com.themaxsmith.game.empire.lemonade.logic.SceneHandler;
import com.themaxsmith.game.empire.lemonade.render.HitBox;
import com.themaxsmith.game.empire.lemonade.render.Screen;

public class Store extends Scene {

	private NavArrows nav;

	
	private int popularity = 5;
	private StoreType type;
	
	private ArrayList<Mob> mobs = new ArrayList<Mob>();
	private int alpha =255;


	public Store(SceneHandler handler, StoreType type) {
		super(handler,type.getRes());
		popularity = type.getPop();
		this.setType(type); 
		
		nav = new NavArrows(this,250);
		initHitBox(getHandler().getUpmenu().getUpgrade().getHitBox());
	}

	public void addCash(int cash){
		getHandler().getGame().addCash(cash);
	}
	

	@Override
	public void render(Screen screen) {
		if(getHandler().isMutiple())
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
	
	@Override
	public void tick(){
		synchronized (getMobs()) {
		for (Mob bot : getMobs()){
			bot.tick();
		}
		for(int x= getHitBoxes().size()-1; x >= 0; x--){
			
			if(getHitBoxes().get(x).isRemove()){
				getHitBoxes().remove(x);
				
			}
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

	public void spawnBot(){
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
		g.fillRect(0, 0, GameFrame.WIDTH, GameFrame.HEIGHT);
		g.setColor(Color.BLACK);
		g.setFont(new Font("Arial", Font.BOLD, 20));
		g.drawString("Store #"+getHandler().getCurrentStoreID(), 700, 20);
		for (Mob bot : getMobs()){
			bot.renderOverlay(g);
		}
//		synchronized (getHitBoxes()) {
//		for (HitBox f : getHitBoxes()){
//			f.renderOverlay(g);
//		}}
	
		getHandler().getUpmenu().renderOverlay(g);
	}
	
	@Override 
	public void onClick(MouseEvent e) {
		if(getHandler().getUpmenu().isActive()){
			getHandler().getUpmenu().onClick(e);
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
			Store store = (this);
			synchronized (store.getMobs()) {
		    store.getMobs().add(new MouseMissed("MouseMissed", this, -2, e.getX(), e.getY()));
		}}}}
	}
	@Override
	public boolean onHover(MouseEvent e) {
		if(getHandler().getUpmenu().isActive()){
			return getHandler().getUpmenu().onHover(e);
		}else
		for(HitBox box : getHitBoxes()){
			if (box.isHovering(e))
				return true;
		}
		return false;
	}

	public int getPopularity() {
		return popularity+getHandler().getGame().getPopularity();
	}

	public void addPopularity(int popularity) {
		this.popularity = popularity+getPopularity();
		
	}

	public int getStorePop() {
		// TODO Auto-generated method stub
		return popularity;
	}

	public StoreType getType() {
		return type;
	}

	public void setType(StoreType type) {
		this.type = type;
	}
	
	
} 