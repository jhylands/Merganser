package com.mygdx.sprite;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Repeatable extends LiveEntity{
	protected int attack;
	
	public Repeatable(int speed){
		this.sprite = new Texture[4];
		this.sprite[0] = new Texture("large_duck.png");
		this.sprite[1] = new Texture("large_duck_down.png");
		this.sprite[2] = new Texture("large_duck_left.png");
		this.sprite[3] = new Texture("large_duck_right.png");
		this.speed = speed;
		this.setPosition(new Vector2(50,50));
	}
	

	// update the ducks position by the direction towards the player multiplied by the ducks speed
	public void move(PlayerDuck duck){
		if(duck.getPosition().sub(this.getPosition()).len2()<10000){
			this.changePosition( this.findDirection(duck).scl(this.speed) );
		}
	}
	
	public Vector2 findDirection(PlayerDuck duck){
		return duck.getPosition().sub(this.getPosition()).nor();
	}
	
}
