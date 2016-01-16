package com.mygdx.game;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Portal extends MapFeature, allowing the user duck to be transported from
 * one place to another on the maps 
 */
public class Portal extends MapFeature {
	private Map exit;
	private int exitRef; // Hold this when loading maps in so all maps can be
						// loaded before linked in
	private Vector2 exitLocal;

	public Portal(Rectangle box, int to, Vector2 exitLocal) {
		super(box, false, false, false);
		this.exit = null;
		this.exitRef = to;
		this.exitLocal = exitLocal;
	}

	public Map getDestination() {
		return this.exit;
	}

	public int getRef() {
		return this.exitRef;
	}

	public void setDestination(Map destination) {
		this.exit = destination;

	}

	public Vector2 getExitLocal() {
		// TODO Auto-generated method stub
		return this.exitLocal;
	}
}