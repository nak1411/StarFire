package com.nak.starfire.editor;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.nak.starfire.Game;
import com.nak.starfire.gfx.SpriteSheet;
import com.nak.starfire.input.Keyboard;
import com.nak.starfire.input.Mouse;
import com.nak.starfire.level.Level;
import com.nak.starfire.tile.Tile;
import com.nak.starfire.utilities.Time;
import com.nak.starfire.utilities.Utilities;

public class Editor {
	public static BufferedImage image;
	private int scrollX, scrollY;
	private final int scrollSpeed = 2;
	private int tileCreateRate = 8;
	private int xOff, yOff;
	private long nextTile = 0;
	private long tileCreateDelay;
	private Font font = new Font("Courier", Font.PLAIN, 10);
	private ArrayList<Integer> tiles = new ArrayList<Integer>();



	public Editor() {
	}

	public void update() {
		
		
		if (Keyboard.up) {
			scrollY -= scrollSpeed;
		}
		if (Keyboard.down) {
			scrollY += scrollSpeed;
		}
		if (Keyboard.left) {
			scrollX -= scrollSpeed;
		}
		if (Keyboard.right) {
			scrollX += scrollSpeed;
		}
		
		if(Keyboard.space && Keyboard.toggleOn){
			for (int y = 0; y < Level.MAPHEIGHT; y++) {
				for (int x = 0; x < Level.MAPWIDTH; x++) {
					tiles.add(y, Tile.id);
					tiles.add(x, Tile.id);
				}
			}
			Utilities.writeFile(tiles, Level.MAPWIDTH, Level.MAPHEIGHT, "res/level.txt");
			Keyboard.toggleOn = false;
		}
		
		tileCreateDelay = Time.SECOND - (100000000 * tileCreateRate);
		
		xOff = scrollX - (Game.WIDTH * Game.SCALE) / 2;
		yOff = scrollY - (Game.WIDTH * Game.SCALE) / 2;
	}

	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.decode("888877"));
		
		for (int y = 0; y < Level.MAPHEIGHT; y++) {
			for (int x = 0; x < Level.MAPWIDTH; x++) {
				g.drawImage(SpriteSheet.voidTile, ((x * Tile.TILEWIDTH) - xOff), ((y * Tile.TILEHEIGHT) - yOff), null);
				g2d.draw(getTileBounds(((x * Tile.TILEWIDTH) - xOff), ((y * Tile.TILEHEIGHT) - yOff), Tile.TILEWIDTH, Tile.TILEHEIGHT));
				
				
				if(getMouseBounds().intersects(getTileBounds(((x * Tile.TILEWIDTH) - xOff), ((y * Tile.TILEHEIGHT) - yOff), Tile.TILEWIDTH, Tile.TILEHEIGHT))){
					g2d.setColor(Color.YELLOW);
					g2d.draw(getTileHighlight(((x * Tile.TILEWIDTH) - xOff), ((y * Tile.TILEHEIGHT) - yOff), Tile.TILEWIDTH, Tile.TILEHEIGHT));
					
					g2d.setColor(Color.WHITE);
					g.setFont(font);
					g.drawString("Tile ID: " + String.valueOf(Tile.id), 10, 60);
					
					g2d.setColor(Color.decode("888877"));
				}
			}
		}
		
		//UI stuff
		g.setColor(Color.WHITE);
		g.setFont(font);
		g.drawString("LOC X: " + String.valueOf(Mouse.mouseVec.getX() - (Game.HEIGHT * Game.SCALE) / 2 + scrollX), 10, 10);
		g.drawString("LOC Y: " + String.valueOf(Mouse.mouseVec.getY() - (Game.HEIGHT * Game.SCALE) / 2 + scrollY), 10, 20);
		g.drawString("RATE: " + String.valueOf((tileCreateRate)), 10, 30);
		g.drawString("MAP X: " + String.valueOf(Level.MAPWIDTH), 10, 40);
		g.drawString("MAP Y: " + String.valueOf(Level.MAPHEIGHT), 10, 50);
		g.setColor(Color.LIGHT_GRAY);
		g.drawLine(0, Mouse.mouseVec.getY(), (Game.WIDTH * Game.SCALE) + 27, Mouse.mouseVec.getY());
		g.drawLine(Mouse.mouseVec.getX(), 0, Mouse.mouseVec.getX(), (Game.HEIGHT * Game.SCALE));
		g2d.draw(getMouseBounds());
	}

	public void input() {
		if (Time.getTime() > nextTile) {
			nextTile = Time.getTime() + tileCreateDelay;
			
			if(Keyboard.increase && tileCreateRate <= 9){
				tileCreateRate++;
			}
			if(Keyboard.decrease && tileCreateRate >= 1){
				tileCreateRate--;
			}
			
			if (Keyboard.numuptoggle && Level.MAPHEIGHT >= 2) {
				Level.MAPHEIGHT -= 1;
			}
			if (Keyboard.numdowntoggle && Level.MAPHEIGHT <= 127) {
				Level.MAPHEIGHT += 1;
			}
			if (Keyboard.numrighttoggle && Level.MAPWIDTH <= 127) {
				Level.MAPWIDTH += 1;
			}
			if (Keyboard.numlefttoggle && Level.MAPWIDTH >= 2) {
				Level.MAPWIDTH -= 1;
			}
		}
	}
	
	private Rectangle getTileBounds(int x, int y, int width, int height){
		return new Rectangle(x, y, width, height);
	}
	private Rectangle getTileHighlight(int x, int y, int width, int height){
		return new Rectangle(x, y, width - 1, height - 1);
	}
	private Rectangle getMouseBounds(){
		return new Rectangle(Mouse.mouseVec.getX() - 1, Mouse.mouseVec.getY() - 1, 2, 2);
	}
}
