package com.nak.starfire.entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.nak.starfire.gamestate.LevelState;
import com.nak.starfire.gfx.SpriteSheet;
import com.nak.starfire.input.Mouse;
import com.nak.starfire.utilities.Time;
import com.nak.starfire.utilities.Vector2i;

public class Player implements Entity {

	public static BufferedImage playerimage;
	private EntityHandler handler;
	public static Vector2i playerVec;
	private int x;
	private int y;
	private int dx;
	private int dy;

	private long nextBullet = 0;
	private int rateOfFire = 8;
	private long bulletDelay = Time.SECOND - (100000000 * rateOfFire);

	public Player(int x, int y, EntityHandler handler) {
		this.handler = handler;
		this.x = x;
		this.y = y;
		playerVec = new Vector2i();
	}

	public void render(Graphics g) {
		g.drawImage(playerimage, x, y, Entity.WIDTH, Entity.HEIGHT, null);
	}

	public void update() {
		dx = LevelState.dX;
		dy = LevelState.dY;

		if (Mouse.left && Time.getTime() > nextBullet) {
			nextBullet = Time.getTime() + bulletDelay;

			if (playerimage == SpriteSheet.playerup) {
				handler.addEntity(new Bullet(SpriteSheet.bulletup, (int) (x + dx), (y + dy) - 20, handler));
			}

			if (playerimage == SpriteSheet.playerdown) {
				handler.addEntity(new Bullet(SpriteSheet.bulletdown, x + dx, (y + dy) + 20, handler));
			}

			if (playerimage == SpriteSheet.playerleft) {
				handler.addEntity(new Bullet(SpriteSheet.bulletleft, (x + dx) + 20, y + dy, handler));
			}

			if (playerimage == SpriteSheet.playerright) {
				handler.addEntity(new Bullet(SpriteSheet.bulletright, (x + dx) - 20, y + dy, handler));
			}
		}
	}
}
