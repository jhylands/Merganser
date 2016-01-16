package com.mygdx.game.desktop.tests;

import com.mygdx.sprite.LiveEntity;
import junit.framework.*;

public class LiveEntityTesting extends TestCase {
	 protected int speed;
	 LiveEntity testEntity = new LiveEntity();
	 
	 protected void setUp(){
	      speed = 120;
	   }
	 
	 public void testLiveEntitySpeed(){
		 testEntity.setSpeed(speed);
		 int result3 = testEntity.getSpeed();
		 assertTrue(result3 == 120);
	 }
	 
	 public void testLiveMaxHealth(){
		 testEntity.setMaxHealth(100);
		 int HealthTest = testEntity.getMaxHealth();
		 assertTrue(HealthTest == 100);
	 }
	 
	 public void testisDead(){
		 testEntity.setHealth(0);
		 assertTrue(testEntity.isAlive() == false);
	 }
	 
	 public void testisAlive(){
		 testEntity.setHealth(100);
		 assertTrue(testEntity.isAlive() == true);
	 }

	 public void testisFlying(){
		 assertTrue(testEntity.isflying() == false);
		 		 
	 }
	 
	 public void testcanswim(){
		 assertTrue(testEntity.canswim() == false);
	 }
	 
	 public void testChangeHealth(){
		 testEntity.changeHealth(100);
		 assertTrue(testEntity.getHealth() == 100);
	 }
	 
	 public void testStrength(){
		 testEntity.setAttackStrength(20);
		 assertTrue(testEntity.getAttackStrength() == 20);
	 }
	 
	 public void testSetHealth(){ 
		 testEntity.setHealth(20);
		 assertTrue(testEntity.getHealth() == 20);
	 }
	 
	 /*
	  * Test Move Validity
	  * Test Width
	  * Test Height
	  * Test Texture
	  */
	 
}
