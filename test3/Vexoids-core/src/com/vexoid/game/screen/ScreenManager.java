package com.vexoid.game.screen;

public class ScreenManager {
	
	private static Screen currentScreen;
	
	public static void setScreen(Screen screen, String difficulty, int Distance) {
		if (currentScreen !=null) currentScreen.dispose();
		currentScreen = screen;
		currentScreen.create(difficulty, Distance);
	}
	
	public static Screen getCurrentScreen() {
		return currentScreen;
	}
}
