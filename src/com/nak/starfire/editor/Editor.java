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
import com.nak.starfire.tile.Tile;
import com.nak.starfire.ui.LeftBar;
import com.nak.starfire.ui.Toolbar;
import com.nak.starfire.ui.TopBar;
import com.nak.starfire.utilities.Time;
import com.nak.starfire.utilities.Utilities;

public class Editor {
	public static BufferedImage image = SpriteSheet.voidTile;
	private int mapwidth = 10, mapheight = 10;
	private int scrollX, scrollY;
	private final int scrollSpeed = 2;
	private int tileCreateRate = 8;
	private int xOff, yOff;
	private long nextTile = 0;
	private long tileCreateDelay;
	private int brushX = 20, brushY = 20;
	private int currentTile, tileQueue;
	private Font font = new Font("Courier", Font.PLAIN, 10);
	private int[] tiles;
	//private ArrayList<Integer> tiles = new ArrayList<Integer>();
	private ArrayList<Toolbar> toolbars = new ArrayList<Toolbar>();
	
	
	public Editor() {
		
		addToolbar(new LeftBar(0, 0, 100, (Game.HEIGHT * Game.SCALE), new Color(0.5f, 0.5f, 1.0f, 0.4f)));
		addToolbar(new TopBar(100, 0, (Game.WIDTH * Game.SCALE) - 100, 40, new Color(1.0f, 0.5f, 0.5f, 0.4f)));
	}

	public void update() {
		
		tileCreateDelay = Time.SECOND - (100000000 * tileCreateRate);
		
		xOff = scrollX - (Game.WIDTH * Game.SCALE) / 2;
		yOff = scrollY - (Game.HEIGHT * Game.SCALE) / 2;
		
		for (int i = 0; i < toolbars.size(); i++) {
			toolbars.get(i).update();
		}
	}

	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.decode("888877"));
		
		for (int y = 0; y < mapheight; y++) {
			for (int x = 0; x < mapwidth; x++) {
				//Draw grid lines
				g2d.draw(getTileBounds(((x * Tile.TILEWIDTH) - xOff), ((y * Tile.TILEHEIGHT) - yOff), Tile.TILEWIDTH, Tile.TILEHEIGHT));
				

				if(getMouseBounds(brushX, brushY).intersects(getTileBounds(((x * Tile.TILEWIDTH) - xOff), ((y * Tile.TILEHEIGHT) - yOff), Tile.TILEWIDTH, Tile.TILEHEIGHT))){
					g.drawImage(Tile.tiles[currentTile].getImage(), ((x * Tile.TILEWIDTH) - xOff), ((y * Tile.TILEHEIGHT) - yOff), null);
				}
				
				
				//Draw tile highlights
				if(getMouseBounds(brushX, brushY).intersects(getTileBounds(((x * Tile.TILEWIDTH) - xOff), ((y * Tile.TILEHEIGHT) - yOff), Tile.TILEWIDTH, Tile.TILEHEIGHT))){
					g2d.setColor(Color.YELLOW);
					g2d.draw(getTileHighlight(((x * Tile.TILEWIDTH) - xOff), ((y * Tile.TILEHEIGHT) - yOff), Tile.TILEWIDTH, Tile.TILEHEIGHT));
					g2d.setColor(Color.decode("888877"));
				}
			}
		}
		
		for (int i = 0; i < toolbars.size(); i++) {
			toolbars.get(i).render(g);
		}
		
		/*UI stuff*************************************************************************************************************/
		g.setColor(Color.WHITE);
		g.setFont(font);
		g.drawString("LOC X: " + String.valueOf(Mouse.mouseVec.getX() - (Game.HEIGHT * Game.SCALE) / 2 + scrollX), 5, 10);
		g.drawString("LOC Y: " + String.valueOf(Mouse.mouseVec.getY() - (Game.HEIGHT * Game.SCALE) / 2 + scrollY), 5, 20);
		g.drawString("RATE: " + String.valueOf((tileCreateRate)), 5, 30);
		g.drawString("MAP X: " + String.valueOf(mapwidth), 5, 40);
		g.drawString("MAP Y: " + String.valueOf(mapheight), 5, 50);
		g.drawString("BRUSH SIZE: " + String.valueOf(brushX), 5, 60);
		g.setColor(Color.LIGHT_GRAY);
		g.drawLine(0, Mouse.mouseVec.getY(), (Game.WIDTH * Game.SCALE) + 27, Mouse.mouseVec.getY());
		g.drawLine(Mouse.mouseVec.getX(), 0, Mouse.mouseVec.getX(), (Game.HEIGHT * Game.SCALE));
		//g.drawString("Tile Selected: " + Tile.tiles[tileQueue].getName(), 100, 13);
		g.drawString("SHORTCUTS:", 100, 13);
		g.drawString("(b) = Main Menu", 165, 13);
		g.drawString("(]) = Increase Brush Size", 265, 13);
		g.drawString("([) = Decrease Brush Size", 425, 13);
		g.drawString("(0-9) = Cycle Tiles", 585, 13);
		g.drawString("(space) = Save Map", 710, 13);
		g.drawString("(NUMPAD 2,4,8,6) = Change Map Size", 165, 30);
		g.drawString("(WASD) = Pan View", 380, 30);
		g2d.draw(getMouseBounds(brushX, brushY));
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
			
			if (Keyboard.numuptoggle && mapheight >= 2) {
				mapheight -= 1;
			}
			if (Keyboard.numdowntoggle && mapheight <= 127) {
				mapheight += 1;
			}
			if (Keyboard.numrighttoggle && mapwidth <= 127) {
				mapwidth += 1;
			}
			if (Keyboard.numlefttoggle && mapwidth >= 2) {
				mapwidth -= 1;
			}
		}
		
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
		
		if (Keyboard.brushInc && brushX <= 798 && brushY <= 798) {
			brushX += 3;
			brushY += 3;
		}
		if (Keyboard.brushDec && brushX >= 3 && brushY >= 3) {
			brushX -= 3;
			brushY -= 3;
		}
		
		if(Keyboard.space && Keyboard.toggleOn){
			for (int y = 0; y < mapheight; y++) {
				for (int x = 0; x < mapwidth; x++) {
					tiles[(x + y * mapwidth)] = Tile.tiles[currentTile].getId();
				}
			}
			Utilities.writeFile(tiles, mapwidth, mapheight, "res/level1.txt");
			Keyboard.toggleOn = false;
		}
		
		if(Mouse.left){
			currentTile = tileQueue;
		}
		
		if (Keyboard.one) {
			tileQueue = Tile.tiles[0].getId();
		}
		
		if (Keyboard.two) {
			tileQueue = Tile.tiles[1].getId();
		}
	}
	
	public void loadLevel(String path) {
		String file = Utilities.loadFileAsString(path);
		String[] tileIndex = file.split("\\s+");
		
		mapwidth = Utilities.parseInt(tileIndex[0]);
		mapheight = Utilities.parseInt(tileIndex[1]);
		
		tiles = new int[mapwidth * mapheight];
		for (int y = 0; y < mapheight; y++) {
			for (int x = 0; x < mapwidth; x++) {
				tiles[x + y * mapwidth] = Utilities.parseInt(tileIndex[(x + y * mapwidth) + 2]);
			}
		}
	}
	
	public void addToolbar(Toolbar toolbar){
		toolbars.add(toolbar);
	}
	
	public void removeToolbar(Toolbar toolbar){
		toolbars.remove(toolbar);
	}
	
	private Rectangle getTileBounds(int x, int y, int width, int height){
		return new Rectangle(x, y, width, height);
	}
	private Rectangle getTileHighlight(int x, int y, int width, int height){
		return new Rectangle(x, y, width - 1, height - 1);
	}
	private Rectangle getMouseBounds(int width, int height){
		return new Rectangle((Mouse.mouseVec.getX() - width / 2), (Mouse.mouseVec.getY() - height / 2), width, height);
	}
}
