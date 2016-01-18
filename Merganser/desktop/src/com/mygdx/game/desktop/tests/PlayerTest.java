package com.mygdx.game.desktop.tests;

import java.awt.Dimension;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.sprite.PlayerDuck;
import com.sun.javafx.geom.Dimension2D;
import com.sun.org.apache.xalan.internal.xsltc.util.IntegerArray;

public class PlayerTest extends AssetTestCase {
	
	PlayerDuck testEntity;
	
	public void setUp() {
		super.setUp();
		 testEntity = new PlayerDuck();
		 testEntity.assignResources(game.getAssetManager());
	}
	
	public void testPlayerInitilaisation() {
		assertEquals(testEntity.getMaxHealth(), testEntity.getHealth());
		assertEquals(testEntity.getStamina(), 100);
		assertTrue(testEntity.getHealth() == 12);
		assertTrue(testEntity.getDUCKSPEED() == 3);
		assertTrue(testEntity.getDUCKSPRINT() == 6);
		assertTrue(testEntity.getMaxHealth() == 12);
		assertTrue(testEntity.getMAXSTAM() == 100);
		assertTrue(testEntity.getMINSTAM() == 0);
		assertTrue(testEntity.getScore() == 0);
	}
	
	public void testingScore() {
		testEntity.addScore(10);
		assertTrue(testEntity.getScore() == 10);
	}
	
	public void testingStamina() {
		testEntity.setStamina(12);
		assertTrue(testEntity.getStamina() == 12);
	}
	
	public void testSetScore() {
		testEntity.setScore(12);
		assertTrue(testEntity.getScore() == 12);
	}
	
	public void testZeroStam() {
		testEntity.setStamina(0);
		assertTrue(testEntity.atMinStam());
	}
	
	public void testMaxStam() {
		testEntity.setStamina(100);
		assertTrue(testEntity.atMaxStam());
	}
	

	public void testRotation() {
		testEntity.setRotation(90);
		assertTrue(testEntity.getRotation() == 90);
	}
	
	public void testgetTexture() {
		assertTrue(testEntity.getTexture() instanceof Texture);
	}
	
	public void testPositionChanging() {
		Vector2 v = testEntity.getPosition();
		testEntity.portalJumpTo(v);
		assertEquals(v, testEntity.getPosition());
	}
	
	public void testMoveIfValid() {
		Vector2 v = testEntity.getPosition();
		assertTrue(testEntity.moveIfValid(v, game.getCurrentMap()));
	}
		
}