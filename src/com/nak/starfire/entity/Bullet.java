package com.nak.starfire.entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.nak.starfire.gamestate.LevelState;
import com.nak.starfire.gfx.SpriteSheet;
import com.nak.starfire.input.Mouse;
import com.nak.starfire.tile.Tile;
import com.nak.starfire.utilities.Utilities;
import com.nak.starfire.utilities.Vector2i;

public class Bullet implements Entity {

	private BufferedImage image;
	private EntityHandler handler;
	public static Vector2i bulletVec;
	private double length;
	public static double angle;
	private int bulletVel = 5;
	private int spawnX, spawnY;
	private int vx, vy;
	
	public Bullet(BufferedImage image, int spawnX, int spawnY, EntityHandler handler) {
		this.image = image;
		this.spawnX = spawnX;
		this.spawnY = spawnY;
		this.handler = handler;
		bulletVec = new Vector2i(Mouse.mouseVec.getX() - spawnX, Mouse.mouseVec.getY() - spawnY);
		angle = Math.atan2(bulletVec.getX(), bulletVec.getY());
	}

	public void render(Graphics g) {
		
			g.drawImage(image, (int) spawnX - LevelState.dX, spawnY - LevelState.dY, Entity.WIDTH, Entity.HEIGHT, null);
	}

	public void update() {

		System.out.println(angle);
		
		if (spawnX <= (Tile.TILEWIDTH + Utilities.xCenter - spawnX) - (Tile.TILEWIDTH * 2) - 8
			|| (spawnX >= Tile.TILEWIDTH * LevelState.MAPWIDTH + (Tile.TILEWIDTH * 2) - 3)
			|| (spawnY >= Tile.TILEHEIGHT * LevelState.MAPHEIGHT + (Tile.TILEHEIGHT) + 12)
			|| (spawnY <= (Tile.TILEHEIGHT + Utilities.yCenter - spawnY) - (Tile.TILEHEIGHT * 2) - 24)) {
			
				handler.removeEntity(this);
		}
			
		if(image == SpriteSheet.bulletup) spawnY -= bulletVel;
		if(image == SpriteSheet.bulletdown) spawnY += bulletVel;
		if(image == SpriteSheet.bulletleft) spawnX += bulletVel;
		if(image == SpriteSheet.bulletright) spawnX -= bulletVel;
	}
}
