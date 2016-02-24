package com.vexoid.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.vexoid.game.screen.GameScreen;
import com.vexoid.game.screen.ScreenManager;

public class MainGame extends ApplicationAdapter {
	
	public static int WIDTH = 800, HEIGHT = 600;
	private SpriteBatch batch;
	public static int CLOCK = 0;
	public int internalCounter = 0;
	public int COUNTER = 0;
	public int score = 0;
	public int counterScore = 0;
	public String displayScore;
	BitmapFont yourBitmapFontName;
	
	public void create () {
		Music CurrentMusic;
		batch = new SpriteBatch();
		ScreenManager.setScreen(new GameScreen());
		CurrentMusic = Gdx.audio.newMusic(Gdx.files.internal("D:/Documents/test workspace/test3/Vexoids-core/Assets/asdd - main.mp3"));
		CurrentMusic.play();
		score = 0;
	    yourBitmapFontName = new BitmapFont();
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
			System.out.println("Count: " + COUNTER);
		}
		if (counterScore == counterScoreLimit) {
			counterScore = 0;
			score ++;
			//System.out.println("Score: " + score);
		}
		displayScore = "Light Years : " + score;
		
		//System.out.println("Tick: " + CLOCK);
		
		if (ScreenManager.getCurrentScreen() !=null)
			ScreenManager.getCurrentScreen().update();
		
		if (ScreenManager.getCurrentScreen() !=null)
			ScreenManager.getCurrentScreen().render(batch);
		batch.begin(); 
		yourBitmapFontName.setColor(1.0f, 1.0f, 1.0f, 1.0f);
		yourBitmapFontName.draw(batch, displayScore, 25, 580); 
		batch.end();
	}
	public int getCount() {
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
