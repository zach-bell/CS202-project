package com.vexoid.game.entity;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.vexoid.game.MainGame;
import com.vexoid.game.SoundManager;
import com.vexoid.game.TextureManager;

public class BasicEnemy extends Entity {

	private String gameDifficulty;
	private long lastFire;
	private int firePower = 4;
	public int timeDelay;
	public int shotDelay;
	float speed;
	public BasicEnemy(Vector2 pos, Vector2 direction, EntityManager entityManager, String difficulty) {
		super(TextureManager.BASIC_ENEMY, pos, direction);
		this.entityManager = entityManager;
		gameDifficulty = difficulty;
		speed = direction.y;
		if (gameDifficulty == "hard") {
			firePower = MathUtils.random(4,7);
			timeDelay = 1500;
			shotDelay = MathUtils.random(0,20);	
		}
		if (gameDifficulty == "medium") {
			firePower = MathUtils.random(3,5);
			timeDelay = 2000;
			shotDelay = MathUtils.random(10,30);
		}
		if (gameDifficulty == "easy") {
			firePower = MathUtils.random(2,4);
			timeDelay = 3000;
			shotDelay = MathUtils.random(20,40);
		}
	}
	
	private final EntityManager entityManager;
	int count = 0, time = 0;
	int shooting = 0;
	float xMovement;
	float xSpeed = MathUtils.random(0.5f,2.0f);
	int xTarget = MathUtils.random(0, MainGame.WIDTH);
	public void update() {
		time = MainGame.getCount();
		
//Movement controlling
		if (gameDifficulty == "hard"){
			if (pos.x > xTarget+5 || pos.x < xTarget-5){
				if (pos.x > xTarget) {
				xMovement = -xSpeed;
				} else {
				xMovement = xSpeed;
				}
			} else{
				xTarget = MathUtils.random(0, MainGame.WIDTH);
				xSpeed = MathUtils.random(0.5f,5.0f);
			}
			pos.add(direction.set(xMovement,speed));
		}
		
		if (gameDifficulty == "medium"){
			if (pos.x > xTarget+5 || pos.x < xTarget-5){
				if (pos.x > xTarget) {
				xMovement = -xSpeed;
				} else {
				xMovement = xSpeed;
				}
			} else{
				xTarget = MathUtils.random(0, MainGame.WIDTH);
				xSpeed = MathUtils.random(0.5f,3.5f);
			}
			pos.add(direction.set(xMovement,speed));
		}
		
		if (gameDifficulty == "easy"){
			if (pos.x > xTarget+5 || pos.x < xTarget-5){
				if (pos.x > xTarget) {
					xMovement = -xSpeed;
				} else {
					xMovement = xSpeed;
				}
			} else{
			xTarget = MathUtils.random(0, MainGame.WIDTH);
			xSpeed = MathUtils.random(0.5f,2.0f);
			}
			pos.add(direction.set(xMovement,speed));
		}
// detects when the enemy is within bounds and controls shooting
		if (pos.y < MainGame.HEIGHT)
		if (System.currentTimeMillis() - lastFire >= MathUtils.random(timeDelay, timeDelay+1500)) {
			shooting ++;
				if(shooting >= shotDelay) {
					count ++;
					entityManager.addEntity(new bullet1(pos.cpy().add(15, 30)));
					SoundManager.shot1.play(0.2f);
					if (count >= firePower) {
						count = 0;
						lastFire = System.currentTimeMillis();
					}
					shooting = 0;
			}
		}
//detects if the enemy has fallen down off screen and resets it back up at the top with a random X
		if (pos.y <= -TextureManager.BASIC_ENEMY.getHeight()) {
			float x = MathUtils.random(0, MainGame.WIDTH - TextureManager.BASIC_ENEMY.getWidth());
			pos.set(x, MainGame.HEIGHT);
		}
	}
	
}
