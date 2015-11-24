package com.mygdx.sprite;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Duck {
	
	private int screenHeight = Gdx.graphics.getHeight();
	private int screenWidth = Gdx.graphics.getWidth();
	private int duckSpeed = 2;
	
	
	
	
	private Texture[] duck = {new Texture("large_duck.png"),new Texture("large_duck_down.png"),new Texture("large_duck_left.png"),new Texture("large_duck_right.png")};
	private Vector2 duckLocation = new Vector2(20, (screenHeight / 2) - (duck[0].getHeight() / 2));
	private int Rotation = 0;
	public Vector2 getDuckLocation(){
		return duckLocation;
	}
	
	public Texture getTexture(){
		return duck[this.Rotation];
	}

	public int getDuckSpeed() {
		return duckSpeed;
	}

	public void setDuckSpeed(int duckSpeed) {
		this.duckSpeed = duckSpeed;
	}
	
	public void setDuckLocation(int x, int y){
		this.duckLocation = new Vector2(x,y);
	}
	
	public int getWidth(){
		return duck[this.Rotation].getWidth();
	}
	
	public int getHeight(){
		return duck[this.Rotation].getHeight();
	}
	
	public void getDuckMovement() {
		if (Gdx.input.isKeyPressed(Keys.W) && (duckLocation.y < (screenHeight - getHeight()))) {
			duckLocation.y += duckSpeed;
			this.Rotation = 0;
		}else if (Gdx.input.isKeyPressed(Keys.S) && (duckLocation.y > 0)) {
			duckLocation.y -= duckSpeed;
			this.Rotation = 1;
		}else if (Gdx.input.isKeyPressed(Keys.A) && (duckLocation.x > 0)) {
			duckLocation.x -= duckSpeed;
			this.Rotation=2;
		}else if (Gdx.input.isKeyPressed(Keys.D) && (duckLocation.x < (screenWidth - duck[3].getWidth()))) {
			duckLocation.x += duckSpeed;
			this.Rotation=3;
		}
	}
	
	
}
