package com.nak.starfire.entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.nak.starfire.gfx.SpriteSheet;

public class Bullet extends Entity {

	private BufferedImage image;
	private int dX = 5, dY = 5;

	public Bullet(BufferedImage image, int x, int y) {
		super(x, y);
		this.image = image;
	}

	public void render(Graphics g) {
		g.drawImage(image, x, y, WIDTH, HEIGHT, null);
	}

	public void update() {
		if(image == SpriteSheet.bulletup) y-= dY;
		if(image == SpriteSheet.bulletdown) y += dY;
		if(image == SpriteSheet.bulletleft) x += dX;
		if(image == SpriteSheet.bulletright) x -= dX;
	}

}
