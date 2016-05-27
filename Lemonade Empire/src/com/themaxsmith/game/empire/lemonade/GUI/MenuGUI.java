package com.themaxsmith.game.empire.lemonade.GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import com.themaxsmith.game.empire.lemonade.GUI.Button.Layout;
import com.themaxsmith.game.empire.lemonade.logic.HitBoxHandler;
import com.themaxsmith.game.empire.lemonade.logic.SaveLoadGame;
import com.themaxsmith.game.empire.lemonade.logic.SceneHandler;
import com.themaxsmith.game.empire.lemonade.perks.AdvertisePerk;
import com.themaxsmith.game.empire.lemonade.render.HitBox;
import com.themaxsmith.game.empire.lemonade.scene.Store;
import com.themaxsmith.game.empire.lemonade.scene.StoreType;


public class MenuGUI implements HitBoxHandler {
	private ArrayList<Button> buttons = new ArrayList<Button>();
	private ArrayList<HitBox> hitboxes = new ArrayList<HitBox>();

	private boolean isActive = false;
	private Button upgrade;
	
	public MenuGUI(SceneHandler sceneHandler){
	
	
	setUpgrade(new Button(sceneHandler, this,"Menu", 350, 0, Layout.SMALL_CENTERED) {
		
		@Override
		public void onHit() {
			if (isActive){
				isActive = false;
				getUpgrade().setName("Menu");
				getUpgrade().setupLayout(Layout.SMALL_CENTERED);
			}else{
				isActive = true;
				getUpgrade().setName("Close");
				getUpgrade().setupLayout(Layout.SMALL_CENTERED);
			}
		}
	});

	buttons.add(new Button(sceneHandler, this, "Save", 70, 50, Layout.MED_CENTERED) {
		@Override
		public void onHit() {
			SaveLoadGame.Save(getLevel().getHandler());
		}
	});
	buttons.add(new Button(sceneHandler, this, "Load Game", 70, 100, Layout.MED_CENTERED) {
		@Override
		public void onHit() {
		SaveLoadGame.Load(getLevel().getHandler());
		}
	});
	buttons.add(new Button(sceneHandler, this, "Quit", 70, 150, Layout.MED_CENTERED) {
		@Override
		public void onHit() {
		System.exit(0);
		}
	});
	buttons.add(new BuyButton(sceneHandler, this, 300, 50, 20, "Advertise for 2 hours", 5) {
		@Override
		public void onHit() {
			 new AdvertisePerk(getLevel().getHandler().getGame(), 120);
		}});
	buttons.add(new BuyStoreButton(sceneHandler, this, 300, 100, 20, StoreType.Stand));
	buttons.add(new BuyStoreButton(sceneHandler, this, 300, 150, 50, StoreType.Cart));
	buttons.add(new BuyStoreButton(sceneHandler, this, 300, 200, 120, StoreType.Truck));
	buttons.add(new BuyStoreButton(sceneHandler, this, 300, 250, 400, StoreType.Building));
	buttons.add(new UpgradeBrandButton(sceneHandler, this, 300, 300, Layout.LARGE_LEFT));
	
}
	public boolean isActive(){
		return isActive;
	}
	public void renderOverlay(Graphics g) {
	getUpgrade().renderOverlay(g);
	if (isActive){
		g.setColor(new Color(0, 0, 0, 170));
		g.fillRect(50, 30, 700, 500);
	for (Button btn : buttons){
		btn.renderOverlay(g);
		
	}
	
	}
	}	@Override
	public void initHitBox(HitBox x){
		synchronized (getHitBoxes()) {
			getHitBoxes().add(x);
		}
		
		
	}
	public void onClick(MouseEvent e){
		synchronized (getHitBoxes()) {
			for(HitBox box : getHitBoxes()){
				if(box.didClick(e)){
				
					box.onHit();
				}
		
			}
			}}
	
	public ArrayList<HitBox> getHitBoxes() {
		return hitboxes;
	}
	public void setHitboxes(ArrayList<HitBox> hitboxes) {
		this.hitboxes = hitboxes;
	}
	public boolean onHover(MouseEvent e) {
		for(HitBox box : getHitBoxes()){
			if (box.isHovering(e))
				return true;
		}
		return false;
		
	}
	public Button getUpgrade() {
		return upgrade;
	}
	public void setUpgrade(Button upgrade) {
		this.upgrade = upgrade;
	}
	
}
