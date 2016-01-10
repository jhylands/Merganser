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
import com.badlogic.gdx.math.Rectangle;

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
		currentMap = this.mapGeneration();
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
	public Map mapGeneration(){
		//map1
		MapFeature[] features = new MapFeature[1];
		features[0] = new MapFeature(new Rectangle().set(1000,1000,0,0),true,false,false);
		Portal[] portals = new Portal[1];
		portals[0] = new Portal(new Rectangle().set(100, 100, 100, 100),null);
		Map map = new Map(new Texture("bio-lab-1.png"),features,portals);
		//map2
		MapFeature[] features2 = new MapFeature[1];
		features2[0] = new MapFeature(new Rectangle().set(1000,1000,50,100),true,false,false);
		Portal[] portals2 = new Portal[1];
		portals2[0] = new Portal(new Rectangle().set(0,0,100,100),map);
		Map map2 = new Map(new Texture("bio-lab-0.png"),features2,portals2);
		
		map.setPortalExit(0, map2);
		
		return map;
		//Rectangle[] a = new Rectangle[1];
		//a[0] = new Rectangle()
		//map.setPortals([new Rectangle() ])
	}
	
}
