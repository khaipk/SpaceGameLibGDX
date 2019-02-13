package com.mygdx.game.screens;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.SpaceGame;
import com.mygdx.game.items.Blood;
import com.mygdx.game.items.Bullet;
import com.mygdx.game.items.Enemy;
import com.mygdx.game.tools.CheckCollistion;

public class GameScreen implements Screen{

	Texture img1;
	private int x = 200;
	private final int y = 50;
	private int Speed = 5;
	private int width = 112;
	private int height = 75;
	
	Random rand = new Random();
	private int score = 0;
	
	private static final float BULLET_RELOAD_TIME = 0.3f;
	private float bulletTime;
	
	private static final float MIN_ENEMY_TIME = 0.3f;
	private static final float MAX_ENEMY_TIME = 0.9f;
	private float enemyTime = rand.nextFloat() * (MAX_ENEMY_TIME - MIN_ENEMY_TIME) + MIN_ENEMY_TIME;
	
	private static final float MIN_BLOOD_TIME = 2f;
	private static final float MAX_BLOOD_TIME = 10f;
	private float bloodTime = rand.nextFloat()* (MAX_BLOOD_TIME-MIN_BLOOD_TIME) + MIN_BLOOD_TIME;
	

	private SpaceGame game;
	
	 ArrayList<Bullet> bullets;
	 
	 ArrayList<Enemy> enemies;
	 
	 ArrayList<Blood> bloods;
	 
	 Texture healthBar;
	 float health = 1;
	
	public GameScreen(SpaceGame game) {
		this.game = game;
		
		bloods = new ArrayList<Blood>();
		bullets = new ArrayList<Bullet>();
		enemies = new ArrayList<Enemy>();
	}
	
	@Override
	public void show() {
		img1 = new Texture("playerShip2_blue.png");
		healthBar = new Texture("shield3.png");
	}

	@Override
	public void render(float delta) {
		
		//creat blood
		bloodTime -= delta;
		if(bloodTime <0 ) {
			bloodTime = rand.nextFloat()* (MAX_BLOOD_TIME-MIN_BLOOD_TIME) + MIN_BLOOD_TIME;
			bloods.add(new Blood());
		}
		//update blood
		ArrayList<Blood>removeBlood = new ArrayList<Blood>();
		for(Blood blood: bloods) {
			blood.update(delta);
			if(blood.remove) {
				removeBlood.add(blood);
			}
		}
		bloods.removeAll(removeBlood);
		
		// creat bullet
		bulletTime += delta;
		if(Gdx.input.isKeyJustPressed(Keys.A) && bulletTime > BULLET_RELOAD_TIME) {
			bulletTime = 0;
			bullets.add(new Bullet(x + width/2));
		}
		
		//update bullets
		ArrayList<Bullet> removeBullet = new ArrayList<Bullet>();
		for(Bullet bullet: bullets) {
			bullet.update(delta);
			
			if(bullet.remove == true) {
				removeBullet.add(bullet);
			}
		}
		
		
		// creat enemy
		enemyTime -= delta;
		if( enemyTime <0) {
			enemyTime = rand.nextFloat() * (MAX_ENEMY_TIME - MIN_ENEMY_TIME) + MIN_ENEMY_TIME;
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
		
		// player
		CheckCollistion player = new CheckCollistion(x, y, width, height);
	
		// move
		if(Gdx.input.isKeyPressed(Keys.LEFT)) {
			x -= Speed;
			if(x < 0) x = 0;
		} else if(Gdx.input.isKeyPressed(Keys.RIGHT)) {
			x += Speed;
			if(x > SpaceGame.WIDTH - width)
				x = SpaceGame.WIDTH - width;
		}
		player.move(x, y);
		
		//after all update
		for(Bullet bullet: bullets) {
			for(Enemy enemy: enemies) {
				if(bullet.getBullet().checkCollistion(enemy.getEnemy())) {
					removeBullet.add(bullet);
					removeEnemy.add(enemy);
					score += 100;
					System.out.println(score);
				}
			}
		}
		
		for(Enemy enemy: enemies) {
			if(enemy.getEnemy().checkCollistion(player)) {
				removeEnemy.add(enemy);
				health -= 0.1;
			}
		}
		
		for(Blood blood: bloods) {
			if(blood.getBlood().checkCollistion(player)) {
				removeBlood.add(blood);
				health += 0.2f;
			}
		}
		
		bloods.removeAll(removeBlood);
		bullets.removeAll(removeBullet);
		enemies.removeAll(removeEnemy);
		
		//draw
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
		
		for(Blood blood: bloods) {
			blood.render(game.batch);
		}
		if (health > 0.6f)
		game.batch.setColor(Color.GREEN);
		else if (health >0.3)
			game.batch.setColor(Color.YELLOW);
		else 
			game.batch.setColor(Color.RED);
		
		game.batch.draw(healthBar, 0, 0, SpaceGame.WIDTH * health, 25);
		
		game.batch.setColor(Color.WHITE);
		
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
