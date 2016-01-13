package com.mygdx.game;

public class Objective {

	MyGdxGame game;
	Map targetMap;
	Objective nextObjective;
	String objectiveDescription;

	int pointsValue;
	int timeCounter = 0;

	public Objective(MyGdxGame game, Map targetMap, String objectiveDescription, int pointsValue) {
		this.game = game;
		this.targetMap = targetMap;
		this.objectiveDescription = objectiveDescription;
		this.pointsValue = pointsValue;
	}

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

	public Objective isComplete(Map currentMap) {
		if (currentMap == targetMap) {
			game.getDuck().addScore(pointsValue);
			return nextObjective;
		} else {

			if (timeCounter == 30) {
				if (pointsValue > 1) {
					pointsValue--;
				}
				timeCounter = 0;

			} else {
				timeCounter++;
			}
			return this;
		}

	}

}
