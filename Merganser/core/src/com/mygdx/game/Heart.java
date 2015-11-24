package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;

public class Heart {

	private Texture[] heart = { new Texture("Heart_0.png"), new Texture("Heart_1.png"), new Texture("Heart_2.png"),
			new Texture("Heart_3.png"), new Texture("Heart_4.png") };

	public ArrayList<Texture> getTexture(int health) {
		ArrayList<Texture> disp = new ArrayList<Texture>();
		if (health % 4 == 0) {
			for (int i = 0; i <= health / 4; i++) {
				disp.add(heart[4]);
			}
		} else {
			int whole = health / 4;
			int rem = health % 4;

			for (int i = 0; i <= whole; i++) {
				disp.add(heart[4]);
			}
			disp.add(heart[rem]);
		}

		return disp;

	}

}
