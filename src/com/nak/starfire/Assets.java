package com.nak.starfire;

import com.nak.starfire.gfx.SpriteSheet;

public class Assets {

	private SpriteSheet spritesheet;
	private int spriteSize = 32;

	public void init() {
		spritesheet = new SpriteSheet(spriteSize, spriteSize);
		spritesheet.init();
	}
}
