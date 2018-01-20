package org.usfirst.frc6033.RobotCodeJava2018.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc6033.RobotCodeJava2018.Robot;
import org.usfirst.frc6033.RobotCodeJava2018.commands.DriveReturnToSetHeading;
import org.usfirst.frc6033.RobotCodeJava2018.commands.DriveStop;
import org.usfirst.frc6033.RobotCodeJava2018.commands.DriveStraight;
import org.usfirst.frc6033.RobotCodeJava2018.subsystems.*;

public class AutoCenter extends AutoBase {

	private final double ourSwitch = 16.8;
	private final double ourScale = 32.4;
	private final double theirSwitch = 47.4;
	private final double wallToSwitch = 8.7;
	private final double wallToScale = 7.2;

	public AutoCenter() {
		SmartDashboard.putString("autoTarget", "Center");

		double distance1 = 72;
		double distance2 = 66;
		double distance3 = 72;

		double robotLength = 0;
		// new comment

		int turnFactor = (this.getSideSwitch() == 'L') ? -1 : 1;

		SmartDashboard.putNumber("turnFactor", turnFactor);

		DriveStraightDistance(distance1);
		Turn90(Robot.drive.DirectionRight * turnFactor);
		DriveStraightDistance(distance2);
		Turn90(Robot.drive.DirectionLeft * turnFactor);
		DriveStraightDistance(distance3);

	}

}
