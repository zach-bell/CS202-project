package com.vexoid.game.entity;

import com.badlogic.gdx.math.Vector2;
import com.vexoid.game.MainGame;
import com.vexoid.game.TextureManager;

public class bullet2 extends Entity {

	public bullet2(Vector2 pos) {
		super(TextureManager.BULLET2, pos, new Vector2(0, 15));
	}

	public void update() {
		pos.add(direction);
		
	}
	
	public boolean checkEnd() {
		return pos.y >= MainGame.HEIGHT;
	}
}
