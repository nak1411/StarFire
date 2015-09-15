package com.nak.starfire;

import com.nak.starfire.gfx.SpriteSheet;

public class Assets {

	private SpriteSheet spritesheet;

	public void init() {
		spritesheet = new SpriteSheet();
		SpriteSheet.init();
	}
}
