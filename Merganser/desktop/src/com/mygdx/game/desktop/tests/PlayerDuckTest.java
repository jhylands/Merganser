package com.mygdx.game.desktop.tests;

import com.mygdx.sprite.PlayerDuck;

public class PlayerDuckTest extends AssetTestCase {

	PlayerDuck testEntity;

	public void setUp() {
		super.setUp();
		testEntity = new PlayerDuck();
		testEntity.assignResources(game.getAssetManager());
	}

	public void testSetStaminaOverMaximum() {
		int stamina = testEntity.getMAXSTAM();
		testEntity.setStamina(stamina + 20);
		assertEquals(stamina, testEntity.getStamina());
	}

	public void testSetStaminaUnderMin() {
		int stamina = testEntity.getMINSTAM();
		testEntity.setStamina(stamina - 20);
		assertEquals(stamina, testEntity.getStamina());
	}

	public void testSetStaminaWithinRange() {
		int stamina = testEntity.getMAXSTAM() / 2;
		testEntity.setStamina(stamina);
		assertEquals(stamina, testEntity.getStamina());
	}

	public void testSetMaxHealthNegative() {
		testEntity.setMaxHealth(-2);
		assertEquals(0, testEntity.getMaxHealth());
	}

	public void testSetMaxHealthNonMul4() {
		testEntity.setMaxHealth(19);
		assertEquals(16, testEntity.getMaxHealth());
	}

	public void testSetMaxHealthMul4() {
		testEntity.setMaxHealth(20);
		assertEquals(20, testEntity.getMaxHealth());
	}

}
