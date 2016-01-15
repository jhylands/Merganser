package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Heart {

	/**
	 * Constructor for Heart allows assetManager to be seen from MyGdxGame class
	 * Store textures for hearts from AssetManager in an Array to enable drawing
	 * of Hearts
	 * 
	 * @param assetManager
	 */
	public Heart(AssetManager assetManager) {
		hearts[0] = assetManager.get("Heart_0.png", Texture.class);
		hearts[1] = assetManager.get("Heart_1.png", Texture.class);
		hearts[2] = assetManager.get("Heart_2.png", Texture.class);
		hearts[3] = assetManager.get("Heart_3.png", Texture.class);
		hearts[4] = assetManager.get("Heart_4.png", Texture.class);
	}

	/**
	 * Array of Textures allows for the storing of the heart images showing
	 * different quarters of the heart shaded in
	 */
	private Texture[] hearts = new Texture[5];
	private ArrayList<Integer> disp = new ArrayList<Integer>();

	public void addTextures(int health, int maxHealth, float screenWidth, float screenHeight) {
		/*
		 * If the player's health is full, the above loop iteratively draws full
		 * hearts a number of times equal to a quarter of the players health. If
		 * the player's health is not full, there will be a partially filled
		 * heart and/or 1 or more empty hearts.
		 */
		
		// Clear disp list so can re-populate it
		disp.clear();
		
		if (health == maxHealth) {
			if (health != 0) {
				for (int i = 1; i <= health / 4; i++) {
					disp.add(4);
				}
			}
		} else {
			int whole = health / 4;
			int rem = health % 4;
			int empty = (maxHealth - health) / 4;

			/*
			 * the number of whole hearts, the state of the partial heart, and
			 * the number of empty hearts are all calculated. A value of zero
			 * for any of these will stop the program trying to draw them.
			 */

			if (whole != 0) {
				for (int i = 1; i <= whole; i++) {
					disp.add(4);
				}
			}
			if (rem != 0) {
				disp.add(rem);
			}
			for (int i = 1; i <= empty; i++) {
				disp.add(0);
			}
		}
	}

	/**
	 * The hearts are drawn at the top of the screen, starting from the right.
	 * The code in this state means the maximum health value can be changed
	 * without changing any code, although the value should always be an integer
	 * multiple of 4.
	 */
	public void drawHearts(SpriteBatch batch, int health, int maxHealth, float screenWidth, float screenHeight) {
		addTextures(health, maxHealth, screenWidth, screenHeight);
		for (int i = 0; i < disp.size(); i++) {
			batch.draw(this.getTexture(disp.get(i)), screenWidth - 20 - i * this.getTexture(disp.get(i)).getWidth(),
					screenHeight - this.getTexture(disp.get(i)).getHeight() - 2);
		}

	}

	public Texture getTexture(int texNum) {
		return this.hearts[texNum];

	}
	
	public ArrayList<Integer> getDisp() {
		return disp;
	}

}
