package com.mygdx.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Objective;

public class ObjectiveScreen implements Screen {

	private MyGdxGame game;
	private BitmapFont objFont;
	private SpriteBatch sb;
	private String screenTitle;

	/**
	 * ObjectiveScreen is called with reference to the ordinal game. The
	 * create() method is run to set up the new SpriteBatch and Font
	 * 
	 * @param game
	 */
	public ObjectiveScreen(MyGdxGame game) {
		this.game = game;
		create();
	}

	/**
	 * Sets up the new Font and SpriteBatch to draw
	 */
	private void create() {
		// New SpriteBatch
		sb = new SpriteBatch();
		// Title to be drawn on the screen
		screenTitle = "Objectives:";

		// Font generator
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("COUR.ttf"));
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		parameter.size = 25;
		parameter.characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789.!'()>?:-";
		objFont = generator.generateFont(parameter);

		generator.dispose();
	}

	/**
	 * Renders the ObjectiveScreen
	 * 
	 * @param delta
	 */
	@Override
	public void render(float delta) {
		if(game.isNewObjective()){
			game.setNewObjective(false);
		}
			
		
		// Handle any input as defined by the handleInput function
		handleInput();

		// Clear background to black
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// Begin drawing SpriteBatch
		sb.begin();

		// Draw title to screen. Should be center of screen width and at a set
		// height. Set colour of font to white.
		objFont.setColor(Color.WHITE);
		objFont.draw(sb, screenTitle, objFont.getSpaceWidth() * screenTitle.length(), 220);

		// Display each objective on the screen.
		for (int i = 0; i < game.getObjectives().size(); i++) {
			// Get objectives as declared in MyGdxGame class
			Objective drawObjective = game.getObjectives().get(i);

			// If objective is current objective; colour red
			// Else; colour white
			if (game.getCurrentObjective() == drawObjective) {
				objFont.setColor(Color.RED);
				objFont.draw(sb, drawObjective.getObjectiveDescription(), (game.getScreenWidth()
						- (objFont.getSpaceWidth() * game.getObjectives().get(i).getObjectiveDescription().length()))
						/ 2, 180 - 35 * i);
			} else {
				objFont.setColor(Color.WHITE);
				objFont.draw(sb, drawObjective.getObjectiveDescription(), (game.getScreenWidth()
						- (objFont.getSpaceWidth() * game.getObjectives().get(i).getObjectiveDescription().length()))
						/ 2, 180 - 35 * i);
			}
		}

		// End drawing spriteBatch
		sb.end();

	}

	/**
	 * Handle input from user. Only need to check for Key O pressed to return
	 * back to Game Or Key M pressed to return to the map
	 */
	public void handleInput() {
		if (Gdx.input.isKeyJustPressed(Keys.O)) {
			game.setScreen(game.getMainGame());
		} else if (Gdx.input.isKeyJustPressed(Keys.M)) {
			game.setScreen(game.getMapScreen());
		}
	}

	/**
	 * Dispose of SpriteBatch when method called
	 */
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		sb.dispose();
		objFont.dispose();
	}

	/**
	 * No implementation
	 */
	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	/**
	 * No implementation
	 */
	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	/**
	 * No implementation
	 */
	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	/**
	 * No implementation
	 */
	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	/**
	 * No implementation
	 */
	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

}
