package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.mygdx.sprite.PlayerDuck;
import com.mygdx.sprite.Repeatable;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Map currentMap;
	Heart heart;
	PlayerDuck duck;
	BitmapFont myFont;
	Texture menu;
	StaminaBar pbar;
	Repeatable[] badies;

	float screenWidth;
	float screenHeight;

	@Override
	public void create() {
		batch = new SpriteBatch();
		currentMap = new Map();
		currentMap.setBackground(new Texture("bio-lab-0.png"));
		duck = new PlayerDuck();
		heart = new Heart();
		menu = new Texture("GUI panel.png");
		pbar = new StaminaBar();
		badies = new Repeatable[1];
		badies[0] = new Repeatable(1);
		screenWidth = Gdx.graphics.getWidth();
		screenHeight = Gdx.graphics.getHeight();

		// FONT
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("COUR.ttf"));
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		parameter.size = 14;
		parameter.characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789.!'()>?:-";
		myFont = generator.generateFont(parameter);
		myFont.setColor(Color.WHITE);
		generator.dispose();
	}

	@Override
	public void render() {
		duck.getMovement();
		badies[0].move(duck);
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(menu, 0, screenHeight - menu.getHeight());
		myFont.draw(batch, String.format("%06d", duck.getScore()), screenWidth / 2 - 72, screenHeight - 6);
		batch.draw(currentMap.getBackground(), 0, 0);
		batch.draw(duck.getTexture(), duck.getPosition().x, duck.getPosition().y);
		batch.draw(badies[0].getTexture(), badies[0].getPosition().x, badies[0].getPosition().y);
		// Needs to pass numbers rather than textures
		heart.addTextures(duck.getHealth(), duck.getMaxHealth(), batch, screenWidth, screenHeight);
		
		batch.draw(pbar.getTexture(duck), 3, screenHeight - 18);
		batch.end();
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		super.dispose();
		batch.dispose();
	}
	
	
}
