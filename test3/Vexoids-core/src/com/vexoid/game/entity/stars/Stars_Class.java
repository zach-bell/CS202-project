package com.vexoid.game.entity.stars;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.vexoid.game.TextureManager;

public class Stars_Class{
	int internalClock = 0;
	boolean timeUp = false;
	protected Texture texture;
	protected Vector2 pos;
	protected Vector2 direction;
	
	public Stars_Class(Vector2 pos, Vector2 direction) {
		this.texture = getTexture();
		this.direction = direction;
		this.pos = pos;
	}
	
	public static Texture getTexture(){
		int text = MathUtils.random(1,9);
		if(text==1)
			return TextureManager.STAR1;
		else
		if(text==2)
			return TextureManager.STAR2;
		else
		if(text==3)
			return TextureManager.STAR3;
		else
		if(text==4)
			return TextureManager.STAR4;
		else
		if(text==5)
			return TextureManager.STAR5;
		else
		if(text==6)
			return TextureManager.STAR6;
		else
		if(text==7)
			return TextureManager.STAR7;
		else
		if(text==8)
			return TextureManager.STARS;
		else
		if(text==9)
			return TextureManager.STAR8;
		else
			return TextureManager.STAR1;
	}
	public void update() {
		pos.add(direction);
	}
	public void render(SpriteBatch sb) {
		sb.draw(texture, pos.x, pos.y);
	}
	public void setDirection(float x, float y) {
		direction.set(x, y);
		direction.scl(Gdx.graphics.getDeltaTime());
	}
	public boolean checkEnd() {
		return pos.y <= 0-texture.getHeight();
	}
}
