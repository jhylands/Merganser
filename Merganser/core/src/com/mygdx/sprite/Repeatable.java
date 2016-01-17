package com.mygdx.sprite;

import java.util.Random;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Map;

/**
 * Creates an enemy obstacle at a random location on a specified map Enemy has a
 * fixed sight area for which it can detect the playerDuck
 */
public class Repeatable extends LiveEntity {
	/**
	 * The amount of damage on a strike
	 */
	protected int attackDamage = 1;
	
	/**
	 * The radius around enemy at which it attacks playerDuck
	 */
	protected int attackRadius = 100;

	/**
	 * The radius around enemy for a visual cue that the enemy is about to
	 * attack
	 */
	protected int threatRadius = 200;

	/**
	 * sight2 is the distance of the sight/ 'field of vision' of the
	 * enemy/repeatable squared
	 */
	private int sight2 = 10000;

	/**
	 * Constructor for Repeatable. Loads textures from assetManager Sets up
	 * variables and generates a random position for repeatable
	 * 
	 * @param speed
	 *            Speed of repeatable
	 * @param manager
	 *            AssetManager where textures are stored
	 */
	public Repeatable(int speed, AssetManager manager) {
		this.sprite = new Texture[4];
		this.sprite[this.UP] = manager.get("goose_up.png", Texture.class);
		this.sprite[this.DOWN] = manager.get("goose_down.png", Texture.class);
		this.sprite[this.LEFT] = manager.get("goose_left.png", Texture.class);
		this.sprite[this.RIGHT] = manager.get("goose_right.png", Texture.class);
		this.attackSprite = new Texture[4];
		this.attackSprite[this.UP] = manager.get("goose_action_up.png", Texture.class);
		this.attackSprite[this.RIGHT] = manager.get("goose_action_right.png", Texture.class);
		this.attackSprite[this.DOWN] = manager.get("goose_action_down.png", Texture.class);
		this.attackSprite[this.LEFT] = manager.get("goose_action_left.png", Texture.class);

		this.speed = speed;
		this.setPosition(new Vector2(randomNumber(470), randomNumber(240)));
	}

	/**
	 * Random number generator within a specified range
	 * 
	 * @param range
	 *            Integer - return a random number between 0 and range
	 * @return
	 */
	public int randomNumber(int range) {
		Random rnd = new Random();
		return rnd.nextInt(range);
	}

	/**
	 * Updates enemy position, attack function if enemy within range and sets
	 * boolean if duck is within threat range
	 * 
	 * @param duck
	 *            PlayerDuck
	 * @param map
	 *            currentMap
	 */
	public void update(PlayerDuck duck, Map map) {
		move(duck, map);
		attack(duck, map);
		threat(duck);
	}

	/**
	 * Update the enemy position by the direction towards the playerDuck
	 * multiplied by the enemy/ repeatable speed Also set rotation of repeatable
	 * 
	 * @param duck
	 *            PlayerDuck
	 * @param map
	 *            Current map to check if move is valid on
	 */
	public void move(PlayerDuck duck, Map map) {
		if (isCloseToDuck(sight2, duck)) {
			this.moveIfValid(this.findDirection(duck).scl(this.speed), map);
			this.rotate(this.findDirection(duck).angle());
		}
	}

	/**
	 * Implement an attack function for the repeatable Deals damage if duck is
	 * within attacking range of repeatable Moves duck a set distance away from
	 * enemy to give a change to get away
	 * 
	 * @param duck
	 */
	public void attack(PlayerDuck duck, Map map) {
		if (isCloseToDuck(attackRadius, duck)) {
			duck.changeHealth(attackDamage * -1);
			this.moveIfValid(this.findDirection(duck).scl(20 * -1), map);
		}
	}

	/**
	 * Set attacking boolean if duck is within threatRadius of Repeatable
	 * 
	 * @param duck
	 */
	public void threat(PlayerDuck duck) {
		this.attacking = isCloseToDuck(threatRadius, duck);
	}

	/**
	 * Calculates if repeatable is with a certain distance of the duck
	 * 
	 * @param close
	 *            - Maximum distance between playerDuck and Repeatable to be
	 *            true
	 * @param duck
	 *            playerDuck
	 * @return Boolean - True if enemy is within range of playerDuck
	 */
	private boolean isCloseToDuck(int close, PlayerDuck duck) {
		return duck.getPosition().sub(this.getPosition()).len2() < close;
	}

	/**
	 * Depending on direction given sets the rotation of the Repeatable enemy
	 * 
	 * @param direction
	 *            Floating point angle
	 */
	public void rotate(float direction) {
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
	 * Returns the direction between the playerDuck and repeatable as a vector
	 * 
	 * @param duck
	 * @return Vector2
	 */
	public Vector2 findDirection(PlayerDuck duck) {
		return duck.getPosition().sub(this.getPosition()).nor();
	}

	/**
	 * Renders the enemy to the screen to a specified SpriteBatch
	 * 
	 * @param batch
	 *            SpriteBatch to render enemy to
	 */
	public void draw(SpriteBatch batch) {
		batch.draw(this.getTexture(), this.getPosition().x, this.getPosition().y);

	}

}
