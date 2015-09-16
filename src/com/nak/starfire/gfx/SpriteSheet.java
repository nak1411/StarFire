package com.nak.starfire.gfx;

import java.awt.image.BufferedImage;

import com.nak.starfire.entity.Player;
import com.nak.starfire.tile.Tile;

public class SpriteSheet {

	public static BufferedImage spritesheet, tilesheet;
	public static BufferedImage starTile, voidTile;
	public static BufferedImage player;

	public static void init() {
		
		tilesheet = ImageLoader.loadImage("/tilesheet.png");
		spritesheet = ImageLoader.loadImage("/spritesheet.png");

		// TILE SPRITES
		starTile = tilesheet.getSubimage(0, 0, Tile.TILEWIDTH, Tile.TILEHEIGHT);
		voidTile = tilesheet.getSubimage(Tile.TILEWIDTH, 0, Tile.TILEWIDTH, Tile.TILEHEIGHT);

		// ENTITY SPRITES
		player = spritesheet.getSubimage(0, 0, Player.WIDTH, Player.HEIGHT);
	}
}
