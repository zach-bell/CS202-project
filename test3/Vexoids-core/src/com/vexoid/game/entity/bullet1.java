package com.vexoid.game.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.vexoid.game.TextureManager;

public class bullet1 extends Entity {
	public static int spread;
	public static int speed;
	public bullet1(Vector2 pos) {
		super(TextureManager.BULLET1, pos, new Vector2(MathUtils.random(-spread,spread), -speed));
	}

	public void update() {
		pos.add(direction);
	}
	public void render(SpriteBatch sb){
		sb.draw(texture, pos.x, pos.y);
		
	}
	public boolean checkEnd() {
		return pos.y <= 0 -this.texture.getHeight();
	}
}
