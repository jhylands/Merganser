package com.mygdx.test.game;

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
	 
	 public void testisAlive(){
		 assertTrue(testEntity.isAlive() == true || testEntity.isAlive() == false);
	 }

	 public void testisFlying(){
		 assertTrue(testEntity.isflying() == true || testEntity.isflying() == false);
		 		 
	 }
	 
	 public void testcanswim(){
		 assertTrue(testEntity.canswim() == true || testEntity.canswim() == false);
	 }
	 
	 public void testHealth(){
		 testEntity.changeHealth(100);
		 assertTrue(testEntity.getHealth() == 100);
	 }
	 
	 public void testStrength(){
		 testEntity.setAttackStrength(20);
		 assertTrue(testEntity.getAttackStrength() == 20);
	 }
	 
	 public void setHealth(){ 
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
