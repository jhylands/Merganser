package com.mygdx.sprite;

import java.io.Console;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Map;

public class PlayerDuck extends LiveEntity {
	
	private int screenHeight = Gdx.graphics.getHeight();
	private int screenWidth = Gdx.graphics.getWidth();
	private int score = 0;
	private int stamina = 100;
	private boolean flying = false;
	private boolean canSwim = true;
	//private Vector2 position <-not needed because position in stored in hitBox inherited from liveEntity
	
	
	public PlayerDuck() {
		this.sprite = new Texture[4];
		this.sprite[this.UP] = new Texture("large_duck.png");
		this.sprite[this.DOWN] = new Texture("large_duck_down.png");
		this.sprite[this.LEFT] = new Texture("large_duck_left.png");
		this.sprite[this.Right] = new Texture("large_duck_right.png");
		this.setHitBox(new Rectangle(0,0,10,10));
		this.hitbox.setPosition(new Vector2(20, (screenHeight / 2) - (this.sprite[0].getHeight() / 2)));
		this.setHealth(12);
		this.setMaxHealth(12);
		this.speed=2;
	}
	
	private void move(Vector2 positionChange,int rotation, Map map){
		//create a tempry box to hold the new state
		Rectangle testBox = new Rectangle();
		//set the tempery box to the current box for the playerduck
		testBox.set(0,0,this.getWidth(rotation),this.getHeight(rotation));
		//add the movement to the tempery box
		testBox.setPosition(this.getPosition().add(positionChange));
		if(map.validSpace(testBox, this.flying, this.canSwim)){
			//update the playerduck's position with that of the temperybox
			this.setPosition(testBox.getPosition(new Vector2()));
			this.setRotation(rotation);
		}
	}
	

	//why does the name of this void function start with get?
	public void getMovement(Map map) {
		if (Gdx.input.isKeyPressed(Keys.W) ) {
			this.move(new Vector2(0,this.speed), this.UP, map);
		}else if (Gdx.input.isKeyPressed(Keys.S) ) {
			this.move(new Vector2(0,-this.speed), this.DOWN, map);
		}else if (Gdx.input.isKeyPressed(Keys.A) ) {
			this.move(new Vector2(-this.speed,0), this.LEFT, map);
		}else if (Gdx.input.isKeyPressed(Keys.D)) {
			this.move(new Vector2(this.speed,0), this.Right, map);
		}
		
		
		//other keyinputs
		if (Gdx.input.isKeyPressed(Keys.RIGHT) ) {
			score += 1;
		}else if (Gdx.input.isKeyPressed(Keys.LEFT) ) {
			score -= 1;
		}else if (Gdx.input.isKeyPressed(Keys.UP) ) {
			if(this.getHealth() < this.getMaxHealth()){
				this.setHealth(this.getHealth() + 1);
			}
		}else if (Gdx.input.isKeyPressed(Keys.DOWN) ) {
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
