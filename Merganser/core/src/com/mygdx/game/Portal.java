package com.mygdx.game;

import com.badlogic.gdx.math.Rectangle;

public class Portal extends MapFeature{
	private Map exit;
	public Portal(Rectangle box, Map to) {
		super(box, false,false,false);
		this.exit = to;
	}

	public Map getDestination() {
		return this.exit;
	}
}