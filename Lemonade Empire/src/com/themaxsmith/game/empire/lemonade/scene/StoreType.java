package com.themaxsmith.game.empire.lemonade.scene;

public class StoreType {
public static StoreType Stand = new StoreType("store1.png", 1, spawnRate.Low_Spawn, "Stand");
public static StoreType Cart = new StoreType("store3.png", 3, spawnRate.Med_Spawn, "Cart");
public static StoreType Truck = new StoreType("store4.png", 7, spawnRate.Med_Spawn, "Truck");
public static StoreType Building = new StoreType("store5.png", 10, spawnRate.High_Spawn, "Building");
public static enum spawnRate{
	Low_Spawn, Med_Spawn, High_Spawn
}
private int pop;
private String res;
private spawnRate rate;
private String name;
public StoreType(String res, int populairty, spawnRate t, String nm){
	this.res = res;
	this.pop = populairty;
	rate = t;
	name= nm;
}
public spawnRate getRate() {
	return rate;
}
public void setRate(spawnRate rate) {
	this.rate = rate;
}
public int getPop() {
	return pop;
}
public void setPop(int pop) {
	this.pop = pop;
}
public String getRes() {
	return res;
}
public void setRes(String res) {
	this.res = res;
}
@Override
public String toString(){
	return name;
}

}
