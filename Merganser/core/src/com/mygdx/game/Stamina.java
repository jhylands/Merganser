package com.mygdx.game;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Stamina{
	private Pixmap staminaBar;
	private int barHeight = 14;
	private int barWidth = 100;
	// StaminaBar x offset for rendering position
	private int STAMXOFFSET = 3;
	// StaminaBar y offset for rendering position
	private int STAMYOFFSET = 18;
	private Texture stan;
	
	/**
	 * Constructor for Stamina
	 * Create a new Pixmap and sets to a new texture
	 */
	public Stamina(){
		staminaBar = new Pixmap(barWidth, barHeight, Format.RGBA8888);
		staminaBar.setColor(0, 1, 0, 0.75f);
		staminaBar.drawRectangle(0, 0, barWidth, barHeight);
		staminaBar.fillRectangle(0, 0,0, barHeight);
		stan = new Texture(staminaBar);
	}
	
	/**
	 * Creates a new PixMap, exports to texture
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
		staminaBar = new Pixmap(barWidth, barHeight, Format.RGBA8888);
		staminaBar.setColor(0, 1, 0, 0.75f);
		staminaBar.drawRectangle(0, 0, barWidth, barHeight);
		staminaBar.fillRectangle(0, 0, game.duck.getStamina(), barHeight);
		
		// Export pixmap to new texture
		stan = new Texture(staminaBar);
		
		// Draw texture to a SpriteBatch at set location
		batch.draw(stan, STAMXOFFSET, game.getScreenHeight() - STAMYOFFSET);

	}
}