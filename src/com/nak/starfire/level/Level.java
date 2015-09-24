package com.nak.starfire.level;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import com.nak.starfire.Game;
import com.nak.starfire.entity.Bullet;
import com.nak.starfire.entity.Entity;
import com.nak.starfire.entity.Projectile;
import com.nak.starfire.input.Keyboard;
import com.nak.starfire.tile.Tile;
import com.nak.starfire.utilities.Utilities;

public class Level {

	private int mapwidth, mapheight;
	private int[][] tiles;
	private int xOff, yOff;
	public int dX, dY;
	private int shipVelX = 2;
	private int shipVelY = 2;

	private List<Entity> entities = new ArrayList<Entity>();
	private List<Projectile> bullets = new ArrayList<Projectile>();

	public Level(String path) {
		loadLevel(path);
	}

	public void update() {
		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).update();
		}
		
		xOff = ((Game.WIDTH * Game.SCALE) / 2) - dX;
		yOff = ((Game.HEIGHT * Game.SCALE) / 2) - dY;
	}

	public void render(Graphics g) {
		for (int y = 0; y < mapheight; y++) {
			for (int x = 0; x < mapwidth; x++) {
				getTile(x, y).render(g, (x * Tile.TILEWIDTH) + xOff, (y * Tile.TILEHEIGHT) + yOff);
			}
		}

		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).render(g);
		}
	}
	
	public void input(){
		if (dX >= 7) {
			if (Keyboard.left) {
				dX -= shipVelX;
			}
		}

		if (dX <= ((Tile.TILEWIDTH * mapwidth) - Tile.TILEWIDTH) + 7) {
			if (Keyboard.right) {
				dX += shipVelX;
			}
		}

		if (dY >= 0) {
			if (Keyboard.up) {
				dY -= shipVelY;
			}
		}

		if (dY <= ((Tile.TILEHEIGHT * mapheight) - Tile.TILEHEIGHT)) {
			if (Keyboard.down) {
				dY += shipVelY;
			}
		}
	}

	public void add(Entity entity) {
		entities.add(entity);
	}
	
	public void remove(Entity entity) {
		entities.remove(entity);
	}
	
	public void addBullet(Bullet bullet) {
		entities.add(bullet);
	}
	
	public void removeBullet(Bullet bullet){
		bullets.remove(bullet);
	}

	public Tile getTile(int x, int y) {
		Tile tile = Tile.tiles[tiles[x][y]];
		if (tile == null) {
			return Tile.voidTile;
		}
		return tile;
	}

	public void loadLevel(String path) {
		String file = Utilities.loadFileAsString(path);
		String[] tileIndex = file.split("\\s+");
		
		mapwidth = Utilities.parseInt(tileIndex[0]);
		mapheight = Utilities.parseInt(tileIndex[1]);
		
		tiles = new int[mapwidth][mapheight];
		for (int y = 0; y < mapheight; y++) {
			for (int x = 0; x < mapwidth; x++) {
				tiles[x][y] = Utilities.parseInt(tileIndex[(x + y * mapwidth) + 2]);
			}
		}
	}

	public int getMapwidth() {
		return mapwidth;
	}

	public void setMapwidth(int mapwidth) {
		this.mapwidth = mapwidth;
	}

	public int getMapheight() {
		return mapheight;
	}

	public void setMapheight(int mapheight) {
		this.mapheight = mapheight;
	}
}
