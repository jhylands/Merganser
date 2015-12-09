package com.mygdx.sprite;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Entity {
	protected Vector2 position;
	protected int rotation = 0;
	protected Texture[] sprite;
	public String name;
	
	public Vector2 getPosition(){
		return this.position;
	}
	public Texture getTexture(){
		return this.sprite[this.rotation];
	}
	public int getWidth(){
		return this.sprite[this.rotation].getWidth();
	}
	
	public int getHeight(){
		return this.sprite[this.rotation].getHeight();
	}
}
