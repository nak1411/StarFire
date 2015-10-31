package com.nak.starfire.entity;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.nak.starfire.Game;

public abstract class Mob extends Entity {

	public abstract void render(Graphics g);

	public abstract void update();
}
