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
	public static int CLOCK = 0;
	public int internalCounter = 0;
	public static int COUNTER = 0;
	public int distance = 0;
	public int counterScore = 0;
	public String displayDistance;
	public String displayScore;
	public String displayPlayerHealth;
	public static String difficulty = "medium";
	BitmapFont displayDistanceFont;
	BitmapFont displayScoreFont;
	BitmapFont displayPlayerHealthFont;
	
	public static void setDifficulty(String gameDif){
		difficulty = gameDif;
	}
	
	public void create () {
		Music CurrentMusic;
		batch = new SpriteBatch();
		ScreenManager.setScreen(new GameScreen(), difficulty);
		CurrentMusic = SoundManager.music;
		CurrentMusic.play();
		CurrentMusic.setLooping(true);
		CurrentMusic.setVolume(0.5f);
		distance = 0;
	    displayDistanceFont = new BitmapFont();
	    displayScoreFont = new BitmapFont();
	    displayPlayerHealthFont = new BitmapFont();
	    
	    System.out.println("Difficulty = " + difficulty);
	}
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		CLOCK ++;
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
		displayDistance = "Distance : " + distance + " kmi";
		displayScore = "Score : " + EntityManager.enemyKillScore();
		displayPlayerHealth = "Hearts : " + EntityManager.playerHealth;
		
		//System.out.println("Tick: " + CLOCK);
		
		if (ScreenManager.getCurrentScreen() !=null) {
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
		batch.end();
		
		if (EntityManager.isGameOver){
			System.out.println("YOU FUCKING DIED");
		}
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
