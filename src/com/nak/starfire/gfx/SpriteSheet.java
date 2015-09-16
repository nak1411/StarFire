package com.nak.starfire.gfx;

import java.awt.image.BufferedImage;

import com.nak.starfire.entity.Entity;
import com.nak.starfire.tile.Tile;

public class SpriteSheet {

	public static BufferedImage spritesheet, tilesheet;
	public static BufferedImage starTile, voidTile;
	public static BufferedImage playerup, playerdown, playerleft, playerright;
	public static BufferedImage playerupperleft, playerupperright, playerlowerleft, playerlowerright;
	public static BufferedImage bulletup, bulletdown, bulletleft, bulletright;
	public static BufferedImage bullletupperleft, bulletupperright, bulletlowerleft, bulletlowerright;

	public static void init() {

		tilesheet = ImageLoader.loadImage("/tilesheet.png");
		spritesheet = ImageLoader.loadImage("/spritesheet.png");

		// TILE SPRITES
		starTile = tilesheet.getSubimage(0, 0, Tile.TILEWIDTH, Tile.TILEHEIGHT);
		voidTile = tilesheet.getSubimage(Tile.TILEWIDTH, 0, Tile.TILEWIDTH, Tile.TILEHEIGHT);

		// ENTITY SPRITES
		playerup = spritesheet.getSubimage(0, 0, Entity.WIDTH, Entity.HEIGHT);
		playerleft = spritesheet.getSubimage(Tile.TILEWIDTH, 0, Entity.WIDTH, Entity.HEIGHT);
		playerdown = spritesheet.getSubimage(Tile.TILEWIDTH * 2, 0, Entity.WIDTH, Entity.HEIGHT);
		playerright = spritesheet.getSubimage(Tile.TILEWIDTH * 3, 0, Entity.WIDTH, Entity.HEIGHT);
		playerupperright = spritesheet.getSubimage(0, Tile.TILEHEIGHT, Entity.WIDTH, Entity.HEIGHT);
		playerupperleft = spritesheet.getSubimage(Tile.TILEWIDTH, Tile.TILEHEIGHT, Entity.WIDTH, Entity.HEIGHT);
		playerlowerleft = spritesheet.getSubimage(Tile.TILEWIDTH * 2, Tile.TILEHEIGHT, Entity.WIDTH, Entity.HEIGHT);
		playerlowerright = spritesheet.getSubimage(Tile.TILEWIDTH * 3, Tile.TILEHEIGHT, Entity.WIDTH, Entity.HEIGHT);
		bulletup = spritesheet.getSubimage(0, Tile.TILEHEIGHT * 2, Entity.WIDTH, Entity.HEIGHT);
		bulletdown = spritesheet.getSubimage(Tile.TILEWIDTH, Tile.TILEHEIGHT * 2, Entity.WIDTH, Entity.HEIGHT);
		bulletright = spritesheet.getSubimage(Tile.TILEWIDTH * 2, Tile.TILEHEIGHT * 2, Entity.WIDTH, Entity.HEIGHT);
		bulletleft = spritesheet.getSubimage(Tile.TILEWIDTH * 3, Tile.TILEHEIGHT * 2, Entity.WIDTH, Entity.HEIGHT);

	}
}
