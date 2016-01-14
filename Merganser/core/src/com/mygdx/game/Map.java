package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.sprite.PlayerDuck;

/**
 *Class to contain an indevidual map and its backgoriund 
 * @author james
 *
 */
public class Map {
	private Texture background;
	private String name;
	private MapFeature[] features;
	private Portal[] portals;// portals take you to a different map
	// swimming and flying will be needed later to calculate stamina use
	private Vector3 globalPosition;
	
	/**
	 * Constructor, A map should only be constructed in the maploader class (with the exception of testing)
	 * @param name
	 * @param background
	 * @param features
	 * @param portals
	 * @param globalPosition
	 */
	public Map(String name, Texture background, MapFeature[] features, Portal[] portals, Vector3 globalPosition) {
		this.name = name;
		this.background = background;
		this.features = features;
		this.portals = portals;
		this.globalPosition = globalPosition;
	}

	/**
	 * Getter for the background texture to be used by the batch drawer
	 * Could do with the map implementing itself on the batch in a seperate function in Map which takes batch as parametor
	 * 
	 * @return Texture
	 */
	public Texture getBackground() {
		return background;
	}

	/**
	 * function as yet unused but could be a useful features if the background became interactive
	 * @param background
	 */
	private void setBackground(Texture background) {
		this.background = background;
	}
	
	/**
	 * gets the destination of a portal with reference portalNumber
	 * @param portalNumber
	 * @return
	 */
	public Map getPortalDestination(int portalNumber) {
		return portals[portalNumber].getDestination();
	}

	/**
	 * setter for portals (more description needed)
	 * @param portals
	 */
	public void setPortals(Portal[] portals) {
		this.portals = portals;
	}

	/**
	 * function to assign the destination a a paortal included in this map
	 * Not in constructor because the Map reference in a portal has to be linked after all make are constructed
	 * @param portal
	 * @param destination
	 */
	public void setPortalExit(int portal, Map destination) {
		this.portals[portal].setDestination(destination);
	}

	/**
	 * function to get the list of map features
	 * @return
	 */
	private MapFeature[] getMapFeatures() {
		return this.features;
	}

	/**
	 *  function gets a list of references to set up the map graph
	 * @return
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
	 * @return
	 */
	public int getPortalNo() {
		return this.portals.length;
	}
	
	/**
	 * function to say weather a box is valid in this map
	 * @param hitbox
	 * @param flying
	 * @param canSwim
	 * @return
	 */
	public boolean validSpace(Rectangle hitbox, boolean flying, boolean canSwim) {
		for (int i = 0; i < features.length; i++) {
			MapFeature feature = this.features[i];
			if (hitbox.overlaps(feature.getBox())) {
				// this needs explaining
				if ((feature.groundImpeedence && !flying) || (feature.flightImpeedence && flying)
						|| (feature.isWater && !canSwim)) {
					return false;
				}
			}
		}

		return true;
	}
	
	/**
	 * function to show if an object can land given a box and if it can land on water
	 */
	public boolean canLand(Rectangle hitbox, boolean canSwim) {
		return this.validSpace(hitbox, false, canSwim);
	}

	/**
	 * function showing if object is touching water
	 * @param hitbox
	 * @return
	 */
	public boolean isSwimming(Rectangle hitbox) { // Only use this if not flying
		int i = 0;
		while (i < this.features.length) {
			if (this.features[i].isWater) {
				if (hitbox.overlaps(this.features[i].getBox())) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Function that returns the map that should be displayed given the position of the duck box
	 * @param duck
	 * @return
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

	// loops through portals checking their hitbox against the playerducks
	private int shouldChangeMap(Rectangle hitbox) {
		for (int i = 0; i < this.portals.length; i++) {
			if (hitbox.overlaps(this.portals[i].getBox())) {
				return i;
			}
		}
		return -1;
	}

	public Vector3 getGlobalPosition() {
		return globalPosition;
	}

	public void setGlobalPosition(Vector3 globalPosition) {
		this.globalPosition = globalPosition;
	}
	
	public String getName(){
		return name;
	}

}
