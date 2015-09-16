package com.nak.starfire.entity;

import java.awt.Graphics;
import java.util.ArrayList;

public class EntityHandler {

	private ArrayList<Entity> entities = new ArrayList<Entity>();
	private Entity entity;

	public EntityHandler() {
	}

	public void render(Graphics g) {
		for (int i = 0; i < entities.size(); i++) {
			entity = entities.get(i);
			entity.render(g);
		}
	}

	public void update() {
		for (int i = 0; i < entities.size(); i++) {
			entity = entities.get(i);
			entity.update();
		}
	}

	public void addEntity(Entity entity) {
		entities.add(entity);
	}

	public void removeEntity(Entity entity) {
		entities.remove(entity);
	}

}
