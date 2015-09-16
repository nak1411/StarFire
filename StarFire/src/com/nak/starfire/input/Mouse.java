package com.nak.starfire.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Mouse implements MouseListener {

	private boolean[] buttons = new boolean[4];
	private boolean left, right, middle;

	public void update() {

		left = buttons[MouseEvent.BUTTON1];
		middle = buttons[MouseEvent.BUTTON2];
		right = buttons[MouseEvent.BUTTON3];

	}

	public void mouseClicked(MouseEvent e) {

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

	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public boolean isMiddle() {
		return middle;
	}

	public void setMiddle(boolean middle) {
		this.middle = middle;
	}
}
