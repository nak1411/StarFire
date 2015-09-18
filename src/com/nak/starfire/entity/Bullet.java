package com.nak.starfire.entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.nak.starfire.gamestate.LevelState;
import com.nak.starfire.gfx.SpriteSheet;

public class Bullet implements Entity {

	private BufferedImage image;
	private int bulletVel = 5;
	private int spawnX, spawnY;
	
	public Bullet(BufferedImage image, int spawnX, int spawnY) {
		this.image = image;
		this.spawnX = spawnX;
		this.spawnY = spawnY;
	}

	public void render(Graphics g) {
		g.drawImage(image, spawnX - LevelState.dX, spawnY - LevelState.dY, Entity.WIDTH, Entity.HEIGHT, null);
	}

	public void update() {
		if(image == SpriteSheet.bulletup) spawnY -= bulletVel;
		if(image == SpriteSheet.bulletdown) spawnY += bulletVel;
		if(image == SpriteSheet.bulletleft) spawnX += bulletVel;
		if(image == SpriteSheet.bulletright) spawnX -= bulletVel;
	}
}
