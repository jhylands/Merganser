package com.mygdx.sprite;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Map;

public class Repeatable extends LiveEntity {
	//The amount of damadge on a strike
	protected int attack = 1;
	protected int attackRadius = 100;
	protected int threatRadius = 200;

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
		this.sprite[this.UP] = manager.get("goose_up.png", Texture.class);
		this.sprite[this.DOWN] = manager.get("goose_down.png", Texture.class);
		this.sprite[this.LEFT] = manager.get("goose_left.png", Texture.class);
		this.sprite[this.RIGHT] = manager.get("goose_right.png", Texture.class);
		this.attackSprite[this.UP] = manager.get("goose_attack_up.png", Texture.class);
		this.attackSprite[this.RIGHT] = manager.get("goose_action_right.png", Texture.class);
		this.attackSprite[this.DOWN] = manager.get("goose_attack_down.png", Texture.class);
		this.attackSprite[this.LEFT] = manager.get("gooes_attack_left.png", Texture.class);
		
		this.speed = speed;
		this.setPosition(new Vector2(50, 50));
	}

	
	public void update(PlayerDuck duck, Map map){
		move(duck, map);
		attack(duck,map);
		threat(duck);
	}
	/**
	 * Update the ducks position by the direction towards the player multiplied by the ducks speed
	 * @param duck
	 * @param map
	 */
	public void move(PlayerDuck duck, Map map) {
		if (isCloseToDuck(sight2,duck)) {
			this.moveIfValid(this.findDirection(duck).scl(this.speed), map);
			this.rotate(this.findDirection(duck).angle());
		}
	}
	/**
	 * Implement an attack function for the repeatable
	 * @param duck
	 */
	public void attack(PlayerDuck duck, Map map){
		if(isCloseToDuck(attackRadius,duck)){
			duck.changeHealth(attack*-1);
			this.moveIfValid(this.findDirection(duck).scl(threatRadius*-1), map);
		}
	}
	public void threat(PlayerDuck duck){
		this.attacking = isCloseToDuck(threatRadius,duck);
	}
	/**
	 * function to calculate if the repeatables is with a cirtasin distance of the duck
	 * @param close
	 * @param duck
	 * @return
	 */
	private boolean isCloseToDuck(int close, PlayerDuck duck){
		return duck.getPosition().sub(this.getPosition()).len2() < close;
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
