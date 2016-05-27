package com.themaxsmith.game.empire.lemonade.logic;


import java.awt.Graphics;

import com.themaxsmith.game.empire.lemonade.engine.GameFrame;
import com.themaxsmith.game.empire.lemonade.engine.Main;
import com.themaxsmith.game.empire.lemonade.render.HitBox;
import com.themaxsmith.game.empire.lemonade.render.HitBoxParent;
import com.themaxsmith.game.empire.lemonade.render.Screen;
import com.themaxsmith.game.empire.lemonade.render.Texture;
import com.themaxsmith.game.empire.lemonade.scene.Store;

public class Bot extends Mob implements HitBoxParent {
	

	
	public int WALKSPEED = 1;
	private int direct = 1, anim=0;
	private boolean goBuy = false,bought=false;
	private HitBox hit;
	private Texture hittext;
	private Store store;
	
	public Bot(Store level, String texturepath,int direct, int yoff){
		super("Bot", level ,texturepath,0,320+yoff);
		this.direct=direct;
		store=level;
		
		hittext=getLevel().getHandler().getGame().getTextureHandler().getTexture("question.png");
		if (direct==1){
		this.setX(5);
		}else{
		setX(GameFrame.WIDTH);
		}
	
		anim= (int)(Math.random()*30);
		int b = (level.getPopularity());
		
		int y = (int)(Math.random()*21);

		
			hit = new HitBox(this,getX(), getY(), 80, 220) {
			
			@Override
			public void onHit() {
				goBuy=true;
				WALKSPEED =1;
				this.setReg(false);
			}
		};
		level.initHitBox(hit);
		if (b>=y){
			hit.setReg(false);
			goBuy=true;
		}
	}
	@Override
	public void tick(){
//		if (store.inBackground()){
//
//			if(direct==1){
//			setX(getX()+WALKSPEED);
//			if(getX() > Main.WIDTH){
//			setRemove(true);
//			}
//			}else{
//				setX(getX()-WALKSPEED);	
//			if(getX() < 0){
//				setRemove(true);
//			}
//			}
//			
//		}else{
		anim++;
		if (anim==30){
		anim=0;	
		}
		if(goBuy){
			
			if(anim > 120){
				//DELETE
				store.addCash(2);
				setRemove(true);
				
			}else{
			
			if(getX() < 425 && 375 < getX()){
				if(bought){
					
				}else{
					bought=true;
				anim=50;}
				
			}else{
			if (getX() > 400){
				
				direct= -1;
				setX(getX()-WALKSPEED);	
			}else{
				direct= 1;
				setX(getX()+WALKSPEED);
			}}}
		}else{
		if(direct==1){
		setX(getX()+WALKSPEED);
		if(getX() > GameFrame.WIDTH){
		setRemove(true);
		}
		}else{
			setX(getX()-WALKSPEED);	
		if(getX() < 0){
			setRemove(true);
		}
		}}
		if (!(getLevel().inBackground()))
		hit.setHit(getX()+20, getY(), 40, 220);
	}
	@Override
	public void render(Screen screen) {
			if(anim > 40){
			screen.render(getX(), getY() ,1, 160, 0, 0, 0, getTexture(), true, direct);	
		}else{
		if(anim > 15){
		screen.render(getX(), getY() ,1, 0, 0, 160, 0, getTexture(), true, direct);
		
		}else{
			screen.render(getX(), getY() ,1, 80, 0, 80, 0, getTexture(), true, direct);
		}}
			if (goBuy && !(bought))
			screen.render(getX(), getY()-30,1,0 , 0, 0, 0, hittext , true, 1);	


}
	@Override
	public void renderOverlay(Graphics g ){
		if(bought){
		g.drawString("+ $2", getX()+20, getY()+20-(anim-50));
		}
	}
	
	public boolean toRemove() {
		// TODO Auto-generated method stub
		return isRemove();
	}
}