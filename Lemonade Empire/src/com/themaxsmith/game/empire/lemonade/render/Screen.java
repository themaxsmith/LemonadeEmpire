package com.themaxsmith.game.empire.lemonade.render;

import java.awt.image.BufferedImage;



public class Screen {
	private int[] pixels;

	private int width;
	private int height;
	private int[] level, player, wholelevel;

	public Screen(int width, int height) {
		this.setWidth(width);
		this.setHeight(height);

		setPixels(new int[width * height]);  
		setLevel(new int[width * height]);
	}

	public void render(int xPos, int yPos, double scale, BufferedImage img) {
		int color;
		for (int y = 0; y < ((img.getHeight() * scale)); y++) {
			for (int x = 0; x < ((img.getWidth() * scale)); x++) {
				color = img.getRGB(x, y);
				if (y + yPos < getHeight() && (x) + xPos < getWidth() && (x) + xPos > 0
						&& y + yPos > 0) {
					getPixels()[(xPos + (x) + ((y + yPos) * getWidth()))] = color;
				}
			}
		}
	}
	
	public void render(int xPos, int yPos, double scale, Texture text) {
		int color;
		for (int y = 0; y < ((text.height * scale)); y++) {
			for (int x = 0; x < ((text.width * scale)); x++) {
				color = text.texture[x+(y*text.width)];
				if (y + yPos < getHeight() && (x) + xPos < getWidth() && (x) + xPos > 0
						&& y + yPos > 0 && (color < 0)) {
					getPixels()[(xPos + (x) + ((y + yPos) * getWidth()))] = color;
					
				}
			}
		}
	}
	public void renderlevel(int xoff, BufferedImage img) {
		
		for (int y = 0; y < getHeight(); y++) {
			for (int x = 0; x < getWidth(); x++) {

				getLevel()[x + (y * getWidth())] = wholelevel[xoff + x + (y * getWidth())];

			}
		}
	}



	public void render(int xPos, int yPos, double scale, int xoff, int yoff,
			int xsize, int ysize, Texture texture, boolean tran, int reverse) {
		int color, transparent = 0;

		if (reverse == -1) {
			xPos = (int) (xPos + ((texture.width * scale) - xoff - xsize));
		}
		if (tran) {
			transparent = 16777215;
		}
		for (int y = 0; y < ((texture.height * scale) - yoff - ysize); y++) {
			for (int x = 0; x < ((texture.width * scale) - xoff - xsize); x++) {
				int xmath = (int) (xoff + (x / scale));
				int ymath = (int) (yoff + (y / scale));
				 color = texture.texture[xmath+(ymath*texture.width)];

				if (y + yPos < getHeight() && (x / reverse) + xPos < getWidth()
						&& (x / reverse) + xPos > 0 && y + yPos > 0
						&& (color < 0)) {
					getPixels()[(xPos + (x / reverse) + ((y + yPos) * getWidth()))] = color;

				}
			}
		}
	}

	public void initlvl(BufferedImage img) {
		wholelevel = new int[img.getHeight() * img.getWidth()];

		for (int y = 0; y < ((img.getHeight())); y++) {
			for (int x = 0; x < ((img.getWidth())); x++) {

				wholelevel[((x) + ((y) * getWidth()))] = img.getRGB(((x)),
						((y)));
			}
		}

	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int[] getPixels() {
		return pixels;
	}

	public void setPixels(int[] pixels) {
		this.pixels = pixels;
	}

	public int[] getLevel() {
		return level;
	}

	public void setLevel(int[] level) {
		this.level = level;
	}
}
