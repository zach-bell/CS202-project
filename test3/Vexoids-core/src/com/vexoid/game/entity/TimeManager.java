package com.vexoid.game.entity;

import com.badlogic.gdx.math.MathUtils;
import com.vexoid.game.screen.GameScreen;

public class TimeManager{

	public int internalCounter = 0;
	public static int COUNTER = 0;
	public static int distance = 0;
	public int counterScore = 0;
	public int level = 1;
	int step = 1,modifier = 0,basicEnemiesCount = 3,AdvancedEnemiesCount = -2,LaserEnemiesCount=-1,
				secondIncrease = 30,ran = MathUtils.random(0,3);
//	EntityManager entityManager;
	
	public TimeManager(String difficulty) {
		if(difficulty=="hard"){
			modifier = 1;
		}
		if(difficulty=="medium"){
			modifier = 0;
		}
		if(difficulty=="easy"){
			modifier = 0;
		}
	}
	public void update(){
		internalCounter ++;

		final int internalCounterLimit = 70;
		final int counterScoreLimit = 2;
		
		if (internalCounter >= internalCounterLimit) {
			internalCounter = 0;
			COUNTER ++;
			counterScore ++;
		//System.out.println("Count: " + COUNTER);
		}
		if (counterScore >= counterScoreLimit) {
			counterScore = 0;
			distance ++;
		}
		if(level == 1){
			if(step == 1)
				if(noEnemies()){
					for (int i = 0; i <2 + modifier; i++) {
						addBasicEnemy();
					}
					if(COUNTER >= 15){
						step = 2;
						COUNTER = 15;
					}
					System.out.println("Is on step 1");
				}
			if(step ==2)
				if(noEnemies()){
					for (int i = 0; i <3 + modifier; i++) {
						addBasicEnemy();
					}
					if(COUNTER >= 30){
						step = 3;
						COUNTER=0;
					}
					System.out.println("Is on step 2");
				}
			if(step ==3){
				if(noEnemies()){
					addBasicLaserEnemy();
					System.out.println("Is on step 3");
				}
				if(COUNTER >= 15){
					step = 4;
				}
			}
			if(step ==4){
				if(noEnemies()){
					addBasicLaserEnemy();
					addBasicEnemy();
					System.out.println("Is on step 4");
				}
				if(COUNTER >= 30)
					step = 5;
			}
			if(step ==5){
				if(noEnemies()){
					for (int i = 0; i <3 + modifier; i++) {
						addBasicEnemy();
					}
					if(COUNTER >= 70){
						step = 6;
						COUNTER = 0;
					}
				}
			}
			if(step ==6){
				if(noEnemies()){
					for (int i = 0; i <3 + modifier; i++) {
						addBasicEnemy();
					}
					if(COUNTER >= 10){
						step = 7;
					}
				}
			}
			if(step ==7){
				if(noEnemies()){
					for (int i = 0; i <3 + modifier; i++) {
						addBasicEnemy();
					}
					addBasicLaserEnemy();
					if(COUNTER >= 20){
						step = 8;
					}
				}
			}
			if(step ==8){
				if(noEnemies()){
					for (int i = 0; i <4 + modifier; i++) {
						addBasicEnemy();
					}
					if(COUNTER >= 30){
						addBasicLaserEnemy();
					}
					if(COUNTER >= 40){
						addBasicLaserEnemy();
					}
					if(COUNTER >= 100){
						step = 9;
					}
				}
			}
			if(step ==9){
				if(noEnemies()){
					level = 9;
					step = 0;
				}
			}
		}
		if(level == 9)
		if(noEnemies()){
			ran = MathUtils.random(0,3);
			if (COUNTER >= secondIncrease){
				basicEnemiesCount += 1;
				secondIncrease += 45;
			}
			for (int i = 0; i <basicEnemiesCount; i++) {
				addBasicEnemy();
			}
			for (int i = 0; i <AdvancedEnemiesCount; i++) {
				addAdvancedEnemy();
			}
			if (ran >= 1)
				addBasicLaserEnemy();
			if (ran >= 2)
				AdvancedEnemiesCount += 1;
		}
	}
	public void addBasicEnemy(){
		GameScreen.addEnemies(1);
	}
	public void addAdvancedEnemy(){
		GameScreen.addEnemies(2);
	}
	public void addBasicLaserEnemy(){
		GameScreen.addEnemies(3);
	}
	
	public boolean noEnemies(){
		if(GameScreen.getEnemies() <= 0)
			return true;
		else
			return false;
	}
	public int getDistance(){
		return distance;
	}
	public int getCount(){
		return COUNTER;
	}
	public int getLevel(){
		return level;
	}
}