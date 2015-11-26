package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class map {
	private Texture background;
	private Rectangle[] portals;  //portals take you to a different map
	private map[] portalExits;    //must use the same indices as portals
	                              //|-this is annoying and can be removed, but requires a Portal class
	private Rectangle[] walls;    //walls impede movement
	private Rectangle[] blocks;   //blocks impede movement, unless you're flying
	private Rectangle[] liquids;  //liquids cause you to be swimming, if you can swim
	
	//swimming and flying will be needed later to calculate stamina use
	
	public Texture getBackground() {
		return background;
	}
	public void setBackground(Texture background) {
		this.background = background;
	}
	public Rectangle[] getPortals() {
		return portals;
	}
	public void setPortals(Rectangle[] portals) {
		this.portals = portals;
	}
	public map[] getPortalExits() {
		return portalExits;
	}
	public void setPortalExits(map[] portalExits) {
		this.portalExits = portalExits;
	}
	public Rectangle[] getWalls() {
		return walls;
	}
	public void setWalls(Rectangle[] walls) {
		this.walls = walls;
	}
	public Rectangle[] getBlocks() {
		return blocks;
	}
	public void setBlocks(Rectangle[] blocks) {
		this.blocks = blocks;
	}
	
	
	public boolean validSpace(Rectangle hitbox, boolean flying, boolean canSwim){
		if(flying == true){
			int i = 0;
			while (i < this.walls.length){
				if(hitbox.overlaps(this.walls[i])){
					return false;
				}
			}
			return true;
		} else {
			int i = 0;
			while (i < this.blocks.length){
				if(hitbox.overlaps(this.blocks[i])){
					return false;
				}
			}
			i = 0;
			if(canSwim == false){
				while (i < this.liquids.length){
					if(hitbox.overlaps(this.liquids[i])){
						return false;
					}
				}
			}
			return true;
		}
	}
	
	public boolean canLand(Rectangle hitbox, boolean canSwim){
		int i = 0;
		while (i < this.blocks.length){
			if(hitbox.overlaps(this.blocks[i])){
				return false;
			}
		}
		if(canSwim == false){
			i = 0;
			while (i < this.liquids.length){
				if(hitbox.overlaps(this.liquids[i])){
					return false;
				}
			}
		}
		return true;
	}
	
	public boolean isSwimming(Rectangle hitbox){
		int i = 0;
		while (i < this.liquids.length){
			if(hitbox.overlaps(this.liquids[i])){
				return true;
			}
		}
		return false;
	}
	
	public boolean shouldChangeMap(Rectangle hitbox){
		int i = 0;
		while (i < this.portals.length){
			if(hitbox.overlaps(this.portals[i])){
				return true;
			}
		}
		return false;
	}
	
	public map changeMap(Rectangle hitbox){
		int i = 0;
		while (i < this.portals.length){
			if(hitbox.overlaps(portals[i])){
				return this.portalExits[i];
			}
		}
		return this;  //this line shouldn't ever be reached, but it's necessary for eclipse
	}
	
}
