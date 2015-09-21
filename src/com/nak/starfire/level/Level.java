package com.nak.starfire.level;

import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import com.nak.starfire.entity.Bullet;
import com.nak.starfire.entity.Entity;
import com.nak.starfire.entity.Projectile;
import com.nak.starfire.input.Keyboard;
import com.nak.starfire.tile.Tile;
import com.nak.starfire.utilities.Utilities;

public class Level {

	public static final int MAPWIDTH = 25, MAPHEIGHT = 23;
	private int[][] tiles;
	public int dX, dY;
	private int shipVelX = 2;
	private int shipVelY = 2;
	public int spawnX = 100;
	public int spawnY = 100;
	private Font font = new Font("Calibri", Font.BOLD, 24);

	private List<Entity> entities = new ArrayList<Entity>();
	private List<Projectile> bullets = new ArrayList<Projectile>();

	public Level(String path) {
		loadLevel(path);
	}

	public void update() {
		if (dX >= 0 - spawnX + 7) {
			if (Keyboard.left) {
				dX -= shipVelX;
			}
		}

		if (dX <= ((Tile.TILEWIDTH * Level.MAPWIDTH) - Tile.TILEWIDTH) - spawnX + 7) {
			if (Keyboard.right) {
				dX += shipVelX;
			}
		}

		if (dY >= 0 - spawnY) {
			if (Keyboard.up) {
				dY -= shipVelY;
			}
		}

		if (dY <= ((Tile.TILEHEIGHT * Level.MAPHEIGHT) - Tile.TILEHEIGHT) - spawnY) {
			if (Keyboard.down) {
				dY += shipVelY;
			}
		}
		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).update();
		}
	}

	public void render(Graphics g) {
		for (int y = 0; y < MAPHEIGHT; y++) {
			for (int x = 0; x < MAPWIDTH; x++) {
				getTile(x, y).render(g, ((x * Tile.TILEWIDTH) + Utilities.xCenter - spawnX) - dX, ((y * Tile.TILEHEIGHT) + Utilities.yCenter - spawnY) - dY);
			}
		}

		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).render(g);
		}
	}

	public void add(Entity e) {
		entities.add(e);
	}
	
	public void remove(Entity e) {
		entities.remove(e);
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
		tiles = new int[MAPWIDTH][MAPHEIGHT];
		for (int y = 0; y < MAPHEIGHT; y++) {
			for (int x = 0; x < MAPWIDTH; x++) {
				tiles[x][y] = Utilities.parseInt(tileIndex[(x + y * MAPWIDTH)]);
			}
		}
	}
}
