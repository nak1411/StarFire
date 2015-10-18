package com.nak.starfire.level;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.nak.starfire.Game;
import com.nak.starfire.entity.Asteroid;
import com.nak.starfire.entity.Bullet;
import com.nak.starfire.entity.Entity;
import com.nak.starfire.entity.Player;
import com.nak.starfire.entity.Projectile;
import com.nak.starfire.input.Keyboard;
import com.nak.starfire.tile.Tile;
import com.nak.starfire.utilities.Grid;
import com.nak.starfire.utilities.Time;
import com.nak.starfire.utilities.Utilities;

public class Level {

	private Random rand = new Random();
	private Font font = new Font("Calibri", Font.BOLD, 18);
	private Player player;
	private Grid grid;
	private int mapwidth, mapheight;
	private int[][] tilecache;
	private int xOff, yOff;
	public int dX, dY;
	private int shipVelX = 2;
	private int shipVelY = 2;
	private boolean colliding;

	private List<Entity> entities = new ArrayList<Entity>();
	private List<Projectile> bullets = new ArrayList<Projectile>();
	private List<Asteroid> asteroids = new ArrayList<Asteroid>();
	private List<Rectangle> tiles = new ArrayList<Rectangle>();

	public Level(String path) {
		loadLevel(path);
		player = new Player(this);
		grid = new Grid(mapwidth, mapheight);

		dX = 50;
		dY = 50;
	}

	public void update() {
		collision();
		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).update();
		}
		for (int i = 0; i < bullets.size(); i++) {
			bullets.get(i).update();
		}
		for (int i = 0; i < asteroids.size(); i++) {
			asteroids.get(i).update();
		}

		xOff = ((Game.WIDTH * Game.SCALE) / 2) - dX;
		yOff = ((Game.HEIGHT * Game.SCALE) / 2) - dY;

		if (Time.getTime() / 10000 % 100 == 0) {
			// addAsteroid(new Asteroid(this, SpriteSheet.asteroidOne,
			// (Game.WIDTH * Game.SCALE) / 2 + rand.nextInt(Game.WIDTH),
			// (Game.HEIGHT * Game.SCALE) / 2 - 7));
			Keyboard.toggleOn = false;
		}
		player.update();
		grid.update();
	}

	public void render(Graphics g) {
		for (int y = 0; y < mapheight; y++) {
			for (int x = 0; x < mapwidth; x++) {
				getTile(x, y).render(g, (x * Tile.TILEWIDTH) + xOff, (y * Tile.TILEHEIGHT) + yOff);
			}
		}

		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).render(g);
		}
		for (int i = 0; i < bullets.size(); i++) {
			bullets.get(i).render(g);
		}
		for (int i = 0; i < asteroids.size(); i++) {
			asteroids.get(i).render(g);
		}
		player.render(g);
		grid.render(g, xOff, yOff);

		g.setColor(Color.WHITE);
		g.setFont(font);
		g.drawString("Asteroids On Screen: " + String.valueOf(asteroids.size()), 10, 15);
		
		for (int y = 0; y < mapheight; y++) {
			for (int x = 0; x < mapwidth; x++) {
				if(player.getCenterBounds().intersects(grid.cell(x, y))){
					g.setColor(Color.WHITE);
					g.drawString("Player Coords: (" + grid.getId().get(x) + "," + grid.getId().get(y) + ")", 10, 50);
				}
			}
		}
	}

	public void collision() {
		if ((dX <= 6)) {
			colliding = true;
			dX = 6;
		}
		if ((dX >= ((Tile.TILEWIDTH * mapwidth) - Tile.TILEWIDTH) + 7)) {
			colliding = true;
			dX = (Tile.TILEWIDTH * mapwidth) - (Tile.TILEWIDTH) + 8;
		}
		if ((dY >= ((Tile.TILEHEIGHT * mapheight) - Tile.TILEHEIGHT))) {
			colliding = true;
			dY = (Tile.TILEHEIGHT * mapheight) - Tile.TILEHEIGHT;
		}
		if ((dY <= 0)) {
			colliding = true;
			dY = 0;
		}
		
		if (player.getTopBounds().intersects(grid.cell(6, 6))) {
			colliding = true;
			dY+= 2;
		}
		if (player.getBotBounds().intersects(grid.cell(6, 6))) {
			colliding = true;
			dY-= 2;
		}
		if (player.getLeftBounds().intersects(grid.cell(6, 6))) {
			colliding = true;
			dX+= 2;
		}
		if (player.getRightBounds().intersects(grid.cell(6, 6))) {
			colliding = true;
			dX-= 2;
		}
		if(colliding){
			colliding = false;
		}
	}

	public void input() {
			if (Keyboard.left && !colliding) {
				dX -= shipVelX;
			}
			if (Keyboard.right && !colliding) {
				dX += shipVelX;
			}
			if (Keyboard.up && !colliding) {
				dY -= shipVelY;
			}
			if (Keyboard.down && !colliding) {
				dY += shipVelY;
		}
	}

	public void add(Entity entity) {
		entities.add(entity);
	}

	public void remove(Entity entity) {
		entities.remove(entity);
	}

	public void addBullet(Bullet bullet) {
		bullets.add(bullet);
	}

	public void removeBullet(Bullet bullet) {
		bullets.remove(bullet);
	}

	public void addAsteroid(Asteroid asteroid) {
		asteroids.add(asteroid);
	}

	public void removeAsteroid(Asteroid asteroid) {
		asteroids.remove(asteroid);
	}

	public Tile getTile(int x, int y) {
		Tile tile = Tile.tiles[tilecache[x][y]];
		if (tile == null) {
			return Tile.voidTile;
		}
		return tile;
	}

	public void loadLevel(String path) {
		String file = Utilities.loadFileAsString(path);
		String[] tileIndex = file.split("\\s+");

		mapwidth = Utilities.parseInt(tileIndex[0]);
		mapheight = Utilities.parseInt(tileIndex[1]);

		tilecache = new int[mapwidth][mapheight];
		for (int y = 0; y < mapheight; y++) {
			for (int x = 0; x < mapwidth; x++) {
				tilecache[x][y] = Utilities.parseInt(tileIndex[(x + y * mapwidth) + 2]);
			}
		}
	}

	public int getMapwidth() {
		return mapwidth;
	}

	public void setMapwidth(int mapwidth) {
		this.mapwidth = mapwidth;
	}

	public int getMapheight() {
		return mapheight;
	}

	public void setMapheight(int mapheight) {
		this.mapheight = mapheight;
	}

	public List<Entity> getEntities() {
		return entities;
	}

	public void setEntities(List<Entity> entities) {
		this.entities = entities;
	}

	public List<Projectile> getBullets() {
		return bullets;
	}

	public void setBullets(List<Projectile> bullets) {
		this.bullets = bullets;
	}

	public List<Asteroid> getAsteroids() {
		return asteroids;
	}

	public void setAsteroids(List<Asteroid> asteroids) {
		this.asteroids = asteroids;
	}

	private Rectangle getTileBounds(int x, int y, int width, int height) {
		return new Rectangle(x, y, width, height);
	}
}
