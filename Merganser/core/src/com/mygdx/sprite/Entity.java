package com.mygdx.sprite;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Entity {
	protected Vector2 position;
	protected int rotation = 0;
	protected Texture[] sprite;
	public String name;
	public int UP =0;
	public int Right = 1;
	public int DOWN = 2;
	public int LEFT = 3;
	
	public Vector2 getPosition(){
		return new Vector2(this.position.x,this.position.y);//to return by value rather than by reference
	}
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
