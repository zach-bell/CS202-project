package com.vexoid.game.entity;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.vexoid.game.MainGame;
import com.vexoid.game.SoundManager;
import com.vexoid.game.TextureManager;

public class BasicLaserEnemy extends Entity {
	String gameDifficulty;
<<<<<<< HEAD
	int timeDelay=0,textureVariation = 2,textureVariation2 = 4,stageDelay = 600;
	public static int health;
	public static int basicLaserEnemyHealth;

=======
	int timeDelay=0;
	public static int health;
	public static int basicLaserEnemyHealth;
	float initSpeed = 0.75f;
	
>>>>>>> origin/master
	public BasicLaserEnemy(Vector2 pos, Vector2 direction, EntityManager entityManager, String difficulty) {
		super(TextureManager.LASER_ENEMY, pos, direction);
		this.entityManager = entityManager;
		gameDifficulty = difficulty;
<<<<<<< HEAD
		stageDelay = MathUtils.random(450,600);
=======
		
>>>>>>> origin/master
		if (gameDifficulty == "hard") {
			timeDelay = 300;
			health = 10;
		}
		if (gameDifficulty == "medium") {
			timeDelay = 300;
			health = 7;
		}
		if (gameDifficulty == "easy") {
			timeDelay = 300;
			health = 5;
		}
	}

	//health stuff
	float healthPercent = 100;
	boolean entityDied = false;
	public void health(){
		if(healthPercent >= 100.000)
			healthPercent=100.0f;
	}
	public void increaseHealth(float ammount){
		healthPercent += ammount;
		if(healthPercent >= 100.0)
			healthPercent=100;
	}
	public void decreaseHealth(float ammount){
		healthPercent -= ammount;
		if(healthPercent <= 0)
			entityDied = true;
	}
	public float getHealth(){
		return healthPercent;
	}
	public boolean entityDied(){
		return entityDied;
	}
	
	private final EntityManager entityManager;
	float xMovement;
	float yMovement;
	float xSpeed = 2;
<<<<<<< HEAD
	float ySpeed = 2;
=======
	float ySpeed = 8 * initSpeed;
>>>>>>> origin/master
	private int xTarget = (int) Player.PositionX;
	private int yTarget = MathUtils.random(MainGame.HEIGHT - 100, MainGame.HEIGHT - TextureManager.LASER_ENEMY.getHeight());
	int firemode = 0;
	int internalClock = 0;
	int internalClock2 = 0;
	boolean sound = true;
	
	public void update() {
		if (firemode == 0){
			internalClock2 = 0;
		xTarget = (int) Player.PositionX;
		if (pos.x > xTarget+2 || pos.x < xTarget-2){
			if (pos.x > xTarget){
				xMovement = -xSpeed;
				} else {
				xMovement = xSpeed;
				}
		} else {
			xMovement = 0;
		}

		// Y movement
			if (pos.y > yTarget+5 || pos.y < yTarget-5){
				if (pos.y > yTarget) {
				yMovement = -ySpeed;
				} else {
				yMovement = ySpeed;
				}
			} else{
				yTarget = MathUtils.random(MainGame.HEIGHT - 100, MainGame.HEIGHT - TextureManager.LASER_ENEMY.getHeight());
				ySpeed = MathUtils.random(0.1f,0.8f);
			}
			pos.add(direction.set(xMovement, yMovement));
		}
		
		if (pos.y < MainGame.HEIGHT){
			internalClock ++;
<<<<<<< HEAD
			if (internalClock >= stageDelay)
				firemode =1;
			if (firemode ==1 && internalClock >= stageDelay) {
=======
			if (internalClock >= 600)
				firemode =1;
			if (firemode ==1 && internalClock >= 600) {
>>>>>>> origin/master
				internalClock = 0;
				firemode = 2;
				SoundManager.warning1.play(0.6f);
			}
			if (firemode >= 1){
<<<<<<< HEAD
				entityManager.addEntity(new Effect1(pos.cpy().add(TextureManager.LASER_ENEMY.getWidth()/2 + MathUtils.random(-70, 70),
						-MathUtils.random(-30,70)), new Vector2(pos.x+(TextureManager.LASER_ENEMY.getWidth()/2),pos.y)));
				
				entityManager.addEntity(new Effect3_LaserWarning(pos.cpy().add(TextureManager.LASER_ENEMY.getHeight()/2 - textureVariation,
						-TextureManager.LASER_WARNING1.getHeight()+10+textureVariation2), direction));
=======
				entityManager.addEntity(new Effect1(pos.cpy().add(TextureManager.LASER_ENEMY.getWidth()/2 + MathUtils.random(-70, 70), -MathUtils.random(-30,70)), pos.cpy()));
>>>>>>> origin/master
			}
// 3rd state of shooting
			if (firemode == 2 && internalClock >= 60){
				if (sound){
					SoundManager.laserShot1.play(0.25f);
				sound = false;
				}
<<<<<<< HEAD
				entityManager.addEntity(new LaserBullet1(pos.cpy().add(TextureManager.LASER_ENEMY.getHeight()/2-8- textureVariation,
						-TextureManager.LASER_BULLET.getHeight()+10+textureVariation2)));
=======
				entityManager.addEntity(new LaserBullet1(pos.cpy().add(TextureManager.LASER_ENEMY.getHeight()/2-8, -TextureManager.LASER_BULLET.getHeight()+10)));
>>>>>>> origin/master
				internalClock2 ++;
				
				if (internalClock2 > 275){
					internalClock = 0;
					firemode =0;
					sound = true;
				}
			}
			
		}
		
	}

}
