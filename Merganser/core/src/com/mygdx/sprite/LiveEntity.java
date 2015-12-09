package com.mygdx.sprite;

import com.badlogic.gdx.math.Rectangle;

public class LiveEntity extends Entity{
	//as a superclass these should be protected rather than private so they can be visible to the subclasses
	protected int health;
	protected int maxHealth; //for duck, should always be divisible by 4, anything else doesn't matter
	protected int attackStrength;
	protected int speed;
	protected Rectangle hitbox;
	
	public int getHealth() {
		return health;
	}
	public void setHealth(int health) {
		this.health = health;
	}
	public int getAttackStrength() {
		return attackStrength;
	}
	public void setAttackStrength(int attackStrength) {
		this.attackStrength = attackStrength;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public Rectangle getHitbox(){
		return hitbox;
	}
	public void setHitbox(Rectangle hitbox){
		this.hitbox = hitbox;
	}
	public int getMaxHealth() {
		return maxHealth;
	}
	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}
	

}
