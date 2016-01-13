package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

public class ObjectiveScreen implements Screen {

	private MyGdxGame game;
	private BitmapFont objFont;
	private SpriteBatch sb;
	private String screenTitle;

	public ObjectiveScreen(MyGdxGame game) {
		this.game = game;
		create();
	}

	private void create() {
		// TODO Auto-generated method stub
		sb = new SpriteBatch();
		screenTitle = "Objectives:";
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("COUR.ttf"));
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		parameter.size = 25;
		parameter.characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789.!'()>?:-";

		objFont = generator.generateFont(parameter);
		objFont.setColor(Color.WHITE);

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
		
		objFont.draw(sb, screenTitle, objFont.getSpaceWidth() * screenTitle.length() , 220);

		for (int i = 0; i < game.objective.length; i++) {
			objFont.draw(sb, game.objective[i],
					(game.screenWidth - (objFont.getSpaceWidth() * game.objective[i].length())) / 2, 180 - 35 * i);
		}

		sb.end();

	}

	public void handleInput() {
		if (Gdx.input.isKeyJustPressed(Keys.O)) {
			game.setScreen(game.mainGame);
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
		sb.dispose();
	}

}
