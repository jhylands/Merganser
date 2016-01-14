package com.mygdx.sprite;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Map;

public class LiveEntity extends Entity {
	// as a superclass these should be protected rather than private so they can
	// be visible to the subclasses
	protected int health;
	protected int maxHealth; // for duck, should always be divisible by 4,
								// anything else doesn't matter
	protected int attackStrength;
	protected int speed;
	protected boolean flying = false;
	protected boolean canSwim = false;

	public boolean isflying() {
		return flying;
	}

	public boolean canswim() {
		return canSwim;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getAttackStrength() {
		return attackStrength;
	}

	public void setAttackStrength(int strength) {
		this.attackStrength = strength;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}

	// public Vector2 getPosition(){
	// use getPosition inherited from enitity
	public boolean moveIfValid(int direction, Map map) {
		setRotation(direction);
		return moveIfValid(direction2Vector(direction).scl(speed), map);
	}

	public boolean moveIfValid(Vector2 v, Map map) {
		Vector2 newPosition;
		newPosition = v.add(this.getPosition());
		if (map.validSpace(this.getHypertheticalHitBox(newPosition), flying, canSwim)) {
			this.setPosition(newPosition);
			return true;
		} else {
			return false;
		}
	}
}
