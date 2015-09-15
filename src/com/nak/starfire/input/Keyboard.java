package com.nak.starfire.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {

	private boolean[] keys = new boolean[120];
	public static boolean up, down, left, right;
	private static boolean pressed;

	public void update() {
		up = keys[KeyEvent.VK_UP] || keys[KeyEvent.VK_W];
		down = keys[KeyEvent.VK_DOWN] || keys[KeyEvent.VK_S];
		left = keys[KeyEvent.VK_LEFT] || keys[KeyEvent.VK_A];
		right = keys[KeyEvent.VK_RIGHT] || keys[KeyEvent.VK_D];
	}

	public void keyPressed(KeyEvent e) {
		pressed = true;
		keys[e.getKeyCode()] = true;
	}

	public void keyReleased(KeyEvent e) {
		pressed = false;
		keys[e.getKeyCode()] = false;
	}

	public void keyTyped(KeyEvent e) {
	}

	public boolean[] getKeys() {
		return keys;
	}

	public void setKeys(boolean[] keys) {
		this.keys = keys;
	}

	public static boolean isUp() {
		return up;
	}

	public static void setUp(boolean up) {
		Keyboard.up = up;
	}

	public static boolean isDown() {
		return down;
	}

	public static void setDown(boolean down) {
		Keyboard.down = down;
	}

	public static boolean isLeft() {
		return left;
	}

	public static void setLeft(boolean left) {
		Keyboard.left = left;
	}

	public static boolean isRight() {
		return right;
	}

	public static void setRight(boolean right) {
		Keyboard.right = right;
	}

	public static boolean isPressed() {
		return pressed;
	}

	public static void setPressed(boolean pressed) {
		Keyboard.pressed = pressed;
	}

}
