package com.mygdx.sprite;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.MyGdxGame;

public class PlayerDuck extends LiveEntity {

	private int screenHeight = Gdx.graphics.getHeight();
	private int score = 0;
	private int stamina = 100;
	private Sound quack = null;
	private int DUCKSPEED = 3;
	private int DUCKSPRINT = 6;
	private int MAXSTAM = 100;
	private int MINSTAM = 0;


	// private Vector2 position <-not needed because position in stored in
	// hitBox inherited from Entity

	public PlayerDuck(AssetManager manager) {
		this.canSwim = true;
		this.sprite = new Texture[4];
		this.sprite[this.UP] = manager.get("large_duck.png", Texture.class);
		this.sprite[this.DOWN] = manager.get("large_duck_down.png", Texture.class);
		this.sprite[this.LEFT] = manager.get("large_duck_left.png", Texture.class);
		this.sprite[this.RIGHT] = manager.get("large_duck_right.png", Texture.class);
		this.setPosition(new Vector2(20, (screenHeight / 2) - (this.getHeight() / 2)));
		this.setHealth(12);
		this.setMaxHealth(12);
		this.speed = DUCKSPEED;
		this.quack = Gdx.audio.newSound(Gdx.files.internal("duckQuack.mp3"));
	}

	public int getDUCKSPEED() {
		return DUCKSPEED;
	}
	
	public int getDUCKSPRINT() {
		return DUCKSPRINT;
	}

	public void setDUCKSPRINT(int dUCKSPRINT) {
		DUCKSPRINT = dUCKSPRINT;
	}
	
	public boolean atMaxStam (){
		return (stamina == MAXSTAM);
	}
	
	public boolean atMinStam (){
		return (stamina <= MINSTAM);
	}

	public void portalJumpTo(Vector2 v) {
		this.setPosition(v);
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public void addScore(int score) {
		this.score += score;
	}

	public int getStamina() {
		return stamina;
	}

	public void setStamina(int stamina) {
		this.stamina = stamina;
	}

	// public int getSpriteWidth(int rotation){
	// use getWidth inherited from entity

	// public int getSpriteHeight(int rotation){
	// use getHieght inherited from entity

	public void quack() {
		this.quack.play();
	}

	public int getMAXSTAM() {
		return MAXSTAM;
	}

	public int getMINSTAM() {
		return MINSTAM;
	}

}
