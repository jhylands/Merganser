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

/**
 * Screen for Main Menu
 */
public class MainMenuScreen implements Screen {
	/**
	 * Game to allow reference to game state
	 */
	private MyGdxGame game;

	/**
	 * SpriteBatch for drawing textures
	 */
	private SpriteBatch sb;
	
	/**
	 * Create new fonts for drawing the title and menu font
	 */
	private BitmapFont titleFont, menuFont;
	
	/**
	 * Constant text for screen title (title of game)
	 */
	private final String gameTitle = "M.E.R.G.A.N.S.E.R";
	
	/**
	 * Integer to know what menu item user is currently on
	 */
	private int currentMenuItem;
	
	/**
	 * String array of text to be on menu
	 */
	private String[] menuItem;

	
	/**
	 * Constructor for Screen
	 * 
	 * @param game
	 *            Current game state (allows reference to PlayerDuck score)
	 */
	public MainMenuScreen(MyGdxGame game) {
		this.game = game;
		create();
	}
	
	/**
	 * Creates new spriteBatch and font. Also contains text to draw to screen.
	 */
	public void create() {
		// Create new SpriteBatch for rendering text to
		sb = new SpriteBatch();
		
		// FONT Generation for title
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("COUR.ttf"));
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		// Font size and characters for font
		parameter.size = 40;
		parameter.characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789.!'()>?:-";
		// FONT Generation for menu items
		FreeTypeFontParameter parameter1 = new FreeTypeFontParameter();
		// Font size and characters for font
		parameter1.size = 25;
		parameter1.characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789.!'()>?:-|";
		
		// Set titleFont to font generated by generator and parameter
		titleFont = generator.generateFont(parameter);
		// Set menuFont to font generated by generator and parameter
		menuFont = generator.generateFont(parameter1);
		
		// Set titleFont colour to white
		titleFont.setColor(Color.WHITE);

		// Populate menuItem String array with menu options
		menuItem = new String[] { "Play | Resume", "Map Screen", "Quit" };

		generator.dispose();
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	/**
	 * Renders text to screen when called
	 */
	@Override
	public void render(float delta) {
		// Handle input from user
		handleInput();
		
		// Clear and set background to black
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// Begin drawing to SpriteBatch
		sb.begin();

		// Draw title to screen
		// Use length of title and screen Height to calculate where to draw
		titleFont.draw(sb, gameTitle, (game.getScreenWidth() - (titleFont.getSpaceWidth() * gameTitle.length())) / 2, 240);

		// For each item in menuItem draw it to screen
		for (int i = 0; i < menuItem.length; i++) {
			// If user currently on menu option then draw in different colour
			if (currentMenuItem == i) {
				menuFont.setColor(Color.RED);
			} else {
				menuFont.setColor(Color.WHITE);
			}
			menuFont.draw(sb, menuItem[i], (game.getScreenWidth() - (menuFont.getSpaceWidth() * menuItem[i].length()))/ 2, 180 - 35 * i);
		}

		// End spriteBatch
		sb.end();

	}

	/**
	 * Handles input from user to navigate menus
	 */
	public void handleInput() {
		// If UP pressed then decrement the pointer to the menu item the user
		// is currently on (if not already on the first menu item)
		if (Gdx.input.isKeyJustPressed(Keys.UP)) {
			if (currentMenuItem > 0) {
				currentMenuItem--;
			}
		}

		// If DOWN pressed then increment the pointer to the menu item the user
		// is currently on (if not already on the last menu item)		
		if (Gdx.input.isKeyJustPressed(Keys.DOWN)) {
			if (currentMenuItem < menuItem.length - 1) {
				currentMenuItem++;
			}
		}

		// If ENTER pressed on menu option then perform action (change screen or exit)
		if (Gdx.input.isKeyJustPressed(Keys.ENTER)) {
			switch (currentMenuItem) {
			case 0:
				game.setScreen(game.getMainGame());
				break;
			case 1:
				game.setScreen(game.getMapScreen());
				break;
			case 2:
				Gdx.app.exit();
				break;
			}
		}

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
	 * Dispose of fonts and spriteBatch to free memory
	 */
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		titleFont.dispose();
		menuFont.dispose();
		sb.dispose();
	}

}
