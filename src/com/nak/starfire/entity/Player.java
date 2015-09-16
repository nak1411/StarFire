package com.nak.starfire.entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.nak.starfire.gfx.SpriteSheet;
import com.nak.starfire.input.Keyboard;
import com.nak.starfire.utilities.Utilities;

public class Player extends Entity {

	public static BufferedImage playerimage;
	private EntityHandler handler;

	public Player(int x, int y, EntityHandler handler) {
		super(Utilities.xCenter, Utilities.yCenter);
		this.handler = handler;
		playerimage = SpriteSheet.playerup;
	}

	public void render(Graphics g) {
		g.drawImage(playerimage, x, y, WIDTH, HEIGHT, null);
	}

	public void update() {

		if (Keyboard.space) {
			if (playerimage == SpriteSheet.playerup) {
				handler.addEntity(new Bullet(SpriteSheet.bulletup, x, (y - 20)));

			}
			if (playerimage == SpriteSheet.playerdown) {
				handler.addEntity(new Bullet(SpriteSheet.bulletdown, x, y + 20));

			}

			if (playerimage == SpriteSheet.playerleft) {
				handler.addEntity(new Bullet(SpriteSheet.bulletleft, x + 20, y));
			}

			if (playerimage == SpriteSheet.playerright) {
				handler.addEntity(new Bullet(SpriteSheet.bulletright, x - 20, y));
			}
		}
	}
}
