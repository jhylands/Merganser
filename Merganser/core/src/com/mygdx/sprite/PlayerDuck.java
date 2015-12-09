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
		this.position = new Vector2(x,y);
	}
	
	//why does the name of this void function start with get?
	public void getMovement() {
		if (Gdx.input.isKeyPressed(Keys.W) && (this.position.y < (screenHeight - 21 - this.sprite[0].getHeight()))) {
			this.position.y += getSpeed();
			this.rotation = 0;
		}else if (Gdx.input.isKeyPressed(Keys.S) && (this.position.y > 0)) {
			this.position.y -= getSpeed();
			this.rotation = 1;
		}else if (Gdx.input.isKeyPressed(Keys.A) && (this.position.x > 0)) {
			this.position.x -= getSpeed();
			this.rotation=2;
		}else if (Gdx.input.isKeyPressed(Keys.D) && (this.position.x < (screenWidth - this.sprite[3].getWidth()))) {
			this.position.x += getSpeed();
			this.rotation=3;}
		else if (Gdx.input.isKeyPressed(Keys.RIGHT) && (this.position.x < (screenWidth - this.sprite[3].getWidth()))) {
			score += 1;
		}else if (Gdx.input.isKeyPressed(Keys.LEFT) && (this.position.x < (screenWidth - this.sprite[3].getWidth()))) {
			score -= 1;
		}else if (Gdx.input.isKeyPressed(Keys.UP) && (this.position.x < (screenWidth - this.sprite[3].getWidth()))) {
			if(this.getHealth() < this.getMaxHealth()){
				this.setHealth(this.getHealth() + 1);
			}
		}else if (Gdx.input.isKeyPressed(Keys.DOWN) && (this.position.x < (screenWidth - this.sprite[3].getWidth()))) {
			if(this.getHealth() > 0){
				this.setHealth(this.getHealth() - 1);
			} //tests for health and score. use arrow keys.
		}
		else if(Gdx.input.isKeyPressed(Keys.NUM_9)){
			this.setStamina(stamina += 1);
		}
		else if(Gdx.input.isKeyPressed(Keys.NUM_0)){
			this.setStamina(stamina -= 1);
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

	public int getStamina() {
		return stamina;
	}

	public void setStamina(int stamina) {
		this.stamina = stamina;
	}
	
	
	
	
}
