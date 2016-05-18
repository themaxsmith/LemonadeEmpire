package com.themaxsmith.game.empire.lemonade.engine;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

import com.themaxsmith.game.empire.lemonade.logic.SceneHandler;
import com.themaxsmith.game.empire.lemonade.render.Screen;
import com.themaxsmith.game.empire.lemonade.render.TextureHandler;
import com.themaxsmith.game.empire.lemonade.scene.Basic;
import com.themaxsmith.game.empire.lemonade.scene.Menu;
import com.themaxsmith.game.empire.lemonade.scene.Store;

import javafx.scene.Cursor;

public class Main extends GameFrame {
	
	public static final String NAME = "Lemonade Empire";
	
	private TextureHandler textHandler;
	private SceneHandler sceneHandler;
	private int cash=0, rate=10, hour=8, min = 0; 


	public static void main(String[] args) {
		Main game = new Main();
		game.setupFrame(game);
		game.start();
	}
	
	public void init() {
      
     
		setScreen(new Screen(WIDTH,HEIGHT));
		sceneHandler = new SceneHandler(this);
		textHandler = new TextureHandler();
		Menu menu = new Menu(sceneHandler, "start.png");
		sceneHandler.setScene(menu);
		addMouseListener(sceneHandler.onClick());
		addKeyListener(menu.keylistner());
		
	}

	public void tick() {
		tickcounter();
		sceneHandler.tick();
	}

	public void render() {
		renderBackground();
		sceneHandler.render(getScreen());
		renderForground();
	}
	
	@Override
	public void renderOverlay(Graphics g) {
		sceneHandler.getCurrentView().renderOverlay(g);
		renderFPS(g);
		if(sceneHandler.inGame()){
		renderCash(g);
		renderGameTime(g);
		g.setColor(new Color(255, 255, 255,137));
		//g.fillRect(0, 200, 200, 70);
	}}
	public void renderCash(Graphics g ){
		g.setFont(new Font("Arial", Font.BOLD, 20));
		g.setColor(new Color(37, 99, 26));
		g.drawString("$"+cash, 10, 75);
		
		g.fillRect(10, 30, 150, 20);
		g.setColor(Color.BLACK);
		g.drawRect(10, 30, 150, 20);
		g.drawString("Popularity:", 10, 20);
		
	}

	public void renderGameTime(Graphics g ){
		g.setFont(new Font("Arial", Font.BOLD, 20));
		g.setColor(Color.white);
		if (min < 10){
		g.drawString(hour+":0"+min, 750, 585);
		}else{
			g.drawString(hour+":"+min, 750, 585);	
		}
	}

	@Override
	public void secTimer() {
		if(sceneHandler.inGame()){
	//	cash+=rate;
		sceneHandler.spawnPeople();
		cash = sceneHandler.getCash();
		if(min == 60){
			min=0;
			if(hour == 16){
				hour=0;
			}else{
				min++;
			hour++;
			}
		}else{
			min++;
		}
		System.out.println("Total Mobs: "+sceneHandler.getBotNumber()+" | Mobs in Current: "+((Store) sceneHandler.getCurrentView()).getMobs().size());
		}
		
	}

	public TextureHandler getTextureHandler(){
		return textHandler;
	}

	


}