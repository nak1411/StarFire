package com.nak.starfire.gamestate;

import java.awt.Graphics;

import com.nak.starfire.editor.Editor;
import com.nak.starfire.input.Keyboard;

public class EditorState {
	
	private Editor editor;

	public EditorState() {
		editor = new Editor();
	}

	public void update() {
		editor.update();
	}

	public void render(Graphics g) {
		editor.render(g);
	}

	public void input() {
		editor.input();
		if(Keyboard.b){
			GameStateManager.gameState = GameState.MENUSTATE;
			GameStateManager.editorState = null;
		}
	}
}
