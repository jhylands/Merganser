package com.mygdx.game.desktop.tests;

import com.mygdx.sprite.Repeatable;

import junit.framework.*;

public class AssetManagement extends AssetTestCase {

	private Repeatable test;

	public void setUp() {
		super.setUp();
		test = new Repeatable(10, game.getAssetManager());
	}
	
	public void testRotation(){
		test.rotate(34.0f);
		assertEquals(test.getRotation(), test.RIGHT);
	}
	
	public void testRotation2(){
		test.rotate(34.0f);
		assertEquals(test.getRotation(), test.RIGHT);
	}
	
	public void testRotation3(){
		int i = 0;
		while(i < 100){
			System.out.println("TestRotation3");
			i++;
		}
			
		test.rotate(90.0f);
		assertEquals(test.getRotation(), test.UP);
	}
}
