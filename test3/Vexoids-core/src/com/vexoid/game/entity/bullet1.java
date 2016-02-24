package com.vexoid.game.entity;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.vexoid.game.MainGame;
import com.vexoid.game.TextureManager;

public class bullet1 extends Entity {

	public bullet1(Vector2 pos) {
		super(TextureManager.BULLET1, pos, new Vector2(MathUtils.random(-3,3), -8));
	}

	public void update() {
		pos.add(direction);
		
	}
	
	public boolean checkEnd() {
		return pos.y >= MainGame.HEIGHT;
	}
}
