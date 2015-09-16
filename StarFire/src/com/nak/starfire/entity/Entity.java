package com.nak.starfire.entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Entity {

	protected BufferedImage image;
	public static final int WIDTH = 32, HEIGHT = 32;
	public static int x, y;

	public Entity(BufferedImage image) {
		this.image = image;
	}

	public void input() {

	}

	public void update() {

	}

	public void render(Graphics g, int x, int y) {
		g.drawImage(image, x, y, WIDTH, HEIGHT, null);
	}
}
