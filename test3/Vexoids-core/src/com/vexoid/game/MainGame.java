package com.vexoid.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.vexoid.game.entity.EntityManager;
import com.vexoid.game.screen.GameScreen;
import com.vexoid.game.screen.ScreenManager;

public class MainGame extends ApplicationAdapter {
	
	public static int WIDTH = 800, HEIGHT = 600;
	private SpriteBatch batch;
	public int internalCounter = 0;
	public static int COUNTER = 0;
	public int distance = 0;
	public int counterScore = 0;
	String displayDistance;
	String displayScore;
	String displayPlayerHealth;
	String displayPlayerShootingMode;
	String displayPlayerLives;
<<<<<<< HEAD
	String displayPlayerBulletMode;
	String displayDifficulty;
	
=======
>>>>>>> origin/master
	public static String difficulty = "medium";
	BitmapFont displayDistanceFont;
	BitmapFont displayScoreFont;
	BitmapFont displayPlayerHealthFont;
	BitmapFont displayPlayerLivesFont;
	BitmapFont displayPlayerShootingModeFont;
<<<<<<< HEAD
	BitmapFont displayPlayerBulletModeFont;
	BitmapFont displayDifficultyFont;
	
=======
>>>>>>> origin/master
	Music CurrentMusic;
	int oneTimeFire=0;
	
	public static void setDifficulty(String gameDif){
		difficulty = gameDif;
	}
	
	public void create () {
		batch = new SpriteBatch();
<<<<<<< HEAD
		ScreenManager.setScreen(new GameScreen(), difficulty, distance);
=======
		ScreenManager.setScreen(new GameScreen(), difficulty);
>>>>>>> origin/master
		CurrentMusic = SoundManager.music;
		CurrentMusic.play();
		CurrentMusic.setLooping(true);
		CurrentMusic.setVolume(0.7f);
		
		distance = 0;
<<<<<<< HEAD
=======
	    displayDistanceFont = new BitmapFont();
	    displayScoreFont = new BitmapFont();
	    displayPlayerHealthFont = new BitmapFont();
	    displayPlayerShootingModeFont = new BitmapFont();
	    displayPlayerLivesFont = new BitmapFont();
>>>>>>> origin/master
	    
	    System.out.println("Difficulty = " + difficulty);
	}
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		internalCounter ++;

		final int internalCounterLimit = 70;
		final int counterScoreLimit = 2;
		
		if (internalCounter == internalCounterLimit) {
			internalCounter = 0;
			COUNTER ++;
			counterScore ++;
			//System.out.println("Count: " + COUNTER);
		}
		if (counterScore == counterScoreLimit) {
			counterScore = 0;
			distance ++;
			//System.out.println("Score: " + score);
		}
		
		displayDistance = "Distance : " + distance + " Km";
		displayScore = "Score : " + EntityManager.enemyKillScore();
		displayPlayerHealth = "Health : " + (int) EntityManager.checkPlayerHealth() + "%";
		displayPlayerShootingMode = "Mode: " + EntityManager.getPlayerShootingMode();
		displayPlayerLives = "Lives : " + EntityManager.getPlayerLives();
		//System.out.println("Tick: " + CLOCK);
		
		if (ScreenManager.getCurrentScreen() !=null) {
<<<<<<< HEAD
			ScreenManager.getCurrentScreen().update(distance);
			ScreenManager.getCurrentScreen().render(batch);
		}
		
		if (EntityManager.isGameOver){
			if(oneTimeFire == 0){
				System.out.println("You Died");
=======
			ScreenManager.getCurrentScreen().update();
			ScreenManager.getCurrentScreen().render(batch);
		}
		batch.begin(); 
		displayDistanceFont.setColor(1.0f, 1.0f, 1.0f, 1.0f);
		displayDistanceFont.draw(batch, displayDistance, 25, 580);
		displayScoreFont.setColor(1.0f, 1.0f, 1.0f, 1.0f);
		displayScoreFont.draw(batch, displayScore, (MainGame.WIDTH/2)-50, 580);
		displayPlayerHealthFont.setColor(1.0f, 1.0f, 1.0f, 1.0f);
		displayPlayerHealthFont.draw(batch, displayPlayerHealth, 25, 35);
		displayPlayerShootingModeFont.setColor(1.0f, 1.0f, 1.0f, 1.0f);
		displayPlayerShootingModeFont.draw(batch, displayPlayerShootingMode, 675, 35);
		displayPlayerLivesFont.setColor(1.0f, 1.0f, 1.0f, 1.0f);
		displayPlayerLivesFont.draw(batch, displayPlayerLives, 25, 50);
		batch.end();
		
		if (EntityManager.isGameOver){
			System.out.println("You Died");
			if(oneTimeFire == 0){
>>>>>>> origin/master
				stop();
				Music newMusic;
				newMusic = SoundManager.endMusic;
				newMusic.play();
				newMusic.setVolume(0.8f);
				newMusic.setLooping(true);
<<<<<<< HEAD
				oneTimeFire =1;
=======
>>>>>>> origin/master
			}
		}
	}
	public void play(){
		CurrentMusic.play();
	}
	public void stop(){
		CurrentMusic.stop();
	}

	public static int getCount() {
		return COUNTER;
	}
	public void dispose() {
		if (ScreenManager.getCurrentScreen() !=null)
			ScreenManager.getCurrentScreen().dispose();
		batch.dispose();
	}
	public void resize(int width, int height) {
		if (ScreenManager.getCurrentScreen() !=null)
			ScreenManager.getCurrentScreen().resize(width, height);
	}
	public void pause() {
		if (ScreenManager.getCurrentScreen() !=null)
			ScreenManager.getCurrentScreen().pause();
	}
	public void resume() {
		if (ScreenManager.getCurrentScreen() !=null)
			ScreenManager.getCurrentScreen().resume();
	}
}
