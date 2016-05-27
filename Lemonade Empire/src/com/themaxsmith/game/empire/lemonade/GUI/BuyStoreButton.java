package com.themaxsmith.game.empire.lemonade.GUI;

import com.themaxsmith.game.empire.lemonade.logic.HitBoxHandler;
import com.themaxsmith.game.empire.lemonade.logic.SceneHandler;
import com.themaxsmith.game.empire.lemonade.scene.Scene;
import com.themaxsmith.game.empire.lemonade.scene.Store;
import com.themaxsmith.game.empire.lemonade.scene.StoreType;

public class BuyStoreButton extends BuyButton {
	private StoreType type;
	public BuyStoreButton(SceneHandler sceneHandler, HitBoxHandler hand, int x, int y, int cost,StoreType p ) {
		super(sceneHandler, hand, x, y, cost, "Basic "+p+" (Store)", 5);
		type=p;
	}

	@Override
	public void onHit() {
		getLevel().getHandler().addStore(new Store(getLevel().getHandler(), type));
		if(getLevel().getHandler().getGame().getPopularity() < 10)
		getLevel().getHandler().getGame().addPopularity(1);
		getLevel().getHandler().setMutiple(true);
		
		
	}

}
