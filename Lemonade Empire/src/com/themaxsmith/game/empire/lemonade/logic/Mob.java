package com.themaxsmith.game.empire.lemonade.logic;

import java.awt.Graphics;

import com.themaxsmith.game.empire.lemonade.render.Screen;
import com.themaxsmith.game.empire.lemonade.render.Texture;
import com.themaxsmith.game.empire.lemonade.scene.Scene;

public abstract class Mob {
	private int x, y;
	private Texture texture;
	private String name;
	private boolean  remove=false;
	private Scene level;
	public Mob(String name, Scene level, String texturepath, int x, int y){
	this.setName(name);
	this.x = x;
	this.y = y;
	this.setLevel(level);
	this.texture = level.getHandler().getGame().getTextureHandler().getTexture(texturepath);
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public Texture getTexture(){
		return texture;
	}
	public void setTexture(String texturepath){
		this.texture = getLevel().getHandler().getGame().getTextureHandler().getTexture(texturepath);
	}
	public abstract void render(Screen screen);
	public abstract void tick();
	public abstract void renderOverlay(Graphics g );
	public void setY(int y) {
		this.y = y;
	}
	public void setX(int x) {
		this.x = x;
	}
	public boolean isRemove() {
		return remove;
	}
	public void setRemove(boolean remove) {
		this.remove = remove;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Scene getLevel() {
		return level;
	}
	public void setLevel(Scene level) {
		this.level = level;
	}
}
