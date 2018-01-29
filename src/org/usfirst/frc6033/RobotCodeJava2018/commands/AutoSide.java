package org.usfirst.frc6033.RobotCodeJava2018.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import java.lang.reflect.Method;

import org.usfirst.frc6033.RobotCodeJava2018.Robot;
import org.usfirst.frc6033.RobotCodeJava2018.subsystems.*;

public class AutoSide extends AutoBase {
	private final double ourSwitch = 168;
	private final double ourScale = 324;
	private final double theirSwitch = 474;
	private final double wallToSwitch = 87;
	private final double wallToScale = 72;
	private final double crossArenaPoint = 240;
	private final double frontScaleFace = 294;

	public AutoSide(char side) {
		int turnFactor = (side == 'L') ? -1 : 1;

		SmartDashboard.putString("autoTarget", "Default");

		double distance1 = 120;
		double distance2 = 10;
		//double rearSensorDistance = 2;
		boolean followSide = false;

		boolean doTurn = false;
		if ((this.getSideScale() == 'R' && side == 'R') || (this.getSideScale() == 'L' && side == 'L')) {
			distance1 = ourScale;
			distance2 = wallToScale;
			followSide = true;
			SmartDashboard.putString("autoTarget", "Scale");
			doTurn = true;
			DropOurSide (distance1, distance2, followSide, turnFactor, doTurn);//, rearSensorDistance);

		} else if ((this.getSideOpponentSwitch() == 'R' && side == 'R') || (this.getSideOpponentSwitch() == 'L' && side == 'L')) {
			distance1 = theirSwitch;
			distance2 = wallToSwitch;
			followSide = true;
			SmartDashboard.putString("autoTarget", "Opponent");
			doTurn = true;
			DropOurSide (distance1, distance2, followSide, turnFactor, doTurn);//, rearSensorDistance);

		} else if ((this.getSideSwitch() == 'R' && side == 'R') || (this.getSideSwitch() == 'L' && side == 'L')) {
			distance1 = ourSwitch;
			distance2 = wallToSwitch;
			SmartDashboard.putString("autoTarget", "Switch");
			doTurn = true;
			DropOurSide (distance1, distance2, followSide, turnFactor, doTurn);//, rearSensorDistance);

		} else {
			DropOppositeSide(turnFactor);
			
		}
	
	}
	private void DropOurSide(double distance1, double distance2, boolean followSide, int turnFactor, boolean doTurn) {//, double rearSensorDistance) {
		SmartDashboard.putNumber("Distance1", distance1);
		SmartDashboard.putNumber("Distance2", distance2);
		double initDistance = 36;
		DriveStraightDistance(initDistance, false, false);
		DriveStraightDistance(distance1 - initDistance, followSide);
		if (doTurn) {
			Turn90(Robot.drive.DirectionLeft * turnFactor);
			double driveDistance = distance2 - Robot.drive.getRearSonarDistance() - Robot.RobotLengthInches;
			if (driveDistance > 0.5) {
				DriveStraightDistance(driveDistance, false, true);
			}
		}
	}
		private void DropOppositeSide( int turnFactor) {
			double initDistance = 36;
			double distance1 = this.crossArenaPoint - initDistance;
			double distanceRobotToWall;
			double distanceWallToDropOff = this.wallToScale + Robot.RobotWidthInches/2;
			double distanceAcross;
			DriveStraightDistance(initDistance, false, false);
			DriveStraightDistance(distance1 - Robot.RobotLengthInches/2, true, true);
			Turn90(Robot.drive.DirectionLeft * turnFactor);
			double currentRearDist = Robot.drive.getRearSonarDistance() < 0 ? Robot.drive.getRearSonarDistance()*-1 : Robot.drive.getRearSonarDistance();
			distanceRobotToWall = (currentRearDist - Robot.SensorOffsetInches) + (Robot.RobotLengthInches/2);
			// use constant for arena width later
			distanceAcross = 324.0 - (distanceRobotToWall + distanceWallToDropOff);
			if (distanceAcross < 0) {
				distanceAcross = distanceAcross*-1;
			}
			DriveStraightDistance(distanceAcross, false, true);
			//DriveStraightDistance(324, false);
			Turn90(Robot.drive.DirectionRight * turnFactor);
			DriveStraightDistance (this.frontScaleFace - (Robot.RobotLengthInches + initDistance + distance1), false, true);
	}
}
