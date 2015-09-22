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

	public static int MAPWIDTH = 20, MAPHEIGHT = 20;
	private int[][] tiles;
	public int dX, dY;
	private int shipVelX = 2;
	private int shipVelY = 2;

	private List<Entity> entities = new ArrayList<Entity>();
	private List<Projectile> bullets = new ArrayList<Projectile>();

	public Level(String path) {
		loadLevel(path);
	}

	public void update() {
		if (dX >= 7) {
			if (Keyboard.left) {
				dX -= shipVelX;
			}
		}

		if (dX <= ((Tile.TILEWIDTH * Level.MAPWIDTH) - Tile.TILEWIDTH) + 7) {
			if (Keyboard.right) {
				dX += shipVelX;
			}
		}

		if (dY >= 0) {
			if (Keyboard.up) {
				dY -= shipVelY;
			}
		}

		if (dY <= ((Tile.TILEHEIGHT * Level.MAPHEIGHT) - Tile.TILEHEIGHT)) {
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
				getTile(x, y).render(g, ((x * Tile.TILEWIDTH) + (Game.WIDTH * Game.SCALE) / 2) - dX, ((y * Tile.TILEHEIGHT) + (Game.HEIGHT * Game.SCALE) / 2) - dY);
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
				tiles[x][y] = Utilities.parseInt(tileIndex[(x + y * MAPWIDTH) + 1]);
			}
		}
	}
}
