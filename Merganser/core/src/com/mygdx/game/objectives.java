package com.mygdx.game;

import com.mygdx.sprite.PlayerDuck;

public class objectives {
	
	private int enemyCounter = 0;
	private int objCounter = 0;
	private String[] objOrder = new String[]{"defeat10", "score500"}; //can convert these strings later on to see if complete?
	
		
	public void currentObjective () {
		System.out.println(objOrder[objCounter]);
		}
		
	public void defeat10(){
		
		//enemyCounter +=1; if enemy defeated +1 to counter
		if (enemyCounter == 10){
			System.out.println("yes"); //will complete the objective
			//also +=1 to objCounter
		}
		//if counter == 10, objective complete, add x points to counter
	}
	
	public void score500(){
		// wherever name of objective will go say "Score 500 points"
		PlayerDuck current = new PlayerDuck();
		if (current.getScore() > 499){
			current.addScore(100); //also show objective complete
		}
		
	}
	
	
}
