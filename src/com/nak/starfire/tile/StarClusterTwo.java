package com.nak.starfire.tile;

import com.nak.starfire.gfx.SpriteSheet;

public class StarClusterTwo extends Tile {
	
	public StarClusterTwo(int id) {
		super(SpriteSheet.starClusterTwo, id, "Star Cluster 2");
	}

	@Override
	public boolean isSolid() {
		return false;
	}
}
