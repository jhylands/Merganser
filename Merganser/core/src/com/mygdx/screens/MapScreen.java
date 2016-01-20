// Download the .Jar file at http://merganser.weebly.com/ or directly on https://drive.google.com/file/d/0B_xhR6pi2K8Kc2Q1MFJrVDY0WVE/view?usp=sharing
package com.mygdx.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.MyGdxGame;

/**
 * Screen for displaying the over-world map along with playerDuck's global position and location description
 *
 */
public class MapScreen implements Screen {
	/**
	 * Game to allow reference to game state
	 */
	private MyGdxGame game;
	
	/**
	 *  Boolean value for which map to display. Initialised to false on map that playerDuck is on.
	 *  True means playerDuck is not on the current map displayed
	 */
	private Boolean changeMap = false;
	
	/**
	 *  Vector of globalMap position. x,y coordinate and z to set what map screen it references to
	 */
	private Vector3 globalMapPosition;
	
	/**
	 *  New SpriteBatch
	 */
	private SpriteBatch sb1;
	
	/**
	 * BitmapFont for displaying map text
	 */
	private BitmapFont mapFont;
	
	/**
	 *  Variable for current map name/ description as set in UML
	 */
	private String currentMapName;
	
	/**
	 *  Constant text for displaying current location on map
	 */
	private String CURRENTLOCATION = "Current Location: ";

	/**
	 * Constructor for MapScreen
	 * Initiates globalMapPosition
	 * @param game MyGdxGame - allows current game state to be passed
	 */
	public MapScreen(MyGdxGame game) {
		this.game = game;
		this.globalMapPosition = game.getCurrentMap().getGlobalPosition();
		create();
	}

	/**
	 * Updates variables (globalPosition and MapName) with current map data 
	 * to display on screen
	 */
	public void updateCurrentMap() {
		globalMapPosition = game.getCurrentMap().getGlobalPosition();
		currentMapName = game.getCurrentMap().getName();
	}

	/**
	 * Handles user input.
	 * If Left or Right Key pressed sets a boolean flag (changeMap) to display opposite map to one currently on
	 */
	public void handleInput() {
		// if left or right key pressed, flip changeMap to opposite boolean value to say that the user
		// has changed the map displayed
		if (Gdx.input.isKeyJustPressed(Keys.LEFT) | Gdx.input.isKeyJustPressed(Keys.RIGHT)) {
			if (!changeMap) {
				changeMap = true;
			} else if (changeMap) {
				changeMap = false;
			}
		}

		// sends user back to display current duck location if on a different map
		else if (Gdx.input.isKeyJustPressed(Keys.SPACE)) {
			changeMap = false;
		}

		// returns to main menu
		else if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
			game.setScreen(game.getMainMenu());
		}

		// returns to game screen
		else if (Gdx.input.isKeyJustPressed(Keys.M)) {
			game.setScreen(game.getMainGame());
		}

	}

	public void create() {
		// create new spriteBatch
		sb1 = new SpriteBatch();

		// Font generator
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("COUR.ttf"));
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		// set font size and available characters
		parameter.size = 15;
		parameter.characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789.!'()>?:-";
		// use generator and parameters to create bitmap font
		mapFont = generator.generateFont(parameter);

		// dispose of generator to free memory as no longer needed
		generator.dispose();
	}

	/**
	 * Function called by the main render loop of LibGDX when the Map screen is
	 * the screen currently being shown. Renders over-world map with the current
	 * PlayerDuck position displayed
	 */
	@Override
	public void render(float delta) {
		// Handle input from user
		handleInput();

		// Update the globalMap position from the currentMap (low level map)
		updateCurrentMap();

		// Clear background before rendering textures
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// Begin SpriteBatch drawing
		sb1.begin();

		// If user has changed the map (i.e. changeMap == true) then display opposite map to the one playerDuck is on and don't display playerDuck
		if (changeMap) {
			if (globalMapPosition.z == 0) {
				sb1.draw(game.getAssetManager().get("map2.png", Texture.class), 0, 0);
			}

			else if (globalMapPosition.z == 1) {
				sb1.draw(game.getAssetManager().get("map1.png", Texture.class), 0, 0);
			}

		} 
		// If changeMap == false (on initial map) then display map that playerDuck is on, along with playerDuck
		else {
			if (globalMapPosition.z == 0) {
				sb1.draw(game.getAssetManager().get("map1.png", Texture.class), 0, 0);
			}

			else if (globalMapPosition.z == 1) {
				sb1.draw(game.getAssetManager().get("map2.png", Texture.class), 0, 0);
			}

			// draw a duck to represent playerDucks global over-world position
			sb1.draw(game.getAssetManager().get("large_duck.png", Texture.class), globalMapPosition.x,
					globalMapPosition.y);
		}

		// Set mapFont colour
		mapFont.setColor(Color.WHITE);

		// Draw text displaying current map location at top of screen
		mapFont.draw(sb1, CURRENTLOCATION + currentMapName,
				game.getScreenWidth() - ((CURRENTLOCATION.length() * mapFont.getSpaceWidth())
						+ (mapFont.getSpaceWidth() * currentMapName.length())),
				game.getScreenHeight() - mapFont.getCapHeight());

		// Finish drawing SpriteBatch
		sb1.end();
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

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

	/**
	 * Dispose of batch and font to free memory
	 */
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		sb1.dispose();
		mapFont.dispose();
	}

}
