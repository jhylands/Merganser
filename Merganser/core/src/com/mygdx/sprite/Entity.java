// Download the .Jar file at http://merganser.weebly.com/ or directly on https://drive.google.com/file/d/0B_xhR6pi2K8Kc2Q1MFJrVDY0WVE/view?usp=sharing
package com.mygdx.sprite;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Top level class for encapsulating an Entity.
 *
 */
public class Entity {
	/**
	 * Rectangle Hitbox for entity
	 */
	private Rectangle hitbox = new Rectangle(0, 0, 0, 0);
	
	/**
	 * Rotation of entity
	 */
	private  int rotation = 0;
	
	/**
	 *  Texture of entity. Array as has different sprites depending on rotation
	 */
	protected Texture[] sprite;
	
	/**
	 * Name of entity
	 */
	private String name;
	
	/**
	 * Constant for direction
	 */
	public final int UP = 0, RIGHT = 1, DOWN = 2, LEFT = 3;

	
	/**
	 * Turns a direction e.g. UP, DOWN, LEFT, RIGHT into a 2D vector
	 * @param direction Integer Direction from constants UP, RIGHT, DOWN and LEFT
	 * @return Vector2 Vector of direction
	 */
	public Vector2 direction2Vector(int direction) {
		switch (direction) {
		case 0: // UP
			return new Vector2(0, 1);
		case 1: // RIGHT
			return new Vector2(1, 0);
		case 2: // DOWN
			return new Vector2(0, -1);
		case 3: // LEFT
			return new Vector2(-1, 0);
		}
		return new Vector2(0, 0); // No direction
	}

	/**
	 * Update the Rectangle hitbox for current values of width and height of sprite
	 * @return Rectangle hitbox
	 */
	public Rectangle getHitBox() {
		this.hitbox.setSize(this.getWidth(), this.getHeight());
		return hitbox;
	}

	/**
	 * Returns a new hitbox based on a given position
	 * @param position 2D position as Vector2
	 * @return Rectangle hitbox
	 */
	protected Rectangle getHypertheticalHitBox(Vector2 position) {
		// local position not to be confused with classwide position
		return new Rectangle(position.x, position.y, getWidth(), getHeight());
	}

	// POSITION
	// ---------------------------------------------------------------------------------
	/**
	 * Get position of entity
	 * @return Vector2
	 */
	public Vector2 getPosition() {
		return new Vector2(this.hitbox.x, this.hitbox.y);// to return by value
															// rather than by reference
	}

	/**
	 * Set position of entity
	 * @param x
	 * @param y
	 */
	protected void setPosition(int x, int y) {
		this.hitbox.setPosition(x, y);
	}

	/**
	 * Set position of entity
	 * @param x
	 */
	protected void setPosition(Vector2 x) {
		this.hitbox.setPosition(x);
	}

	/**
	 * Change entity position by given amount
	 * @param x
	 * @param y
	 */
	protected void changePosition(int x, int y) {
		setPosition(getPosition().add(x, y));
	}

	/**
	 * Change entity position by given Vector amount
	 * @param v Vector
	 */
	protected void changePosition(Vector2 v) {
		setPosition(getPosition().add(v));
	}

	// END POSITION
	// -------------------------------------------------------------------------------
	/**
	 * Returns texture of Entity based on current rotation
	 * @return
	 */
	public Texture getTexture() {
		return this.sprite[this.rotation];
	}

	/**
	 * Returns width of sprite for current rotation
	 * @return Integer width
	 */
	public int getWidth() {
		return this.sprite[this.rotation].getWidth();
	}

	/**
	 * Returns width of sprite (texture) for a given rotation
	 * @return Integer width of sprite
	 */
	public int getWidth(int sprite) {
		assert (this.sprite.length > sprite);
		return this.sprite[sprite].getWidth();
	}

	/**
	 * Returns height of sprite for current rotation
	 * @return Integer height
	 */
	public int getHeight() {
		return this.sprite[this.rotation].getHeight();
	}

	/**
	 * Returns height of sprite (texture) for a given rotation
	 * @return Integer height of sprite
	 */
	public int getHeight(int sprite) {
		assert (this.sprite.length > sprite);
		return this.sprite[sprite].getHeight();
	}

	/**
	 * Sets the current rotation
	 * @param Integer rotation from rotation constants
	 */
	public void setRotation(int rotation) {
		this.rotation = rotation;
	}

	/**
	 * Returns the current rotation
	 * @return Integer for current rotation
	 */
	public int getRotation() {
		return rotation;
	}
}
