package com.mygdx.game;

import com.badlogic.gdx.math.Rectangle;

public class MapFeature {
	public boolean groundImpeedence;
	public boolean flightImpeedence;
	public boolean isWater;
	private Rectangle box;
	
	public MapFeature(Rectangle box, boolean groundImpeedence,boolean flightImpeedence, boolean isWater){
		this.box=box;
		this.groundImpeedence = groundImpeedence;
		this.flightImpeedence = flightImpeedence;
		this.isWater = isWater;
	}
	public Rectangle getBox(){
		return this.box;
	}
	
}
