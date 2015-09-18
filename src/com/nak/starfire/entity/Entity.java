package com.nak.starfire.entity;

import java.awt.Graphics;

public interface Entity {

	public final int WIDTH = 32;
	public final int HEIGHT = 32;

	public abstract void render(Graphics g);

	public abstract void update();
}
