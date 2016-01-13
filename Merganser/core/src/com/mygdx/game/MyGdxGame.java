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
	Texture menu, stam, globmap1, globmap2;
	Repeatable[] badies;
	GameScreen mainGame;
	String[] objective = new String[]{"defeat10", "score500"};
	Map[] maps;
	float screenWidth;
	float screenHeight;
	Objective currentObjective; 

	@Override
	public void create() {
		batch = new SpriteBatch();
		maps = this.mapGeneration();
		currentMap = maps[0];
		duck = new PlayerDuck();
		heart = new Heart();
		menu = new Texture("GUI panel.png");
		globmap1 = new Texture("map1.png");
		globmap2 = new Texture("map2.png");
		badies = new Repeatable[1];
		badies[0] = new Repeatable(1);
		screenWidth = Gdx.graphics.getWidth();
		screenHeight = Gdx.graphics.getHeight();
		
		Objective objective1 = new Objective(this, maps[1], "Go outside", 1000);
		Objective objective2 = new Objective(this, maps[0], "Go inside", 1000);
		objective1.setNextObjective(objective2);
		objective2.setNextObjective(objective1);
		
		currentObjective = objective1;
		
		// FONT
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("COUR.ttf"));
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		parameter.size = 14;
		parameter.characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789.!'()>?:-";
		myFont = generator.generateFont(parameter);
		myFont.setColor(Color.WHITE);
		generator.dispose();
		mainGame = new GameScreen(this);
		
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
	public Map[] mapGeneration(){
		Map[] maps = new Map[2];
		//map1
		MapFeature[] features = new MapFeature[1];
		features[0] = new MapFeature(new Rectangle().set(1000,1000,0,0),true,false,false);
		Portal[] portals = new Portal[1];
		portals[0] = new Portal(new Rectangle().set(100, 100, 100, 100),null);
		maps[0]= new Map(new Texture("bio-lab-1.png"),features,portals);
		//map2
		MapFeature[] features2 = new MapFeature[1];
		features2[0] = new MapFeature(new Rectangle().set(1000,1000,50,100),true,false,false);
		Portal[] portals2 = new Portal[1];
		portals2[0] = new Portal(new Rectangle().set(0,0,100,100),maps[0]);
		maps[1] = new Map(new Texture("bio-lab-0.png"),features2,portals2);
		
		maps[0].setPortalExit(0, maps[1]);
		
		return maps;
		//Rectangle[] a = new Rectangle[1];
		//a[0] = new Rectangle()
		//map.setPortals([new Rectangle() ])
	}
	
}
