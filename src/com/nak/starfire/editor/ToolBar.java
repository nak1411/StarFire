package com.nak.starfire.editor;

import java.awt.Graphics;

public abstract class ToolBar {
	
	public abstract void update();
		
	public abstract void render(Graphics g);
	
	public abstract void input();

}
