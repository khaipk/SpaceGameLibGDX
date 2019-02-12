package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.SpaceGame;

public class MenuScreen implements Screen {

	private SpaceGame game;

	private Texture img1, img2;
	private int height = 100;
	private int width = 200;
	private int x = 150;
	private int y = 200;

	public MenuScreen(SpaceGame game) {
		this.game = game;
	}

	@Override
	public void show() {
		img1 = new Texture("1.bmp");
		img2 = new Texture("2.bmp");
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		game.batch.begin();

		game.batch.draw(img2, x, y, width, height);
		if(Gdx.input.getX()>x && Gdx.input.getX()<(x+width) &&
				(SpaceGame.HEIGHT - Gdx.input.getY()) > y && (SpaceGame.HEIGHT - Gdx.input.getY())<(y+height) ) {
			game.batch.draw(img1, x, y, width, height);{
				if(Gdx.input.isTouched()) {
					this.dispose();
					game.setScreen(new GameScreen(game));
				}
			}
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

	}

}
