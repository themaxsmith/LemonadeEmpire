package com.themaxsmith.game.empire.lemonade.engine;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;



import com.themaxsmith.game.empire.lemonade.render.Screen;
import com.themaxsmith.game.empire.lemonade.render.TextureHandler;

public abstract class GameFrame extends Canvas implements Runnable {
	//CONSTANTS
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 800;
	public static final int HEIGHT = WIDTH / 12 * 9;
	public static final int SCALE = 1;
	public static final String NAME = "Lemonade Empire";
	public static final Dimension DIMENSIONS = new Dimension(WIDTH * SCALE,HEIGHT * SCALE);
	
	
	private Screen screen;
	private static Thread thread;
	private static boolean running = false;
	private JFrame frame;
	private String fps = "";
	
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT,BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
	private int tickCount;
	

	public void setupFrame(Main game){
		
		game.setMinimumSize(GameFrame.DIMENSIONS);
		game.setMaximumSize(GameFrame.DIMENSIONS);
		game.setPreferredSize(GameFrame.DIMENSIONS);
		game.setFrame(new JFrame(GameFrame.NAME));

		game.getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.getFrame().setLayout(new BorderLayout());

		game.getFrame().add(game, BorderLayout.CENTER);
		game.getFrame().pack();

		game.getFrame().setResizable(false);
		game.getFrame().setLocationRelativeTo(null);
		game.getFrame().setVisible(true);
		System.out.println("LEMONADE EMPIRE | 0.1");
	}
	public abstract void init();

	public synchronized void start() {
		running = true;
		setScreen(new Screen(WIDTH, HEIGHT));
		thread = new Thread(this, NAME + "_main");
		thread.start();

	}

	public synchronized void stop() {
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		
		long lastTime = System.nanoTime();
		double nsPerTick = 1000000000D / 60D; //CAP @ 60 ticks

		int ticks = 0;
		int frames = 0;

		long lastTimer = System.currentTimeMillis();
		double delta = 0;

		init();

		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / nsPerTick;
			lastTime = now;
			boolean shouldRender = true;
			while (delta >= 1) {
				ticks++;
				tick();
				delta -= 1;
				shouldRender = true;
			}

			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			if (shouldRender) {

				frames++;
				renderframe();
			}

			if (System.currentTimeMillis() - lastTimer >= 1000) {
				lastTimer += 1000;
				fps = "FPS: " + frames + " Ticks: " + ticks;
				frames = 0;
				ticks = 0;
				secTimer();
			}
		}
	}
	public abstract void secTimer();
	public void tickcounter() {
		tickCount++;
	}
	
	public void renderForground(){
		for (int y = 0; y < getScreen().getHeight(); y++) {
			for (int x = 0; x < getScreen().getWidth(); x++) {
				getPixels()[x + (y * WIDTH)] = getScreen().getPixels()[x + (y * WIDTH)];
			}
		}
	}
	
	public void renderBackground(){
		for (int y = 0; y < getScreen().getHeight(); y++) {
			for (int x = 0; x < getScreen().getWidth(); x++) {
				getScreen().getPixels()[x + (y * WIDTH)] = getScreen().getLevel()[x + (y * WIDTH)];
			}
		}
	}
	
	public void renderFPS(Graphics g ){
		g.setFont(new Font("Arial", Font.BOLD, 20));
		g.setColor(Color.white);
		g.drawString(fps, 10, 585);
	}
	
	
	public abstract void tick();
	public abstract void render();
	public abstract void renderOverlay(Graphics g);

	@Override
	public void paint (Graphics g) { //Initial Loading Screen
	    try {
	    	
			g.drawImage(ImageIO.read(new File("res/Loading.png")), 0,0, null);
			System.out.println("Loaded Loading.png");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void renderframe(){
		
		render();
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
	
		
		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		renderOverlay(g);
		
		g.dispose();
		bs.show();
	}
	public JFrame getFrame() {
		return frame;
	}
	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
	public Screen getScreen() {
		return screen;
	}
	public void setScreen(Screen screen) {
		this.screen = screen;
	}
	public int[] getPixels() {
		return pixels;
	}
	public void setPixels(int[] pixels) {
		this.pixels = pixels;
	}
	public void delay(int sec){
		System.currentTimeMillis();
	}
	

}