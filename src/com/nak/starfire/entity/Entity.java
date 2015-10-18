package com.nak.starfire.entity;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.nak.starfire.level.Level;

public abstract class Entity {

	protected Level level;
	protected double x, y;
	protected int xBound, yBound;
	public static final int WIDTH = 32;
	public static final int HEIGHT = 32;
	private boolean removed = false;
	
	
	public Entity(){
	}

	public abstract void render(Graphics g);

	public abstract void update();

	public void remove() {
		removed = true;
	}

	public boolean isRemoved() {
		return removed;
	}
	
	public void init(Level level){
		this.level = level;
	}
	
	public Rectangle getTopBounds(){
		return new Rectangle(xBound, yBound, 32, 32);
	}
	public Rectangle getBotBounds(){
		return new Rectangle(xBound, yBound, 32, 32);
	}
	public Rectangle getLeftBounds(){
		return new Rectangle(xBound, yBound, 32, 32);
	}
	public Rectangle getRightBounds(){
		return new Rectangle(xBound, yBound, 32, 32);
	}
}
