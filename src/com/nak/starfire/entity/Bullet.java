package com.nak.starfire.entity;

import java.awt.Graphics;
import java.awt.Rectangle;
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
		velocity = 8;
		nx = velocity * Math.cos(angle);
		ny = velocity * Math.sin(angle);
		this.x = x + (Game.WIDTH * Game.SCALE) / 2;
		this.y = y + (Game.HEIGHT * Game.SCALE) / 2;
	}

	public void render(Graphics g) {
	
		if(!isRemoved()){
			g.drawImage(image, (int)x - level.dX, (int)y - level.dY, WIDTH, HEIGHT, null);
		}

//		Graphics2D g2d = (Graphics2D) g;
//		g2d.setColor(Color.RED);
//		g2d.draw(getBounds());
	}

	public void update() {
		collision();
		if (x - level.dX <= (level.getMapwidth() - Tile.TILEWIDTH) + (Game.WIDTH * Game.SCALE / 2) - level.dX
			|| (x - level.dX >= (Tile.TILEWIDTH * level.getMapwidth() - Tile.TILEWIDTH) + ((Game.WIDTH * Game.SCALE / 2) + 10) - level.dX)
			|| (y - level.dY >= (Tile.TILEHEIGHT * level.getMapheight() - Tile.TILEHEIGHT) + ((Game.HEIGHT * Game.SCALE / 2) + 8) - level.dY)
			|| (y - level.dY <= (level.getMapheight() - Tile.TILEHEIGHT) + (Game.HEIGHT * Game.SCALE / 2) - level.dY)) {
			level.removePlayerBullet(this);
			level.removeEnemyBullet(this);
		}
		y += ny;
		x += nx;
	}
	
	public void collision() {

	}
	
	public Rectangle getBounds(){
		return new Rectangle(((int)x - level.dX) + 12, ((int)y - level.dY) + 12, 8, 8);
	}
	
//	public void remove() {
//		level.removeBullet(this);
//	}
}
