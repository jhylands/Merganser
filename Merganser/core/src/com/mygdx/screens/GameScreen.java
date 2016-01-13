package com.mygdx.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Map;
import com.mygdx.game.MyGdxGame;
import com.badlogic.gdx.graphics.Texture;

public class GameScreen implements Screen {

	private MyGdxGame game;
	private int barHeight = 14;
	private int barWidth = 100;

	public GameScreen(MyGdxGame game) {
		this.game = game;
	}

	private void move(Vector2 positionChange,int rotation, Map map){
		//create a tempry box to hold the new state
		Rectangle testBox = new Rectangle();
		//set the tempery box to the current box for the playerduck
		testBox.set(0,0,game.getDuck().getWidth(rotation),game.getDuck().getHeight(rotation));
		//add the movement to the tempery box
		testBox.setPosition(game.getDuck().getPosition().add(positionChange));
		if(map.validSpace(testBox, game.getDuck().isflying(), game.getDuck().canswim())){
			//update the playerduck's position with that of the temperybox
			game.getDuck().setPosition(testBox.getPosition(new Vector2()));
			game.getDuck().setRotation(rotation);}
		}
	
	private void handleInput(Map map) {
		if (Gdx.input.isKeyJustPressed(Keys.Q)){
			game.getDuck().quack();
		}
		if (Gdx.input.isKeyPressed(Keys.W)
				&& (game.getDuck().getPosition().y < (game.getScreenHeight() - 21 - game.getDuck().getSpriteHeight(0)))) {
			this.move(new Vector2(0,game.getDuck().getSpeed()), game.getDuck().UP, map);
		} else if (Gdx.input.isKeyPressed(Keys.S) && (game.getDuck().getPosition().y > 0)) {
			this.move(new Vector2(0,-(game.getDuck().getSpeed())), game.getDuck().DOWN, map);
		} else if (Gdx.input.isKeyPressed(Keys.A) && (game.getDuck().getPosition().x > 0)) {
			this.move(new Vector2(-(game.getDuck().getSpeed()),0), game.getDuck().LEFT, map);
		} else if (Gdx.input.isKeyPressed(Keys.D)
				&& (game.getDuck().getPosition().x < (game.getScreenWidth() - game.getDuck().getSpriteWidth(3)))) {
			this.move(new Vector2(game.getDuck().getSpeed(),0), game.getDuck().Right, map);
		} 
		
		else if (Gdx.input.isKeyPressed(Keys.RIGHT)
				&& (game.getDuck().getPosition().x < (game.getScreenWidth() - game.getDuck().getSpriteWidth(3)))) {
			game.getDuck().addScore(1);
		} else if (Gdx.input.isKeyPressed(Keys.LEFT)
				&& (game.getDuck().getPosition().x < (game.getScreenWidth() - game.getDuck().getSpriteWidth(3)))) {
			game.getDuck().addScore(-1);
		} else if (Gdx.input.isKeyPressed(Keys.UP)
				&& (game.getDuck().getPosition().x < (game.getScreenWidth() - game.getDuck().getSpriteWidth(3)))) {
			if (game.getDuck().getHealth() < game.getDuck().getMaxHealth()) {
				game.getDuck().setHealth(game.getDuck().getHealth() + 1);
			}
		} else if (Gdx.input.isKeyPressed(Keys.DOWN)
				&& (game.getDuck().getPosition().x < (game.getScreenWidth() - game.getDuck().getSpriteWidth(3)))) {
			if (game.getDuck().getHealth() > 0) {
				game.getDuck().setHealth(game.getDuck().getHealth() - 1);
			} // tests for health and score. use arrow keys.
		} else if (Gdx.input.isKeyPressed(Keys.NUM_9)) {
			game.getDuck().setStamina(game.getDuck().getStamina() - 1);
		} else if (Gdx.input.isKeyPressed(Keys.NUM_0)) {
			game.getDuck().setStamina(game.getDuck().getStamina() + 1);
		}
		else if(Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
			game.setScreen(new MainMenuScreen(game));
		}
		else if(Gdx.input.isKeyJustPressed(Keys.O)) {
			game.setScreen(new ObjectiveScreen(game));
		}
	}

	public void showStamina() {
		// Create a pixmap to draw new stamina bar from the current stamina
		// value
		Pixmap staminaBar = new Pixmap(barWidth, barHeight, Format.RGBA8888);
		staminaBar.setColor(0, 1, 0, 0.75f);
		staminaBar.drawRectangle(0, 0, barWidth, barHeight);
		staminaBar.fillRectangle(0, 0, game.getDuck().getStamina(), barHeight);
		// Export pixmap to texture
		game.setStam(null);
		game.setStam(new Texture(staminaBar));
		// Dispose of pixmap as no longer needed
		staminaBar.dispose();
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(float delta) {
		this.handleInput(game.getCurrentMap());
		game.setCurrentObjective(game.getCurrentObjective().isComplete(game.getCurrentMap()));
		game.setCurrentMap(game.getCurrentMap().managePortals(game.getDuck()));
		game.getBadies()[0].move(game.getDuck());
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		game.getBatch().begin();
		game.getBatch().draw(game.getMenu(), 0, game.getScreenHeight() - game.getMenu().getHeight());
		game.getMyFont().draw(game.getBatch(), String.format("%06d", game.getDuck().getScore()), game.getScreenWidth() / 2 - 72,
				game.getScreenHeight() - 6);
		game.getBatch().draw(game.getCurrentMap().getBackground(), 0, 0);
		game.getBatch().draw(game.getDuck().getTexture(), game.getDuck().getPosition().x, game.getDuck().getPosition().y);
		game.getBatch().draw(game.getBadies()[0].getTexture(), game.getBadies()[0].getPosition().x, game.getBadies()[0].getPosition().y);
		// Needs to pass numbers rather than textures
		game.getHeart().addTextures(game.getDuck().getHealth(), game.getDuck().getMaxHealth(), game.getBatch(), game.getScreenWidth(),
				game.getScreenHeight());
		showStamina();
		game.getBatch().draw(game.getStam(), 3, game.getScreenHeight() - 18);
		game.getBatch().end();
		game.getStam().dispose();

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
		this.dispose();
		game.getMyFont().dispose();
		game.getStam().dispose();
	}

}
