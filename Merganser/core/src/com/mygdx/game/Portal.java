package com.mygdx.game;

import com.badlogic.gdx.math.Rectangle;

public class Portal extends MapFeature{
	private Map exit;
	public int exitRef; //Hold this when loading maps in so all maps can be loaded before linked in
	
	public Portal(Rectangle box, Map to) {
		super(box, false,false,false);
		this.exit = to;
	}
	public Portal(Rectangle box, int to){
		super(box, false,false,false);
		this.exit = null;
		this.exitRef = to;
	}
	public Map getDestination() {
		return this.exit;
	}
	public int getRef(){
		return this.exitRef;
	}
	public void setDestination(Map destination) {
		this.exit = destination;
		
	}
}