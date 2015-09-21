package com.nak.starfire.entity;

import java.awt.Graphics;

public abstract class Entity {

	protected double x, y;
	public static final int WIDTH = 32;
	public static final int HEIGHT = 32;
	private boolean removed = false;
	
	public Entity(){
	}

	public void render(Graphics g) {

	}

	public void update() {

	}

	public void remove() {
		removed = true;
	}

	public boolean isRemoved() {
		return removed;
	}
}
