package com.nak.starfire.editor;

import java.awt.Color;
import java.awt.Graphics;

import com.nak.starfire.Game;

public class LeftBar extends ToolBar {
	
	private Color color = new Color(1.0f, 1.0f, 1.0f, 0.5f);
	
	public void update() {
	}

	
	public void render(Graphics g) {
		
		g.setColor(color);
		g.fillRect(0, 0, 150, Game.HEIGHT * Game.SCALE);
	}

	
	public void input() {
		
	}

}
