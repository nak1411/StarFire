package com.nak.starfire.entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.nak.starfire.gfx.SpriteSheet;
import com.nak.starfire.input.Keyboard;
import com.nak.starfire.utilities.Time;
import com.nak.starfire.utilities.Utilities;

public class Player extends Entity {

	public static BufferedImage playerimage;
	private EntityHandler handler;
	private long nextBullet = 0;
	private int rateOfFire = 8;
	private long bulletDelay = Time.SECOND - (100000000  * rateOfFire);

	public Player(int x, int y, EntityHandler handler) {
		super(Utilities.xCenter, Utilities.yCenter);
		this.handler = handler;
		playerimage = SpriteSheet.playerup;
	}

	public void render(Graphics g) {
		g.drawImage(playerimage, x, y, WIDTH, HEIGHT, null);
	}

	public void update() {

		if (Keyboard.space && Time.getTime() > nextBullet) {
			nextBullet = Time.getTime() + bulletDelay;
			
			if (playerimage == SpriteSheet.playerup) {
				handler.addBullet(new Bullet(SpriteSheet.bulletup, x, (y - 20)));
			}
			if (playerimage == SpriteSheet.playerdown) {
				handler.addBullet(new Bullet(SpriteSheet.bulletdown, x, y + 20));
			}

			if (playerimage == SpriteSheet.playerleft) {
				handler.addBullet(new Bullet(SpriteSheet.bulletleft, x + 20, y));
			}

			if (playerimage == SpriteSheet.playerright) {
				handler.addBullet(new Bullet(SpriteSheet.bulletright, x - 20, y));
			}
		}
	}
}
