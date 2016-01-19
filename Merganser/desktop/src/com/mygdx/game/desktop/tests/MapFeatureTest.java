package com.mygdx.game.desktop.tests;

import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.MapFeature;

import junit.framework.TestCase;

public class MapFeatureTest extends TestCase {
	
	public void testGetBox() {
		MapFeature testFeature  = new MapFeature(new Rectangle(0, 0, 10, 10), true, true, false);
		assertEquals(new Rectangle(0, 0, 10, 10), testFeature.getBox());		
	}
	
	public void testGroundImpedance() {
		MapFeature testFeature  = new MapFeature(new Rectangle(0, 0, 10, 10), true, true, false);
		assertEquals(true, testFeature.isGroundImpeedence());		
	}
	
	public void testsetGroundImpeance() {
		MapFeature testFeature  = new MapFeature(new Rectangle(0, 0, 10, 10), true, true, false);
		testFeature.setGroundImpeedence(false);
		assertEquals(false, testFeature.isGroundImpeedence());		
	}
	
	public void testisWater() {
		MapFeature testFeature  = new MapFeature(new Rectangle(0, 0, 10, 10), true, true, false);
		assertEquals(false, testFeature.isWater());		
	}
	
	public void testsetWater() {
		MapFeature testFeature  = new MapFeature(new Rectangle(0, 0, 10, 10), true, true, false);
		testFeature.setWater(true);
		assertEquals(true, testFeature.isGroundImpeedence());		
	}
	
	public void testFlightImpedance() {
		MapFeature testFeature  = new MapFeature(new Rectangle(0, 0, 10, 10), true, true, false);
		assertEquals(true, testFeature.isFlightImpeedence());		
	}
	
	public void testsetFlightImpeance() {
		MapFeature testFeature  = new MapFeature(new Rectangle(0, 0, 10, 10), true, true, false);
		testFeature.setFlightImpeedence(false);
		assertEquals(false, testFeature.isFlightImpeedence());		
	}
	
	
	

}
