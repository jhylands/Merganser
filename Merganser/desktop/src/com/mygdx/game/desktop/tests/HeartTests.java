package com.mygdx.game.desktop.tests;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.Heart;
import com.mygdx.sprite.PlayerDuck;

public class HeartTests extends AssetTestCase {

	

	public void setUp() {
		super.setUp();
	}

	// Test that heart has been initialised with the 5 heart textures
	public void testHeartInit() {
		Heart heart = new Heart(game.getAssetManager());
		assertTrue(heart.getTexture(0) instanceof Texture);
		assertTrue(heart.getTexture(1) instanceof Texture);
		assertTrue(heart.getTexture(2) instanceof Texture);
		assertTrue(heart.getTexture(3) instanceof Texture);
		assertTrue(heart.getTexture(4) instanceof Texture);
	}

	// Test that part health calculations give the correct output of hearts to
	// draw
	public void testAddTextures() {
		Heart heart = new Heart(game.getAssetManager());
		int health = 6;
		int maxHealth = 12;
		ArrayList<Integer> expectedResult = new ArrayList<Integer>();
		expectedResult.add(4);
		expectedResult.add(2);
		expectedResult.add(0);
		heart.addTextures(health, maxHealth);
		assertEquals(expectedResult, heart.getDisp());
	}

	// Test zero case that zero health produces the correct output of hearts
	public void testAddTexturesZero() {
		Heart heart = new Heart(game.getAssetManager());
		int health = 0;
		int maxHealth = 12;
		ArrayList<Integer> expectedResult = new ArrayList<Integer>();
		expectedResult.add(0);
		expectedResult.add(0);
		expectedResult.add(0);
		heart.addTextures(health, maxHealth);
		assertEquals(expectedResult, heart.getDisp());
	}

	// Test zero case that full health produces the correct output of hearts
	public void testAddTexturesFull() {
		Heart heart = new Heart(game.getAssetManager());
		int health = 12;
		int maxHealth = 12;
		ArrayList<Integer> expectedResult = new ArrayList<Integer>();
		expectedResult.add(4);
		expectedResult.add(4);
		expectedResult.add(4);
		heart.addTextures(health, maxHealth);
		assertEquals(expectedResult, heart.getDisp());
	}

}