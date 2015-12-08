package com.mygdx.sprite;

import com.badlogic.gdx.graphics.Texture;

public class Repeatable extends LiveEntity{
	protected int attack;
	
	public Repeatable(){
		this.sprite = new Texture[4];
		this.sprite[0] = new Texture("large_duck.png");
		this.sprite[1] = new Texture("large_duck_down.png");
		this.sprite[2] = new Texture("large_duck_left.png");
		this.sprite[3] = new Texture("large_duck_right.png");
	}
	
	public boolean attack(){
		return true;
	}
}
