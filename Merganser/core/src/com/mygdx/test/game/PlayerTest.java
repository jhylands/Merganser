package com.mygdx.test.game;

import com.mygdx.sprite.PlayerDuck;

import junit.framework.TestCase;

public class PlayerTest extends TestCase {
	PlayerDuck testEntity;
	
	protected void setUp() {
		 testEntity = new PlayerDuck();
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
	
	public void testSprint() {
		testEntity.setDUCKSPRINT(1213);
		assertTrue(testEntity.getDUCKSPRINT() == 1213);
	}

	public void testSpeed() {
		testEntity.setDUCKSPEED(20);
		assertTrue(testEntity.getDUCKSPEED() == 20);
	}

}