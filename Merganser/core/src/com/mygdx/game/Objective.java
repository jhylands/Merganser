package com.mygdx.game;

public class Objective {

	private MyGdxGame game;
	private Map targetMap;
	private Objective nextObjective;
	private String objectiveDescription;
	private int pointsValue;
	private int timeCounter = 0;

	/**
	 * Constructor for Objective.
	 * 
	 * @param game
	 * @param targetMap
	 * @param objectiveDescription
	 * @param pointsValue
	 */
	public Objective(MyGdxGame game, Map targetMap, String objectiveDescription, int pointsValue) {
		this.game = game;
		this.targetMap = targetMap;
		this.objectiveDescription = objectiveDescription;
		this.pointsValue = pointsValue;
	}

	/**
	 * Update objective - if isComplete() returns true then return the next
	 * objective and sets NewObjective boolean to True to notify of new
	 * objective If objective not complete then reduce the reward
	 * 
	 * @param currentMap
	 * @return
	 */
	public Objective updateObjective() {
		if (isComplete()) {
			game.setNewObjective(true);
			return nextObjective;
		} else {
			reduceReward();
			return this;
		}
	}

	/**
	 * function to cause a time penalty as the player takes longer to carry out
	 * the objective (decrement pointsValue for completing objective)
	 */
	private void reduceReward() {
		if (timeCounter == 30) {
			if (pointsValue > 1) {
				pointsValue--;
			}
			timeCounter = 0;
		} else {
			timeCounter++;
		}
	}

	/**
	 * Updates playerDuck score by pointsValue
	 */
	private void updateScore() {
		game.getDuck().addScore(pointsValue);
	}

	/**
	 * Checks if objective is complete
	 * 
	 * @return
	 */
	private boolean isComplete() {
		if (game.getCurrentMap() == targetMap) {
			updateScore();
			return true;
		} else {
			return false;
		}
	}

	// IN DEVELOPMENT READY FOR XML
	// // Location based.
	// private boolean isCorrectLocation(Map currentMap){
	// return false;
	//
	// //some past data representing states so that more than one location can
	// be represented
	//
	// //if(currentMap.getRef == this.mapRef());
	// }

	// GETTERS AND SETTERS

	public Map getTargetMap() {
		return targetMap;
	}

	public void setTargetMap(Map targetMap) {
		this.targetMap = targetMap;
	}

	public Objective getNextObjective() {
		return nextObjective;
	}

	public void setNextObjective(Objective nextObjective) {
		this.nextObjective = nextObjective;
	}

	public String getObjectiveDescription() {
		return objectiveDescription;
	}

	public void setObjectiveDescription(String objectiveDescription) {
		this.objectiveDescription = objectiveDescription;
	}

	public int getPointsValue() {
		return pointsValue;
	}

	public void setPointsValue(int pointsValue) {
		this.pointsValue = pointsValue;
	}

}
