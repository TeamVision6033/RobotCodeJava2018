
package org.usfirst.frc6033.RobotCodeJava2018.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc6033.RobotCodeJava2018.Robot;
import org.usfirst.frc6033.RobotCodeJava2018.commands.DriveReturnToSetHeading;
import org.usfirst.frc6033.RobotCodeJava2018.commands.DriveStop;
import org.usfirst.frc6033.RobotCodeJava2018.commands.DriveStraight;
import org.usfirst.frc6033.RobotCodeJava2018.subsystems.*;

public class AutoBase extends CommandGroup {
	protected char sideSwitch = 'U';
	protected char sideScale = 'U';
	protected char sideOpponentSwitch = 'U';

	protected void getGameData() {
		String gameData = DriverStation.getInstance().getGameSpecificMessage();
		if (gameData.length() >= 3) {
			sideSwitch = gameData.charAt(0);
			sideScale = gameData.charAt(1);
			sideOpponentSwitch = gameData.charAt(2);
			SmartDashboard.putString("gameData", gameData);
		}
	}

	protected void Turn45(int direction) {
		addSequential(new DriveTurn(direction, Robot.AutoTurnSpeed, 0, 32));
		addSequential(new DriveStop(Robot.AutoDwell));
	}

	protected void Turn90(int direction) {
		addSequential(new DriveTurn(direction, Robot.AutoTurnSpeed, 0, 72.25));
		addSequential(new DriveStop(Robot.AutoDwell));
	}

	protected void DriveStraightDistance(double inches) {
		DriveStraightDistance(inches, false);
	}

	protected void DriveStraightDistanceFromRear(double inches) {
		addSequential(new DriveStraight(Robot.AutoSpeed, 15, 0, 0, false, inches * Robot.AutoScale));
		addSequential(new DriveStop(Robot.AutoDwell));
	}

	protected void DriveStraightDistance(double inches, boolean followSide) {
		DriveStraightDistance(inches, followSide, true);
	}

	protected void DriveStraightDistance(double inches, boolean followSide, boolean stop) {
		addSequential(new DriveStraight(Robot.AutoSpeed, 15, inches * Robot.AutoScale, 12, followSide, 0));
		if (stop)
			addSequential(new DriveStop(Robot.AutoDwell));
	}

	/**
	 * returns position of current teams side of the switch
	 * 
	 * @return char R = Right, L = Left, U = Unknown
	 */
	public char getSideSwitch() {
		getGameData();
		return sideSwitch;
	}

	/**
	 * returns position of current teams side of the scale
	 * 
	 * @return char R = Right, L = Left, U = Unknown
	 */
	public char getSideScale() {
		getGameData();
		return sideScale;
	}

	/**
	 * returns position of current teams side of the opponents switch
	 * 
	 * @return char R = Right, L = Left, U = Unknown
	 */
	public char getSideOpponentSwitch() {
		getGameData();
		return sideOpponentSwitch;
	}
}
