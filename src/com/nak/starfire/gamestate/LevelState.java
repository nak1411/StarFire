package com.nak.starfire.gamestate;

import java.awt.Graphics;

import com.nak.starfire.entity.EntityHandler;
import com.nak.starfire.entity.Player;
import com.nak.starfire.gfx.SpriteSheet;
import com.nak.starfire.input.Keyboard;
import com.nak.starfire.tile.Tile;
import com.nak.starfire.utilities.Utilities;

public class LevelState {

	private EntityHandler entityHandler;

	public static final int MAPWIDTH = 25, MAPHEIGHT = 23;
	private int[][] tiles;
	private int dX, dY;
	private int spawnX = 0;
	private int spawnY = 0;
	private int xVel = 2;
	private int yVel = 2;

	public LevelState(String path) {
		entityHandler = new EntityHandler();
		entityHandler.addEntity(new Player(spawnX, spawnY, entityHandler));
		loadLevel(path);
	}

	public void update() {
		entityHandler.update();
		if (dX >= 0) {
			if (Keyboard.left) {
				dX -= xVel;
				Player.playerimage = SpriteSheet.playerright;
			}
		}

		if (dX <= (Tile.TILEWIDTH * MAPWIDTH) - Tile.TILEWIDTH) {
			if (Keyboard.right) {
				dX += xVel;
				Player.playerimage = SpriteSheet.playerleft;
			}
		}

		if (dY >= 0) {
			if (Keyboard.up) {
				dY -= yVel;
				Player.playerimage = SpriteSheet.playerup;
			}
		}

		if (dY <= (Tile.TILEHEIGHT * MAPHEIGHT) - Tile.TILEHEIGHT) {
			if (Keyboard.down) {
				dY += yVel;
				Player.playerimage = SpriteSheet.playerdown;
			}
		}

		// Handle angled sprite animation
		if (Keyboard.up && Keyboard.left)
			Player.playerimage = SpriteSheet.playerupperleft;
		if (Keyboard.up && Keyboard.right)
			Player.playerimage = SpriteSheet.playerupperright;
		if (Keyboard.down && Keyboard.left)
			Player.playerimage = SpriteSheet.playerlowerleft;
		if (Keyboard.down && Keyboard.right)
			Player.playerimage = SpriteSheet.playerlowerright;

		// System.out.println("Player Pos|X: " + dX + " Y: " + dY);

	}

	public void render(Graphics g) {
		for (int y = 0; y < MAPHEIGHT; y++) {
			for (int x = 0; x < MAPWIDTH; x++) {
				getTile(x, y).render(g, ((x * Tile.TILEWIDTH) + Utilities.xCenter - spawnX) - dX, ((y * Tile.TILEHEIGHT) + Utilities.yCenter - spawnY) - dY);
			}
		}
		entityHandler.render(g);
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
