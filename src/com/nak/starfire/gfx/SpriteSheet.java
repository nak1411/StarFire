package com.nak.starfire.gfx;

import java.awt.image.BufferedImage;

public class SpriteSheet {

	private static int WIDTH, HEIGHT;

	public static BufferedImage spritesheet, tilesheet;
	public static BufferedImage starClusterOne, starClusterTwo, starClusterThree, voidTile;
	public static BufferedImage playerup, playerdown, playerleft, playerright;
	public static BufferedImage playerupperleft, playerupperright, playerlowerleft, playerlowerright;
	public static BufferedImage bulletup, bulletdown, bulletleft, bulletright;
	public static BufferedImage bulletupperleft, bulletupperright, bulletlowerleft, bulletlowerright;
	public static BufferedImage asteroidOne;

	public SpriteSheet(int width, int height) {
		SpriteSheet.WIDTH = width;
		SpriteSheet.HEIGHT = height;
	}

	public void init() {

		tilesheet = ImageLoader.loadImage("/tilesheet.png");
		spritesheet = ImageLoader.loadImage("/spritesheet.png");

		// TILE SPRITES
		starClusterOne = tilesheet.getSubimage(WIDTH, 0, WIDTH, HEIGHT);
		starClusterTwo = tilesheet.getSubimage(WIDTH * 2, 0, WIDTH, HEIGHT);
		starClusterThree = tilesheet.getSubimage(WIDTH * 3, 0, WIDTH, HEIGHT);
		voidTile = tilesheet.getSubimage(0, 0, WIDTH, HEIGHT);
		asteroidOne = tilesheet.getSubimage(0, HEIGHT, WIDTH, HEIGHT);

		// ENTITY SPRITES
		playerup = spritesheet.getSubimage(0, 0, WIDTH, HEIGHT);
		playerleft = spritesheet.getSubimage(WIDTH, 0, WIDTH, HEIGHT);
		playerdown = spritesheet.getSubimage(WIDTH * 2, 0, WIDTH, HEIGHT);
		playerright = spritesheet.getSubimage(WIDTH * 3, 0, WIDTH, HEIGHT);
		playerupperright = spritesheet.getSubimage(0, HEIGHT, WIDTH, HEIGHT);
		playerupperleft = spritesheet.getSubimage(WIDTH, HEIGHT, WIDTH, HEIGHT);
		playerlowerleft = spritesheet.getSubimage(WIDTH * 2, HEIGHT, WIDTH, HEIGHT);
		playerlowerright = spritesheet.getSubimage(WIDTH * 3, HEIGHT, WIDTH, HEIGHT);
		bulletup = spritesheet.getSubimage(0, HEIGHT * 2, WIDTH, HEIGHT);
		bulletdown = spritesheet.getSubimage(HEIGHT, HEIGHT * 2, WIDTH, HEIGHT);
		bulletright = spritesheet.getSubimage(HEIGHT * 2, HEIGHT * 2, WIDTH, HEIGHT);
		bulletleft = spritesheet.getSubimage(WIDTH * 3, HEIGHT* 2, WIDTH, HEIGHT);

	}
}
