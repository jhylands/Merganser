package com.mygdx.test.game;

import com.badlogic.gdx.assets.AssetManager;
import com.mygdx.game.MyGdxGame;
import com.mygdx.sprite.PlayerDuck;

import junit.framework.*;

public class PlayerTest extends TestCase {
	 MyGdxGame newGame = new MyGdxGame();
	 AssetManager assetManager2 = newGame.getAssetManager();
	 PlayerDuck testEntity = new PlayerDuck();
	 
	 protected void setUp(){
		 testEntity.assignResources(assetManager2);
	   }
	
	 
	 public void testSprint(){
		 testEntity.setDUCKSPRINT(1213);
		 assertTrue(testEntity.getDUCKSPRINT() == 1213);
	 }
	 
	 public void testSpeed(){
		 testEntity.setDUCKSPEED(20);
		 assertTrue(testEntity.getDUCKSPEED() == 20);
	 }
	 
	 public void testPlayerInitilaisation(){
		 assertTrue((testEntity.getHealth() == 12) && (testEntity.getDUCKSPEED() == 3) && 
				 (testEntity.getDUCKSPRINT() == 6) && (testEntity.getMaxHealth() == 12) &&
				 (testEntity.getMAXSTAM() == 100) && (testEntity.getMINSTAM() == 0) &&
				 (testEntity.getScore() == 0));
	 }
	 
	 
	 
	 
}