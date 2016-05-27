package com.themaxsmith.game.empire.lemonade.logic;

import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import com.themaxsmith.game.empire.lemonade.GUI.MenuGUI;
import com.themaxsmith.game.empire.lemonade.engine.Main;
import com.themaxsmith.game.empire.lemonade.render.Screen;
import com.themaxsmith.game.empire.lemonade.scene.Scene;
import com.themaxsmith.game.empire.lemonade.scene.Store;

public class SceneHandler {
	private Main game;
	private boolean inGame;
	private Scene currentScene;
	private MenuGUI upmenu;
	private MouseEvent currentMouseLocation;
	private int currentStore =1;
	private boolean isMutiple = false;
	
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
			@Override
			public void mouseClicked(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
			@Override
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
			switch (store.getType().getRate()){
			case High_Spawn:
				store.spawnBot();
				store.spawnBot();
				store.spawnBot();
				break;
			case Low_Spawn:
				if (((int)(Math.random()*2))==1){
					store.spawnBot();
				}
				break;
			case Med_Spawn:
				store.spawnBot();
				break;
			default:
				break;
				
			}
		
		}
	}


	public boolean isMutiple() {
		return isMutiple;
	}

	public void setMutiple(boolean isMutiple) {
		this.isMutiple = isMutiple;
	}

	public MenuGUI getUpmenu() {
		return upmenu;
	}

	public void setUpmenu(MenuGUI upmenu) {
		this.upmenu = upmenu;
	}
	
} 