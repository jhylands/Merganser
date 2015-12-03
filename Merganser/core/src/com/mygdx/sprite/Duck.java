package com.mygdx.sprite;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Duck extends LiveEntity {
	
	private int screenHeight = Gdx.graphics.getHeight();
	private int screenWidth = Gdx.graphics.getWidth();
	private int score = 0;
	
	
	private Texture[] duck = {new Texture("large_duck.png"),new Texture("large_duck_down.png"),new Texture("large_duck_left.png"),new Texture("large_duck_right.png")};
	private Vector2 duckLocation = new Vector2(20, (screenHeight / 2) - (duck[0].getHeight() / 2));
	private int Rotation = 0;
	
	public Duck() {
		this.setHealth(12);
		this.setMaxHealth(12);
	}

	public Vector2 getDuckLocation(){
		return duckLocation;
	}
	
	public Texture getTexture(){
		return duck[this.Rotation];
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
		if (Gdx.input.isKeyPressed(Keys.W) && (duckLocation.y < (screenHeight - 21 - duck[0].getHeight()))) {
			duckLocation.y += getSpeed();
			this.Rotation = 0;
		}else if (Gdx.input.isKeyPressed(Keys.S) && (duckLocation.y > 0)) {
			duckLocation.y -= getSpeed();
			this.Rotation = 1;
		}else if (Gdx.input.isKeyPressed(Keys.A) && (duckLocation.x > 0)) {
			duckLocation.x -= getSpeed();
			this.Rotation=2;
		}else if (Gdx.input.isKeyPressed(Keys.D) && (duckLocation.x < (screenWidth - duck[3].getWidth()))) {
			duckLocation.x += getSpeed();
			this.Rotation=3;}
		else if (Gdx.input.isKeyPressed(Keys.RIGHT) && (duckLocation.x < (screenWidth - duck[3].getWidth()))) {
			score += 1;
			this.Rotation=3;
		}else if (Gdx.input.isKeyPressed(Keys.LEFT) && (duckLocation.x < (screenWidth - duck[3].getWidth()))) {
			score -= 1;
			this.Rotation=3;
		}else if (Gdx.input.isKeyPressed(Keys.UP) && (duckLocation.x < (screenWidth - duck[3].getWidth()))) {
			if(this.getHealth() < this.getMaxHealth()){
				this.setHealth(this.getHealth() + 1);
			}
		}else if (Gdx.input.isKeyPressed(Keys.DOWN) && (duckLocation.x < (screenWidth - duck[3].getWidth()))) {
			if(this.getHealth() > 0){
				this.setHealth(this.getHealth() - 1);
			} //tests for health and score. use arrow keys.
		}
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	public void addScore(int score){
		this.score += score;
	}
	
	
	
	
}
