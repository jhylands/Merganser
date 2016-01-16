package com.mygdx.game;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/**
 * Creates a stamina bar by using a Pixmap.
 * Calling draw creates a new Texture displaying the current stamina level
 */
public class Stamina{
	// Pixmap for drawing staminaBar
	private Pixmap staminaBar;
	// Constants for the height and width of the stamina bar
	private final int BARHEIGHT = 14;
	private final int BARWIDTH = 100;
	// StaminaBar x offset for rendering position
	private final int STAMXOFFSET = 3;
	// StaminaBar y offset for rendering position
	private final int STAMYOFFSET = 18;
	// Texture for stamina bar
	private Texture stan;
	
	/**
	 * Constructor for Stamina
	 * Create a new Pixmap and exports Pixmap to a new texture
	 */
	public Stamina(){
		staminaBar = new Pixmap(BARWIDTH, BARHEIGHT, Format.RGBA8888);
		staminaBar.setColor(0, 1, 0, 0.75f);
		staminaBar.drawRectangle(0, 0, BARWIDTH, BARHEIGHT);
		staminaBar.fillRectangle(0, 0,0, BARHEIGHT);
		stan = new Texture(staminaBar);
	}
	
	/**
	 * Creates a new PixMap as need to redraw stamina bar, exports to texture
	 * Renders to given SpriteBatch
	 * @param batch
	 * @param game
	 */
	public void draw(SpriteBatch batch, MyGdxGame game) {
		
		// Dispose of old pixmap as no longer needed
		staminaBar.dispose();
		// Dispose of old texture as no longer needed
		stan.dispose();
		staminaBar = null;
		stan = null;
		// Create a pixmap to draw new stamina bar from the current stamina
		// value
		staminaBar = new Pixmap(BARWIDTH, BARHEIGHT, Format.RGBA8888);
		staminaBar.setColor(0, 1, 0, 0.75f);
		staminaBar.drawRectangle(0, 0, BARWIDTH, BARHEIGHT);
		staminaBar.fillRectangle(0, 0, game.duck.getStamina(), BARHEIGHT);
		
		// Export pixmap to new texture
		stan = new Texture(staminaBar);
		
		// Draw texture to a SpriteBatch at set location
		batch.draw(stan, STAMXOFFSET, game.getScreenHeight() - STAMYOFFSET);

	}
}