package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.sprite.PlayerDuck;
import com.mygdx.sprite.Repeatable;

/**
 * Class to contain an individual map and its background
 */
public class Map {
	private Texture background;
	private String name;
	private MapFeature[] features;
	private Portal[] portals; // portals take you to a different map
	// swimming and flying will be needed later to calculate stamina use
	private Vector3 globalPosition;

	private Repeatable[] enemies;

	/**
	 * Constructor, A map should only be constructed in the maploader class
	 * (with the exception of testing)
	 * 
	 * @param name
	 * @param background
	 * @param features
	 * @param portals
	 * @param globalPosition
	 */
	public Map(String name, Texture background, MapFeature[] features, Portal[] portals, Vector3 globalPosition,
			Repeatable[] enemies) {
		this.name = name;
		this.background = background;
		this.features = features;
		this.portals = portals;
		this.globalPosition = globalPosition;
		this.enemies = enemies;
	}

	/**
	 * Getter for the background texture to be used by the batch drawer Could do
	 * with the map implementing itself on the batch in a separate function in
	 * Map which takes batch as parameter
	 * 
	 * @return Texture
	 */
	public Texture getBackground() {
		return background;
	}

	/**
	 * function as yet unused but could be a useful features if the background
	 * became interactive to set the background
	 * 
	 * @param background
	 */
	private void setBackground(Texture background) {
		this.background = background;
	}

	/**
	 * gets the destination of a portal from portals array with reference
	 * portalNumber
	 * 
	 * @param portalNumber
	 * @return Map
	 */
	public Map getPortalDestination(int portalNumber) {
		return portals[portalNumber].getDestination();
	}

	/**
	 * setter for portals (more description needed)
	 * 
	 * @param portals
	 */
	public void setPortals(Portal[] portals) {
		this.portals = portals;
	}

	/**
	 * function to assign the destination a a portal included in this map Not in
	 * constructor because the Map reference in a portal has to be linked after
	 * all maps are constructed
	 * 
	 * @param portal
	 * @param destination
	 */
	public void setPortalExit(int portal, Map destination) {
		this.portals[portal].setDestination(destination);
	}

	/**
	 * function to get the list of map features from map
	 * 
	 * @return MapFeature[]
	 */
	private MapFeature[] getMapFeatures() {
		return this.features;
	}

	/**
	 * function gets a list of references to set up the map graph
	 * 
	 * @return Integer
	 */
	public int[] getPortalRefs() {
		int[] references = new int[this.portals.length];
		for (int i = 0; i < references.length; i++) {
			references[i] = this.portals[i].getRef();
		}
		return references;
	}

	/**
	 * Gets a count of the portals in this map
	 * 
	 * @return Integer
	 */
	public int getPortalNo() {
		return this.portals.length;
	}

	/**
	 * Function to say weather a box (rectangle) is valid in this map taking
	 * into account whether the duck is on the ground, flying or possible to
	 * swim
	 * 
	 * @param hitbox
	 * @param flying
	 * @param canSwim
	 * @return Boolean
	 */
	public boolean validSpace(Rectangle hitbox, boolean flying, boolean canSwim) {
		for (int i = 0; i < features.length; i++) {
			MapFeature feature = this.features[i];
			if (hitbox.overlaps(feature.getBox())) {
				if ((feature.isGroundImpeedence() && !flying) || (feature.isFlightImpeedence() && flying)
						|| (feature.isWater() && !canSwim)) {
					return false;
				}
			}
		}

		return true;
	}

	/**
	 * function to show if an object (rectangle) can land given a box and if it can land on
	 * water
	 * 
	 * @return Boolean
	 */
	public boolean canLand(Rectangle hitbox, boolean canSwim) {
		return this.validSpace(hitbox, false, canSwim);
	}

	/**
	 * function showing if object is touching water
	 * 
	 * @param hitbox
	 * @return Boolean
	 */
	public boolean isSwimming(Rectangle hitbox) { // Only use this if not flying
		int i = 0;
		while (i < this.features.length) {
			if (this.features[i].isWater()) {
				if (hitbox.overlaps(this.features[i].getBox())) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Function that returns the map that should be displayed given the position
	 * of the duck box
	 * 
	 * @param duck
	 * @return Map
	 */
	public Map managePortals(PlayerDuck duck) {
		int change = shouldChangeMap(duck.getHitBox());
		if (change > -1) {
			// duck.setposition(portal.destinationPosition)
			duck.portalJumpTo(this.portals[change].getExitLocal());
			return this.portals[change].getDestination();
		} else {
			return this;
		}
	}

	/**
	 * Function that loops through portals checking their hitbox against the
	 * playerDucks Returns overlapping portal reference in list of portals,
	 * otherwise -1
	 * 
	 * @param hitbox
	 * @return Integer
	 */
	private int shouldChangeMap(Rectangle hitbox) {
		for (int i = 0; i < this.portals.length; i++) {
			if (hitbox.overlaps(this.portals[i].getBox())) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Returns globalPosition vector
	 * 
	 * @return Vector3
	 */
	public Vector3 getGlobalPosition() {
		return globalPosition;
	}

	/**
	 * Sets globalPosition vector
	 * 
	 * @param Vector3 globalPosition
	 */
	public void setGlobalPosition(Vector3 globalPosition) {
		this.globalPosition = globalPosition;
	}

	/**
	 * Returns the name/description of the map
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * Updates enemies in enemies array 
	 * Calls repeatable update function
	 * @param duck
	 */
	public void updateEnemies(PlayerDuck duck) {
		for (int enemyIterator = 0; enemyIterator < enemies.length; enemyIterator++) {
			enemies[enemyIterator].update(duck, this);
		}
	}

	/**
	 * Renders the map background and enemies to SpriteBatch
	 * @param batch
	 */
	public void draw(SpriteBatch batch) {
		batch.draw(this.getBackground(), 0, 0);
		for (int enemyIterator = 0; enemyIterator < enemies.length; enemyIterator++) {
			enemies[enemyIterator].draw(batch);
		}
	}

}
