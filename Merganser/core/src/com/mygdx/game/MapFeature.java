package com.mygdx.game;

import com.badlogic.gdx.math.Rectangle;

public class MapFeature {
	private boolean groundImpeedence;
	private boolean flightImpeedence;
	private boolean isWater;
	private Rectangle box;

	public MapFeature(Rectangle box, boolean groundImpeedence, boolean flightImpeedence, boolean isWater) {
		this.box = box;
		this.setGroundImpeedence(groundImpeedence);
		this.setFlightImpeedence(flightImpeedence);
		this.setWater(isWater);
	}

	public Rectangle getBox() {
		return this.box;
	}

	public boolean isGroundImpeedence() {
		return groundImpeedence;
	}

	public void setGroundImpeedence(boolean groundImpeedence) {
		this.groundImpeedence = groundImpeedence;
	}

	public boolean isWater() {
		return isWater;
	}

	public void setWater(boolean isWater) {
		this.isWater = isWater;
	}

	public boolean isFlightImpeedence() {
		return flightImpeedence;
	}

	public void setFlightImpeedence(boolean flightImpeedence) {
		this.flightImpeedence = flightImpeedence;
	}

}
