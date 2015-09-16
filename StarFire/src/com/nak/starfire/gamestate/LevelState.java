package com.nak.starfire.gamestate;

import java.awt.Graphics;

import com.nak.starfire.Game;
import com.nak.starfire.entity.Player;
import com.nak.starfire.input.Keyboard;
import com.nak.starfire.tile.Tile;
import com.nak.starfire.utilities.Utilities;

public class LevelState {

	private Player player;
	private int width, height;
	private int[][] tiles;
	private int dX, dY;
	private int xSpawn = (Game.WIDTH * Game.SCALE / 2) - 12;
	private int ySpawn = (Game.HEIGHT * Game.SCALE / 2) - 24;
	private int xVel = 2;
	private int yVel = 2;

	public LevelState(String path) {
		player = new Player(xSpawn, ySpawn);
		loadLevel(path);
	}

	public void update() {
		if(Keyboard.left) dX -= xVel;
		if(Keyboard.right) dX += xVel;
		if(Keyboard.up) dY -= yVel;
		if(Keyboard.down) dY += yVel;
	}

	public void render(Graphics g) {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				getTile(x, y).render(g, (x * Tile.TILEWIDTH) - dX, (y * Tile.TILEHEIGHT) - dY);
			}
		}
		player.render(g, xSpawn, ySpawn);
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
