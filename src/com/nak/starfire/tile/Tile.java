package com.nak.starfire.tile;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Tile {

	protected BufferedImage image;
	public static final int TILEWIDTH = 32, TILEHEIGHT = 32;
	public static Tile[] tiles = new Tile[8];
	protected final int id;
	protected final String name;

	public static Tile voidTile = new VoidTile(0);
	public static Tile starTile = new StarTile(1);

	public Tile(BufferedImage image, int id, String name) {
		this.image = image;
		this.id = id;
		this.name = name;
		tiles[id] = this;
	}

	public void update() {

	}

	protected boolean isSolid() {
		return false;
	}

	public void render(Graphics g, int x, int y) {
		g.drawImage(image, x, y, TILEWIDTH, TILEHEIGHT, null);
	}

	public int getId() {
		return id;
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

	public String getName() {
		return name;
	}
}
