package org.usfirst.frc6033.RobotCodeJava2018.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc6033.RobotCodeJava2018.Robot;
import org.usfirst.frc6033.RobotCodeJava2018.subsystems.*;

public class AutoSide extends AutoBase {
	private final double ourSwitch = 168;
	private final double ourScale = 324;
	private final double theirSwitch = 474;
	private final double wallToSwitch = 87;
	private final double wallToScale = 72;

	public AutoSide(char side) {
		int turnFactor = (side == 'L') ? -1 : 1;

		SmartDashboard.putString("autoTarget", "Default");

		double distance1 = 120;
		double distance2 = 10;
		double rearSensorDistance = 2;
		boolean followSide = false;

		boolean doTurn = false;
		if (this.getSideScale() == 'R') {
			distance1 = ourScale;
			distance2 = wallToScale;
			followSide = true;
			SmartDashboard.putString("autoTarget", "Scale");
			doTurn = true;

		} else if (this.getSideOpponentSwitch() == 'R') {
			distance1 = theirSwitch;
			distance2 = wallToSwitch;
			followSide = true;
			SmartDashboard.putString("autoTarget", "Opponent");
			doTurn = true;

		} else if (this.getSideSwitch() == 'R') {
			distance1 = ourSwitch;
			distance2 = wallToSwitch;
			SmartDashboard.putString("autoTarget", "Switch");
			doTurn = true;

		}
		SmartDashboard.putNumber("Distance1", distance1);
		SmartDashboard.putNumber("Distance2", distance2);
		double initDistance = 36;
		DriveStraightDistance(initDistance, false, false);
		DriveStraightDistance(distance1 - initDistance, followSide);
		if (doTurn) {
			Turn90(Robot.drive.DirectionLeft * turnFactor);
			double driveDistance = distance2 - rearSensorDistance - Robot.RobotLengthInches;
			if (driveDistance > 0.5)
				DriveStraightDistance(driveDistance);
		}
	}
}
