package com.mygdx.sprite;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class PlayerDuck extends LiveEntity {
	
	private int screenHeight = Gdx.graphics.getHeight();
	private int screenWidth = Gdx.graphics.getWidth();
	private int score = 0;
	private int stamina = 100;
	
	public PlayerDuck() {
		this.sprite = new Texture[4];
		this.sprite[0] = new Texture("large_duck.png");
		this.sprite[1] = new Texture("large_duck_down.png");
		this.sprite[2] = new Texture("large_duck_left.png");
		this.sprite[3] = new Texture("large_duck_right.png");
		this.position = new Vector2(20, (screenHeight / 2) - (this.sprite[0].getHeight() / 2));
		this.setHealth(12);
		this.setMaxHealth(12);
		this.speed=2;
	}
	
	public void setposition(int x, int y){
		this.position.set(x, y);
	}
	
	public void incPosition(int x, int y){
		this.position.x += x;
		this.position.y += y;
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

	public int getStamina() {
		return stamina;
	}

	public void setStamina(int stamina) {
		this.stamina = stamina;
	}
	
	public int getSpriteWidth(int i){
		return this.sprite[i].getWidth();
	}
	
	public int getSpriteHeight(int i){
		return this.sprite[i].getHeight();
	}
	
	
}
