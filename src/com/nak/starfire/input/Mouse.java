package com.nak.starfire.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import com.nak.starfire.utilities.Vector2i;

public class Mouse implements MouseListener, MouseMotionListener {

	public static Vector2i mouseVec;
	private boolean[] buttons = new boolean[256];
	public static boolean left, right, middle;

	public Mouse() {
		mouseVec = new Vector2i();
	}

	public void update() {
		left = buttons[MouseEvent.BUTTON1];
		middle = buttons[MouseEvent.BUTTON2];
		right = buttons[MouseEvent.BUTTON3];
	}

	public void mouseClicked(MouseEvent e) {
		mouseVec.setX(e.getX());
		mouseVec.setY(e.getY());

	}

	public void mouseReleased(MouseEvent e) {
		buttons[e.getButton()] = false;
	}

	public void mouseEntered(MouseEvent e) {
		
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
		buttons[e.getButton()] = true;
	}

	public void mouseDragged(MouseEvent e) {
		mouseVec.setX(e.getX());
		mouseVec.setY(e.getY());
	}

	public void mouseMoved(MouseEvent e) {
		mouseVec.setX(e.getX());
		mouseVec.setY(e.getY());
	}
}
