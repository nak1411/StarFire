package com.nak.starfire.utilities;

public class Vector2i {

	private int x, y;

	public Vector2i() {
		set(x, y);
	}

	public Vector2i(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void set(int x, int y){
		this.x = x;
		this.y = y;
	}

	public int length(int x, int y) {
		return (int) Math.sqrt(x + x + y + y);
	}

	public Vector2i add(Vector2i add) {
		return new Vector2i(x + add.getX(), y + add.getY());
	}

	public Vector2i add(int add) {
		return new Vector2i(x + add, y + add);
	}

	public Vector2i sub(Vector2i sub) {
		return new Vector2i(x - sub.getX(), y - sub.getY());
	}

	public Vector2i sub(int sub) {
		return new Vector2i(x - sub, y - sub);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
}
