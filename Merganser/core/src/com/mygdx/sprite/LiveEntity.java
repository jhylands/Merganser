package com.mygdx.sprite;

import com.badlogic.gdx.math.Rectangle;

public class LiveEntity {
	private int health;
	private int maxHealth;
	private int attackStrength;
	private int speed = 2;
	private Rectangle hitbox;
	
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
