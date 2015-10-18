package com.nak.starfire;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import com.nak.starfire.gamestate.GameStateManager;
import com.nak.starfire.gfx.Window;
import com.nak.starfire.input.Keyboard;
import com.nak.starfire.input.Mouse;

public class Game extends Canvas implements Runnable {
	private static final long serialVersionUID = -2778996679942910918L;

	private Thread thread;
	private Keyboard keyboard;
	private Mouse mouse;
	private GameStateManager gsm;
	private Assets assets;

	public static final int HEIGHT = 130;
	public static final int WIDTH = 130;
	public static final String TITLE = "StarFire";
	public static final int SCALE = 3;
	private boolean running = false;

	public Game() {

		keyboard = new Keyboard();
		mouse = new Mouse();
		gsm = new GameStateManager();
		assets = new Assets();

		addKeyListener(keyboard);
		addMouseListener(mouse);
		addMouseMotionListener(mouse);
		requestFocus();
	}

	public void init() {
		assets.init();
	}

	public void start() {
		if (running)
			return;

		running = true;

		thread = new Thread(this);
		thread.start();
	}

	public void run() {
		long lastTime = System.nanoTime();
		double unprocessed = 0;
		double nsPerTick = 1000000000.0 / 60;
		int frames = 0;
		int ticks = 0;
		long lastTimer1 = System.currentTimeMillis();

		init();

		while (running) {
			long now = System.nanoTime();
			unprocessed += (now - lastTime) / nsPerTick;
			lastTime = now;
			boolean shouldRender = true;
			while (unprocessed >= 1) {
				ticks++;
				update();
				unprocessed -= 1;
				shouldRender = true;
			}

			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			if (shouldRender) {
				frames++;
				render();
			}

			if (System.currentTimeMillis() - lastTimer1 > 1000) {
				lastTimer1 += 1000;
				if(Keyboard.enter){
					System.out.println(ticks + " ticks, " + frames + " fps");
				}
				frames = 0;
				ticks = 0;
			}
		}
	}

	public void stop() {
		if (!running)
			return;

		running = false;

		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void update() {
		keyboard.update();
		mouse.update();
		gsm.update();
	}

	public void render() {
		BufferStrategy bs = getBufferStrategy();

		if (bs == null) {
			createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
		gsm.render(g);

		g.dispose();
		bs.show();
	}

	public static void main(String[] args) {
		new Window(WIDTH, HEIGHT, SCALE, TITLE, new Game());
	}
}
