package com.nak.starfire.tile;

import com.nak.starfire.gfx.SpriteSheet;

public class VoidTile extends Tile {

	public VoidTile(int id) {
		super(SpriteSheet.voidTile, id);
	}

	@Override
	protected boolean isSolid() {
		return false;
	}
}
