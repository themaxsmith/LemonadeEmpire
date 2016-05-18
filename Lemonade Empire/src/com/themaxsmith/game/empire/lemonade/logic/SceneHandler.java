package com.themaxsmith.game.empire.lemonade.logic;

import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import com.themaxsmith.game.empire.lemonade.engine.Main;
import com.themaxsmith.game.empire.lemonade.render.HitBox;
import com.themaxsmith.game.empire.lemonade.render.Screen;
import com.themaxsmith.game.empire.lemonade.scene.Scene;
import com.themaxsmith.game.empire.lemonade.scene.Store;

public class SceneHandler {
	private Main game;
	private boolean inGame;
	private Scene currentScene;
	private MouseEvent currentMouseLocation;
	private int currentStore =1;
	private int popularity = 20;
	
	private ArrayList<Store> stores = new ArrayList<Store>();
	
	public SceneHandler(Main game) {
		this.game=game;
		game.addMouseMotionListener(onHover());
	}

	public void addStore(Store store ){
		
		stores.add(store);
		store.setInBackground(true);
		
	}

	public ArrayList<Store> getStores(){
		return stores;
	}
	public int getCash(){
		int totalcash = 0;
		for (Store store : stores){
			totalcash += store.getCash();
			
		}
		return totalcash;
	}
	
	public void render(Screen screen) {
	currentScene.render(screen);

	}
	public void startGame(){
		inGame=true;
	}
	
	public void tick(){
		if (currentMouseLocation != null)
			updateMouseCursor();
		if (inGame){
		
		for (Store store : stores){
			store.tick();
		}}else{
			currentScene.tick();
		}
	}
	public Scene getCurrentView(){
		return currentScene;
	}
	public void rerenderScene() {
	}
	
	public void setStore(int x){
		if(x==0)
			x=stores.size();
		if(x==stores.size()+1)
			x=1;

		stores.get(x-1).setInBackground(false);
		setScene(stores.get(x-1));
		currentStore =x;
		
	}
	public int getCurrentStoreID(){
		return currentStore;
	}
	public void setScene(Scene scene){
		if(currentScene !=null)
			currentScene.setInBackground(true);
		currentScene = scene;
		System.out.println("New Current Scene");
		scene.setScreen();
		
	}
	public int getBotNumber(){
		int mobs = 0;
		for (Store store : stores){
			mobs += store.getMobs().size();
			
		}
		return mobs;
	}
	public Main getGame(){
		return game;
	}
	public boolean inGame(){
		return inGame;
	}
	public MouseListener onClick() {
		return new MouseListener() {
			
			@Override
			public void mousePressed(MouseEvent e) {
				System.out.println("CLICK x:"+e.getX()+" y:"+e.getY()); 
				currentScene.onClick(e);
			}
			//unused mouse events
			public void mouseClicked(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
			};
			
		
	}
	public  MouseMotionListener onHover(){
	return	new MouseMotionListener() {
		@Override
		public void mouseMoved(MouseEvent e) {
			currentMouseLocation = e;
		}
		@Override
		public void mouseDragged(MouseEvent e) {}
	};
}
	public void updateMouseCursor(){
		if(currentScene.onHover(currentMouseLocation)){
			getGame().setCursor(new Cursor(Cursor.HAND_CURSOR));
		}else{
			getGame().setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}
	}
	public void spawnPeople(){

		for (Store store : stores){
			store.spawnPeople();
		}
	}

	public int getPopularity() {
		return popularity;
	}

	public void setPopularity(int popularity) {
		this.popularity = popularity;
	}
	
} 