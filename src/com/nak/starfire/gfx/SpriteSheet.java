package com.nak.starfire.gfx;

import java.awt.image.BufferedImage;

import com.nak.starfire.entity.Player;
import com.nak.starfire.tile.Tile;

public class SpriteSheet {

	public static BufferedImage spritesheet, tilesheet;
	public static BufferedImage starTile, voidTile;
	public static BufferedImage playerup, playerdown, playerleft, playerright;
	public static BufferedImage playerupperleft, playerupperright, playerlowerleft, playerlowerright;

	public static void init() {
		
		tilesheet = ImageLoader.loadImage("/tilesheet.png");
		spritesheet = ImageLoader.loadImage("/spritesheet.png");

		// TILE SPRITES
		starTile = tilesheet.getSubimage(0, 0, Tile.TILEWIDTH, Tile.TILEHEIGHT);
		voidTile = tilesheet.getSubimage(Tile.TILEWIDTH, 0, Tile.TILEWIDTH, Tile.TILEHEIGHT);

		// ENTITY SPRITES
		playerup = spritesheet.getSubimage(0, 0, Player.WIDTH, Player.HEIGHT);
		playerleft = spritesheet.getSubimage(Tile.TILEWIDTH, 0, Player.WIDTH, Player.HEIGHT);
		playerdown = spritesheet.getSubimage(Tile.TILEWIDTH * 2, 0, Player.WIDTH, Player.HEIGHT);
		playerright = spritesheet.getSubimage(Tile.TILEWIDTH * 3, 0, Player.WIDTH, Player.HEIGHT);
		playerupperright = spritesheet.getSubimage(0, Tile.TILEHEIGHT, Player.WIDTH, Player.HEIGHT);
		playerupperleft = spritesheet.getSubimage(Tile.TILEWIDTH, Tile.TILEHEIGHT, Player.WIDTH, Player.HEIGHT);
		playerlowerleft = spritesheet.getSubimage(Tile.TILEWIDTH * 2, Tile.TILEHEIGHT, Player.WIDTH, Player.HEIGHT);
		playerlowerright = spritesheet.getSubimage(Tile.TILEWIDTH * 3, Tile.TILEHEIGHT, Player.WIDTH, Player.HEIGHT);
	}
}
