package com.nak.starfire.gamestate;

import java.awt.Graphics;

import com.nak.starfire.entity.Entity;
import com.nak.starfire.entity.Player;
import com.nak.starfire.gfx.SpriteSheet;
import com.nak.starfire.input.Keyboard;
import com.nak.starfire.tile.Tile;
import com.nak.starfire.utilities.Utilities;

public class LevelState {

	private Entity entity;
	public static final int MAPWIDTH = 25, MAPHEIGHT = 23;
	private int[][] tiles;
	private int dX, dY;
	private int spawnX = 0;
	private int spawnY = 0;
	private int xVel = 2;
	private int yVel = 2;

	public LevelState(String path) {
		entity = new Player(spawnX, spawnY);
		loadLevel(path);
	}

	public void update() {
		if(dX >= 0){
			if(Keyboard.left){
				dX -= xVel;
				Entity.image = SpriteSheet.playerright;
			}
		}
		
		if(dX <= (Tile.TILEWIDTH * MAPWIDTH) - Tile.TILEWIDTH){
			if(Keyboard.right){
				dX += xVel;
				Entity.image = SpriteSheet.playerleft;
			}
		}
		
		if(dY >= 0){
			if(Keyboard.up){
				dY -= yVel;
				Entity.image = SpriteSheet.playerup;
			}
		}
		
		if(dY <= (Tile.TILEHEIGHT * MAPHEIGHT) - Tile.TILEHEIGHT){
			if(Keyboard.down){
				dY += yVel;
				Entity.image = SpriteSheet.playerdown;
			}
		}
		
		//Handle angled sprite animation
		if(Keyboard.up && Keyboard.left) Entity.image = SpriteSheet.playerupperleft;
		if(Keyboard.up && Keyboard.right) Entity.image = SpriteSheet.playerupperright;
		if(Keyboard.down && Keyboard.left) Entity.image = SpriteSheet.playerlowerleft;
		if(Keyboard.down && Keyboard.right) Entity.image = SpriteSheet.playerlowerright;

		//System.out.println("Player Pos|X: " + dX + " Y: " + dY);

	}

	public void render(Graphics g) {
		for (int y = 0; y < MAPHEIGHT; y++) {
			for (int x = 0; x < MAPWIDTH; x++) {
				getTile(x, y).render(g, ((x * Tile.TILEWIDTH) + Utilities.xCenter  - spawnX) - dX, ((y * Tile.TILEHEIGHT) + Utilities.yCenter  - spawnY) - dY);
			}
		}
		entity.render(g);
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
