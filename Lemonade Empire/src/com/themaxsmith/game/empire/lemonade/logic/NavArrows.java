package com.themaxsmith.game.empire.lemonade.logic;

import com.themaxsmith.game.empire.lemonade.render.HitBox;
import com.themaxsmith.game.empire.lemonade.render.Screen;
import com.themaxsmith.game.empire.lemonade.render.Texture;
import com.themaxsmith.game.empire.lemonade.scene.Scene;


public class NavArrows {
	private int y;
	private HitBox left,right;
	private Texture texture;
	private Scene scene;
	public NavArrows(Scene s,int y){
		this.y = y;
		scene=s;
		this.texture = s.getHandler().getGame().getTextureHandler().getTexture("arrow.png");
		scene.initHitBox(new HitBox(740,y,60,100) {
			
			@Override
			public void onHit() {
				scene.getHandler().setStore(scene.getHandler().getCurrentStoreID()+1);
				
			}
		});
		

		scene.initHitBox(new HitBox(0,y,60,100) {
	
	@Override
	public void onHit() {
		scene.getHandler().setStore(scene.getHandler().getCurrentStoreID()-1);
		
	}
});


		
	}
	
	public void render(Screen screen){
		screen.render(0, y, 1, texture);
		
	}
}
