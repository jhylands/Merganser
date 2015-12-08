package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Heart {

	private Texture[] hearts = { new Texture("Heart_0.png"), new Texture("Heart_1.png"), new Texture("Heart_2.png"),
			new Texture("Heart_3.png"), new Texture("Heart_4.png") };

	public void addTextures(int health, int maxHealth, SpriteBatch batch, float screenWidth, float screenHeight ) {
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

		for (int i = 0;  i < disp.size(); i++) {
			batch.draw(this.getTexture(disp.get(i)),
					screenWidth - 20 - i * this.getTexture(disp.get(i)).getWidth(),
					screenHeight - this.getTexture(disp.get(i)).getHeight() - 2);
		}

	}

	public Texture getTexture(int texNum) {
		return this.hearts[texNum];

	}

}
