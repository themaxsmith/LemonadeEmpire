package com.themaxsmith.game.empire.lemonade.perks;

import com.themaxsmith.game.empire.lemonade.engine.Main;

public class AdvertisePerk  extends Perk{

	public AdvertisePerk(Main main,int duration) {
		super(main, duration);
	}

	@Override
	public void start() {
	 getGame().addPopularity(2);
		
	}

	@Override
	public void stop() {
    getGame().addPopularity(-2);
		
	}

}
