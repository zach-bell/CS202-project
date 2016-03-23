package com.vexoid.game.screen;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.vexoid.game.MainGame;
import com.vexoid.game.camera.OrthoCamera;
import com.vexoid.game.entity.EntityManager;

public class GameScreen extends Screen{
	private OrthoCamera camera;
	private EntityManager entityManager;
	public static int basicEnemiesCount = 4;
	public String gameDifficulty;
<<<<<<< HEAD
	int distance;

	String displayDistance;
	String displayScore;
	String displayPlayerHealth;
	String displayPlayerShootingMode;
	String displayPlayerLives;
	String displayPlayerBulletMode;
	String displayDifficulty;

	BitmapFont displayDistanceFont;
	BitmapFont displayScoreFont;
	BitmapFont displayPlayerHealthFont;
	BitmapFont displayPlayerLivesFont;
	BitmapFont displayPlayerShootingModeFont;
	BitmapFont displayPlayerBulletModeFont;
	BitmapFont displayDifficultyFont;
	
	public void create(String difficulty, int Distance) {
		gameDifficulty = difficulty;
		camera = new OrthoCamera();
		entityManager = new EntityManager(basicEnemiesCount, camera, gameDifficulty);
		distance = Distance;
		
	    displayDistanceFont = new BitmapFont();
	    displayScoreFont = new BitmapFont();
	    displayPlayerHealthFont = new BitmapFont();
	    displayPlayerShootingModeFont = new BitmapFont();
	    displayPlayerLivesFont = new BitmapFont();
	    displayPlayerBulletModeFont = new BitmapFont();
	    displayDifficultyFont = new BitmapFont();
=======

	
	public void create(String difficulty) {
		gameDifficulty = difficulty;
		camera = new OrthoCamera();
		entityManager = new EntityManager(basicEnemiesCount, camera, gameDifficulty);
>>>>>>> origin/master
		}
	
	public void update(int Distance) {
		camera.update();
		
		entityManager.update();
		distance = Distance;
		
		displayScore = "Score : " + EntityManager.enemyKillScore();
		displayPlayerHealth = "Health : " + (int) EntityManager.checkPlayerHealth() + "%";
		displayPlayerShootingMode = "Spread : " + EntityManager.getPlayerShootingMode();
		displayPlayerLives = "Lives : " + EntityManager.getPlayerLives();
		displayPlayerBulletMode = "Mode : " + EntityManager.getPlayerBulletMode();
		displayDifficulty = "Difficulty : " + gameDifficulty;
		displayDistance = "Distance : " + distance + " Km";
	}

	public void render(SpriteBatch sb) {
		sb.setProjectionMatrix(camera.combined);
		sb.begin();
		entityManager.render(sb);
		displayDistanceFont.setColor(1.0f, 1.0f, 1.0f, 1.0f);
		displayDistanceFont.draw(sb, displayDistance, 25, 580);
		
		displayScoreFont.setColor(1.0f, 1.0f, 1.0f, 1.0f);
		displayScoreFont.draw(sb, displayScore, (MainGame.WIDTH/2)-50, 580);
		
		displayPlayerHealthFont.setColor(1.0f, 1.0f, 1.0f, 1.0f);
		displayPlayerHealthFont.draw(sb, displayPlayerHealth, 25, 35);
		
		displayPlayerShootingModeFont.setColor(1.0f, 1.0f, 1.0f, 1.0f);
		displayPlayerShootingModeFont.draw(sb, displayPlayerShootingMode, 675, 35);
		
		displayPlayerBulletModeFont.setColor(1.0f, 1.0f, 1.0f, 1.0f);
		displayPlayerBulletModeFont.draw(sb, displayPlayerBulletMode, 675, 50);
		
		displayPlayerLivesFont.setColor(1.0f, 1.0f, 1.0f, 1.0f);
		displayPlayerLivesFont.draw(sb, displayPlayerLives, 25, 50);
		
		displayDifficultyFont.setColor(1.0f, 1.0f, 1.0f, 1.0f);
		displayDifficultyFont.draw(sb, displayDifficulty, (MainGame.WIDTH)-125, 580);
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
