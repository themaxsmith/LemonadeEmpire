package com.themaxsmith.game.empire.lemonade.logic;

import com.themaxsmith.game.empire.lemonade.render.HitBox;
import com.themaxsmith.game.empire.lemonade.render.HitBoxParent;
import com.themaxsmith.game.empire.lemonade.render.Screen;
import com.themaxsmith.game.empire.lemonade.render.Texture;
import com.themaxsmith.game.empire.lemonade.scene.Scene;


public class NavArrows implements HitBoxParent {
	private int y;
	private HitBox left,right;
	private Texture texture;
	private Scene scene;
	private boolean work = false;
	public NavArrows(Scene s,int y){
		this.y = y;
		scene=s;
		this.texture = s.getHandler().getGame().getTextureHandler().getTexture("arrow.png");
		
		right = new HitBox(this,740,y,60,100) {
			
			@Override
			public void onHit() {
				scene.getHandler().setStore(scene.getHandler().getCurrentStoreID()+1);
				
			}
		};
		left =  new HitBox(this,0,y,60,100) {
			
			@Override
			public void onHit() {
				scene.getHandler().setStore(scene.getHandler().getCurrentStoreID()-1);
				
			}
		};
		scene.initHitBox(left);

		scene.initHitBox(right);
		left.setReg(false);
		right.setReg(false);
		
	}
	
	public void render(Screen screen){
		if(scene.getHandler().isMutiple()){
			if (work==false){
			left.setReg(true);
			right.setReg(true);
			work=true;
			}	}
		screen.render(0, y, 1, texture);
		
	}

	@Override
	public boolean isRemove() {
		// TODO Auto-generated method stub
		return false;
	}
}
