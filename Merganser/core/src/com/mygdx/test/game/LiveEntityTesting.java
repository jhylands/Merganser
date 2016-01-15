package com.mygdx.test.game;

import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.Heart;
import com.mygdx.sprite.Entity;
import com.mygdx.sprite.LiveEntity;

import junit.framework.*;

public class LiveEntityTesting extends TestCase {
	 protected int value1, value2, speed;
	 LiveEntity testEntity = new LiveEntity();
	 protected void setUp(){
	      value1=3;
	      value2=3;
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


}
