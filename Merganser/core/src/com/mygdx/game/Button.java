package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.TimeUtils;

public class Button extends Rectangle {

	Texture texture;
	long time = TimeUtils.millis();
	SpriteBatch button;

	public Button(Texture texture, int x, int y, int width, int height) {
		this.texture = texture;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;

		button = new SpriteBatch();
	}

	public void draw() {
		button.begin();
		button.draw(texture, x, y, width, height);
		button.end();
	}

	public boolean isPressed() {
		if (Gdx.input.isTouched() && TimeUtils.millis() - time > 300) {
			Vector2 touchCoord = new Vector2();
			touchCoord.set(Gdx.graphics.getWidth() - Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY());
			if (this.contains(touchCoord.x, touchCoord.y)) {
				time = TimeUtils.millis();
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

}
