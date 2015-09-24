package com.nak.starfire.ui;

import java.awt.Color;
import java.awt.Graphics;

public abstract class Toolbar {

	protected int xPos, yPos;
	protected int width, height;
	protected Color color = new Color(0, 0, 0, 0);

	public Toolbar(int xPos, int yPos, int width, int height, Color color) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.height = height;
		this.width = width;
		this.color = color;

	}

	public abstract void render(Graphics g);

	public abstract void update();

	public abstract void input();

}
