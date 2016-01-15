package com.mygdx.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.mygdx.game.MyGdxGame;

public class CompleteEndScreen implements Screen {
	
	private SpriteBatch sb;
	private BitmapFont endFont, menuFont;
	private MyGdxGame game;
	private String[] menuItem;
	private String GAMECOMPLETE = "Game Complete!"; 
	private int currentMenuItem;
	
	public CompleteEndScreen(MyGdxGame game){
		this.game = game;
		create();
	}

	public void create(){
		sb = new SpriteBatch();
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("COUR.ttf"));
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		parameter.size = 50;
		parameter.characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789.!'()>?:-";
		FreeTypeFontParameter parameter1 = new FreeTypeFontParameter();
		parameter1.size = 25;
		parameter1.characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789.!'()>?:-|";

		endFont = generator.generateFont(parameter);
		menuFont = generator.generateFont(parameter1);
		endFont.setColor(Color.WHITE);

		menuItem = new String[] { "Score: " + game.duck.getScore(), "Quit" };

		generator.dispose();
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		handleInput();
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		sb.begin();

		endFont.draw(sb, GAMECOMPLETE, (game.getScreenWidth() - (endFont.getSpaceWidth() * GAMECOMPLETE.length())) / 2, 240);

		for (int i = 0; i < menuItem.length; i++) {
			if (currentMenuItem == i) {
				menuFont.setColor(Color.RED);
			} else {
				menuFont.setColor(Color.WHITE);
			}
			menuFont.draw(sb, menuItem[i], (game.getScreenWidth() - (menuFont.getSpaceWidth() * menuItem[i].length()))/ 2, 180 - 35 * i);
		}

		sb.end();

	}
	
	public void handleInput() {
		if (Gdx.input.isKeyJustPressed(Keys.UP)) {
			if (currentMenuItem > 0) {
				currentMenuItem--;
			}
		}

		if (Gdx.input.isKeyJustPressed(Keys.DOWN)) {
			if (currentMenuItem < menuItem.length - 1) {
				currentMenuItem++;
			}
		}

		if (Gdx.input.isKeyJustPressed(Keys.ENTER)) {
			switch (currentMenuItem) {
			case 0:
				break;
			case 1:
				Gdx.app.exit();
				break;
			}
		}

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
		endFont.dispose();
		sb.dispose();
	}

}
