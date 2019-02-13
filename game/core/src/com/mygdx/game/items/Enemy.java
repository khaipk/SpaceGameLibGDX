package com.mygdx.game.items;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.SpaceGame;
import com.mygdx.game.tools.CheckCollistion;

public class Enemy {
	
	CheckCollistion check;
	
	private Texture enemy1;
	public boolean remove = false;
	
	private static final int speed = 400;
	private int x1;
	private int y;
	private final int defaultY = 720;
	private static final int WIDTH = 43;
	private static final int HEIGHT = 43;
	
	public Enemy() {
		this.x1 = (int)(Math.random() * SpaceGame.WIDTH);
		this.y = defaultY;
		
		check = new CheckCollistion(x1, y, WIDTH, HEIGHT);
		
			enemy1 = new Texture("meteorBrown_med1.png");
		
	}
	
	public void update(float delta) {
		y -= speed * delta;
		if (y<0) remove = true;
		check.move(x1, y);
	}
	
	public void render(SpriteBatch batch) {
		batch.draw(enemy1, x1, y);
	}
	
	public CheckCollistion getEnemy() {
		return check;
	}
	
}
