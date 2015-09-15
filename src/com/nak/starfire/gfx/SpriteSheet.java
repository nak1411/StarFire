package com.nak.starfire.gfx;

import java.awt.image.BufferedImage;

import com.nak.starfire.tile.Tile;

public class SpriteSheet {

	public static BufferedImage spritesheet;
	public static BufferedImage starTile, voidTile;

	public static void init() {
		spritesheet = ImageLoader.loadImage("/spritesheet.png");
		
		starTile = spritesheet.getSubimage(0, 0, Tile.TILEWIDTH, Tile.TILEHEIGHT);
		voidTile = spritesheet.getSubimage(Tile.TILEWIDTH, 0, Tile.TILEWIDTH, Tile.TILEHEIGHT);
	}
}
