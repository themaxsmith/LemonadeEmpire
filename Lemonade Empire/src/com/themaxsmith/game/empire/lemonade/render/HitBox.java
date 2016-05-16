package com.themaxsmith.game.empire.lemonade.render;

import java.awt.event.MouseEvent;

public abstract class HitBox implements onHit{
private int x,y,endx,endy;
private boolean reg = true;

	public HitBox(int x, int y, int width, int height){
		this.x = x;
		this.y = y;
		this.endx = x+width;
		this.endy = y+height;
		
	}
public void setReg(boolean x){
	reg = x;
}
public boolean didClick(MouseEvent e){
	
	if (x < e.getX() && e.getX() < endx && y < e.getY() && e.getY() < endy && reg)
	return true;
	return false;
	
}
public boolean isHovering(MouseEvent e){
	
	if (x < e.getX() && e.getX() < endx && y < e.getY() && e.getY() < endy && reg)
	return true;
	return false;
	
}
public void setHit(int x, int y, int width, int height){
	this.x = x;
	this.y = y;
	this.endx = x+width;
	this.endy = y+height;
}
public void onClick(MouseEvent e){
	if (didClick(e))
		onHit();
}
public void setHit(int x, int y){
	this.x = x;
	this.y = y;
	
}
}
