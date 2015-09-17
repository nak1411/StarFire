package com.nak.starfire.entity;

import java.awt.Graphics;
import java.util.ArrayList;

public class EntityHandler {

	private ArrayList<Entity> entities = new ArrayList<Entity>();
	private ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	private Entity entity;
	private Bullet bullet;

	public EntityHandler() {
	}

	public void render(Graphics g) {
		for (int i = 0; i < entities.size(); i++) {
			entity = entities.get(i);
			entity.render(g);
		}
		for (int i = 0; i < bullets.size(); i++) {
			bullet = bullets.get(i);
			bullet.render(g);
		}
	}

	public void update() {
		for (int i = 0; i < entities.size(); i++) {
			entity = entities.get(i);
			entity.update();
		}
		for (int i = 0; i < bullets.size(); i++) {
			bullet = bullets.get(i);
			bullet.update();
		}
	}

	public void addEntity(Entity entity) {
		entities.add(entity);
	}

	public void removeEntity(Entity entity) {
		entities.remove(entity);
	}

	public void addBullet(Bullet bullet) {
		bullets.add(bullet);
	}

	public void removeBullet(Bullet bullet) {
		bullets.remove(bullet);
	}
}
