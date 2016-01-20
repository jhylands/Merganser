package com.mygdx.game.desktop.tests;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.sprite.LiveEntity;

import junit.framework.TestCase;

public class LiveEntityTest extends TestCase {
	
	LiveEntity testEntity = new LiveEntity(); 
	Texture testTexture;
	
	public void setUp(){
	}

	public void testDirection2VectorUp(){
		assertEquals(new Vector2(0, 1), testEntity.direction2Vector(testEntity.UP));
	}
	
	public void testDirection2VectorDown(){
		assertEquals(new Vector2(1, 0), testEntity.direction2Vector(testEntity.RIGHT));
	}
	
	public void testDirection2VectorLeft(){
		assertEquals(new Vector2(0, -1), testEntity.direction2Vector(testEntity.DOWN));
	}
	
	public void testDirection2VectorRight(){
		assertEquals(new Vector2(-1, 0), testEntity.direction2Vector(testEntity.LEFT));
	}
	
	public void testDirection2VectorNone(){
		assertEquals(new Vector2(0, 0), testEntity.direction2Vector(-1));
	}
	
	public void testSetHealthNegative(){
		testEntity.setMaxHealth(16);
		testEntity.setHealth(-3);
		assertEquals(0, testEntity.getHealth());
	}
	
	public void testSetHealthGreaterMaxHealth(){
		testEntity.setMaxHealth(16);
		testEntity.setHealth(20);
		assertEquals(16, testEntity.getHealth());
	}
	
	public void testSetHealthNormal(){
		testEntity.setMaxHealth(16);
		testEntity.setHealth(6);
		assertEquals(6, testEntity.getHealth());
	}
	
	public void testChangeHealthBelowZero(){
		testEntity.setHealth(3);
		testEntity.changeHealth(-5);
		assertEquals(0, testEntity.getHealth());
	}
	
	public void testChangeHealthAboveZero(){
		testEntity.setMaxHealth(16);
		testEntity.setHealth(3);
		testEntity.changeHealth(4);
		assertEquals(7, testEntity.getHealth());
	}
	
	public void testSetMaxHealthNegative(){
		testEntity.setMaxHealth(-3);
		assertEquals(0, testEntity.getMaxHealth());
	}
	
	public void testSetMaxHealthNormal(){
		testEntity.setMaxHealth(16);
		assertEquals(16, testEntity.getMaxHealth());
	}
	
	public void testisAlive(){
		 testEntity.setMaxHealth(16);
		 testEntity.setHealth(5);
		 assertTrue(testEntity.isAlive());
	 }
	
	public void testIsDead(){
		testEntity.setHealth(0);
		assertFalse(testEntity.isAlive());
	}
	
	
}
