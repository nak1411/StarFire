package com.nak.starfire.tile;

import com.nak.starfire.gfx.SpriteSheet;

public class Wall extends Tile {
	
	public Wall(int id) {
		super(SpriteSheet.wall, id, "Wall");
	}

	@Override
	public boolean isSolid() {
		return true;
	}
}
