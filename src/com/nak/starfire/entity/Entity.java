package com.nak.starfire.entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Entity {

	public static BufferedImage image;
	public static final int WIDTH = 32, HEIGHT = 32;
	protected int x, y;

	public Entity(BufferedImage image, int x, int y) {
		Entity.image = image;
		this.x = x;
		this.y = y;
	}

	public void input() {

	}

	public void update() {

	}

	public void render(Graphics g) {
		g.drawImage(image, x, y, WIDTH, HEIGHT, null);
	}
}
