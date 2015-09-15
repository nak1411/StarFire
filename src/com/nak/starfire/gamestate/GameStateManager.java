package com.nak.starfire.gamestate;

import java.awt.Graphics;

import com.nak.starfire.level.LevelState;

public class GameStateManager {

	public static final int MENUSTATE = 0;
	public static final int LEVEL1STATE = 1;

	public static GameState gameState = GameState.MENUSTATE;
	public static MenuState menuState;
	public static LevelState levelState;

	public void update() {
		switch (gameState) {
		case MENUSTATE:
			if (menuState == null) {
				menuState = new MenuState("/mainmenu.png");
			}
			menuState.update();
			break;
		case LEVELSTATE:
			if (levelState == null) {
				levelState = new LevelState("res/level1.txt");
			}
			levelState.update();
			break;
		default:
		}
	}

	public void render(Graphics g) {
		switch (gameState) {
		case MENUSTATE:
			if (menuState == null) {
				menuState = new MenuState("/mainmenu.png");
			}
			menuState.render(g);
			break;
		case LEVELSTATE:
			if (levelState == null) {
				levelState = new LevelState("res/level1.txt");
			}
			levelState.render(g);
			break;
		default:
		}
	}
}
