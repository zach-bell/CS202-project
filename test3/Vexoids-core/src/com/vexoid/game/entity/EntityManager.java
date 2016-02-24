package com.vexoid.game.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.vexoid.game.MainGame;
import com.vexoid.game.TextureManager;
import com.vexoid.game.camera.OrthoCamera;

public class EntityManager {
	
	private final Array<Entity> entities = new Array<Entity>();
	private final Player player;
	
	public EntityManager(int amount, OrthoCamera camera) {
		player = new Player(new Vector2(470, 15), new Vector2(0, 0), this, camera);
		
		for (int i = 0; i <amount; i++) {
			float x = MathUtils.random(0, MainGame.WIDTH - TextureManager.BASIC_ENEMY.getWidth());
			float y = MathUtils.random(MainGame.HEIGHT, MainGame.HEIGHT * 3);
			float speed = MathUtils.random(2, 5);
			addEntity(new BasicEnemy(new Vector2(x, y), new Vector2(0, - speed), this));
			
		}
	}
	
	public void update() {
		for (Entity e : entities)
			e.update();
		for (bullet2 m : getBullets())
			if (m.checkEnd())
				entities.removeValue(m, false);
		
		player.update();
		checkCollisions();
	}
	
	public void render(SpriteBatch sb) {
		for (Entity e : entities)
			e.render(sb);
		player.render(sb);
	}
	
	public void addEntity(Entity entity) {
		entities.add(entity);
	}
	
	private Array<BasicEnemy> getEnemies() {
		Array<BasicEnemy> ret = new Array<BasicEnemy>();
		for (Entity e : entities)
			if (e instanceof BasicEnemy)
				ret.add((BasicEnemy)e);
		return ret;
	}
	
	private void checkCollisions() {
		for (BasicEnemy e : getEnemies()) {
			for (bullet2 m : getBullets()) {
				if (e.getBounds().overlaps(m.getBounds())) {
					entities.removeValue(e, false);
					entities.removeValue(m, false);
				}
			}
		}
	}
	
	private Array<bullet2> getBullets() {
		Array<bullet2> ret = new Array<bullet2>();
		for (Entity e : entities)
			if (e instanceof bullet2)
				ret.add((bullet2)e);
		return ret;
	}

	public boolean gameOver() {
		return getEnemies().size <= 0;
	}
}
