package com.vexoid.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class SoundManager {
	
	public static Music music = Gdx.audio.newMusic(Gdx.files.internal("E:/User/Documents/Workspaces/test3/Vexoids-android/assets/asdd - main.mp3"));
	public static Sound hit1 = Gdx.audio.newSound(Gdx.files.internal("E:/User/Documents/Workspaces/test3/Vexoids-android/assets/hit1.mp3"));
	public static Sound shot1 = Gdx.audio.newSound(Gdx.files.internal("E:/User/Documents/Workspaces/test3/Vexoids-android/assets/shot1.mp3"));
	public static Sound shot2 = Gdx.audio.newSound(Gdx.files.internal("E:/User/Documents/Workspaces/test3/Vexoids-android/assets/shot2.mp3"));
	public static Sound hit2 = Gdx.audio.newSound(Gdx.files.internal("E:/User/Documents/Workspaces/test3/Vexoids-android/assets/hit2.mp3"));

}
