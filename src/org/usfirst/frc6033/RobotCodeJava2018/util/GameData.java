package org.usfirst.frc6033.RobotCodeJava2018.util;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class GameData {
	protected static String gameData;
	protected static char switchSide;
	protected static char scaleSide;
	protected static char opponentSwitchSide;
	protected static double trials = 0;

	public void setGameData(String value) {
		gameData = value;

		if (value.length() >= 3) {

			switchSide = value.charAt(0);
			scaleSide = value.charAt(1);
			opponentSwitchSide = value.charAt(2);
		}

	}

	public String getGameData() {
		return gameData;
	}

	public char getSwitchSide() {
		return switchSide;
	}

	public char getScaleSide() {
		return scaleSide;
	}

	public char getOpponentSwitchSide() {
		return opponentSwitchSide;
	}
}
