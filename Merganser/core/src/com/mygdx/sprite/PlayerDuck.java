package com.mygdx.sprite;

import java.io.Console;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Map;
import com.mygdx.game.MapFeature;

public class PlayerDuck extends LiveEntity {
	
	private int screenHeight = Gdx.graphics.getHeight();
	private int screenWidth = Gdx.graphics.getWidth();
	private int score = 0;
	private int stamina = 100;
	private Sound quack = null;
	
	//private Vector2 position <-not needed because position in stored in hitBox inherited from Entity
	

	
	public PlayerDuck() {
		this.canSwim = true;
		this.sprite = new Texture[4];
		this.sprite[this.UP] = new Texture("large_duck.png");
		this.sprite[this.DOWN] = new Texture("large_duck_down.png");
		this.sprite[this.LEFT] = new Texture("large_duck_left.png");
		this.sprite[this.RIGHT] = new Texture("large_duck_right.png");
		this.setPosition(new Vector2(20, (screenHeight / 2) - (this.getHeight() / 2)));
		this.setHealth(12);
		this.setMaxHealth(12);
		this.speed=3;
		this.quack = Gdx.audio.newSound(Gdx.files.internal("duckQuack.mp3"));
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
	
	//public int getSpriteWidth(int rotation){
	//use getWidth inherited from entity
	
	//public int getSpriteHeight(int rotation){
	//use getHieght inherited from entity

	public void quack() {
		this.quack.play();
	}

}
