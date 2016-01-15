package com.mygdx.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Map;
import com.mygdx.game.MyGdxGame;

public class GameScreen implements Screen {

	private MyGdxGame game;

	private SpriteBatch batch;

	public GameScreen(MyGdxGame game) {
		this.game = game;
		create();
	}

	public void create() {
		batch = new SpriteBatch();
	}

	private void handleInput(Map map) {

		// quack
		if (Gdx.input.isKeyJustPressed(Keys.Q)) {
			game.duck.quack();
		}

		// duck movement
		if (Gdx.input.isKeyPressed(Keys.W)) {
			game.duck.moveIfValid(game.duck.UP, game.getCurrentMap());
		} else if (Gdx.input.isKeyPressed(Keys.S)) {
			game.duck.moveIfValid(game.duck.DOWN, game.getCurrentMap());
		} else if (Gdx.input.isKeyPressed(Keys.A)) {
			game.duck.moveIfValid(game.duck.LEFT, game.getCurrentMap());
		} else if (Gdx.input.isKeyPressed(Keys.D)) {
			game.duck.moveIfValid(game.duck.RIGHT, game.getCurrentMap());
		} else if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
			game.setScreen(game.getMainMenu());
		}

		// If O pressed then go to objective screen
		if (Gdx.input.isKeyJustPressed(Keys.O)) {
			game.setScreen(game.getObjScreen());
		}

		// If M pressed then go to Map Screen
		if (Gdx.input.isKeyJustPressed(Keys.M)) {
			game.setScreen(game.getMapScreen());
		}

		if (Gdx.input.isKeyPressed(Keys.SHIFT_LEFT)) {
			if (!game.duck.atMinStam()) {
				game.duck.setSpeed(game.duck.getDUCKSPRINT());
				game.duck.setStamina(game.duck.getStamina() - 2);
			}
			if (game.duck.atMinStam()) {
				game.duck.setSpeed(game.duck.getDUCKSPEED());
			}
		}

		if (!Gdx.input.isKeyPressed(Keys.SHIFT_LEFT) && (game.duck.getSpeed() == game.duck.getDUCKSPRINT())) {
			game.duck.setSpeed(game.duck.getDUCKSPEED());
			game.duck.setStamina(game.duck.getStamina() + 1);
		} else if (!Gdx.input.isKeyPressed(Keys.SHIFT_LEFT) && !game.duck.atMaxStam()) {
			game.duck.setStamina(game.duck.getStamina() + 1);
		}

		// control of other features for testing
		// inputTests();
	}

	// function contsining keys that are not required in the game but are used
	// for other testing

	private void inputTests() {
		if (Gdx.input.isKeyPressed(Keys.RIGHT)
				&& (game.duck.getPosition().x < (game.getScreenWidth() - game.duck.getWidth(3)))) {
			game.duck.addScore(1);
		} else if (Gdx.input.isKeyPressed(Keys.LEFT)
				&& (game.duck.getPosition().x < (game.getScreenWidth() - game.duck.getWidth(3)))) {
			game.duck.addScore(-1);
		} else if (Gdx.input.isKeyPressed(Keys.UP)
				&& (game.duck.getPosition().x < (game.getScreenWidth() - game.duck.getWidth(3)))) {
			if (game.duck.getHealth() < game.duck.getMaxHealth()) {
				//game.duck.setHealth(game.duck.getHealth() + 1);
			}
		} else if (Gdx.input.isKeyPressed(Keys.DOWN)
				&& (game.duck.getPosition().x < (game.getScreenWidth() - game.duck.getWidth(3)))) {
			if (game.duck.getHealth() > 0) {
				//game.duck.setHealth(game.duck.getHealth() - 1);
			}
			// tests for health and score. use arrow keys.
		} else if (Gdx.input.isKeyPressed(Keys.NUM_9)) {
			game.duck.setStamina(game.duck.getStamina() - 1);
		} else if (Gdx.input.isKeyPressed(Keys.NUM_0)) {
			game.duck.setStamina(game.duck.getStamina() + 1);
		}

	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	/**
	 * function called by the main render loop of LIB GDX when the game screen
	 * is the screen being shown.
	 */
	public void render(float delta) {
		// update the game state
		this.update();

		// begine the drawing element
		batch.begin();
		// batch passed by reference (because its a class)
		this.draw(batch);
		// end the drawer element
		batch.end();

	}

	/**
	 * function to update the game state
	 */
	private void update() {
		// handle the input
		this.handleInput(game.getCurrentMap());
		// set the current objective
		game.setCurrentObjective(game.getCurrentObjective().updateObjective());
		// set current map
		game.setCurrentMap(game.getCurrentMap().managePortals(game.duck));
		
		// Checks to see if last objective complete.
		// If complete then go to Game Complete screen
		if(game.isLastObjComplete()){
			game.setScreen(game.getGameCompleteScreen());
		}

		// include the baddies that need to be included in the game
		game.getCurrentMap().updateEnemies(game.duck);

		// manage interation between baddies and the duck
	}

	/**
	 * Function to draw the sprites and text to the game screen
	 * 
	 * @param batch
	 *            batch is passed by reference (because its a class) so function
	 *            doesn't need to return batch
	 */
	private void draw(SpriteBatch batch) {
		// draw the GUI
		batch.draw(game.getAssetManager().get("GUI panel.png", Texture.class), 0,
				game.getScreenHeight() - game.getAssetManager().get("GUI panel.png", Texture.class).getHeight());
		// draw the score
		game.getMyFont().draw(batch, String.format("%06d", game.duck.getScore()), game.getScreenWidth() / 2 - 72,
				game.getScreenHeight() - 6);
		// draw the map background (and the maps enemies)
		game.getCurrentMap().draw(batch);
		// draw the duck texture
		batch.draw(game.duck.getTexture(), game.duck.getPosition().x, game.duck.getPosition().y);
		// draw health
		// Needs to pass numbers rather than textures
		game.getHeart().addTextures(game.duck.getHealth(), game.duck.getMaxHealth(), batch, game.getScreenWidth(),
				game.getScreenHeight());
		// draw stamina
		game.getStamina().draw(batch, game);
		// draw new objective
		if (game.isNewObjective()) {
			batch.draw(game.getAssetManager().get("objective.png", Texture.class), 350,
					game.getScreenHeight() - game.getAssetManager().get("objective.png", Texture.class).getHeight());
		}
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		batch.dispose();
		game.getMyFont().dispose();
	}
	
	// Functions required by Screen implementation but not needed
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


}
