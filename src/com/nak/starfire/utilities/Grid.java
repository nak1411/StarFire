package com.nak.starfire.utilities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import com.nak.starfire.tile.Tile;

public class Grid {

	private int mapwidth;
	private int xOff, yOff;
	private List<Rectangle> grid = new ArrayList<Rectangle>();
	private List<Integer> id = new ArrayList<Integer>();

	public Grid(int mapwidth, int mapheight) {
		this.mapwidth = mapwidth;

		for (int y = 0; y < mapheight; y++) {
			for (int x = 0; x < mapwidth; x++) {
				grid.add(cell(x, y));
				id.add(x + y * mapwidth, x + y * mapwidth);
			}
		}
	}

	public void render(Graphics g, int xOff, int yOff) {
		this.xOff = xOff;
		this.yOff = yOff;
	}

	public void update() {

	}

	public Rectangle cell(int x, int y) {
		return new Rectangle((x * Tile.TILEWIDTH) + xOff, (y * Tile.TILEHEIGHT) + yOff, Tile.TILEWIDTH, Tile.TILEHEIGHT);
	}

	public String getCoord(int x, int y) {
		try {
			return (String.valueOf(grid.get(x).x) + "," + String.valueOf(grid.get(x + y * mapwidth).y));
		} catch (Exception e) {
		}
		return "Your coordinate exceeds the size of the map.";
	}

	public List<Integer> getId() {
		return id;
	}

	public void setId(List<Integer> id) {
		this.id = id;
	}
}
