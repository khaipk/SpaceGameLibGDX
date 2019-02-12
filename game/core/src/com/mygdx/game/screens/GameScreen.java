package com.mygdx.game.screens;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.SpaceGame;
import com.mygdx.game.items.Bullet;
import com.mygdx.game.items.Enemy;

public class GameScreen implements Screen{

	Texture img1;
	private int x = 200;
	private final int y = 50;
	private int Speed = 5;
	private int width = 112;
	private int height = 75;

	private SpaceGame game;
	
	 ArrayList<Bullet> bullets;
	 
	 ArrayList<Enemy> enemies;
	
	public GameScreen(SpaceGame game) {
		this.game = game;
		
		bullets = new ArrayList<Bullet>();
		enemies = new ArrayList<Enemy>();
	}
	
	@Override
	public void show() {
		img1 = new Texture("playerShip2_blue.png");
	}

	@Override
	public void render(float delta) {
		
		// creat bullet
		if(Gdx.input.isKeyJustPressed(Keys.A)) {
			bullets.add(new Bullet(x+4));
			bullets.add(new Bullet(x+width-4));
		}
		
		//update bullets
		ArrayList<Bullet> removeBullet = new ArrayList<Bullet>();
		for(Bullet bullet: bullets) {
			bullet.update(delta);
			if(bullet.remove == true) {
				removeBullet.add(bullet);
			}
		}
		bullets.removeAll(removeBullet);
		
		// creat enemy
		if(Gdx.input.isKeyJustPressed(Keys.S)) {
			enemies.add(new Enemy());
		}
		//update enemy
		ArrayList<Enemy> removeEnemy = new ArrayList<Enemy>();
		for(Enemy enemy : enemies) {
			enemy.update(delta);
			if(enemy.remove) {
				removeEnemy.add(enemy);
			}
		}
		enemies.removeAll(removeEnemy);
		
		/*//enemy
		if(Gdx.input.isKeyPressed(Keys.Q)) {
			bullets.add(new Enemy());
		}
		//enemy update
		ArrayList<Enemy> removeEnemy = new ArrayList<Enemy>();
		for(Enemy enemy: bullets) {
			enemy.update(delta);
			if(enemy.remove) {
				removeEnemy.add(enemy);
			}
		}
		enemies.removeAll(removeEnemy);
		//bullet
		if(Gdx.input.isKeyJustPressed(Keys.A)) {
			enemies.add(new Bullet(x+4));
			enemies.add(new Bullet(x+width-4));
		}
		//update bullet
		ArrayList<Bullet> removeBullet = new ArrayList<Bullet>();
		for(Bullet bullet: enemies) {
			bullet.update(delta);
			if(bullet.remove) {
				removeBullet.add(bullet);
			}
		}
		enemies.removeAll(removeBullet);*/
		
		// move
		if(Gdx.input.isKeyPressed(Keys.LEFT)) {
			x -= Speed;
			if(x < 0) x = 0;
		} else if(Gdx.input.isKeyPressed(Keys.RIGHT)) {
			x += Speed;
			if(x > SpaceGame.WIDTH - width)
				x = SpaceGame.WIDTH - width;
		}
		
		Gdx.gl.glClearColor(0.2f, 0.3f, 0.5f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		
		game.batch.begin();
		
		game.batch.draw(img1, x, y, width, height);
		
		for(Enemy enemy: enemies) {
			enemy.render(game.batch);
		}
		
		for(Bullet bullet: bullets) {
			bullet.render(game.batch);
		}
		
		game.batch.end();
	}

	@Override
	public void resize(int width, int height) {
		
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void hide() {
		
	}

	@Override
	public void dispose() {
		game.batch.dispose();
		img1.dispose();
	}

}
