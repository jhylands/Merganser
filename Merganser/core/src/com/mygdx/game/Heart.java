package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/**
 * Handles drawing the hearts for the GUI
 *
 */
public class Heart {

	/**
	 * Constructor for Heart Store textures for hearts from AssetManager in an
	 * Array to enable drawing of Hearts
	 * 
	 * @param assetManager
	 *            AssetManger where textures are stored
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
	
	/**
	 * ArrayList containing integers as references to which heart in the
	 * hearts array to draw
	 */
	private ArrayList<Integer> disp = new ArrayList<Integer>();

	/**
	 * Calculates which heart textures to draw to represent current health level
	 * @param health
	 * @param maxHealth Should be a multiple of 4. Will get rounded down otherwise.
	 * @param screenWidth
	 * @param screenHeight
	 */
	public void addTextures(int health, int maxHealth, float screenWidth, float screenHeight) {
		// Clear disp list so can re-populate it each call
		disp.clear();

		// If player health full, number of full hearts drawn equal
		// to a quarter of the players health. Otherwise calculate partially
		// filled hearts and/or 1 or more empty heart
		if (health == maxHealth) {
			if (health != 0) {
				for (int i = 1; i <= health / 4; i++) {
					disp.add(4);
				}
			}
		} else {
			// number of whole hearts
			int whole = health / 4;
			// state of the partial heart
			int rem = health % 4;
			// number of empty hearts
			int empty = (maxHealth - health) / 4;

			// A value of zero will not add to display list and
			// stop the program trying to draw them
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
	 * Render hearts at top of the screen, right to left.
	 * Allows maximum health value to be changed without changing any code
	 */
	public void drawHearts(SpriteBatch batch, int health, int maxHealth, float screenWidth, float screenHeight) {
		addTextures(health, maxHealth, screenWidth, screenHeight);
		for (int i = 0; i < disp.size(); i++) {
			batch.draw(this.getTexture(disp.get(i)), screenWidth - 20 - i * this.getTexture(disp.get(i)).getWidth(),
					screenHeight - this.getTexture(disp.get(i)).getHeight() - 2);
		}

	}

	/**
	 * Returns texture by indexing Array of Heart textures
	 * @param texNum
	 * @return Texture
	 */
	public Texture getTexture(int texNum) {
		return this.hearts[texNum];

	}

	/**
	 * Returns array of Integer values based on which
	 * Texture to draw when passed to getTexture
	 * @return ArrayList
	 */
	public ArrayList<Integer> getDisp() {
		return disp;
	}

}
