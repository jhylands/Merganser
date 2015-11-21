package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.math.Vector2;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture background;
	Texture duck;
	BitmapFont myFont;

	float screenWidth;
	float screenHeight;
	Vector2 duckLocation = new Vector2(0, 0);
	int duckSpeed = 2;

	@Override
	public void create() {
		batch = new SpriteBatch();
		background = new Texture("bio-lab-0.png");
		duck = new Texture("large_duck.png");

		screenWidth = Gdx.graphics.getWidth();
		screenHeight = Gdx.graphics.getHeight();

		duckLocation = new Vector2(20, (screenHeight / 2) - (duck.getHeight() / 2));

		// FONT
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("COUR.ttf"));
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		parameter.size = 12;
		parameter.characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789.!'()>?:";
		myFont = generator.generateFont(parameter);
		myFont.setColor(Color.BLACK);
		generator.dispose();
	}

	public void update() {
		if (Gdx.input.isKeyPressed(Keys.W) && (duckLocation.y < (screenHeight - duck.getHeight()))) {
			duckLocation.y += duckSpeed;
			// System.out.println(shipLocation.y);
		}

		if (Gdx.input.isKeyPressed(Keys.S) && (duckLocation.y > 0)) {
			duckLocation.y -= duckSpeed;
		}

		if (Gdx.input.isKeyPressed(Keys.A) && (duckLocation.x > 0)) {
			duckLocation.x -= duckSpeed;
		}

		if (Gdx.input.isKeyPressed(Keys.D) && (duckLocation.x < (screenWidth - duck.getWidth()))) {
			duckLocation.x += duckSpeed;
		}
	}

	@Override
	public void render() {
		update();
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(background, 0, 0);
		batch.draw(duck, duckLocation.x, duckLocation.y);
		myFont.draw(batch, "Use W,S,A,D to move the Duck around the screen", 0, 30);
		batch.end();
	}
}
