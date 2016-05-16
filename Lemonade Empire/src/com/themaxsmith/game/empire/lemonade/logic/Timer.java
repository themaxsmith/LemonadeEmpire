package com.themaxsmith.game.empire.lemonade.logic;

public class Timer {
	private long time;

public void start(){
	time = System.currentTimeMillis();
}
public void end(){
	time = System.currentTimeMillis() - time ;
}
public double getTimeTook(){
	return (double) time/1000;
	
}

}
