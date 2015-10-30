package com.nak.starfire.tile;

import com.nak.starfire.gfx.SpriteSheet;

public class StarClusterOne extends Tile {
	
	public StarClusterOne(int id) {
		super(SpriteSheet.starClusterOne, id, "Star Cluster 1");
	}

	@Override
	public boolean isSolid() {
		return false;
	}
}
