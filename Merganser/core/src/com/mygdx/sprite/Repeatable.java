package com.mygdx.sprite;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Map;

public class Repeatable extends LiveEntity {
	protected int attack;
	// sight2 is the distance of the sight squared
	private int sight2 = 10000;

	/**
	 * Constructor for Repeatable.
	 * Loads textures from assetManager
	 * Sets up variables and position
	 * @param speed
	 * @param manager
	 */
	public Repeatable(int speed, AssetManager manager) {
		this.sprite = new Texture[4];
		this.sprite[0] = manager.get("goose_up.png", Texture.class);
		this.sprite[2] = manager.get("goose_down.png", Texture.class);
		this.sprite[3] = manager.get("goose_left.png", Texture.class);
		this.sprite[1] = manager.get("goose_right.png", Texture.class);
		this.speed = speed;
		this.setPosition(new Vector2(50, 50));
	}

	/**
	 * Update the ducks position by the direction towards the player multiplied by the ducks speed
	 * @param duck
	 * @param map
	 */
	public void move(PlayerDuck duck, Map map) {
		if (duck.getPosition().sub(this.getPosition()).len2() < sight2) {
			this.moveIfValid(this.findDirection(duck).scl(this.speed), map);
			this.rotate(this.findDirection(duck).angle());
		}
	}

	/**
	 * Depending on direction given sets rotation of Repeatable
	 * @param direction
	 */
	private void rotate(float direction) {
		if (direction < 45 || direction > 315) {
			this.setRotation(this.RIGHT);
		} else if (direction < 135) {
			this.setRotation(this.UP);
		} else if (direction < 225) {
			this.setRotation(this.LEFT);
		} else {
			this.setRotation(this.DOWN);
		}
	}

	/**
	 * Returns the direction of playerDuck from repeatable
	 * @param duck
	 * @return
	 */
	public Vector2 findDirection(PlayerDuck duck) {
		return duck.getPosition().sub(this.getPosition()).nor();
	}

}
