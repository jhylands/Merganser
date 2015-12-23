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
import com.mygdx.sprite.PlayerDuck;
import com.mygdx.sprite.Repeatable;
import com.badlogic.gdx.math.Rectangle;

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
		currentMap = this.mapGeneration();
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
		//move playerduck
		duck.getMovement(currentMap);
		
		//check if the map should be updated
		currentMap = currentMap.managePortals(duck);
		
		//move enmeyduck
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
