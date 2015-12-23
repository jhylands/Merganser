package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

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
	/*Why would you want all the exits
	public Map[] getPortalExits() {
		return portalExits;
	}*/
	
	//should be in constructor
	/*public void setPortalExits(Map[] portalExits) {
		this.portalExits = portalExits;
	}*/
	public MapFeature[] getMapFeatures() {
		return this.features;
	}
	/*Should this be in the constructor
	public void setWalls(Rectangle[] walls) {
		this.walls = walls;
	}*/

	
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
	
	public boolean shouldChangeMap(Rectangle hitbox){
		int i = 0;
		while (i < this.portals.length){
			if(hitbox.overlaps(this.portals[i].getBox())){
				return true;
			}
		}
		return false;
	}
	
	//shouldn't the code outside of this be currentMap = currentMap.getPortalMap(index) ?
	public Map changeMap(Rectangle hitbox){
		int i = 0;
		while (i < this.portals.length){
			if(hitbox.overlaps(portals[i].getBox())){
				return this.portals[i].getDestination();
			}
		}
		return this;  //this line shouldn't ever be reached, but it's necessary for eclipse
	}
	
}
