package com.themaxsmith.game.empire.lemonade.scene;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import com.themaxsmith.game.empire.lemonade.engine.GameFrame;
import com.themaxsmith.game.empire.lemonade.engine.Main;
import com.themaxsmith.game.empire.lemonade.logic.Bot;
import com.themaxsmith.game.empire.lemonade.logic.SceneHandler;
import com.themaxsmith.game.empire.lemonade.render.HitBox;
import com.themaxsmith.game.empire.lemonade.render.HitBoxParent;
import com.themaxsmith.game.empire.lemonade.render.Screen;

public class Menu extends Scene implements HitBoxParent {
	private int x, imgx;
	private boolean switchb= false, setup=false;
	private BufferedImage level;
	private String name = new String();
	private List<Bot> enemys = new ArrayList<Bot>();
	private int alpha = 0;
	private int timer, dialog;
	private String line1 = new String(), line2= new String(), p1= new String(),p2= new String();
	private boolean clicked = false;
	public Menu( SceneHandler handler, String path) {
		super(handler,path);
		setMenu(true);
	initHitBox(new HitBox(this,288,515,540,567) {
			
			@Override
			public void onHit() {
				System.out.println("enter: "+name);
				clicked = true;
			}
		});
		

	}
	

	public void render(Screen screen) {
		if (switchb){
			
			
			switchb=false;
			
		}
	}
	
	public void tick(){
if(clicked){
	if (alpha <250){
	alpha+=5;
	}else{
		alpha=255;
		timer++;
		if(timer == 59){
			line1="";
			line2="";
		}
		if (timer == 64){
			timer=60;
		if(line1.length() != p1.length()){
			line1=p1.substring(0, line1.length()+1);
			if(line1.length() == p1.length()){
				line2="";
			}
		}else if (line2.length() != p2.length()){
			line2=p2.substring(0, line2.length()+1);
		
		}else if(dialog < 3){
			timer=0;
			
			
			
		switch (dialog) {
			case 0:
				switchb=true;
				p1="they need to end summer with a blast!";
				p2="but how?";
				dialog++;
				break;
			case 1:
			
				dialog++;
				p1="Jack: \"Lemonade?\"";
				p2=name+": \"Sure. let's make some Lemonade\"";
				break;
			case 2:
				clicked=false;
			
				startGame();
				break;
			}	
	
	}}
	}
	}else{
	
	}
	}
	public void startGame(){
		
		
		getHandler().addStore(new Store(getHandler(), StoreType.Stand));	
		getHandler().setStore(1);
		getHandler().startGame();

	

		
	}
	public KeyListener keylistner(){
		return new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {

			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (clicked){
					if(e.getKeyCode() == KeyEvent.VK_SPACE){
						startGame();
					}
				}else{
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					System.out.println("enter: "+name);
					clicked=true;
					p1="thirteen year olds "+name+" and Jack are in the middle of summer";
					p2="when it hits them...";
				}else if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
					if (name.length()>0)
					name = name.substring(0, name.length()-1);
				}else{
					if (e.getKeyCode() > 40 || e.getKeyCode() == KeyEvent.VK_SPACE){
						if (name.length() <= 15)
					name += e.getKeyChar();
					
				}}}
			}

			@Override
			public void keyReleased(KeyEvent e) {

			}
		};
		
	
	}

	@Override
	public void renderOverlay(Graphics g) {

	
		g.setColor(Color.BLACK);
		g.setFont(new Font("Arial", Font.BOLD, 20));
		if (!(setup)){
		g.drawString(name, 300, 490);
		}else{
			
		}
		g.setColor(new Color(0, 0, 0, alpha));
		g.fillRect(0, 0, Main.WIDTH, Main.HEIGHT);
		g.setColor(new Color(255, 255, 255, alpha));
		g.setFont(new Font("Arial", Font.BOLD, 10));
		g.drawString("Lemonade Empire By Maxwell Smith", 600, 580);
		g.setFont(new Font("Arial", Font.BOLD, 20));
		g.drawString(line1, 100, 250);
		g.drawString(line2, 100, 300);
	}


	@Override
	public boolean isRemove() {
		// TODO Auto-generated method stub
		return false;
	}
} 