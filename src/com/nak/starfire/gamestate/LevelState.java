package com.nak.starfire.gamestate;

import java.awt.Graphics;

import com.nak.starfire.entity.Player;
import com.nak.starfire.input.Keyboard;
import com.nak.starfire.level.Level;

public class LevelState {
	
	private Level level;

	public LevelState(String path) {
		level = new Level(path);
	}

	public void render(Graphics g) {
		level.render(g);
	}

	public void update() {
		level.update();
	}

	public void input() {
		level.input();
	if(Keyboard.b){
		GameStateManager.gameState = GameState.MENUSTATE;
		GameStateManager.levelState = null;
		}
	}
}
