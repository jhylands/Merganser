package com.mygdx.sprite;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class LiveEntity extends Entity{
	//as a superclass these should be protected rather than private so they can be visible to the subclasses
	protected int health;
	protected int maxHealth; //for duck, should always be divisible by 4, anything else doesn't matter
	protected int attackStrength;
	protected int speed;
	protected Rectangle hitbox = new Rectangle();
	
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
	public void setHitBox(Rectangle hitbox){
		this.hitbox = hitbox;
	}
	public int getMaxHealth() {
		return maxHealth;
	}
	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}
	protected void setPosition(int x, int y){
		this.hitbox.setPosition(x, y);
	}
	protected void setPosition(Vector2 x){
		this.hitbox.setPosition(x);
	}
	public Vector2 getPosition(){
		return this.hitbox.getPosition(new Vector2());
	}

}
