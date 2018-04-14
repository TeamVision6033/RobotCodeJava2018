package org.usfirst.frc6033.RobotCodeJava2018.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc6033.RobotCodeJava2018.Robot;
import org.usfirst.frc6033.RobotCodeJava2018.commands.DriveReturnToSetHeading;
import org.usfirst.frc6033.RobotCodeJava2018.commands.DriveStop;
import org.usfirst.frc6033.RobotCodeJava2018.commands.DriveStraight;
import org.usfirst.frc6033.RobotCodeJava2018.subsystems.*;
import org.usfirst.frc6033.RobotCodeJava2018.util.GameData;

public class AutoCenter extends AutoBase {

	private final double ourSwitch = 16.8;
	private final double ourScale = 32.4;
	private final double theirSwitch = 47.4;
	private final double wallToSwitch = 8.7;
	private final double wallToScale = 7.2;
	
	public AutoCenter() {
		GameData gameData = new GameData();
		char SwitchSideChar = gameData.getSwitchSide();
		
		double distance1 = 36;
		double distance2 = 66 - 12;
		double distance3 = 108 - Robot.RobotLengthInches;
		// new comment
		

		
		
		int turnFactor = (SwitchSideChar == 'L') ? -1 : 1;

		DriveStraightDistance(distance1);
		ReturnToSetHeading();
		Turn90(Robot.drive.DirectionRight * turnFactor);
		DriveStraightDistance( Robot.AutoSpeed, distance2 - 5, 'N', true, switchHeight, true,0);
		ReturnToSetHeading();
		Turn90(Robot.drive.DirectionLeft * turnFactor);
		DriveStraightDistance(Robot.AutoApproachSpeed, distance3 + 18, 'N', true,0,false,1.5);
		ClampOpen();

	}

}
