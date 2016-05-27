package com.themaxsmith.game.empire.lemonade.perks;

import com.themaxsmith.game.empire.lemonade.engine.Main;

public abstract class Perk {
	private int duration;
	private Main game;
	private boolean remove = false;
	public Perk(Main parent,int duration){
		this.setDuration(duration);
		this.setGame(parent);
		getGame().setPerk(this);
		start();
	}
public void tick(){
	duration--;
	System.out.println(duration);
	if (duration==0){
		stop();
		setRemove(true);
	}
}
public abstract void start();
public abstract void stop();
public int getDuration() {
	return duration;
}
public void setDuration(int duration) {
	this.duration = duration;
}
public boolean isRemove() {
	return remove;
}
public void setRemove(boolean remove) {
	this.remove = remove;
}
public Main getGame() {
	return game;
}
public void setGame(Main game) {
	this.game = game;
}

}
