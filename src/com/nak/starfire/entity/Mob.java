package com.nak.starfire.entity;

import java.awt.Graphics;

public abstract class Mob extends Entity {

	public abstract void render(Graphics g);

	public abstract void update();
}
