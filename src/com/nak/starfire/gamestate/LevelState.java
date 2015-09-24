package com.nak.starfire.gamestate;

import java.awt.Graphics;

import com.nak.starfire.entity.Player;
import com.nak.starfire.input.Keyboard;
import com.nak.starfire.level.Level;

public class LevelState {
	
	private Level level;
	private Player player;

	public LevelState(String path) {
		level = new Level(path);
		player = new Player(level);
	}

	public void render(Graphics g) {
		level.render(g);
		player.render(g);
	}

	public void update() {
		level.update();
		player.update();
	}

	public void input() {
		level.input();
	if(Keyboard.b){
		GameStateManager.gameState = GameState.MENUSTATE;
		GameStateManager.levelState = null;
		}
	}
}
