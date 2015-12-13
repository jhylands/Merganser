package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.mygdx.sprite.PlayerDuck;
import com.mygdx.sprite.Repeatable;

public class MyGdxGame extends Game {
	SpriteBatch batch;
	Map currentMap;
	Heart heart;
	PlayerDuck duck;
	BitmapFont myFont;
	Texture menu, play, how, quit, stam;
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
		badies = new Repeatable[1];
		badies[0] = new Repeatable(1);
		screenWidth = Gdx.graphics.getWidth();
		screenHeight = Gdx.graphics.getHeight();
		
		how = new Texture(Gdx.files.internal("Text/How To.png"), true);
		play = new Texture(Gdx.files.internal("Text/Play.png"), true);
		quit = new Texture(Gdx.files.internal("Text/Quit.png"), true);

		// FONT
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("COUR.ttf"));
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		parameter.size = 14;
		parameter.characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789.!'()>?:-";
		myFont = generator.generateFont(parameter);
		myFont.setColor(Color.WHITE);
		generator.dispose();
		
		this.setScreen(new MainMenuScreen(this));
	}

	@Override
	public void render() {
		super.render();
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		super.dispose();
		batch.dispose();
	}
	
	
}
