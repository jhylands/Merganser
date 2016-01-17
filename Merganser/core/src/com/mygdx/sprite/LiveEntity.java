package com.mygdx.sprite;

import org.omg.PortableInterceptor.SUCCESSFUL;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Map;

import jdk.nashorn.internal.runtime.regexp.joni.exception.ValueException;

/**
 * Implementation of LiveEntity which extends Entity containing features such as
 * health, speed and attacking functionality
 *
 */
public class LiveEntity extends Entity {
	// as a superclass these should be protected rather than private so they can
	// be visible to the subclasses
	/**
	 * Integer health value for LiveEntity
	 */
	private int health;

	/**
	 * Maximum health value for LiveEntity PlayerDuck health must always be
	 * divisible by 4 - this is handled within the PlayerDuck class
	 */
	private int maxHealth;

	/**
	 * Attack strength of the LiveEntity
	 */
	protected int attackStrength;

	/**
	 * Speed of the LiveEntity for navigating the map
	 */
	protected int speed;

	/**
	 * Booleans for whether LiveEntity is fly/ can swim
	 */
	protected boolean flying = false, canSwim = false;

	/**
	 * Texture array for sprite Textures to display when the LiveEntity is
	 * attacking
	 */
	protected Texture[] attackSprite;

	/**
	 * Boolean for if the LiveEntity is currently attacking
	 */
	protected boolean attacking;

	/**
	 * If the health of LiveEntity is not zero the entity is alive
	 * 
	 * @return Boolean
	 */
	public boolean isAlive() {
		return health != 0;
	}

	/**
	 * Returns if LiveEntity is flying
	 * 
	 * @return Boolean
	 */
	public boolean isflying() {
		return flying;
	}

	/**
	 * Returns if LiveEntity can swim
	 * 
	 * @return Boolean
	 */
	public boolean canswim() {
		return canSwim;
	}

	/**
	 * Returns health of LiveEntity
	 * 
	 * @return Integer
	 */
	public int getHealth() {
		return health;
	}

	/**
	 * Sets entity health. If trying to set health > MaxHealth set health to
	 * maxHealth
	 * 
	 * @param health
	 */
	public void setHealth(int health) {
		if (health <= 0) {
			this.health = 0;
		} else if (health > this.maxHealth) {
			this.health = this.maxHealth;
		} else {
			this.health = health;
		}

	}

	/**
	 * Changes LiveEntity health by a specified amount Also returns a boolean if
	 * change in health was successful
	 * 
	 * @param change
	 *            Integer amount to change health by
	 * @return returns a boolean if change in health successful
	 */
	public boolean changeHealth(int change) {
		if (this.getHealth() + change < 0) {
			this.setHealth(0);
			return false;
		} else {
			this.setHealth(this.getHealth() + change);
			return true;
		}
	}

	/**
	 * Set max health
	 * Maximum health must be >= 0 otherwise game will exit with an error
	 * 
	 * @param maxHealth Integer
	 */
	public void setMaxHealth(int maxHealth) {
		try {
			if (maxHealth < 0) {
				throw new Exception("MaxHealth must be >= 0");
			} else {
				this.maxHealth = maxHealth;
			}
		} catch (Exception e) {
			System.err.println("Max health must be >= 0");
			Gdx.app.exit();
		}
	}

	/**
	 * Move LiveEntity to position by direction amount if valid position
	 * @param direction Integer direction to move live Entity (from constants)
	 * @param map Map to check if move is valid for
	 * @return Boolean
	 */
	public boolean moveIfValid(int direction, Map map) {
		setRotation(direction);
		return moveIfValid(direction2Vector(direction).scl(speed), map);
	}

	/**
	 * Move LiveEntity to position by vector if valid position
	 * @param v Vector direction to move live Entity (from constants)
	 * @param map Map to check if move is valid for
	 * @return Boolean
	 */
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

	/**
	 * Returns texture of Entity based on current rotation
	 * Different texture whether attacking or not
	 * @return Texture texture
	 */
	@Override
	public Texture getTexture() {
		if (attacking) {
			return this.attackSprite[this.getRotation()];
		} else {
			return this.sprite[this.getRotation()];
		}
	}

	/**
	 * Returns width of sprite (texture) for current rotation and
	 * whether attacking or not as different sizes
	 * @return Integer width of sprite
	 */
	@Override
	public int getWidth() {
		if (attacking) {
			return this.attackSprite[this.getRotation()].getWidth();
		} else {
			return this.sprite[this.getRotation()].getWidth();
		}
	}

	/**
	 * Returns width of sprite (texture) for current rotation and
	 * whether attacking or not as different sizes
	 * @return Integer width of sprite
	 * @param Integer rotation (from rotation constants)
	 */
	@Override
	public int getWidth(int sprite) {
		assert (this.sprite.length > sprite);
		if (attacking) {
			return this.attackSprite[sprite].getWidth();
		} else {
			return this.sprite[sprite].getWidth();
		}
	}

	/**
	 * Returns height of sprite for current rotation and whether
	 * attacking or not as will be different sizes
	 * @return Integer height
	 */
	@Override
	public int getHeight() {
		if (attacking) {
			return this.attackSprite[this.getRotation()].getHeight();
		} else {
			return this.sprite[this.getRotation()].getHeight();
		}
	}

	/**
	 * Returns height of sprite (texture) for a given rotation
	 * and whether attacking or not as will be different sizes
	 * @return Integer height of sprite
	 */
	@Override
	public int getHeight(int sprite) {
		assert (this.sprite.length > sprite);
		if (attacking) {
			return this.attackSprite[sprite].getHeight();
		} else {
			return this.sprite[sprite].getHeight();
		}
	}

	// -------- STANDARD getters/ setters --------
	
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

}
