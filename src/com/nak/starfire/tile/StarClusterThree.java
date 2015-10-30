package com.nak.starfire.tile;

import com.nak.starfire.gfx.SpriteSheet;

public class StarClusterThree extends Tile {
	
	public StarClusterThree(int id) {
		super(SpriteSheet.starClusterThree, id, "Star Cluster 3");
	}

	@Override
	public boolean isSolid() {
		return false;
	}
}
