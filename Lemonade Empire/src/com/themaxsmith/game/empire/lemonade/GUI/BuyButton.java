package com.themaxsmith.game.empire.lemonade.GUI;

import com.themaxsmith.game.empire.lemonade.logic.HitBoxHandler;
import com.themaxsmith.game.empire.lemonade.logic.SceneHandler;
import com.themaxsmith.game.empire.lemonade.scene.Scene;

public abstract class BuyButton extends Button{
	private int cost;
	private int multipler;
	private String desc;
	public BuyButton(SceneHandler sceneHandler, HitBoxHandler hand, int x, int y, int cost, String desc, int mutipler) {
		super(sceneHandler, hand, "$"+cost+" | "+desc, x, y, Layout.LARGE_LEFT);
		this.cost = cost;
		this.desc = desc;
		this.multipler = mutipler;
	}
	@Override
	public void hit(){
	 if (getLevel().getHandler().getGame().canBuy(cost)){	
		onHit();
		getLevel().getHandler().getGame().addCash(-cost);
		cost*=multipler;
		setName("$"+cost+" | "+desc);
	 }
	}

}
