package com.nak.starfire.entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public abstract class Projectile extends Entity {
	
	protected int spawnX, spawnY;
	protected double angle;
	public double nx;
	protected double ny;
	protected BufferedImage image;
	protected double velocity, rateOfFire;
	
	public Projectile(double x, double y, double dir){
		this.x = x;
		this.x = x;
		this.angle = dir;
	}

	public void render(Graphics g) {

	}
	public void update(){
		
	}
}
