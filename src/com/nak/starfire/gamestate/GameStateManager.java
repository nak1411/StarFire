package com.nak.starfire.gamestate;

import java.awt.Graphics;

public class GameStateManager {

	public static GameState gameState = GameState.MENUSTATE;
	public static MenuState menuState;
	public static LevelState levelState;
	public static EditorState editorState;

	public void update() {
		switch (gameState) {
		case MENUSTATE:
			if (menuState == null) {
				menuState = new MenuState("/mainmenu.png");
			}
			menuState.update();
			menuState.input();
			break;
		case LEVELSTATE:
			if (levelState == null) {
				levelState = new LevelState("res/levelcache.txt");
			}
			levelState.update();
			levelState.input();
			break;
		case EDITORSTATE:
			if (editorState == null) {
				editorState = new EditorState();
			}
			editorState.update();
			editorState.input();
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
				levelState = new LevelState("res/levelcache.txt");
			}
			levelState.render(g);
			break;
		case EDITORSTATE:
			if (editorState == null) {
				editorState = new EditorState();
			}
			editorState.render(g);
			break;
		default:
		}
	}
}
