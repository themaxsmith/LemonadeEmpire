package com.themaxsmith.game.empire.lemonade.engine;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;



import com.themaxsmith.game.empire.lemonade.render.Screen;

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

	@Override
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
			if (System.currentTimeMillis() - lastTimer >= 5000){
			System.out.println("Has System Time Changed, or the Game is laging? Time changed by "+(System.currentTimeMillis() - lastTimer)/1000+" sec");	
			lastTimer = System.currentTimeMillis() - 1000;
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
	    	
			g.drawImage(ImageIO.read(new File("LemonadeEmpire/Loading.png")), 0,0, null);
			System.out.println("Loaded Loading.png");
		} catch (IOException e) {
			String imageCode = "data:image/png;base64,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AAAECAAAgAABAAAECAAAgAABAAAECAAAIEAAAAAECAAAIEAAAAAECAAAIEAAAAABAgAAIEAAAAABAgAAIEAAAAABAgAACBAAAAABAgAACBAAAAABAgAACBAAAAABAgAACBAAAECAAAAACBAAAECAAAAACBAAAECAAAAAAgQAAECAAAAAAgQAAECAAAAAAgQAABAgAAAAAgQAABAgAAAAAgQAABAgAACAAAEAABAgAACAAAEAABAgAACAAAEAAAQIAACAAAEAAAQIAACAAAEAAAQIAACAAAEAAAQIAAAgQAAAAAQIAAAgQAAAAAQIAAAgQAAAAAECAAAgQAAAAAECAAAgQAAAAAECAAAIEAAAAAECAAAIEAAAAAECAAAIEAAAQIAAAAAIEAAAQIAAAAAIEAAAQIAAAAACBAAAQIAAAAACBAAAQIAAAAACBAAAECAAAAACBAAAECAAAAACBAAAECAAAAACBAAAECAAAIAAAQAAECAAAIAAAQAAECAAAIAAAQAABAgAAIAAAQAABAgAAIAAAQAABAgAACBAAAAABAgAACBAAAAABAgAACBAAAAAAQIAACBAAAAAAQIAACBAAAAAAQIAAAgQAAAAAQIAAAgQAAAAAQIAAAgQAAAAAQIAAAgQAABAgAAAAAgQAABAgAAAAAgQAABAgAAAAAIEAABAgAAAAAIEAABAgAAAAAIEAAAQIAAAAAIEAAAQIAAAAAIEAAAQIAAAgAABAAAQIAAAgAABAAAQIAAAgAABAAAECAAAgAABAAAECAAAgAABAAAECAAAgAABAAAECAAAIEAAAAAECAAAIEAAAAAECAAAIEAAAAABAgAAIEAAAAABAgAAIEAAAAABAgAACBAAAAABAgAACBAAAAABAgAACBAAAECAAAAACBAAAECAAAAACBAAAECAAAAAAgQAAECAAAAAAgQAAECAAAAAAgQAABAgAAAAAgQAABAgAAAAAgQAABAgAAAAAgQAABAgAACAAAEAABAgAACAAAEAABAgAACAAAEAAAQIAACAAAEAAAQIAACAAAEAAAQIAAAgQAAAAAQIAAAgQAAAAAQIAAAgQAAAAAECAAAgQAAAAAECAAAgQAAAAAECAAAIEAAAAAECAAAIEAAAAAECAAAIEAAAAAECAAAIEAAAYFr6fwMA7kXKhp+fixIAAAAASUVORK5CYII=";
			String base64Image = imageCode.split(",")[1];

			// Convert the image code to bytes.
			byte[] imageBytes = javax.xml.bind.DatatypeConverter.parseBase64Binary(base64Image);

			try {
				BufferedImage down = ImageIO.read(new ByteArrayInputStream(imageBytes));
				g.drawImage(down, 0, 0, null);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
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