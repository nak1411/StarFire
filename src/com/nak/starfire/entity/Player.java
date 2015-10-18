package com.nak.starfire.entity;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.nak.starfire.Game;
import com.nak.starfire.gfx.SpriteSheet;
import com.nak.starfire.input.Mouse;
import com.nak.starfire.level.Level;
import com.nak.starfire.utilities.Time;

public class Player extends Mob {

	public static BufferedImage playerimage = SpriteSheet.playerup;
	private Level level ;
	private double dirInDeg;
	private int rateOfFire = 9;
	private long nextBullet = 0;
	private long bulletDelay = Time.SECOND - (100000000 * rateOfFire);
	private Font font = new Font("Calibri", Font.BOLD, 18);

	public Player(Level level) {
		this.level = level;
	}

	public void render(Graphics g) {
		g.drawImage(playerimage, (Game.WIDTH * Game.SCALE) / 2 - 7, (Game.HEIGHT * Game.SCALE) / 2 , WIDTH, HEIGHT, null);
		g.setColor(Color.WHITE);
		g.setFont(font);
		g.drawString("ROF: " + String.valueOf(rateOfFire), 10, 35);
		
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.GREEN);
		g2d.draw(getTopBounds());
		g2d.draw(getBotBounds());
		g2d.draw(getLeftBounds());
		g2d.draw(getRightBounds());
		g2d.draw(getCenterBounds());
//		g.setColor(Color.RED);
//		g.drawLine(0, 0, (Game.WIDTH * Game.SCALE) + 27, Game.HEIGHT * Game.SCALE);
//		g.drawLine((Game.WIDTH * Game.SCALE) - 7, 0, 0, (Game.HEIGHT * Game.SCALE) - 30);
//		g.drawLine(0, ((Game.HEIGHT * Game.SCALE) / 2) - 16, (Game.WIDTH * Game.SCALE) + 27, ((Game.HEIGHT * Game.SCALE) / 2) - 16);
//		g.drawLine(((Game.WIDTH * Game.SCALE) - 7) / 2, 0, ((Game.WIDTH * Game.SCALE) - 7) / 2, (Game.HEIGHT * Game.SCALE));
	}

	public void update() {
		updateFiring();
	}

	private void updateFiring() {
		if (Mouse.left && Time.getTime() > nextBullet) {
			nextBullet = Time.getTime() + bulletDelay;

			double dx = Mouse.mouseVec.getX() - (Game.WIDTH * Game.SCALE) / 2 - 16;
			double dy = Mouse.mouseVec.getY() - (Game.HEIGHT * Game.SCALE) / 2 - 16;
			double dir = Math.atan2(dy, dx);
			dirInDeg = Math.toDegrees(dir);
			
			level.addBullet(new Bullet(level, SpriteSheet.bulletup, x + level.dX, y + level.dY, dir));
			
			if(dirInDeg <= 158.0 && dirInDeg > 113.0){
				playerimage = SpriteSheet.playerlowerleft;
			}
			if(dirInDeg > 158.0 && dirInDeg >= -158.0){
				playerimage = SpriteSheet.playerright;
			}
			if(dirInDeg > -158.0 && dirInDeg <= -113.0){
				playerimage = SpriteSheet.playerupperleft;
			}
			if(dirInDeg > -113.0 && dirInDeg <= -70.0){
				playerimage = SpriteSheet.playerup;
			}
			if(dirInDeg > -70.0 && dirInDeg <= -20.0){
				playerimage = SpriteSheet.playerupperright;
			}
			if(dirInDeg > -20.0 && dirInDeg <= 70.0){
				playerimage = SpriteSheet.playerleft;
			}
			if(dirInDeg > 20.0 && dirInDeg <= 70.0){
				playerimage = SpriteSheet.playerlowerright;
			}
			if(dirInDeg > 70.0 && dirInDeg <= 113.0){
				playerimage = SpriteSheet.playerdown;
			}
		}
	}
	
	public Rectangle getTopBounds(){
		return new Rectangle(((Game.WIDTH * Game.SCALE) / 2), (Game.HEIGHT * Game.SCALE) / 2 , 18, 1);
	}
	public Rectangle getBotBounds(){
		return new Rectangle(((Game.WIDTH * Game.SCALE) / 2), ((Game.HEIGHT * Game.SCALE) / 2) + 30 , 18, 1);
	}
	public Rectangle getLeftBounds(){
		return new Rectangle(((Game.WIDTH * Game.SCALE) / 2) - 4, ((Game.HEIGHT * Game.SCALE) / 2) + 3 , 1, 24);
	}
	public Rectangle getRightBounds(){
		return new Rectangle(((Game.WIDTH * Game.SCALE) / 2) + 21, ((Game.HEIGHT * Game.SCALE) / 2) + 3 , 1, 24);
	}
	public Rectangle getCenterBounds(){
		return new Rectangle(((Game.WIDTH * Game.SCALE) / 2) + 8, ((Game.HEIGHT * Game.SCALE) / 2) + 14, 1, 1);
	}
}
