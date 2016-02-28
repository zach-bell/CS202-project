package com.vexoid.game.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;
import com.vexoid.game.MainGame;
import com.vexoid.game.SoundManager;
import com.vexoid.game.TextureManager;
import com.vexoid.game.camera.OrthoCamera;

public class Player extends Entity{

	private final EntityManager entityManager;
	private long lastFire;
	private final OrthoCamera camera;
		
	public Player(Vector2 pos, Vector2 direction, EntityManager entityManager, OrthoCamera camera) {
		super(TextureManager.PLAYER, pos, direction);
		this.entityManager = entityManager;
		this.camera = camera;
	}

	
	
	public int shootDelay = 250;
	
	public void update() {
		pos.add(direction);
		int dir = 0;
		if (Gdx.input.isTouched()) {
			Vector2 touch = camera.unprojectCoordinates(Gdx.input.getX(), Gdx.input.getY());
			if (touch.x < MainGame.WIDTH / 2)
				dir = 1;
			else
				dir = 2;
	}
		if (Gdx.input.isKeyPressed(Keys.A) || dir == 1 || Gdx.input.isKeyPressed(Keys.LEFT)) {
				setDirection(-300, 0);
		} else {
			if (Gdx.input.isKeyPressed(Keys.D) || dir ==2 || Gdx.input.isKeyPressed(Keys.RIGHT)) {
			setDirection(300, 0);
			} else {
				if (Gdx.input.isKeyPressed(Keys.W) || Gdx.input.isKeyPressed(Keys.UP)) {
					setDirection(0, 300);
				} else {
					if (Gdx.input.isKeyPressed(Keys.S) || Gdx.input.isKeyPressed(Keys.DOWN)) {
						setDirection(0, -300);
					} else {
						setDirection(0,0);
						}
					}
				}
			}
		if (Gdx.input.isKeyPressed(Keys.K)){
			MainGame.setDifficulty("Easy");
		}
		if (Gdx.input.isKeyPressed(Keys.SPACE) || dir ==1 || dir == 2){
			if (System.currentTimeMillis() - lastFire >= shootDelay) {
				entityManager.addEntity(new bullet2(pos.cpy().add(15, 10)));
				SoundManager.shot2.play(0.4f);
				lastFire = System.currentTimeMillis();
			}
		}
	}
}
