package com.nak.starfire.level;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.nak.starfire.Game;
import com.nak.starfire.entity.Bullet;
import com.nak.starfire.entity.Enemy;
import com.nak.starfire.entity.Entity;
import com.nak.starfire.entity.Player;
import com.nak.starfire.input.Keyboard;
import com.nak.starfire.tile.Tile;
import com.nak.starfire.utilities.Grid;
import com.nak.starfire.utilities.Utilities;

public class Level {

	private Grid grid;
	private Random rand = new Random();
	private int mapwidth, mapheight;
	private int[][] tilecache;
	public double xOff, yOff;
	public int dX, dY;
	private int shipVelX = 2;
	private int shipVelY = 2;
	private boolean colliding;

	private List<Entity> entities = new ArrayList<Entity>();
	private List<Enemy> enemies = new ArrayList<Enemy>();
	private List<Player> players = new ArrayList<Player>();
	private List<Bullet> enemyBullets = new ArrayList<Bullet>();
	private List<Bullet> playerBullets = new ArrayList<Bullet>();
	

	public Level(String path) {
		loadLevel(path);
		grid = new Grid(mapwidth, mapheight);
		players.add(new Player(this, grid));
		
		for (int i = 0; i < 1; i++) {
			enemies.add(new Enemy(this, 400, 300)); 
		}			
	}

	public void update() {
		
		System.out.println();
		collision();
		for (int y = 0; y < mapheight; y++) {
			for (int x = 0; x < mapwidth; x++) {
				Tile tile = Tile.tiles[tilecache[x][y]];
				if(tile.isSolid()){
					tileCollision(x, y);
				}
			}
		}
		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).update();
		}
		for (int i = 0; i < enemyBullets.size(); i++) {
			enemyBullets.get(i).update();
		}
		for (int i = 0; i < playerBullets.size(); i++) {
			playerBullets.get(i).update();
		}
		for (int i = 0; i < players.size(); i++) {
			players.get(i).update();
		}
		for (int i = 0; i < enemies.size(); i++) {
			enemies.get(i).update();
		}

		xOff = ((Game.WIDTH * Game.SCALE) / 2) - dX;
		yOff = ((Game.HEIGHT * Game.SCALE) / 2) - dY;

		grid.update();
		
		for (int i = 0; i < playerBullets.size(); i++) {
			for (int j = 0; j < enemies.size(); j++) {
					if(playerBullets.get(i).getBounds().intersects(enemies.get(j).getBounds())){
						enemies.remove(j);
				}
			}
		}
		
		if(enemies.size() == 0){
			enemies.add(new Enemy(this, 400, 300));
					
		}
	}

	public void render(Graphics g) {
		for (int y = 0; y < mapheight; y++) {
			for (int x = 0; x < mapwidth; x++) {
				getTile(x, y).render(g, (x * Tile.TILEWIDTH) + (int)xOff, (y * Tile.TILEHEIGHT) + (int)yOff);
			}
		}

		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).render(g);
		}
		for (int i = 0; i < enemyBullets.size(); i++) {
			enemyBullets.get(i).render(g);
		}
		for (int i = 0; i < playerBullets.size(); i++) {
			playerBullets.get(i).render(g);
		}
		for (int i = 0; i < players.size(); i++) {
			players.get(i).render(g);
		}
		for (int i = 0; i < enemies.size(); i++) {
			enemies.get(i).render(g);
		}
		
		grid.render(g, (int)xOff, (int)yOff);
		
		for (int i = 0; i < players.size(); i++) {
			for (int y = 0; y < mapheight; y++) {
				for (int x = 0; x < mapwidth; x++) {
					if(players.get(i).getCenterBounds().intersects(grid.cell(x, y))){
						g.setColor(Color.WHITE);
						g.drawString("Player Coords: (" + grid.getId().get(x) + "," + grid.getId().get(y) + ")", 10, 15);
					}
				}
			}
		}
	}
	
	public void tileCollision(int x, int y){
		
		for (int i = 0; i < playerBullets.size(); i++) {
			if(playerBullets.get(i).getBounds().intersects(grid.cell(x, y))){
				playerBullets.remove(i);
			}
		}
		
		for (int i = 0; i < enemyBullets.size(); i++) {
			if(enemyBullets.get(i).getBounds().intersects(grid.cell(x, y))){
				enemyBullets.remove(i);
			}
		}
		
		for (int i = 0; i < players.size(); i++) {
			if (players.get(i).getTopBounds().intersects(grid.cell(x, y))) {
				colliding = true;
				dY+= 2;
			}
			if (players.get(i).getBotBounds().intersects(grid.cell(x, y))) {
				colliding = true;
				dY-= 2;
			}
			if (players.get(i).getLeftBounds().intersects(grid.cell(x, y))) {
				colliding = true;
				dX+= 2;
			}
			if (players.get(i).getRightBounds().intersects(grid.cell(x, y))) {
				colliding = true;
				dX-= 2;
			}
		}
		
		////////////////////////////////////////////////////////////////
		for (int i = 0; i < enemies.size(); i++) {
			if (enemies.get(i).getTopBounds().intersects(grid.cell(x, y))) {
				colliding = true;
				enemies.get(i).setY(grid.cell(x, y).getY() - yOff + 232);
			}
			if (enemies.get(i).getBotBounds().intersects(grid.cell(x, y))) {
				colliding = true;
				enemies.get(i).setY(grid.cell(x, y).getY() - yOff + 169);
			}
			if (enemies.get(i).getLeftBounds().intersects(grid.cell(x, y))) {
				colliding = true;
				enemies.get(i).setX(grid.cell(x, y).getX() - xOff + 378);
			}
			if (enemies.get(i).getRightBounds().intersects(grid.cell(x, y))) {
				colliding = true;
				enemies.get(i).setX(grid.cell(x, y).getX() - xOff + 323);
			}
			if(colliding){
				colliding = false;
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

	public void addPlayerBullet(Bullet bullet) {
		playerBullets.add(bullet);
	}

	public void removePlayerBullet(Bullet bullet) {
		playerBullets.remove(bullet);
	}
	
	public void addEnemyBullet(Bullet bullet) {
		enemyBullets.add(bullet);
	}

	public void removeEnemyBullet(Bullet bullet) {
		enemyBullets.remove(bullet);
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

	private Rectangle getTileBounds(int x, int y, int width, int height) {
		return new Rectangle(x, y, width, height);
	}
	
}
