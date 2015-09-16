package com.nak.starfire.entity;

import com.nak.starfire.gfx.SpriteSheet;
import com.nak.starfire.utilities.Utilities;

public class Player extends Entity {

	public Player(int x, int y) {
		super(SpriteSheet.playerup, Utilities.xCenter, Utilities.yCenter);
	}
}
