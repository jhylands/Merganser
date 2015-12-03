package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.mygdx.sprite.Duck;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Map currentMap;
	Heart heart; 
	Duck duck;
	BitmapFont myFont;
	Texture menu;

	float screenWidth;
	float screenHeight;
	
	@Override
	public void create() {
		batch = new SpriteBatch();
		currentMap = new Map();
		currentMap.setBackground(new Texture("bio-lab-0.png"));
		duck = new Duck();
		heart = new Heart();
		menu = new Texture("GUI panel.png");

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
		duck.getDuckMovement();
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(menu, 0, screenHeight-menu.getHeight());
		myFont.draw(batch, String.format("%06d", duck.getScore()), screenWidth/2 - 72, screenHeight - 6);
		batch.draw(currentMap.getBackground(), 0, 0);
		batch.draw(duck.getTexture(), duck.getDuckLocation().x, duck.getDuckLocation().y);
		for (int i = 0; i < heart.getTexture(duck.getHealth(), duck.getMaxHealth()).size(); i++){
			batch.draw(heart.getTexture(duck.getHealth(), duck.getMaxHealth()).get(i), screenWidth - 20 - i*heart.getTexture(duck.getHealth(), duck.getMaxHealth()).get(i).getWidth(), screenHeight - heart.getTexture(duck.getHealth(), duck.getMaxHealth()).get(i).getHeight() - 2);
		}
		batch.end();
	}
}
