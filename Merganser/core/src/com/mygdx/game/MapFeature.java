// Download the .Jar file at http://merganser.weebly.com/ or directly on https://drive.google.com/file/d/0B_xhR6pi2K8Kc2Q1MFJrVDY0WVE/view?usp=sharing
package com.mygdx.game;

import com.badlogic.gdx.math.Rectangle;
/**
 * MapFeature defines a feature on the map
 * Map is built up of MapFeatures 
 *
 */
public class MapFeature {
	// Boolean values for impedance.
	// If true then duck/ entity will be impeded
	// from travelling through/ on MapFeature
	private boolean groundImpeedence;
	private boolean flightImpeedence;
	private boolean isWater;
	
	private Rectangle box;

	/**
	 * Constructor for MapFeature.
	 * Sets ground Impedance, flight Impedance and if water boolean values for MapFeature
	 * @param box Rectangle
	 * @param groundImpeedence Boolean
	 * @param flightImpeedence Boolean
	 * @param isWater Boolean
	 */
	public MapFeature(Rectangle box, boolean groundImpeedence, boolean flightImpeedence, boolean isWater) {
		this.box = box;
		this.setGroundImpeedence(groundImpeedence);
		this.setFlightImpeedence(flightImpeedence);
		this.setWater(isWater);
	}

	/**
	 * Returns the rectangle box of mapFeature
	 * @return Rectangle
	 */
	public Rectangle getBox() {
		return this.box;
	}

	/**
	 * Return true if ground is impeded
	 * @return Boolean
	 */
	public boolean isGroundImpeedence() {
		return groundImpeedence;
	}

	/**
	 * Sets groundImpeance
	 * @param groundImpeedence Boolean
	 */
	public void setGroundImpeedence(boolean groundImpeedence) {
		this.groundImpeedence = groundImpeedence;
	}

	/**
	 * Returns true if mapFeature water
	 * @return
	 */
	public boolean isWater() {
		return isWater;
	}

	/**
	 * Sets water boolean. True if mapFeature is water.
	 * @param isWater Boolean
	 */
	public void setWater(boolean isWater) {
		this.isWater = isWater;
	}

	/**
	 * Returns true if MapFeature impedes flight 
	 * @return Boolean
	 */
	public boolean isFlightImpeedence() {
		return flightImpeedence;
	}

	/**
	 * Sets flightImpedance boolean - true if flight is impeded
	 * @param flightImpeedence Boolean
	 */
	public void setFlightImpeedence(boolean flightImpeedence) {
		this.flightImpeedence = flightImpeedence;
	}

}
