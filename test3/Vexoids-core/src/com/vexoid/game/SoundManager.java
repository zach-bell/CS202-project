package com.vexoid.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class SoundManager {
	

	public static Music menuMusic = Gdx.audio.newMusic(Gdx.files.internal("assets/asdd - main 2.mp3"));
	public static Music gameMusic = Gdx.audio.newMusic(Gdx.files.internal("assets/asdd - main.mp3"));
	public static Music endMusic = Gdx.audio.newMusic(Gdx.files.internal("assets/end_game.mp3"));
	
	public static Sound startSound = Gdx.audio.newSound(Gdx.files.internal("assets/start_game.mp3"));
	public static Sound hit1 = Gdx.audio.newSound(Gdx.files.internal("assets/hit1.mp3"));
	public static Sound shot1 = Gdx.audio.newSound(Gdx.files.internal("assets/shot1.mp3"));
	public static Sound shot2 = Gdx.audio.newSound(Gdx.files.internal("assets/shot2.mp3"));
	public static Sound hit2 = Gdx.audio.newSound(Gdx.files.internal("assets/hit2.mp3"));
	public static Sound laserShot1 = Gdx.audio.newSound(Gdx.files.internal("assets/laser_shot.mp3"));
	public static Sound warning1 = Gdx.audio.newSound(Gdx.files.internal("assets/warning.mp3"));
	public static Sound sound1 = Gdx.audio.newSound(Gdx.files.internal("assets/sound1.mp3"));
	public static Sound liveLost = Gdx.audio.newSound(Gdx.files.internal("assets/live_lost.mp3"));
	

}
