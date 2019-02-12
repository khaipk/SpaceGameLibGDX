package com.mygdx.game.items;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.SpaceGame;

public class Enemy {
	
	private Texture enemy1;
	public boolean remove = false;
	
	private static final int speed = 500;
	private int x1;
	private int y;
	private final int defaultY = 720;
	
	public Enemy() {
		this.x1 = (int)(Math.random() * SpaceGame.WIDTH);
		this.y = defaultY;
		
			enemy1 = new Texture("meteorBrown_med1.png");
		
	}
	
	public void update(float delta) {
		y -= speed * delta;
		if (y<0) remove = true;
	}
	
	public void render(SpriteBatch batch) {
		batch.draw(enemy1, x1, y);
		
	}
	
}
