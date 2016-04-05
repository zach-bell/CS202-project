package com.vexoid.game.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.vexoid.game.MainGame;
import com.vexoid.game.SoundManager;
import com.vexoid.game.TextureManager;
import com.vexoid.game.camera.OrthoCamera;
import com.vexoid.game.entity.stars.Stars_Class;

public class EntityManager {
	
	private final Array<Entity> entities = new Array<Entity>();
	private final Array<Stars_Class> stars = new Array<Stars_Class>();
	
	private static Player player;
	public static int basicEnemiesCount =2;
	public static int AdvancedEnemiesCount =-1;
	
	String gameDifficulty;
	public static int enemiesKilled = 0;
	public int nullEnemiesKilled = 0;
	public static boolean isGameOver = false;
	private float damageMultiplier, healthMultiplier;
	public static int lives;
	public int modifier=0;
	
	public EntityManager(OrthoCamera camera, String difficulty) {
		player = new Player(new Vector2(MainGame.WIDTH/2-TextureManager.PLAYER.getWidth()/2, 15),
				new Vector2(0, 0), this, camera);
		
		gameDifficulty = difficulty;
		
		if (difficulty == "hard"){
			BasicLaserEnemy.basicLaserEnemyHealth = 25;
			damageMultiplier = 1.5f;
			healthMultiplier = 0.8f;
			lives = 5;
		}
		if (difficulty == "medium"){
			BasicLaserEnemy.basicLaserEnemyHealth = 20;
			damageMultiplier = 1.25f;
			healthMultiplier = 1;
			lives = 4;
		}
		if (difficulty == "easy"){
			BasicLaserEnemy.basicLaserEnemyHealth = 15;
			damageMultiplier = 1;
			healthMultiplier = 1.2f;
			lives = 3;
		}
	}
	
	int ran = MathUtils.random(0,1);
	int secondIncrease = 30;
	int starcount = 0;
	
	static boolean clearedEntities = false;
	private int limit=101,starLimit=limit,starToggle=0;
	
	
	public void update() {
/*********************
This is the star creation
part of it
**********************/
		starcount ++;
		if(starcount == 4) {		//	Controls spawing ammount
			if(starToggle>=1){
				starLimit=99;
				starToggle --;
			} else					//	This controls aditional planets and such
				starLimit=limit;
			int text = MathUtils.random(0,starLimit);
			if(text>=100)
				starToggle=100;						//	This sets the wait for a planet to spawn
	//	Sets the position of the stars and speed
			float y = MainGame.HEIGHT + TextureManager.STAR1.getHeight();
			float x = MathUtils.random(0, MainGame.WIDTH-TextureManager.STAR1.getWidth());
			float speedy = MathUtils.random(2.5f,4.5f);
			if(text>=100)
				speedy -= 2;
			addStars(new Stars_Class(new Vector2(x,y), new Vector2(0,-speedy), text));
			starcount = 0;
		}
/*********************
 * This is the end of
 * star creation
 * and the rest of the
 * code follows
 *********************/
		
		if(player.tookLives){
			clearAllEntities();
			clearedEntities = true;
			player.tookLives = false;
		} else	clearedEntities = false;
	
	// updating the enemies in the array
		for (Entity e : entities)
			e.update();
		for (Stars_Class s : stars)
			s.update();

	// bullet removing
		for (bullet2 m : getGoodBullets())
			if (m.checkEnd()){
				entities.removeValue(m, false);
			}
		for (bullet1 n : getBadBullets())
			if (n.checkEnd()){
				entities.removeValue(n, false);
			}
		for (LaserBullet1 e : getLaserBullets())
			if (e.destroyTime()){
				entities.removeValue(e, false);
			}
		for (BlastEffect e : getBlastEffect())
			if (e.destroyTime()){
				entities.removeValue(e, false);
			}
		for (Effect1 e : getEffects1())
			if (e.destroyTime()){
				entities.removeValue(e, false);
			}
		for (Effect3_LaserWarning e : getEffect3_LaserWarning())
			if (e.destroyTime()){
				entities.removeValue(e, false);
			}
		for (Effect2 e : getEffects2())
			if (e.destroyTime()){
				entities.removeValue(e, false);
			}
		for (Stars_Class s : stars)
			if (s.checkEnd())
				stars.removeValue(s, false);
		
		player.update();
		checkCollisions();
		if (lives < 0) clearPlayer(true);
	}
	
// ***********************
// **	Adds Entities	**
// **	for TimeManager	**
// ***********************
	public void addBasicEnemy(){
		float x = MathUtils.random(0, MainGame.WIDTH - TextureManager.BASIC_ENEMY.getWidth());
		float y = MathUtils.random(MainGame.HEIGHT, MainGame.HEIGHT * 2);
		addEntity(new BasicEnemy(new Vector2(x, y), new Vector2(0, 0), this, gameDifficulty));
	}
	public void addBasicLaserEnemy(){
		float w = MathUtils.random(0, MainGame.WIDTH - TextureManager.LASER_ENEMY.getWidth());
		float s = (MainGame.HEIGHT + TextureManager.LASER_ENEMY.getWidth());
		addEntity(new BasicLaserEnemy(new Vector2(w, s), new Vector2(0, 0), this, gameDifficulty));
	}
	public void addAdvancedEnemy(){
		float x = MathUtils.random(0, MainGame.WIDTH - TextureManager.ADVANCED_ENEMY.getWidth());
		float y = MathUtils.random(MainGame.HEIGHT, MainGame.HEIGHT * 2);
		addEntity(new AdvancedEnemy(new Vector2(x, y), new Vector2(0, 0), this, gameDifficulty));
	}
	
	public void render(SpriteBatch sb) {
		for (Stars_Class s : stars)
			s.render(sb);
		player.render(sb);
		for (Entity e : entities)
			e.render(sb);
	}
	public void addEntity(Entity entity) {
		entities.add(entity);
	}
	private void addStars(Stars_Class entity) {
		stars.add(entity);
	}

	private void doBlastEffect(Vector2 pos, int ammount, Texture texture, String color){
		Vector2 Position = pos;
		for(int i=1; i <=ammount; i++){
		addEntity(new BlastEffect(new Vector2(Position.x+(texture.getWidth()/2)+MathUtils.random(-5,5),Position.y+(texture.getWidth()/2)+MathUtils.random(-5,5)),new Vector2(0, 0), 1.5f, color));
		}
	}
	private void doExplosion(Vector2 pos, int ammount, String color){
		Vector2 Position = pos;
		for(int i=1; i <=ammount; i++){
		addEntity(new BlastEffect(new Vector2(Position.x, Position.y), new Vector2(0, 0), 50, color));
		}
	}
	public void clearAllEntities(){
		entities.removeAll(getBasicEnemies(), false);
		entities.removeAll(getAdvancedEnemies(), false);
		entities.removeAll(getBasicLaserEnemies(), false);
		entities.removeAll(getBadBullets(), false);
		entities.removeAll(getLaserBullets(), false);
		entities.removeAll(getGoodBullets(), false);
		SoundManager.liveLost.play(0.6f);
		doExplosion(player.pos, 300, "red");
		player.pos.set(new Vector2(470, 15));
		System.out.println("Entities Cleared");
	}
	public void clearPlayer(boolean PlayerToo){
		if(PlayerToo)
			player.dontMove();
			isGameOver = true;
			entities.removeAll(getBasicEnemies(), false);
			entities.removeAll(getBasicLaserEnemies(), false);
			entities.removeAll(getAdvancedEnemies(), false);
	}
	
	/****************************
	 *	collision detection
	 ****************************/
	private void checkCollisions() {
		
// 
		for (bullet1 n : getBadBullets()) {
			if (player.getBounds().overlaps(n.getBounds())){
			entities.removeValue(n, false);
			doBlastEffect(n.pos.cpy(),10,n.texture, "red");
			SoundManager.hit1.play(1.0f);
			player.decreaseHealth(8 * damageMultiplier);
			}
		}
		for (LaserBullet1 e : getLaserBullets()) {
			if (player.getBounds().overlaps(e.getBounds())){
				SoundManager.hit1.play(1.0f);
				player.decreaseHealth(0.9f * damageMultiplier);
				}
		}
/*	***************
	Enemy Collision
	***************
		
	***********
	Basic Enemy
	***********
*/
		for (BasicEnemy e : getBasicEnemies()) {
			for (bullet2 m : getGoodBullets()) {
				if (e.getBounds().overlaps(m.getBounds())) {
					e.decreaseHealth(4 * healthMultiplier);;
					entities.removeValue(m, false);
					doBlastEffect(m.pos.cpy(),10,m.texture, "blue");
					SoundManager.hit1.play(0.7f);
					if (e.entityDied){
						enemiesKilled += 100;
						nullEnemiesKilled += 100;
						doBlastEffect(e.pos.cpy(),10,e.texture, "blue");
						entities.removeValue(e, false);
					}
				}
			}
			for (Red_Bullet2 m : getPlayerRedBullets()) {
				if (e.getBounds().overlaps(m.getBounds())) {
					e.decreaseHealth(3.5f * healthMultiplier);;
					entities.removeValue(m, false);
					doBlastEffect(m.pos.cpy(),10,m.texture, "red");
					SoundManager.hit1.play(0.6f);
					if (e.entityDied){
						enemiesKilled += 500;
						nullEnemiesKilled += 500;
						doBlastEffect(e.pos.cpy(),15,e.texture, "red");
						entities.removeValue(e, false);
					}
				}
			}
			if (e.getBounds().overlaps(player.getBounds())){
				player.decreaseHealth(25 * damageMultiplier);
				SoundManager.hit2.play(0.3f);
				entities.removeValue(e, false);
				doBlastEffect(e.pos.cpy(),10,e.texture, "red");
			}
		}
		
//	**************
//	Advanced Enemy
//	**************
		for (AdvancedEnemy e : getAdvancedEnemies()) {
		// Blue Player bullet
			for (bullet2 m : getGoodBullets()) {
				if (e.getBounds().overlaps(m.getBounds())) {
					e.decreaseHealth(1 * healthMultiplier);;
					entities.removeValue(m, false);
					doBlastEffect(m.pos.cpy(),10,m.texture, "blue");
					SoundManager.hit1.play(0.7f);
					if (e.entityDied){
						enemiesKilled += 100;
						nullEnemiesKilled += 100;
						doBlastEffect(e.pos.cpy(),10,e.texture, "blue");
						entities.removeValue(e, false);
					}
				}
			}
		// Red Player Bullet
			for (Red_Bullet2 m : getPlayerRedBullets()) {
				if (e.getBounds().overlaps(m.getBounds())) {
					e.decreaseHealth(4.5f * healthMultiplier);;
					entities.removeValue(m, false);
					doBlastEffect(m.pos.cpy(),10,m.texture, "red");
					SoundManager.hit1.play(0.6f);
					if (e.entityDied){
						enemiesKilled += 500;
						nullEnemiesKilled += 500;
						doBlastEffect(e.pos.cpy(),15,e.texture, "red");
						entities.removeValue(e, false);
					}
				}
			}
		// If player runs into him
			if (e.getBounds().overlaps(player.getBounds())){
				player.decreaseHealth(25 * damageMultiplier);
				SoundManager.hit2.play(0.3f);
				entities.removeValue(e, false);
				doBlastEffect(e.pos.cpy(),10,e.texture, "red");
			}
		}
// *********
// Laser Guy
// *********
		for (BasicLaserEnemy e : getBasicLaserEnemies()) {
	// Blue Player bullet
			for (bullet2 m : getGoodBullets()) {
				if (e.getBounds().overlaps(m.getBounds())) {
					e.decreaseHealth(0.3f * healthMultiplier);;
					entities.removeValue(m, false);
					doBlastEffect(m.pos.cpy(),10,m.texture, "blue");
					SoundManager.hit1.play(0.6f);
					if (e.entityDied){
						enemiesKilled += 500;
						nullEnemiesKilled += 500;
						doBlastEffect(e.pos.cpy(),15,e.texture, "blue");
						entities.removeValue(e, false);
					}
				}
			}
		// Red player bullet
			for (Red_Bullet2 m : getPlayerRedBullets()) {
				if (e.getBounds().overlaps(m.getBounds())) {
					e.decreaseHealth(3.5f * healthMultiplier);;
					entities.removeValue(m, false);
					doBlastEffect(m.pos.cpy(),10,m.texture, "red");
					SoundManager.hit1.play(0.6f);
					if (e.entityDied){
						enemiesKilled += 500;
						nullEnemiesKilled += 500;
						doBlastEffect(e.pos.cpy(),15,e.texture, "red");
						entities.removeValue(e, false);
					}
				}
			}
		// If player runs into him
			if (e.getBounds().overlaps(player.getBounds())){
				player.decreaseHealth(25 * damageMultiplier);
				SoundManager.hit2.play(0.2f);
				doBlastEffect(e.pos.cpy(),10,e.texture, "blue");
				SoundManager.laserShot1.stop();
				entities.removeValue(e, false);
			}
		}
	}
	public static String getPlayerShootingMode(){
		return player.shootingMode();
	}
	public static int getPlayerLives(){
		return lives;
	}
	
/********************************************
 * 		All things relating to Arrays
 *********************************************/
	
	private Array<BasicEnemy> getBasicEnemies() {
		Array<BasicEnemy> ret = new Array<BasicEnemy>();
		for (Entity e : entities)
			if (e instanceof BasicEnemy)
				ret.add((BasicEnemy)e);
		return ret;
	}
	private Array<AdvancedEnemy> getAdvancedEnemies() {
		Array<AdvancedEnemy> ret = new Array<AdvancedEnemy>();
		for (Entity e : entities)
			if (e instanceof AdvancedEnemy)
				ret.add((AdvancedEnemy)e);
		return ret;
	}
	private Array<BasicLaserEnemy> getBasicLaserEnemies() {
		Array<BasicLaserEnemy> ret = new Array<BasicLaserEnemy>();
		for (Entity l : entities)
			if (l instanceof BasicLaserEnemy)
				ret.add((BasicLaserEnemy)l);
		return ret;
	}
	private Array<bullet1> getBadBullets() {
		Array<bullet1> ret = new Array<bullet1>();
		for (Entity n : entities)
			if (n instanceof bullet1)
				ret.add((bullet1)n);
		return ret;
	}
	private Array<bullet2> getGoodBullets() {
		Array<bullet2> ret = new Array<bullet2>();
		for (Entity e : entities)
			if (e instanceof bullet2)
				ret.add((bullet2)e);
		return ret;
	}
	private Array<Red_Bullet2> getPlayerRedBullets() {
		Array<Red_Bullet2> ret = new Array<Red_Bullet2>();
		for (Entity e : entities)
			if (e instanceof Red_Bullet2)
				ret.add((Red_Bullet2)e);
		return ret;
	}
	private Array<LaserBullet1> getLaserBullets() {
		Array<LaserBullet1> ret = new Array<LaserBullet1>();
		for (Entity e : entities)
			if (e instanceof LaserBullet1)
				ret.add((LaserBullet1)e);
		return ret;
	}
	private Array<Effect1> getEffects1() {
		Array<Effect1> ret = new Array<Effect1>();
		for (Entity t : entities)
			if (t instanceof Effect1)
				ret.add((Effect1)t);
		return ret;
	}
	private Array<Effect2> getEffects2() {
		Array<Effect2> ret = new Array<Effect2>();
		for (Entity t : entities)
			if (t instanceof Effect2)
				ret.add((Effect2)t);
		return ret;
	}
	private Array<BlastEffect> getBlastEffect() {
		Array<BlastEffect> ret = new Array<BlastEffect>();
		for (Entity t : entities)
			if (t instanceof BlastEffect)
				ret.add((BlastEffect)t);
		return ret;
	}
	private Array<Effect3_LaserWarning> getEffect3_LaserWarning() {
		Array<Effect3_LaserWarning> ret = new Array<Effect3_LaserWarning>();
		for (Entity t : entities)
			if (t instanceof Effect3_LaserWarning)
				ret.add((Effect3_LaserWarning)t);
		return ret;
	}
	
// ***********************
// **	Other things	**
// ***********************
	public int getEntities(){
		return getBasicLaserEnemies().size+getBasicEnemies().size+getAdvancedEnemies().size;
	}
	public static float checkPlayerHealth(){
		return (int) player.getHealth();
	}
	public static int enemyKillScore(){
		return enemiesKilled;
	}
	public int nullEnemyKillScore(){
		return nullEnemiesKilled;
	}
	public boolean noEnemies() {
		return (getBasicEnemies().size+getBasicLaserEnemies().size+getAdvancedEnemies().size <= 0);
	}
	public static String getPlayerBulletMode() {
		return player.bulletMode();
	}
}
