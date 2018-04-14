package org.usfirst.frc6033.RobotCodeJava2018.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import java.lang.reflect.Method;

import org.usfirst.frc6033.RobotCodeJava2018.Robot;
import org.usfirst.frc6033.RobotCodeJava2018.subsystems.*;
import org.usfirst.frc6033.RobotCodeJava2018.util.GameData;

public class AutoSide extends AutoBase {
	private final double ourSwitch = 168;
	private final double ourScale = 324;
	//private final double theirSwitch = 474;
	private final double wallToSwitch = 87;
	private final double wallToScale = 72;
	//private final double crossArenaPoint = 240;
	//private final double frontScaleFace = 294;
	
	public AutoSide(char side) {
		GameData gameData = new GameData();
		char SwitchSideChar = gameData.getSwitchSide();

		char ScaleSideChar = gameData.getScaleSide();
		
		int turnFactor = (side == 'L') ? -1 : 1;

		
		double distance1 = 120;
		double distance2 = 10;
		double elevatorPosition = switchHeight;
		char followSide = 'N';

		boolean doTurn = false;
		if ((ScaleSideChar == 'R' && side == 'R') || (ScaleSideChar == 'L' && side == 'L')) {
			distance1 = ourScale;
			distance2 = wallToScale;
			elevatorPosition = scaleHeight;
			followSide = side;
	
			doTurn = true;
			DropOurSide (distance1, distance2, followSide, turnFactor, doTurn, elevatorPosition);//, rearSensorDistance);

		} else if ((SwitchSideChar== 'R' && side == 'R') || (SwitchSideChar == 'L' && side == 'L')) {
			distance1 = ourSwitch;
			distance2 = wallToSwitch;
			
			doTurn = true;
			DropOurSide (distance1, distance2, followSide, turnFactor, doTurn, elevatorPosition);//, rearSensorDistance);

		} else {
			DriveAccrossLine();
		}
	
	}
	private void DropOurSide(double distance1, double distance2, char followSide, int turnFactor, boolean doTurn, double elevatorPosition) {//, double rearSensorDistance) {
		SmartDashboard.putNumber("Distance1", distance1);
		SmartDashboard.putNumber("Distance2", distance2);
		double initDistance = distance1 - Robot.RobotLengthInches/2; //36;
		//DriveStraightDistance(initDistance, 'N', true);
		DriveStraightDistance( Robot.AutoSpeed, initDistance - 10, 'N', true,  5, true,0);
		DriveRaiseElevator (elevatorPosition);
		//DriveStraightDistance( Robot.AutoSpeed, 2, 'N', true,  elevatorPosition, true,0);
		//ReturnToSetHeading();
		if (doTurn) {
			Turn90(Robot.drive.DirectionLeft * turnFactor);
			double driveDistance = distance2 - Robot.RobotLengthInches;
			if (driveDistance > 0.5) {
				DriveStraightDistanceFromRear(Robot.AutoApproachSpeed, driveDistance, 0);
			}
		}
		ClampOpen();
	}
	
	private void DriveAccrossLine() {
			DriveStraightDistance(crossLineDistance, 'N', true);
	}
}
