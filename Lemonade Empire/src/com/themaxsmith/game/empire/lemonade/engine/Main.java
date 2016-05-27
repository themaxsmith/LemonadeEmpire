package com.themaxsmith.game.empire.lemonade.engine;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import com.themaxsmith.game.empire.lemonade.logic.SceneHandler;
import com.themaxsmith.game.empire.lemonade.perks.Perk;
import com.themaxsmith.game.empire.lemonade.render.Screen;
import com.themaxsmith.game.empire.lemonade.render.TextureHandler;
import com.themaxsmith.game.empire.lemonade.scene.Menu;
import com.themaxsmith.game.empire.lemonade.scene.Store;

public class Main extends GameFrame {
	
	public static final String NAME = "Lemonade Empire";
	
	private TextureHandler textHandler;
	private SceneHandler sceneHandler;
	private int cash=0, hour=8, min = 0; 
	private int popularity = 0;
	private Perk perk;

	public static void main(String[] args) {
		Main game = new Main();
		game.setupFrame(game);
		game.start();
	}
	
	@Override
	public void init() {
      
		setScreen(new Screen(WIDTH,HEIGHT));
		textHandler = new TextureHandler();
		sceneHandler = new SceneHandler(this);
		
		Menu menu = new Menu(sceneHandler, "start.png");
		sceneHandler.setScene(menu);
		addMouseListener(sceneHandler.onClick());
		addKeyListener(menu.keylistner());
		
	}

	@Override
	public void tick() {
		tickcounter();
		sceneHandler.tick();
	}

	@Override
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
		renderCashAndPop(g);
		renderGameTime(g);
		g.setColor(new Color(255, 255, 255,137));
		//g.fillRect(0, 200, 200, 70);
	}}
	
	public void renderCashAndPop(Graphics g ){
		
		g.setFont(new Font("Arial", Font.BOLD, 20));
		g.setColor(new Color(37, 99, 26));
		g.drawString("$"+getCash(), 10, 55);
		
		g.fillRect(10, 20, 10*((Store)sceneHandler.getCurrentView()).getPopularity(), 10);
		g.setColor(Color.BLACK);
		g.drawRect(10, 20, 200, 10);
		g.setFont(new Font("Arial", Font.BOLD, 14));
		g.drawString("Brand Popularity: "+getPopularity()+" Store: "+((Store)sceneHandler.getCurrentView()).getStorePop()+"", 10, 15);
		g.setFont(new Font("Arial", Font.BOLD, 20));
		
		
	}


	public void renderGameTime(Graphics g ){
		g.setFont(new Font("Arial", Font.BOLD, 20));
		g.setColor(Color.white);
		if (min < 10){
		g.drawString(hour+":0"+min, 750, 585);
		}else{
			g.drawString(hour+":"+min, 750, 585);	
		}
		if (perk !=null){
			g.drawString("BONUS: "+perk.getDuration(), 500, 585);	
		}
	}

	@Override
	public void secTimer() {
		if(sceneHandler.inGame()){
		sceneHandler.spawnPeople();
		
		if(min == 59){
			min=0;
			if(hour == 16){
				hour=8;
			}else{
				min++;
			hour++;
			}
		}else{
			min++;
		}
		
		if (perk !=null){
			perk.tick();
			
		if (perk.isRemove())
			perk=null;
		}
		
		System.out.println("Total Mobs: "+sceneHandler.getBotNumber()+" | Mobs in Current: "+((Store) sceneHandler.getCurrentView()).getMobs().size());
		}
		
	}
	
	public TextureHandler getTextureHandler(){
		return textHandler;
	}

	public int getCash() {
		return cash;
	}

	public void addCash(int cash) {
		this.cash += cash;
	}

	public int getPopularity() {
		return popularity;
	}

	public void addPopularity(int popularity) {
		
		this.popularity = popularity+getPopularity();
	}

	public boolean canBuy(int cost){
		if (cash >= cost)
		return true;
		return false;
	}

	public Perk getPerk() {
		return perk;
	}

	public void setPerk(Perk perk) {
		if (this.perk != null)
			this.perk.stop();
		
		this.perk = perk;
	}

	public void setPopularity(int o) {
		popularity = o;
		
	}

	public void setCash(int u) {
	cash = u;
		
	}



}