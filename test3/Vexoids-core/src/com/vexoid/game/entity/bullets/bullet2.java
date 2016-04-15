package com.vexoid.game.entity.bullets;

import com.badlogic.gdx.math.Vector2;
import com.vexoid.game.MainGame;
import com.vexoid.game.TextureManager;
import com.vexoid.game.entity.Entity;

public class bullet2 extends Entity {

	public bullet2(Vector2 pos, Vector2 direction) {
		super(TextureManager.BULLET2, pos, direction);
	}

	public void update() {
		pos.add(direction);
	}
	
	public boolean checkEnd() {
		return pos.y >= MainGame.HEIGHT;
	}
	
}
