package com.vexoid.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.vexoid.game.entity.EntityManager;
import com.vexoid.game.screen.GameScreen;
import com.vexoid.game.screen.ScreenManager;

public class MainGame extends ApplicationAdapter {
	
	public static int WIDTH = 800, HEIGHT = 600;
	private SpriteBatch batch;
	
	public static String difficulty = "medium";
	
	Music CurrentMusic;
	private int oneTimeFire=0;
	
	public static void setDifficulty(String gameDif){
		difficulty = gameDif;
	}
	
	public void create () {
		batch = new SpriteBatch();
		ScreenManager.setScreen(new GameScreen(), difficulty);
		CurrentMusic = SoundManager.music;
		CurrentMusic.play();
		CurrentMusic.setLooping(true);
		CurrentMusic.setVolume(0.7f);
			    
	    System.out.println("Difficulty = " + difficulty);
	}
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		if (ScreenManager.getCurrentScreen() !=null) {
			ScreenManager.getCurrentScreen().update();
			ScreenManager.getCurrentScreen().render(batch);
		}
		
		if (EntityManager.isGameOver){
			if(oneTimeFire == 0){
				System.out.println("You Died");
				stop();
				Music newMusic;
				newMusic = SoundManager.endMusic;
				newMusic.play();
				newMusic.setVolume(0.8f);
				newMusic.setLooping(true);
				oneTimeFire =1;
			}
		}
	}
	public void play(){
		CurrentMusic.play();
	}
	public void stop(){
		CurrentMusic.stop();
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
