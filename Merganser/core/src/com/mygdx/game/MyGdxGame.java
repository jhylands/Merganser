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
	Texture background;
	Duck duck;
	Duck duck2;
	BitmapFont myFont;

	float screenWidth;
	float screenHeight;
	
	@Override
	public void create() {
		batch = new SpriteBatch();
		background = new Texture("bio-lab-0.png");
		duck = new Duck();
		duck2 = new Duck();
		duck2.setDuckLocation(0, 0);

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
		duck2.getDuckMovement();
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(background, 0, 0);
		batch.draw(duck.getTexture(), duck.getDuckLocation().x, duck.getDuckLocation().y);
		batch.draw(duck2.getTexture(), duck2.getDuckLocation().x, duck2.getDuckLocation().y);
		myFont.draw(batch, "Score: " + duck.getScore(), screenWidth/2 - 5, screenHeight - 5);
		batch.end();
	}
}
