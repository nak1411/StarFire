package com.nak.starfire.entity;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.nak.starfire.gfx.SpriteSheet;
import com.nak.starfire.level.Level;

public class Enemy extends Mob {

	private Level level;
	private Random rand = new Random();
	public static BufferedImage enemyimage = SpriteSheet.enemyup;
	private int timer = 0;
	private int dX, dY;

	public Enemy(Level level, int x, int y) {
		this.x = x;
		this.y = y;
		this.level = level;

	}

	public void render(Graphics g) {
		g.drawImage(enemyimage, (int) (x + level.xOff) - 350, (int) (y + level.yOff) - 200, null);
//
//		Graphics2D g2d = (Graphics2D) g;
//		g2d.setColor(Color.GREEN);
//		g2d.draw(getTopBounds());
//		g2d.draw(getBotBounds());
//		g2d.draw(getLeftBounds());
//		g2d.draw(getRightBounds());
//		g2d.draw(getCenterBounds());
	}

	public void update() {
		timer++;

		if (timer % (rand.nextInt(100) + 50) == 0) {
			dX = rand.nextInt(10);
			dY = rand.nextInt(10);
		}

		if (dX >= 5) {
			x++;
		}
		if (dX < 5)	{
			x--;
		}
		
		if (dY >= 5) {
			y++;
		}
		if (dY < 5)	{
			y--;
		}
	}

	public Rectangle getTopBounds() {
		return new Rectangle((int) (x + level.xOff) - 335, (int) (y + level.yOff) - 200, 1, 1);
	}

	public Rectangle getBotBounds() {
		return new Rectangle((int) (x + level.xOff) - 335, (int) (y + level.yOff) - 170, 1, 1);
	}

	public Rectangle getLeftBounds() {
		return new Rectangle((int) (x + level.xOff) - 346, (int) (y + level.yOff) - 185, 1, 1);
	}

	public Rectangle getRightBounds() {
		return new Rectangle((int) (x + level.xOff) - 324, (int) (y + level.yOff) - 185, 1, 1);
	}

	public Rectangle getCenterBounds() {
		return new Rectangle((int) (x + level.xOff) - 335, (int) (y + level.yOff) - 185, 1, 1);
	}
	

	public int getdX() {
		return dX;
	}

	public void setdX(int dX) {
		this.dX = dX;
	}

	public int getdY() {
		return dY;
	}

	public void setdY(int dY) {
		this.dY = dY;
	}
}
