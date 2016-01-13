package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.sprite.PlayerDuck;

public class Map {
	private Texture background;
 
	/*private Rectangle[] walls;    //walls impede movement
	private Rectangle[] blocks;   //blocks impede movement, unless you're flying
	private Rectangle[] liquids;  //liquids cause you to be swimming, if you can swim
	*/
	private MapFeature[] features;
	private Portal[] portals;//portals take you to a different map
	//swimming and flying will be needed later to calculate stamina use
	
	public Map(Texture background, MapFeature[] features, Portal[] portals){
		this.background = background;
		this.features = features;
		this.portals = portals;
	}
	
	public Texture getBackground() {
		return background;
	}
	
	//should this be included in the constructor?
	public void setBackground(Texture background) {
		this.background = background;
	}
	public Rectangle getPortalBox(int portalNumber) {
		return portals[portalNumber].getBox();
	}
	public Map getPortalDestination(int portalNumber){
		return portals[portalNumber].getDestination();
	}
	public void setPortals(Portal[] portals) {
		this.portals = portals;
	}

	
	//should be in constructor
	public void setPortalExit(int portal, Map destination) {
		this.portals[portal].setDestination(destination);
	}
	public MapFeature[] getMapFeatures() {
		return this.features;
	}

	//function gets a list of references to set up the map graph
	public int[] getPortalRef(){
		int[] references = new int[this.portals.length];
		for(int i=0; i<references.length;i++){
			references[i] = this.portals[i].getRef();
		}
		return references;
	}
	
	public int getPortalNo(){
		return this.portals.length;
	}
	
	
	public boolean validSpace(Rectangle hitbox, boolean flying, boolean canSwim){
		for(int i=0;i<features.length;i++){
			MapFeature feature = this.features[i];
			if(hitbox.overlaps(feature.getBox())){
				//this needs explaining
				if((feature.groundImpeedence && !flying) || (feature.flightImpeedence && flying) || (feature.isWater &&  !canSwim)){
					return false;
				}
			}
		}
		return true;
	}
	
	public boolean canLand(Rectangle hitbox, boolean canSwim){
		return this.validSpace(hitbox, false, canSwim);
	}
	
	public boolean isSwimming(Rectangle hitbox){   //Only use this if not flying
		int i = 0;
		while (i < this.features.length){
			if(this.features[i].isWater){
				if(hitbox.overlaps(this.features[i].getBox())){
					return true;
				}
			}
		}
		return false;
	}
	
	
	public Map managePortals(PlayerDuck duck){
		int change = shouldChangeMap(duck.getHitbox());
		if(change>-1){
			//duck.setposition(portal.destinationPosition)
			return this.portals[change].getDestination();
		}else{
			return this;
		}
	}
	private int shouldChangeMap(Rectangle hitbox){
		for(int i = 0; i < this.portals.length; i++){
			if(hitbox.overlaps(this.portals[i].getBox())){
				return i;
			}
		}
		return -1;
	}
	
}
