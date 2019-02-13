package com.mygdx.game.items;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.SpaceGame;
import com.mygdx.game.tools.CheckCollistion;

public class Bullet {
	
	private CheckCollistion Check;
	
	private Texture texture;
	private static final int speed = 300;
	
	private static final int defaultY = 40;
	private int x;
	private int y;
	
	private static final int WIDTH = 13;
	private static final int HEIGHT = 45;
	
	public boolean remove = false;
	
	public Bullet(int x) {
		this.x = x;
		this.y = defaultY;
		
		this.Check = new CheckCollistion(x, y, WIDTH, HEIGHT);
		
			texture = new Texture("laserBlue13.png");
		
	}
	
	public void update(float delta) {
		y += speed * delta;
		if (y > SpaceGame.HEIGHT) {
			remove = true;
		}
		Check.move(x, y);
	}
	
	public void render(SpriteBatch batch) {
		batch.draw(texture, x, y);
	}
	
	public CheckCollistion getBullet() {
		return Check;
	}
}
