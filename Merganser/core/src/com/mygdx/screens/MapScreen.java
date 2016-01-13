package com.mygdx.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.mygdx.game.MyGdxGame;

public class MapScreen implements Screen {

	private MyGdxGame game;
	private int currentMap;
	private SpriteBatch sb1;

	public MapScreen(MyGdxGame game) {
		this.game = game;
		this.currentMap = 0;
		create();
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	public void handleInput(){
		if (Gdx.input.isKeyJustPressed(Keys.LEFT)) {
			if (currentMap == 1) {
				currentMap--;
			}
		}

		else if (Gdx.input.isKeyJustPressed(Keys.RIGHT)) {
			if (currentMap == 0) {
				currentMap++;
			}
		}
		
		else if(Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
			game.setScreen(new MainMenuScreen(game));
		}
		
		else if(Gdx.input.isKeyJustPressed(Keys.M)) {
			game.setScreen(game.mainGame);
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
		if (currentMap == 0){
			sb1.draw(game.getAssetManager().get("map1.png", Texture.class), 0, 0);
		}
		else if (currentMap == 1){
			sb1.draw(game.getAssetManager().get("map2.png", Texture.class), 0, 0);
		}
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
		game.getMyFont().dispose();
		game.getStam().dispose();
		sb1.dispose();
	}

}
