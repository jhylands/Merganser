package com.mygdx.sprite;

import java.io.Console;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Sound;
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
	private Sound quack = null;
	
	//private Vector2 position <-not needed because position in stored in hitBox inherited from liveEntity
	
	public boolean isflying(){
		return flying;
	}
	
	public boolean canswim(){
		return canSwim;
	}
	
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
		this.quack = Gdx.audio.newSound(Gdx.files.internal("duckQuack.mp3"));
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

	public void quack() {
		this.quack.play();
	}
	
	
}
