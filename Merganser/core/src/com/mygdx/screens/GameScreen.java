package com.mygdx.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Map;
import com.mygdx.game.MyGdxGame;

/**
 * Screen for displaying the game
 */
public class GameScreen implements Screen {
	/**
	 * Game to allow reference to game state
	 */
	private MyGdxGame game;

	/**
	 * SpriteBatch for drawing textures
	 */
	private SpriteBatch batch;

	/**
	 * Constructor for GameScreen Calls create() method
	 * 
	 * @param game
	 *            Current game state (allows reference to PlayerDuck score)
	 */
	public GameScreen(MyGdxGame game) {
		this.game = game;
		create();
	}

	/**
	 * Set up spriteBatch Assigns duck resources from assetManager so it can be
	 * rendered
	 */
	public void create() {
		batch = new SpriteBatch();
		game.duck.assignResources(game.getAssetManager());
	}

	/**
	 * Handles any user input for defined keys
	 */
	private void handleInput() {

		// Play quack audio if Q pressed
		if (Gdx.input.isKeyJustPressed(Keys.Q)) {
			game.duck.quack();
		}

		// Input for duck movement.
		// Checks whether move is valid for current map position
		if (Gdx.input.isKeyPressed(Keys.W)) {
			game.duck.moveIfValid(game.duck.UP, game.getCurrentMap());
		} else if (Gdx.input.isKeyPressed(Keys.S)) {
			game.duck.moveIfValid(game.duck.DOWN, game.getCurrentMap());
		} else if (Gdx.input.isKeyPressed(Keys.A)) {
			game.duck.moveIfValid(game.duck.LEFT, game.getCurrentMap());
		} else if (Gdx.input.isKeyPressed(Keys.D)) {
			game.duck.moveIfValid(game.duck.RIGHT, game.getCurrentMap());
		}

		// Checks whether sprint key is pressed
		if (Gdx.input.isKeyPressed(Keys.SHIFT_LEFT)) {
			// If duck has stamina then set movement speed to sprint speed
			// and decrement stamina by sprint cost
			if (!game.duck.atMinStam()) {
				game.duck.setSpeed(game.duck.getDUCKSPRINT());
				game.duck.setStamina(game.duck.getStamina() - game.duck.getSPRINTCOST());
			}
			// If duck has no stamina then reset duck speed to normal speed
			if (game.duck.atMinStam()) {
				game.duck.setSpeed(game.duck.getDUCKSPEED());
			}
		}

		// Reset speed and regenerate stamina when not sprinting
		if (!Gdx.input.isKeyPressed(Keys.SHIFT_LEFT) && (game.duck.getSpeed() == game.duck.getDUCKSPRINT())) {
			game.duck.setSpeed(game.duck.getDUCKSPEED());
			game.duck.setStamina(game.duck.getStamina() + game.duck.getSTAMINAREGEN());
		} 
		// Regenerate stamina if not sprinting
		else if (!Gdx.input.isKeyPressed(Keys.SHIFT_LEFT) && !game.duck.atMaxStam()) {
			game.duck.setStamina(game.duck.getStamina() + game.duck.getSTAMINAREGEN());
		}

		// Set screen to Main Menu if Escape pressed
		if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
			game.setScreen(game.getMainMenu());
		}

		// Set screen to objective screen if O pressed
		if (Gdx.input.isKeyJustPressed(Keys.O)) {
			game.setScreen(game.getObjScreen());
		}

		// If M pressed then go to Map Screen
		if (Gdx.input.isKeyJustPressed(Keys.M)) {
			game.setScreen(game.getMapScreen());
		}
	}

	
	/**
	 * Input tests to check if score, health and stamina display
	 * correctly when changed
	 */
	private void inputTests() {
		if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
			game.duck.addScore(1);
		} else if (Gdx.input.isKeyPressed(Keys.LEFT)) {
			game.duck.addScore(-1);
		} else if (Gdx.input.isKeyPressed(Keys.UP)) {
			game.duck.setHealth(game.duck.getHealth() + 1);
		} else if (Gdx.input.isKeyPressed(Keys.DOWN)) {
			game.duck.setHealth(game.duck.getHealth() - 1);
		} else if (Gdx.input.isKeyPressed(Keys.NUM_9)) {
			game.duck.setStamina(game.duck.getStamina() - 4);
		} else if (Gdx.input.isKeyPressed(Keys.NUM_0)) {
			game.duck.setStamina(game.duck.getStamina() + 1);
		}
	}

	@Override
	public void show() {
	}

	@Override
	/**
	 * Function called by the main render loop of LibGDX when the game screen
	 * is the screen currently being shown.
	 */
	public void render(float delta) {
		// Update the game state
		this.update();

		// Begin the drawing element
		batch.begin();
		// Batch passed by reference (because its a class)
		this.draw(batch);
		// End the drawer element
		batch.end();

	}

	/**
	 * Function to update the game state 
	 * (input, objectives, maps, if game needs to end and enemies)
	 */
	private void update() {
		// Handle user input
		this.handleInput();
//		this.inputTests();
		
		// Updates current objective
		game.getCurrentObjective().updateObjective();
		
		// Sets current map
		game.setCurrentMap(game.getCurrentMap().managePortals(game.duck));

		// Checks to see if last objective complete.
		// If complete then go to Game Complete screen
		if (game.isLastObjComplete()) {
			game.setScreen(game.getGameCompleteScreen());
		}
		
		// Checks if duck is alive. If not then set to game over screen
		if (!game.duck.isAlive()) {
			game.setScreen(game.getGameOverScreen());
		}
		// Update the badies/ enemies that need to be included in the game
		game.getCurrentMap().updateEnemies(game.duck);

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
		game.getHeart().drawHearts(batch, game.duck.getHealth(), game.duck.getMaxHealth(), game.getScreenWidth(),
				game.getScreenHeight());

		// draw stamina
		game.getStamina().draw(batch, game);

		// draw new objective
		if (game.isNewObjective()) {
			batch.draw(game.getAssetManager().get("objective.png", Texture.class), 350,
					game.getScreenHeight() - game.getAssetManager().get("objective.png", Texture.class).getHeight());
		}
	}

	/**
	 * Dispose of batch and font to free memory
	 */
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		batch.dispose();
		game.getMyFont().dispose();
	}

	// Below are functions required by Screen implementation but not needed
	
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
