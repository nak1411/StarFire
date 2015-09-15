package com.nak.starfire.level;

import java.awt.Graphics;

import com.nak.starfire.tile.Tile;
import com.nak.starfire.utilities.Utilities;

public class LevelState {

	private int width, height;
	private int[][] tiles;

	public LevelState(String path) {
		loadLevel(path);
	}

	public void update() {
	}

	public void render(Graphics g) {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				getTile(x, y).render(g, (x * Tile.TILEWIDTH), (y * Tile.TILEHEIGHT));
			}
		}
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
		width = 24;
		height = 22;
		tiles = new int[width][height];
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				tiles[x][y] = Utilities.parseInt(tileIndex[(x + y * width)]);
			}
		}
	}
}
