// Download the .Jar file at http://merganser.weebly.com/ or directly on https://drive.google.com/file/d/0B_xhR6pi2K8Kc2Q1MFJrVDY0WVE/view?usp=sharing
package com.mygdx.game;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Portal extends MapFeature, allowing the user duck to be transported from one
 * place to another on the maps
 */
public class Portal extends MapFeature {
	/**
	 * Map at which the portal exits
	 */
	private Map exit;

	/**
	 * Reference to map to exit to on travelling through portal
	 */
	private int exitRef; 	// Hold this when loading maps in so all maps can be
							// loaded before linked in

	/**
	 * Vector of exit position that playerDuck should be transported to
	 */
	private Vector2 exitLocal;

	/**
	 * Constructor for Portal
	 * 
	 * @param box
	 *            - Rectangle for portal to be in
	 * @param to
	 *            - Reference to Map to exit to
	 * @param exitLocal
	 *            - Exit coordinates for where playerDuck should be placed once
	 *            travelled through portal
	 */
	public Portal(Rectangle box, int to, Vector2 exitLocal) {
		super(box, false, false, false);
		this.exit = null;
		this.exitRef = to;
		this.exitLocal = exitLocal;
	}

	/**
	 * Return destination map
	 * 
	 * @return Map
	 */
	public Map getDestination() {
		return this.exit;
	}

	/**
	 * Return reference to exit map
	 * 
	 * @return
	 */
	public int getRef() {
		return this.exitRef;
	}

	/**
	 * Set destination Map of portal
	 * 
	 * @param destination
	 *            Map
	 */
	public void setDestination(Map destination) {
		this.exit = destination;

	}

	/**
	 * Return exit coordinates x,y of where portal should position playerDuck
	 * 
	 * @return Vector2
	 */
	public Vector2 getExitLocal() {
		return this.exitLocal;
	}
}