package com.nak.starfire.entity;

import com.nak.starfire.gfx.SpriteSheet;

public class Player extends Entity {

	protected int xPos, yPos;

	public Player(int xPos, int yPos) {
		super(SpriteSheet.player);
		this.xPos = xPos;
		this.yPos = yPos;
	}
}
