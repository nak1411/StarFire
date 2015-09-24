package com.nak.starfire.ui;

import java.awt.Color;
import java.awt.Graphics;

public class LeftBar extends Toolbar{
	
	public LeftBar(int xPos, int yPos, int width, int height, Color color){
		super(xPos, yPos, width, height, color);
	}

	public void render(Graphics g) {
		g.setColor(color);
		g.fillRect(xPos, yPos, width, height);
	}

	public void update() {
		
	}

	public void input() {
		
	}

}
