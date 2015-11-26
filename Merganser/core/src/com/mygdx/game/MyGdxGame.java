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

	float screenWidth;
	float screenHeight;
	
	@Override
	public void create() {
		batch = new SpriteBatch();
		currentMap = new Map();
		currentMap.setBackground(new Texture("bio-lab-0.png"));
		duck = new Duck();
		heart = new Heart();

		screenWidth = Gdx.graphics.getWidth();
		screenHeight = Gdx.graphics.getHeight();

		// FONT
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("COUR.ttf"));
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		parameter.size = 12;
		parameter.characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789.!'()>?:";
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
		batch.draw(currentMap.getBackground(), 0, 0);
		batch.draw(duck.getTexture(), duck.getDuckLocation().x, duck.getDuckLocation().y);
		//System.out.println(heart.getTexture(duck.getHealth()).size());
		// 
		for (int i = 0; i < heart.getTexture(duck.getHealth()).size(); i++){
			batch.draw(heart.getTexture(duck.getHealth()).get(i), screenWidth - 20 - i*heart.getTexture(duck.getHealth()).get(i).getWidth(), screenHeight - heart.getTexture(duck.getHealth()).get(i).getHeight() - 2);
		}
		myFont.draw(batch, "Score: " + duck.getScore(), screenWidth/2 - 25, screenHeight - 5);
		batch.end();
	}
}
