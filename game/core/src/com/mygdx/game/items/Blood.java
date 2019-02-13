package com.mygdx.game.items;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.tools.CheckCollistion;

public class Blood {
	
	private Texture blood;
	private static final int speed = 500;
	public boolean remove = false;
	
	private int x;
	private int y;
	private static final int width = 100;
	private static final int height =50;
	private static final int default_y = 720;
	private static final int default_x = 480;
	
	public Blood() {
		this.x = (int)(Math.random()* (default_x-width));
		this.y = default_y;
		
		blood = new Texture("enemyBlack1.png");
	}
	
	public void update(float delta) {
		y -= speed*delta;
		if (y<0) remove = true;
	}
	
	public void render(SpriteBatch batch) {
		batch.draw(blood, x, y, width, height);
	}
	
	public CheckCollistion getBlood() {
		return new CheckCollistion(x, y, width, height);
	}
}
