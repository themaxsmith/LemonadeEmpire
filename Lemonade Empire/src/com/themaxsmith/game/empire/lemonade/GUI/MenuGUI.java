package com.themaxsmith.game.empire.lemonade.GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import com.themaxsmith.game.empire.lemonade.GUI.Button.Layout;
import com.themaxsmith.game.empire.lemonade.logic.HitBoxHandler;
import com.themaxsmith.game.empire.lemonade.perks.Perk;
import com.themaxsmith.game.empire.lemonade.render.HitBox;
import com.themaxsmith.game.empire.lemonade.scene.Scene;
import com.themaxsmith.game.empire.lemonade.scene.Store;
import com.themaxsmith.game.empire.lemonade.scene.StoreType;


public class MenuGUI implements HitBoxHandler {
	private ArrayList<Button> buttons = new ArrayList<Button>();
	private ArrayList<HitBox> hitboxes = new ArrayList<HitBox>();

	private boolean isActive = false;
	private Button upgrade;
	
	public MenuGUI(Store level){
	
	
	upgrade = new Button(level, this,"Menu", 350, 0, Layout.SMALL_CENTERED) {
		
		@Override
		public void onHit() {
			if (isActive){
				isActive = false;
				upgrade.setName("Menu");
				upgrade.setupLayout(Layout.SMALL_CENTERED);
			}else{
				isActive = true;
				upgrade.setName("Close");
				upgrade.setupLayout(Layout.SMALL_CENTERED);
			}
		}
	};
	buttons.add(new Button(level, this, "Buy/Upgrades", 70, 50, Layout.MED_CENTERED) {
		@Override
		public void onHit() {
			// TODO Auto-generated method stub	
		}
	});
	buttons.add(new Button(level, this, "Stats", 70, 100, Layout.MED_CENTERED) {
		@Override
		public void onHit() {
			// TODO Auto-generated method stub	
		}
	});

	buttons.add(new BuyButton(level, this, 300, 50, 20, "Advertise for a hour", 5) {
		public void onHit() {
			Perk p = new Perk(getLevel().getHandler().getGame(), 10) {
				
				@Override
				public void stop() {
					getGame().addPopularity(-5);
					
				}
				
				@Override
				public void start() {
					getGame().setPerk(this);
					getGame().addPopularity(2);
				}
			};
	
		}});
	
	buttons.add(new Button(level, this, "$40 | Basic Table (Store)", 300, 100, Layout.LARGE_LEFT) {
		
		@Override
		public void onHit() {
			getLevel().getHandler().addStore(new Store(getLevel().getHandler(), StoreType.Building));
			getLevel().getHandler().setMutiple(true);
		}
	});
	buttons.add(new Button(level, this, "$60 | Basic Cart (Store)", 300, 150, Layout.LARGE_LEFT) {
		
		@Override
		public void onHit() {
			// TODO Auto-generated method stub
			
		}
	});
	buttons.add(new Button(level, this, "$120 | Truck (Store)", 300, 200, Layout.LARGE_LEFT) {
		
		@Override
		public void onHit() {
			// TODO Auto-generated method stub
			
		}
	});
	buttons.add(new Button(level, this, "$500 | Building (Store)", 300, 250, Layout.LARGE_LEFT) {
		
		@Override
		public void onHit() {
			// TODO Auto-generated method stub
			
		}
	});
	buttons.add(new Button(level, this, "$10 P/Hour | Hire an Employee @ Store "+level.getHandler().getCurrentStoreID(), 300, 300, Layout.LARGE_LEFT) {
		
		@Override
		public void onHit() {
			// TODO Auto-generated method stub
			
		}
	});
	buttons.add(new UpgradeBrandButton(level, this, 300, 350, Layout.LARGE_LEFT));
	level.initHitBox(upgrade.getHitBox());
}
	public boolean isActive(){
		return isActive;
	}
	public void renderOverlay(Graphics g) {
	upgrade.renderOverlay(g);
	if (isActive){
		g.setColor(new Color(0, 0, 0, 170));
		g.fillRect(50, 30, 700, 500);
	for (Button btn : buttons){
		btn.renderOverlay(g);
		
	}
	
	}
	}	public void initHitBox(HitBox x){
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
	
}
