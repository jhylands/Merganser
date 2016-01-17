package com.mygdx.sprite;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

/**
 * Class encapsulates all variables for the for players duck.
 * Initialization of this class sets values for the duck such as
 * health, score, stamina 
 */
public class PlayerDuck extends LiveEntity {

	// CONSTANTS
	private int DEFAULTDUCKSPEED = 3;
	private int DEFAULTDUCKSPRINT = 6;
	// MAXSTAM should always be 100
	private int MAXSTAM = 100;
	private int MINSTAM = 0;
	private int SPRINTCOST = 2;
	private int STAMINAREGEN = 1;

	/**
	 * Current screenHeight of game
	 */
	private int screenHeight = Gdx.graphics.getHeight();

	// Initialize variables
	private int score;
	private int stamina;

	// Initialize quack sound;
	private Sound quack = null;

	// private Vector2 position <-not needed because position in stored in
	// hitBox inherited from Entity

	/**
	 * Constructor for PlayerDuck Initialize players values (maxHealth, health,
	 * starting score, stamina)
	 */
	public PlayerDuck() {
		this.canSwim = true;
		// MaxHealth needs to be a non negative integer && multiple of 4 (will
		// be rounded down if %4!=0)
		// MaxHealth must be initialized before setting users health
		this.setMaxHealth(12);
		this.setHealth(12);
		this.setScore(0);
		this.setStamina(MAXSTAM);
		this.speed = DEFAULTDUCKSPEED;

	}

	/**
	 * Retrieves sprite Textures from AssetManager
	 * Loads audio file from assets
	 * @param manager AssetManager where resources are stored
	 */
	public void assignResources(AssetManager manager) {
		this.sprite = new Texture[4];
		this.sprite[this.UP] = manager.get("large_duck.png", Texture.class);
		this.sprite[this.DOWN] = manager.get("large_duck_down.png", Texture.class);
		this.sprite[this.LEFT] = manager.get("large_duck_left.png", Texture.class);
		this.sprite[this.RIGHT] = manager.get("large_duck_right.png", Texture.class);
		this.setPosition(new Vector2(20, (screenHeight / 2) - (this.getHeight() / 2)));
		this.quack = Gdx.audio.newSound(Gdx.files.internal("duckQuack.mp3"));
	}

	/**
	 * Get default duck normal speed
	 * @return Integer speed
	 */
	public int getDUCKSPEED() {
		return DEFAULTDUCKSPEED;
	}

	/**
	 * Get default duck sprint speed
	 * @return Integer speed
	 */
	public int getDUCKSPRINT() {
		return DEFAULTDUCKSPRINT;
	}

	/**
	 * Returns true if stamina is at maximum possible Value
	 * @return Boolean
	 */
	public boolean atMaxStam() {
		return (stamina == MAXSTAM);
	}

	/**
	 * Returns true if stamina is at minimum possible value
	 * @return Boolean
	 */
	public boolean atMinStam() {
		return (stamina <= MINSTAM);
	}

	/**
	 * Sets playerDuck Position where portal should transport user to
	 * @param v Vector2 
	 */
	public void portalJumpTo(Vector2 v) {
		this.setPosition(v);
	}

	/**
	 * Returns the PlayerDuck score
	 * @return Integer current score
	 */
	public int getScore() {
		return score;
	}

	/**
	 * Sets the score
	 * @param score Integer score value
	 */
	public void setScore(int score) {
		this.score = score;
	}

	/**
	 * Increments/decrements score
	 * @param score Integer to increment/decrement score by
	 */
	public void addScore(int score) {
		this.score += score;
	}

	/**
	 * Retrieve stamina value
	 * @return Integer stamina value
	 */
	public int getStamina() {
		return stamina;
	}

	/**
	 * Sets the stamina value.
	 * @param stamina If stamina > maxStamina then cap stamina at MaxStamina.
	 * If stamina < MINSTAMINA then cap stamina at MINSTAMINA
	 */
	public void setStamina(int stamina) {
		if(stamina > MAXSTAM){
			this.stamina = MAXSTAM;
		} else if (stamina < MINSTAM){
			this.stamina = MINSTAM;
		} else {
			this.stamina = stamina;
		}
				
	}

	// public int getSpriteWidth(int rotation){
	// use getWidth inherited from entity

	// public int getSpriteHeight(int rotation){
	// use getHieght inherited from entity

	/**
	 * Plays duck quack noise
	 * @return Integer
	 */
	public void quack() {
		this.quack.play();
	}
	
	/**
	 * Return maximum Stamina constant
	 * @return Integer
	 */
	public int getMAXSTAM() {
		return MAXSTAM;
	}

	/**
	 * Return Minimum Stamina constant
	 * @return Integer
	 */
	public int getMINSTAM() {
		return MINSTAM;
	}

	public int getSPRINTCOST() {
		return SPRINTCOST;
	}

	public int getSTAMINAREGEN() {
		return STAMINAREGEN;
	}

	public void setSTAMINAREGEN(int sTAMINAREGEN) {
		STAMINAREGEN = sTAMINAREGEN;
	}

}
