package com.nak.starfire.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.nak.starfire.Game;
import com.nak.starfire.gfx.SpriteSheet;
import com.nak.starfire.level.Level;
import com.nak.starfire.utilities.Time;

public class Enemy extends Mob {

	private Level level;
	private Random rand = new Random();
	public static BufferedImage enemyimage = SpriteSheet.enemyup;
	private int timer = 0;
	private int dX, dY;
	private int aggroRange = 150;
	private boolean aggroAlert;
	private double roamSpeed = 0.5;
	private double chaseSpeed = 1.0;
	private double rateOfFire = 7.0;
	private long nextBullet = 0;
	private long bulletDelay = (long) (Time.SECOND - (100000000 * rateOfFire));

	public Enemy(Level level, double x, double y) {
		this.x = x;
		this.y = y;
		this.level = level;

	}

	public void render(Graphics g) {
		if(!isRemoved()){
			g.drawImage(enemyimage, (int) (x + level.xOff) - 350, (int) (y + level.yOff) - 200, null);
		}

		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.GREEN);
		g2d.draw(getBounds());
//		g2d.draw(getBotBounds());
//		g2d.draw(getLeftBounds());
//		g2d.draw(getRightBounds());
//		g2d.draw(getCenterBounds());
//		g2d.draw(getLongAggroBounds());
//		g2d.setColor(Color.YELLOW);
//		g2d.draw(getShortAggroBounds());
		g2d.setColor(Color.RED);
		
		if(aggroAlert){
			g2d.drawString("AGGRO!", (Game.WIDTH * Game.SCALE) - 60, 15);
		}
	}
	
	private boolean longProximity(){
		if((x + level.xOff) - 350 < ((Game.WIDTH * Game.SCALE) / 2 - 7) - aggroRange
			|| (x + level.xOff) - 350 > ((Game.WIDTH * Game.SCALE) / 2 - 7) + aggroRange
			|| (y + level.yOff) - 200 < (Game.HEIGHT * Game.SCALE) / 2 - aggroRange
			|| (y + level.yOff) - 200 > (Game.HEIGHT * Game.SCALE) / 2 + aggroRange){
			
			return true;
		}
		return false;
	}
	
	private boolean shortProximity(){
		if((x + level.xOff) - 440 < ((Game.WIDTH * Game.SCALE) / 2 - 7) - aggroRange
			|| (x + level.xOff) - 260 > ((Game.WIDTH * Game.SCALE) / 2 - 7) + aggroRange
			|| (y + level.yOff) - 260 < (Game.HEIGHT * Game.SCALE) / 2 - aggroRange + 32
			|| (y + level.yOff) - 80 > (Game.HEIGHT * Game.SCALE) / 2 + aggroRange + 32){
			return true;
		}
		return false;
	}

	public void update() {
		updateFiring();
		
		if(shortProximity()){
			if(!longProximity()){
				if((x + level.xOff) - 350 < (Game.WIDTH * Game.SCALE) / 2 - 7){
					x += chaseSpeed;
				}
				if((x + level.xOff) - 350 > (Game.WIDTH * Game.SCALE) / 2 - 7){
					x -= chaseSpeed;
				}
				if((y + level.yOff) - 200 < (Game.HEIGHT * Game.SCALE) / 2){
					y += chaseSpeed;
				}
				if((y + level.yOff) - 200 > (Game.HEIGHT * Game.SCALE) / 2){
					y -= chaseSpeed;
				}
				aggroAlert = true;

			}else{
				
				timer++;
				aggroAlert = false;
				
				if (timer % (rand.nextInt(100) + 50) == 0) {
					dX = rand.nextInt(10);
					dY = rand.nextInt(10);
				}

				if (dX >= 5) {
					x += roamSpeed;
				}
				if (dX < 5)	{
					x -= roamSpeed;
				}
			
				if (dY >= 5) {
					y += roamSpeed;
				}
				if (dY < 5)	{
					y -= roamSpeed;
				}
			}
		}
	}
	
	private void updateFiring() {
		if (!longProximity() && Time.getTime() > nextBullet) {
			nextBullet = Time.getTime() + bulletDelay;

			double dx = ((x + level.xOff) - 355) - ((Game.WIDTH * Game.SCALE) / 2 - 7);
			double dy = ((y + level.yOff) - 210) - (Game.HEIGHT * Game.SCALE) / 2;
			double dir = Math.atan2(-dy, -dx);
			
			level.addEnemyBullet(new Bullet(level, SpriteSheet.bulletup, (x - (Game.WIDTH * Game.SCALE) / 2) - 140, (y - (Game.HEIGHT * Game.SCALE) / 2), dir));
		}
	}

	public Rectangle getTopBounds() {
		return new Rectangle((int) (x + level.xOff) - 335, (int) (y + level.yOff) - 200, 1, 1);
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int) (x + level.xOff) - 345, (int) (y + level.yOff) - 200, 22, 28);
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
	
	public Rectangle getLongAggroBounds() {
		return new Rectangle((int) (x + level.xOff) - (334 + aggroRange), (int) (y + level.yOff) - (184 + aggroRange), (aggroRange * 2), (aggroRange * 2));
	}
	
	public Rectangle getShortAggroBounds() {
		return new Rectangle((int) (x + level.xOff) - (244 + aggroRange), (int) (y + level.yOff) - (94 + aggroRange), (aggroRange - 32), (aggroRange - 32));
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
