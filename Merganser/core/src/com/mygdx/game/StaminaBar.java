package com.mygdx.game;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.sprite.PlayerDuck;
import com.badlogic.gdx.graphics.Pixmap.Format;

public class StaminaBar {
	
	private int barHeight = 14;
	private int barWidth = 100;
	
	public Texture getTexture(PlayerDuck duck){
		// Create a pixmap to draw new stamina bar from the current stamina value
		Pixmap staminaBar = new Pixmap(barWidth, barHeight, Format.RGBA8888);
		staminaBar.setColor(0, 1, 0, 0.75f);
		staminaBar.drawRectangle(0, 0, barWidth, barHeight);
		staminaBar.fillRectangle(0, 0, duck.getStamina(), barHeight);
		// Export pixmap to texture
		Texture stam = new Texture(staminaBar);
		// Dispose of pixmap as no longer needed
		staminaBar.dispose();
		return stam;
	}
	
	

}
