package com.mygdx.sprite;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Map;

public class Repeatable extends LiveEntity {
	protected int attack;
	// sight2 is the distance of the sight squared
	public int sight2 = 10000;

	public Repeatable(int speed) {
		this.sprite = new Texture[4];
		this.sprite[0] = new Texture("goose_up.png");
		this.sprite[2] = new Texture("goose_down.png");
		this.sprite[3] = new Texture("goose_left.png");
		this.sprite[1] = new Texture("goose_right.png");
		this.speed = speed;
		this.setPosition(new Vector2(50, 50));
	}

	// update the ducks position by the direction towards the player multiplied
	// by the ducks speed
	public void move(PlayerDuck duck, Map map) {
		if (duck.getPosition().sub(this.getPosition()).len2() < sight2) {
			this.moveIfValid(this.findDirection(duck).scl(this.speed), map);
			this.rotate(this.findDirection(duck).angle());
		}
	}

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

	public Vector2 findDirection(PlayerDuck duck) {
		return duck.getPosition().sub(this.getPosition()).nor();
	}

}
