package com.nak.starfire.entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.nak.starfire.level.Level;

public class Asteroid extends Entity{
	protected int x, y;
	private BufferedImage image;
	private Level level;
	
	public Asteroid(Level level, BufferedImage image, int x, int y){
		this.x = x;
		this.y = y;
		this.image = image;
		this.level = level;
		level.add(this);
	}
	
	public void render(Graphics g){
		
		if(!isRemoved()){
			g.drawImage(image, (int)x - level.dX, (int)y - level.dY, WIDTH, HEIGHT, null);
		}
	}
	
	public void update(){
		y++;
	}

}
