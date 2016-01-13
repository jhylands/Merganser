package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Heart {

	/**
	 * Constructor for Heart allows assetManager to be seen from MyGdxGame class
	 * Store textures for hearts from AssetManager in an Array to enable
	 * drawing of Hearts
	 * @param assetManager
	 */
	public Heart(AssetManager assetManager){
		hearts[0] = assetManager.get("Heart_0.png", Texture.class);
		hearts[1] = assetManager.get("Heart_1.png", Texture.class);
		hearts[2] = assetManager.get("Heart_2.png", Texture.class);
		hearts[3] = assetManager.get("Heart_3.png", Texture.class);
		hearts[4] = assetManager.get("Heart_4.png", Texture.class);
	}

	/**
	 * Array of Textures
	 * Allows for the storing of the heart images showing different
	 * quarters of the heart shaded in
	 */
	private Texture[] hearts = new Texture[5];

	public void addTextures(int health, int maxHealth, SpriteBatch batch, float screenWidth, float screenHeight) {
		ArrayList<Integer> disp = new ArrayList<Integer>();
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

		for (int i = 0; i < disp.size(); i++) {
			batch.draw(this.getTexture(disp.get(i)), screenWidth - 20 - i * this.getTexture(disp.get(i)).getWidth(),
					screenHeight - this.getTexture(disp.get(i)).getHeight() - 2);
		}

	}

	public Texture getTexture(int texNum) {
		return this.hearts[texNum];

	}

}
