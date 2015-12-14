package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;

public class GameScreen implements Screen {

	private MyGdxGame game;
	private int barHeight = 14;
	private int barWidth = 100;

	public GameScreen(MyGdxGame game) {
		this.game = game;
	}

	private void handleInput() {
		if (Gdx.input.isKeyPressed(Keys.W)
				&& (game.duck.getPosition().y < (game.screenHeight - 21 - game.duck.getSpriteHeight(0)))) {
			game.duck.incPosition(0, game.duck.getSpeed());
			game.duck.setRotation(0);
		} else if (Gdx.input.isKeyPressed(Keys.S) && (game.duck.getPosition().y > 0)) {
			game.duck.incPosition(0, -(game.duck.getSpeed()));
			game.duck.setRotation(1);
		} else if (Gdx.input.isKeyPressed(Keys.A) && (game.duck.getPosition().x > 0)) {
			game.duck.incPosition(-game.duck.getSpeed(), 0);
			game.duck.setRotation(2);
		} else if (Gdx.input.isKeyPressed(Keys.D)
				&& (game.duck.getPosition().x < (game.screenWidth - game.duck.getSpriteWidth(3)))) {
			game.duck.incPosition(game.duck.getSpeed(), 0);
			game.duck.setRotation(3);
		} else if (Gdx.input.isKeyPressed(Keys.RIGHT)
				&& (game.duck.getPosition().x < (game.screenWidth - game.duck.getSpriteWidth(3)))) {
			game.duck.addScore(1);
		} else if (Gdx.input.isKeyPressed(Keys.LEFT)
				&& (game.duck.getPosition().x < (game.screenWidth - game.duck.getSpriteWidth(3)))) {
			game.duck.addScore(-1);
		} else if (Gdx.input.isKeyPressed(Keys.UP)
				&& (game.duck.getPosition().x < (game.screenWidth - game.duck.getSpriteWidth(3)))) {
			if (game.duck.getHealth() < game.duck.getMaxHealth()) {
				game.duck.setHealth(game.duck.getHealth() + 1);
			}
		} else if (Gdx.input.isKeyPressed(Keys.DOWN)
				&& (game.duck.getPosition().x < (game.screenWidth - game.duck.getSpriteWidth(3)))) {
			if (game.duck.getHealth() > 0) {
				game.duck.setHealth(game.duck.getHealth() - 1);
			} // tests for health and score. use arrow keys.
		} else if (Gdx.input.isKeyPressed(Keys.NUM_9)) {
			game.duck.setStamina(game.duck.getStamina() + 1);
		} else if (Gdx.input.isKeyPressed(Keys.NUM_0)) {
			game.duck.setStamina(game.duck.getStamina() - 1);
		}
	}

	public void showStamina() {
		// Create a pixmap to draw new stamina bar from the current stamina
		// value
		Pixmap staminaBar = new Pixmap(barWidth, barHeight, Format.RGBA8888);
		staminaBar.setColor(0, 1, 0, 0.75f);
		staminaBar.drawRectangle(0, 0, barWidth, barHeight);
		staminaBar.fillRectangle(0, 0, game.duck.getStamina(), barHeight);
		// Export pixmap to texture
		game.stam = null;
		game.stam = new Texture(staminaBar);
		// Dispose of pixmap as no longer needed
		staminaBar.dispose();
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(float delta) {
		this.handleInput();
		game.badies[0].move(game.duck);
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		game.batch.begin();
		game.batch.draw(game.menu, 0, game.screenHeight - game.menu.getHeight());
		game.myFont.draw(game.batch, String.format("%06d", game.duck.getScore()), game.screenWidth / 2 - 72,
				game.screenHeight - 6);
		game.batch.draw(game.currentMap.getBackground(), 0, 0);
		game.batch.draw(game.duck.getTexture(), game.duck.getPosition().x, game.duck.getPosition().y);
		game.batch.draw(game.badies[0].getTexture(), game.badies[0].getPosition().x, game.badies[0].getPosition().y);
		// Needs to pass numbers rather than textures
		game.heart.addTextures(game.duck.getHealth(), game.duck.getMaxHealth(), game.batch, game.screenWidth,
				game.screenHeight);
		showStamina();
		game.batch.draw(game.stam, 3, game.screenHeight - 18);
		game.batch.end();
		game.stam.dispose();

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
		game.myFont.dispose();
		game.stam.dispose();
	}

}
