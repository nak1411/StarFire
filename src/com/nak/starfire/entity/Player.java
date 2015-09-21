package com.nak.starfire.entity;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.nak.starfire.gfx.SpriteSheet;
import com.nak.starfire.input.Mouse;
import com.nak.starfire.level.Level;
import com.nak.starfire.utilities.Time;
import com.nak.starfire.utilities.Utilities;

public class Player extends Mob {

	public static BufferedImage playerimage = SpriteSheet.playerup;
	private Level level ;
	private double dirInDeg;
	private int rateOfFire = 9;
	private long nextBullet = 0;
	private long bulletDelay = Time.SECOND - (100000000 * rateOfFire);;
	private Font font = new Font("Calibri", Font.BOLD, 24);

	public Player(Level level) {
		this.level = level;
	}

	public void render(Graphics g) {
		g.setColor(Color.WHITE);
		g.setFont(font);
		g.drawString("X: " + String.valueOf((level.spawnX + level.dX) - 6), 10, 20);
		g.drawString("Y: " + String.valueOf((level.spawnY + level.dY) + 2), 80, 20);
		g.drawImage(playerimage, Utilities.xCenter - 7, Utilities.yCenter, WIDTH, HEIGHT, null);
		g.setColor(Color.WHITE);
		g.setFont(font);
		g.drawString("ROF: " + String.valueOf(rateOfFire), 10, 50);
		
//		g.setColor(Color.RED);
//		g.drawLine(0, 0, (Game.WIDTH * Game.SCALE) + 27, Game.HEIGHT * Game.SCALE);
//		g.drawLine((Game.WIDTH * Game.SCALE) - 7, 0, 0, (Game.HEIGHT * Game.SCALE) - 30);
//		g.drawLine(0, ((Game.HEIGHT * Game.SCALE) / 2) - 16, (Game.WIDTH * Game.SCALE) + 27, ((Game.HEIGHT * Game.SCALE) / 2) - 16);
//		g.drawLine(((Game.WIDTH * Game.SCALE) - 7) / 2, 0, ((Game.WIDTH * Game.SCALE) - 7) / 2, (Game.HEIGHT * Game.SCALE));
	}

	public void update() {
		// Handle angled sprite animation
//		if (Keyboard.up && Keyboard.left)
//			Player.playerimage = SpriteSheet.playerupperleft;
//		if (Keyboard.up && Keyboard.right)
//			Player.playerimage = SpriteSheet.playerupperright;
//		if (Keyboard.down && Keyboard.left)
//			Player.playerimage = SpriteSheet.playerlowerleft;
//		if (Keyboard.down && Keyboard.right)
//			Player.playerimage = SpriteSheet.playerlowerright;

		updateFiring();
	}

	private void updateFiring() {
		if (Mouse.left && Time.getTime() > nextBullet) {
			nextBullet = Time.getTime() + bulletDelay;

			double dx = Mouse.mouseVec.getX() - Utilities.xCenter - 16;
			double dy = Mouse.mouseVec.getY() - Utilities.yCenter - 16;
			double dir = Math.atan2(dy, dx);
			dirInDeg = Math.toDegrees(dir);
			System.err.println(dirInDeg);

			level.addBullet(new Bullet(level, SpriteSheet.bulletup, x, y, dir));
			
	//Rotate ship sprite based on bullet direction
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
}
