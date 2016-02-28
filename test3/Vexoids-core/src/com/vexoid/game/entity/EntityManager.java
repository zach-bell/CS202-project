package com.vexoid.game.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.vexoid.game.MainGame;
import com.vexoid.game.SoundManager;
import com.vexoid.game.TextureManager;
import com.vexoid.game.camera.OrthoCamera;

public class EntityManager {
	
	private final Array<Entity> entities = new Array<Entity>();
	private final Player player;
	int health;
	String gameDifficulty;
	public int basicEnemiesCount =3;
	public static int enemiesKilled = 0;
	
	public EntityManager(int amount, OrthoCamera camera, String difficulty) {
		player = new Player(new Vector2(470, 15), new Vector2(0, 0), this, camera);
		
		for (int i = 0; i <amount && i <basicEnemiesCount; i++) {
			float x = MathUtils.random(0, MainGame.WIDTH - TextureManager.BASIC_ENEMY.getWidth());
			float y = MathUtils.random(MainGame.HEIGHT, MainGame.HEIGHT * 2.5f);
			float speed = MathUtils.random(0.5f, 1.5f);
			addEntity(new BasicEnemy(new Vector2(x, y), new Vector2(0, - speed), this, difficulty));
		}
		gameDifficulty = difficulty;
		if (difficulty == "hard"){
			health = 4;
		}
		if (difficulty == "medium"){
			health = 3;
		}
		if (difficulty == "easy"){
			health = 2;
		}
		
	}

	int secondIncrease = 30;
	public void update() {
		for (Entity e : entities)
			e.update();
		for (bullet2 m : getBullets())
			if (m.checkEnd()){
				entities.removeValue(m, false);	
			}
		
		if(this.noEnemies()){
			if (MainGame.getCount() >= secondIncrease){
				basicEnemiesCount += 3;
				secondIncrease += 30;
			System.out.println("Enemies: " + basicEnemiesCount);
			}
			for (int i = 0; i <basicEnemiesCount; i++) {
				float x = MathUtils.random(0, MainGame.WIDTH - TextureManager.BASIC_ENEMY.getWidth());
				float y = MathUtils.random(MainGame.HEIGHT, MainGame.HEIGHT * 2);
				float speed = MathUtils.random(0.3f, 1.5f);
				addEntity(new BasicEnemy(new Vector2(x, y), new Vector2(0, - speed), this, gameDifficulty));
			}
		}
		player.update();
		checkCollisions();
	}
	
	public void render(SpriteBatch sb) {
		for (Entity e : entities)
			e.render(sb);
		player.render(sb);
	}
	
	public void addEntity(Entity entity) {
		entities.add(entity);
	}
	
	private Array<BasicEnemy> getEnemies() {
		Array<BasicEnemy> ret = new Array<BasicEnemy>();
		for (Entity e : entities)
			if (e instanceof BasicEnemy)
				ret.add((BasicEnemy)e);
		return ret;
	}
	int basicHealth;
	private void checkCollisions() {
		
		for (BasicEnemy e : getEnemies()) {
			for (bullet2 m : getBullets()) {
				if (e.getBounds().overlaps(m.getBounds())) {
					basicHealth ++;
					entities.removeValue(m, false);
					SoundManager.hit1.play(1.0f);
					if (basicHealth >= health){
						basicHealth = 0;
						enemiesKilled += 100;
						entities.removeValue(e, false);
					}
				}
			}
		}
	}
	
	private Array<bullet2> getBullets() {
		Array<bullet2> ret = new Array<bullet2>();
		for (Entity e : entities)
			if (e instanceof bullet2)
				ret.add((bullet2)e);
		return ret;
	}
	public static int enemyKillScore(){
		return enemiesKilled;
	}
	public boolean noEnemies() {
		return getEnemies().size <= 0;
	}
}
