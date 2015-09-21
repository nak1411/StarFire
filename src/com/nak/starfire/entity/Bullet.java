package com.nak.starfire.entity;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.nak.starfire.level.Level;
import com.nak.starfire.tile.Tile;
import com.nak.starfire.utilities.Utilities;

public class Bullet extends Projectile{

	private BufferedImage image;
	private Level level;
	private Font font = new Font("Calibri", Font.BOLD, 24);

	public Bullet(Level level, BufferedImage image, double x, double y, double dir) {
		super(x, y, dir);
		this.image = image;
		this.level = level;
		velocity = 4;
		nx = velocity * Math.cos(angle);
		ny = velocity * Math.sin(angle);
		this.x = Utilities.xCenter + x;
		this.y = y + Utilities.yCenter;
		level.add(this);
	}

	public void render(Graphics g) {
	
		if(!isRemoved()){
			g.drawImage(image, (int)x, (int)y, WIDTH, HEIGHT, null);
		}
	}

	public void update() {
		if (x <= (((Tile.TILEWIDTH * 10) + 10) - level.dX)
			|| (x >= (Tile.TILEWIDTH * Level.MAPWIDTH) + (Tile.TILEWIDTH * 10 - 7) - level.dX)
			|| (y >= (Tile.TILEHEIGHT * Level.MAPHEIGHT) + (Tile.TILEHEIGHT * 9 + 4) - level.dY)
			|| (y <= (((Tile.TILEHEIGHT * 10) - 10) - level.dY))) {
			level.remove(this);
		}	
		y += ny;
		x += nx;
	}
}
