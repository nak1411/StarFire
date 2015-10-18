package com.nak.starfire.utilities;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import com.nak.starfire.tile.Tile;

public class Grid {

	private int mapwidth, mapheight;
	private int xOff, yOff;
	private Font font = new Font("Calibri", 0, 12);
	private List<Rectangle> grid = new ArrayList<Rectangle>();
	private List<Integer> id = new ArrayList<Integer>();

	public Grid(int mapwidth, int mapheight) {
		this.mapwidth = mapwidth;
		this.mapheight = mapheight;

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
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.WHITE);

		for (int y = 0; y < mapheight; y++) {
			for (int x = 0; x < mapwidth; x++) {
				g2d.draw(cell(x, y));
				
				g2d.setFont(font);
				g2d.setColor(Color.YELLOW);
				g2d.drawString(String.valueOf(id.get(x)) + "," + String.valueOf(id.get(y)), (x * Tile.TILEWIDTH + 2) + xOff, (y * Tile.TILEHEIGHT + 10) + yOff);
				g2d.setColor(Color.WHITE);
			}
		}
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
