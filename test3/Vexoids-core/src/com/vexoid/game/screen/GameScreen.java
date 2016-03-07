package com.vexoid.game.screen;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.vexoid.game.camera.OrthoCamera;
import com.vexoid.game.entity.EntityManager;

public class GameScreen extends Screen{
	private OrthoCamera camera;
	private EntityManager entityManager;
	public static int basicEnemiesCount = 4;
	public String gameDifficulty;

	
	public void create(String difficulty) {
		gameDifficulty = difficulty;
		camera = new OrthoCamera();
		entityManager = new EntityManager(basicEnemiesCount, camera, gameDifficulty);
		}
	
	public void update() {
		camera.update();
		
		entityManager.update();
	}

	public void render(SpriteBatch sb) {
		sb.setProjectionMatrix(camera.combined);
		sb.begin();
		entityManager.render(sb);
		sb.end();
		
	}

	public void resize(int width, int height) {
		camera.resize();
	}

	public void dispose() {
		
	}

	public void pause() {
		
	}

	public void resume() {
		
	}

}
