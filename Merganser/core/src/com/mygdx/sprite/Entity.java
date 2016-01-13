package com.mygdx.sprite;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Entity {
	private Rectangle hitbox = new Rectangle();
	protected int rotation = 0;
	protected Texture[] sprite;
	public String name;
	public static final int UP =0;
	public int RIGHT = 1;
	public int DOWN = 2;
	public int LEFT = 3;
	
	public Rectangle getHitBox(){
		//update the hitbox for current values
		this.hitbox.setSize(this.getWidth(), this.getHeight());
		return hitbox;
	}
	
	protected Rectangle getHypertheticalHitBox(Vector2 position){
		//local position not to be confused with classwide position
		return new Rectangle(position.x,position.y,getWidth(),getHeight());
	}
	
	//POSITION ---------------------------------------------------------------------------------
	public Vector2 getPosition(){
		return new Vector2(this.hitbox.x,this.hitbox.y);//to return by value rather than by reference
	}
	protected void setPosition(int x, int y){
		this.hitbox.setPosition(x, y);
	}
	protected void setPosition(Vector2 x){
		this.hitbox.setPosition(x);
	}
	protected void changePosition(int x, int y){
		setPosition( getPosition().add(x, y));
	}
	protected void changePosition(Vector2 v){
		setPosition(getPosition().add(v));
	}
	//END POSITION -------------------------------------------------------------------------------
	public Texture getTexture(){
		return this.sprite[this.rotation];
	}
	public int getWidth(){
		return this.sprite[this.rotation].getWidth();
	}
	public int getWidth(int sprite){
		assert(this.sprite.length>sprite);
		return this.sprite[sprite].getWidth();
	}
	
	public int getHeight(){
		return this.sprite[this.rotation].getHeight();
	}

	public int getHeight(int sprite){
		assert(this.sprite.length>sprite);
		return this.sprite[sprite].getHeight();
	}
	public void setRotation(int rotation){
		this.rotation = rotation;
	}

	public int getRotation() {
		return rotation;
	}
}
