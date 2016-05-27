package com.themaxsmith.game.empire.lemonade.GUI;

import com.themaxsmith.game.empire.lemonade.logic.HitBoxHandler;
import com.themaxsmith.game.empire.lemonade.logic.SceneHandler;
import com.themaxsmith.game.empire.lemonade.scene.Scene;
import com.themaxsmith.game.empire.lemonade.scene.Store;

public  class UpgradeBrandButton extends Button {

	private int cost;


	public UpgradeBrandButton(SceneHandler sceneHandler, HitBoxHandler hand, int x, int y, Layout l) {
		super(sceneHandler, hand, "Upgrade Button", x, y, l);
		cost=20;
		setName("$"+cost+" | Upgrade Lemonade Formula");
	}

	

	@Override
	public void onHit() {
		if (getLevel().getHandler().getGame().canBuy(cost) && getLevel().getHandler().getGame().getPopularity() < 9){
		Store g = ((Store) getLevel());
		g.getHandler().getGame().addPopularity(2);
		g.addCash(-cost);
		cost*=8;
		setName("$"+cost+" | Upgrade Lemonade Formula");
		}
		
	}

	

}
