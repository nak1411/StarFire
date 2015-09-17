package com.nak.starfire.entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.nak.starfire.gamestate.LevelState;
import com.nak.starfire.gfx.SpriteSheet;
import com.nak.starfire.utilities.Utilities;

public class Bullet {

	private BufferedImage image;
	private int dX = 5, dY = 5;
	private int x, y;


	public Bullet(BufferedImage image, int x, int y) {
		this.image = image;
		this.x = x;
		this.y = y;
	}

	public void render(Graphics g) {
		g.drawImage(image, x, y, Entity.WIDTH, Entity.HEIGHT, null);
	}

	public void update() {
		if(image == SpriteSheet.bulletup) y-= dY;
		if(image == SpriteSheet.bulletdown) y += dY;
		if(image == SpriteSheet.bulletleft) x += dX;
		if(image == SpriteSheet.bulletright) x -= dX;
	}

}
