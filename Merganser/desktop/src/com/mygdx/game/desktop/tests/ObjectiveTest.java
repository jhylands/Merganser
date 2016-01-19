package com.mygdx.game.desktop.tests;

import com.mygdx.game.Map;
import com.mygdx.game.Objective;

public class ObjectiveTest extends AssetTestCase {

	Objective objective;
	Map map;

	public void setUp() {
		super.setUp();
		map = new Map("testMap", null, null, null, null, null);
		objective = new Objective(game, map, "testName", 100);
	}

	public void testUpdateScore(){
		int beforeUpdate = game.duck.getScore();
		objective.updateScore();
		assertEquals(beforeUpdate + objective.getPointsValue(), game.duck.getScore());;
	}
	
	
	public void testIsCompleteT(){
		objective.setTargetMap(game.getCurrentMap());
		int beforeUpdate = game.duck.getScore();
		assertTrue(objective.isComplete());
		assertEquals(beforeUpdate + objective.getPointsValue(), game.duck.getScore()  );;
	}
	
	public void testIsCompleteF(){
		objective.setTargetMap(map);
		int beforeUpdate = game.duck.getScore();
		assertFalse(objective.isComplete());
		assertEquals(beforeUpdate, game.duck.getScore());
	}
	
	public void testReduceReward(){
		int beforeTime = objective.getPointsValue();
		objective.setTimeCounter(30);
		objective.reduceReward();
		int afterTime = objective.getPointsValue();
		
		assertEquals(beforeTime - objective.getReduceReward() , afterTime);
	}
	
	public void testUpdateObjectiveLast(){
		objective.setTargetMap(game.getCurrentMap());
		objective.setLastObjective(true);
		objective.updateObjective();
		assertEquals(true,game.isLastObjComplete());;
	}
	
	public void testUpdateObjectiveTrue(){
		game.setLastObjComplete(false);
		objective.setTargetMap(game.getCurrentMap());
		Objective newTestObjective = new Objective(game, null, null, 0);
		objective.setNextObjective(newTestObjective);
		objective.setLastObjective(false);
		objective.updateObjective();
		assertEquals(false, game.isLastObjComplete());
		assertEquals(newTestObjective, game.getCurrentObjective());
	}

	public void testUpdateObjectiveFalse(){
		objective.setTargetMap(map);
		int rewardBeforeUpdate = objective.getPointsValue();
		Objective currentObjBeforeUpdate = game.getCurrentObjective();
		objective.setTimeCounter(30);
		objective.updateObjective();
		assertEquals(currentObjBeforeUpdate, game.getCurrentObjective());
		assertTrue(rewardBeforeUpdate > objective.getPointsValue());
		
	}
}
