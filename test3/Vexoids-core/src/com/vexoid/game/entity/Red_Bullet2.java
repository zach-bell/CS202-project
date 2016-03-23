package com.vexoid.game.entity;

import com.badlogic.gdx.math.Vector2;
import com.vexoid.game.MainGame;
import com.vexoid.game.TextureManager;

public class Red_Bullet2 extends Entity {

	public Red_Bullet2(Vector2 pos, Vector2 direction) {
		super(TextureManager.RED_BULLET2, pos, direction);
	}

	public void update() {
		pos.add(direction);
		
	}
	
	public boolean checkEnd() {
		return pos.y >= MainGame.HEIGHT;
	}
	
}
