package com.mygdx.game.items;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.SpaceGame;

public class Bullet {
	
	private Texture texture;
	private static final int speed = 500;
	
	private static final int defaultY = 40;
	private int x;
	private int y;
	
	public boolean remove = false;
	
	public Bullet(int x) {
		this.x = x;
		this.y = defaultY;
		
		
			texture = new Texture("laserBlue13.png");
		
	}
	
	public void update(float delta) {
		y += speed * delta;
		if (y > SpaceGame.HEIGHT) {
			remove = true;
		}
	}
	
	public void render(SpriteBatch batch) {
		batch.draw(texture, x, y);
	}
}
