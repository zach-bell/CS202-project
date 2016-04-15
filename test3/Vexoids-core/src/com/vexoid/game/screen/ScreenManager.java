package com.vexoid.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.vexoid.game.MainGame;

public class ScreenManager {
	
	private static Screen currentScreen;
	private static String Difficulty;
	
	public static void setScreen(Screen screen, String difficulty) {
		Difficulty = difficulty;
		if (currentScreen !=null) currentScreen.dispose();
		currentScreen = screen;
		currentScreen.create(difficulty);
	}
	
	public static Screen getCurrentScreen() {
		return currentScreen;
	}
	public static void screenManagement(){
		if (Gdx.input.isKeyPressed(Keys.ESCAPE)&& (ScreenManager.getCurrentScreen().whatScreen() == "GameScreen")){
			ScreenManager.getCurrentScreen().dispose();
			MainGame.stopMusic();
			ScreenManager.setScreen(new MenuScreen(), Difficulty);
		}
		if (Gdx.input.isKeyPressed(Keys.ENTER)&& (ScreenManager.getCurrentScreen().whatScreen() == "MenuScreen")){
			ScreenManager.getCurrentScreen().dispose();
			MainGame.stopMusic();
			ScreenManager.setScreen(new GameScreen(), Difficulty);
		}
	}
}
