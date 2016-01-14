package com.mygdx.game;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Stamina{
	private Pixmap staminaBar;
	private int barHeight = 14;
	private int barWidth = 100;
	private Texture stan;
	
	public Stamina(){
		staminaBar = new Pixmap(barWidth, barHeight, Format.RGBA8888);
		staminaBar.setColor(0, 1, 0, 0.75f);
		staminaBar.drawRectangle(0, 0, barWidth, barHeight);
		staminaBar.fillRectangle(0, 0,0, barHeight);
		stan = new Texture(staminaBar);
	}
	public void draw(SpriteBatch batch, MyGdxGame game) {
		// Create a pixmap to draw new stamina bar from the current stamina
		// value
		staminaBar = null;
		staminaBar = new Pixmap(barWidth, barHeight, Format.RGBA8888);
		staminaBar.setColor(0, 1, 0, 0.75f);
		staminaBar.drawRectangle(0, 0, barWidth, barHeight);
		staminaBar.fillRectangle(0, 0, game.duck.getStamina(), barHeight);
		// Export pixmap to texture
		stan = null;
		stan = new Texture(staminaBar);
		batch.draw(stan, 3, game.getScreenHeight() - 18);
		// Dispose of pixmap as no longer needed

	}
}