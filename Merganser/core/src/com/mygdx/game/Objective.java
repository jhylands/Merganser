package com.mygdx.game;
/**
 * Objective class contains information for an objective
 * along with methods to update points system and check
 * whether objective is complete
 *
 */
public class Objective {

	/**
	 *  Current game state
	 */
	private MyGdxGame game;
	
	/**
	 *  Target map
	 */
	private Map targetMap;
	
	/**
	 *  Next objective
	 */
	private Objective nextObjective;
	
	/**
	 * Description of objective
	 */
	private String objectiveDescription;
	
	/**
	 *  Points awarded for completion of objective
	 */
	private int pointsValue;
	
	/**
	 * Time counter to check when to reduce rewards
	 */
	private int timeCounter = 0;
	
	/**
	 *  Constant amount to reduce objective reward by
	 */
	private int REDUCEREWARD = 1;
	
	/**
	 *  Objective automatically set to last objective
	 */
	private boolean  lastObjective = true;

	/**
	 * Constructor for Objective.
	 * 
	 * @param game					Current game state
	 * @param targetMap				Target map for the objective to be completed
	 * @param objectiveDescription	Description of objective
	 * @param pointsValue			Points awarded for completion of objective
	 */
	public Objective(MyGdxGame game, Map targetMap, String objectiveDescription, int pointsValue) {
		this.game = game;
		this.targetMap = targetMap;
		this.objectiveDescription = objectiveDescription;
		this.pointsValue = pointsValue;
	}

	/**
	 * Updates current objective
	 * If complete and last objective will set last objective flag to true
	 * Otherwise will get next objective
	 * If not complete then reduce reward for current objective
	 */
	public void updateObjective() {
		if (isComplete()) {
			if (isLastObjective()){
				game.setLastObjComplete(true);
			}
			else{
				game.setNewObjective(true);
				game.setCurrentObjective(nextObjective);
			}
		} else {
			reduceReward();
		}
	}

	/**
	 * function to cause a time penalty as the player takes longer to carry out
	 * the objective (decrement pointsValue for completing objective)
	 */
	private void reduceReward() {
		// Reduce rewards every 30 frames by REDUCEREWARD constant and reset time counter
		if (timeCounter == 30) {
			if (pointsValue > 1) {
				pointsValue -= REDUCEREWARD;
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

	/**
	 * Return the target map of the objective
	 * @return
	 */
	public Map getTargetMap() {
		return targetMap;
	}

	/**
	 * Set a Map to be the objective target
	 * @param targetMap
	 */
	public void setTargetMap(Map targetMap) {
		this.targetMap = targetMap;
	}

	/**
	 * Gets the next objective
	 * @return
	 */
	public Objective getNextObjective() {
		return nextObjective;
	}

	/**
	 * Sets the next objective. 
	 * Sets the boolean last objective to false as no longer last objective.
	 * @param nextObjective
	 */
	public void setNextObjective(Objective nextObjective) {
		this.nextObjective = nextObjective;
		this.lastObjective = false;
	}

	/**
	 * Returns the objective description
	 * @return
	 */
	public String getObjectiveDescription() {
		return objectiveDescription;
	}

	/**
	 * Set objective description
	 * @param objectiveDescription
	 */
	public void setObjectiveDescription(String objectiveDescription) {
		this.objectiveDescription = objectiveDescription;
	}

	/**
	 * Get points value for completing the objective
	 * @return
	 */
	public int getPointsValue() {
		return pointsValue;
	}

	/**
	 * Set points value for completing the objective
	 * @param pointsValue Integer
	 */
	public void setPointsValue(int pointsValue) {
		this.pointsValue = pointsValue;
	}

	/**
	 * Returns true if this objective is the last objective
	 * @return
	 */
	public boolean isLastObjective() {
		return lastObjective;
	}

	/**
	 * Changes boolean to sets whether this is the last objective
	 * @param lastObjective
	 */
	public void setLastObjective(boolean lastObjective) {
		this.lastObjective = lastObjective;
	}
	


}
