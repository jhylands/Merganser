package com.mygdx.game;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.sprite.Duck;
import com.badlogic.gdx.graphics.Pixmap.Format;

public class StaminaBar {
	
	private int barHeight = 14;
	private int barWidth = 100;
	
//	public static Pixmap getPixmapRoundedRectangle(int width, int height, int radius, int color) {
//
//		 Pixmap pixmap = new Pixmap(width, height, Format.RGBA8888);
//		 pixmap.setColor(0, 1, 0, 0.75f);
//
//		 // Pink rectangle
//		 pixmap.fillRectangle(0, radius, pixmap.getWidth(), pixmap.getHeight()-2*radius);
//
//		// Green rectangle
//		 pixmap.fillRectangle(radius, 0, pixmap.getWidth() - 2*radius, pixmap.getHeight());
//		 
//
//		// Bottom-left circle
//		 pixmap.fillCircle(radius, radius, radius);
//		 
//		// Top-left circle
//		pixmap.fillCircle(radius, pixmap.getHeight()-radius, radius);
//
//		// Bottom-right circle
//		 pixmap.fillCircle(pixmap.getWidth()-radius, radius, radius);
//
//		// Top-right circle
//		 pixmap.fillCircle(pixmap.getWidth()-radius, pixmap.getHeight()-radius, radius);
//		 return pixmap;
//		}
//	
		
	
	public Texture getTexture(Duck duck){
		Pixmap staminaBar = new Pixmap(barWidth, barHeight, Format.RGBA8888);
		staminaBar.setColor(0, 1, 0, 0.75f);
		staminaBar.drawRectangle(0, 0, barWidth, barHeight);
		staminaBar.fillRectangle(0, 0, duck.getStamina(), barHeight);
		Texture stam = new Texture(staminaBar);
		staminaBar.dispose();
		return stam;
	}
	
	

}
