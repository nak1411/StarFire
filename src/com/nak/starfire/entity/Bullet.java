package com.nak.starfire.entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.nak.starfire.Game;
import com.nak.starfire.level.Level;
import com.nak.starfire.tile.Tile;

public class Bullet extends Projectile{

	private BufferedImage image;
	private Level level;

	public Bullet(Level level, BufferedImage image, double x, double y, double dir) {
		super(x, y, dir);
		this.image = image;
		this.level = level;
		velocity = 4;
		nx = velocity * Math.cos(angle);
		ny = velocity * Math.sin(angle);
		this.x = x + (Game.WIDTH * Game.SCALE) / 2;
		this.y = y + (Game.HEIGHT * Game.SCALE) / 2;
		level.add(this);
	}

	public void render(Graphics g) {
	
		if(!isRemoved()){
			g.drawImage(image, (int)x, (int)y, WIDTH, HEIGHT, null);
		}
	}

	public void update() {
		if (x <= (level.getMapwidth() - Tile.TILEWIDTH) + (Game.WIDTH * Game.SCALE / 2) - level.dX
			|| (x >= (Tile.TILEWIDTH * level.getMapwidth() - Tile.TILEWIDTH) + ((Game.WIDTH * Game.SCALE / 2) + 10) - level.dX)
			|| (y >= (Tile.TILEHEIGHT * level.getMapheight() - Tile.TILEHEIGHT) + ((Game.HEIGHT * Game.SCALE / 2) + 8) - level.dY)
			|| (y <= (level.getMapheight() - Tile.TILEHEIGHT) + (Game.HEIGHT * Game.SCALE / 2) - level.dY)) {
			level.remove(this);
		}	
		y += ny;
		x += nx;
	}
}
