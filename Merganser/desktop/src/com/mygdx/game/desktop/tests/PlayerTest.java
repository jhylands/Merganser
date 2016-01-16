package com.mygdx.game.desktop.tests;

import com.mygdx.sprite.PlayerDuck;

public class PlayerTest extends AssetTestCase {
	
	PlayerDuck testEntity;
	
	public void setUp() {
		super.setUp();
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
	

}