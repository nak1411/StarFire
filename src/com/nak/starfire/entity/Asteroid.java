package com.nak.starfire.entity;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.nak.starfire.Game;
import com.nak.starfire.level.Level;
import com.nak.starfire.tile.Tile;

public class Asteroid extends Entity {
	private BufferedImage image;
	private Level level;
	private int x, y;
	
	public Asteroid(Level level, BufferedImage image, int x, int y){
		this.image = image;
		this.level = level;
		this.x = x;
		this.y = y;
	}
	
	public void render(Graphics g){
		if(!isRemoved()){
			g.drawImage(image, x - level.dX, y - level.dY, WIDTH, HEIGHT, null);
		}
//		Graphics2D g2d = (Graphics2D) g;
//		g2d.setColor(Color.YELLOW);
//		g2d.draw(getBounds());
	}
	
	public void update(){
		if (x - level.dX <= (level.getMapwidth() - Tile.TILEWIDTH) + (Game.WIDTH * Game.SCALE / 2) - level.dX
				|| (x - level.dX >= (Tile.TILEWIDTH * level.getMapwidth() - Tile.TILEWIDTH) + ((Game.WIDTH * Game.SCALE / 2) + 10) - level.dX)
				|| (y - level.dY >= (Tile.TILEHEIGHT * level.getMapheight() - Tile.TILEHEIGHT) + ((Game.HEIGHT * Game.SCALE / 2) + 8) - level.dY)
				|| (y - level.dY <= (level.getMapheight() - Tile.TILEHEIGHT) + (Game.HEIGHT * Game.SCALE / 2) - level.dY)) {
				level.removeAsteroid(this);
			}
		y++;
	}
	
	public void input(){
		
	}
	
	public void remove() {
		level.removeAsteroid(this);
	}

	public Rectangle getBounds(){
		return new Rectangle((x - level.dX) + 4, (y - level.dY) + 4, 24, 24);
	}
}
