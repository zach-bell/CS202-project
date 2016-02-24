package com.vexoid.game.entity;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.vexoid.game.MainGame;
import com.vexoid.game.TextureManager;

public class BasicEnemy extends Entity {

	private long lastFire;
	public BasicEnemy(Vector2 pos, Vector2 direction, EntityManager entityManager) {
		super(TextureManager.BASIC_ENEMY, pos, direction);
		this.entityManager = entityManager;
	}
	private final EntityManager entityManager;
	public int count = 0;
	public void update() {
		pos.add(direction);
		int firePower = 4;
		if (System.currentTimeMillis() - lastFire >= MathUtils.random(2000, 3000)) {
			entityManager.addEntity(new bullet1(pos.cpy().add(15, 30)));
			count ++;
			if (count >= firePower) {
				count = 0;
				lastFire = System.currentTimeMillis();
			}
		}
		if (pos.y <= -TextureManager.BASIC_ENEMY.getHeight()) {
			float x = MathUtils.random(0, MainGame.WIDTH - TextureManager.BASIC_ENEMY.getWidth());
			pos.set(x, MainGame.HEIGHT);
		}
	}
	
}
