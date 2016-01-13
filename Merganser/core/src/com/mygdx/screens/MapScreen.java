package com.mygdx.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.MyGdxGame;

public class MapScreen implements Screen {

	private MyGdxGame game;
	private int currentGlobalMap;
	private Vector2 globalMapPosition;
	private SpriteBatch sb1;

	public MapScreen(MyGdxGame game) {
		this.game = game;
		this.currentGlobalMap = 0;
		this.globalMapPosition = game.currentMap.getGlobalPosition();
		create();
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	public void handleInput() {
		if (Gdx.input.isKeyJustPressed(Keys.LEFT)) {
			if (currentGlobalMap == 1) {
				currentGlobalMap--;
			}
		}

		else if (Gdx.input.isKeyJustPressed(Keys.RIGHT)) {
			if (currentGlobalMap == 0) {
				currentGlobalMap++;
			}
		}

		else if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
			game.setScreen(new MainMenuScreen(game));
		}

		else if (Gdx.input.isKeyJustPressed(Keys.M)) {
			game.setScreen(game.getMainGame());
		}

	}

	public void create() {
		sb1 = new SpriteBatch();
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		handleInput();
		sb1.begin();
		
		// Testing of different maps
		if (currentGlobalMap == 0) {
			sb1.draw(game.getAssetManager().get("map1.png", Texture.class), 0, 0);
		} else if (currentGlobalMap == 1) {
			sb1.draw(game.getAssetManager().get("map2.png", Texture.class), 0, 0);
		}

		sb1.draw(game.getAssetManager().get("large_duck.png", Texture.class), globalMapPosition.x, globalMapPosition.y);

		sb1.end();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		sb1.dispose();
	}

}
