
package org.usfirst.frc6033.RobotCodeJava2018.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc6033.RobotCodeJava2018.Robot;
import org.usfirst.frc6033.RobotCodeJava2018.RobotMap;
import org.usfirst.frc6033.RobotCodeJava2018.commands.DriveReturnToSetHeading;
import org.usfirst.frc6033.RobotCodeJava2018.commands.DriveStop;
import org.usfirst.frc6033.RobotCodeJava2018.commands.DriveStraight;
import org.usfirst.frc6033.RobotCodeJava2018.subsystems.*;
import org.usfirst.frc6033.RobotCodeJava2018.util.GameData;

public class AutoBase extends CommandGroup {

	protected final double scaleHeight = 68;
	protected final double switchHeight = 24;
	protected final double crossLineDistance = 84;
	
	protected void Turn45(int direction) {
		// initial value was 32
		addSequential(new DriveTurn(direction, Robot.AutoTurnSpeed, 0, 45));
		addSequential(new DriveStop(Robot.AutoDwell));
	}

	protected void Turn90(int direction) {
		// initial value was 72.25
		addSequential(new DriveTurn(direction, Robot.AutoTurnSpeed, 3, 80));
		addSequential(new DriveStop(Robot.AutoDwell));
	}

	protected void DriveStraightDistance(double inches) {
		DriveStraightDistance(inches, 'N');
	}

	protected void DriveStraightDistance(double inches, char followSide) {
		DriveStraightDistance(inches, followSide, true);
	}

	protected void DriveStraightDistance(double inches, char followSide, boolean stop) {
		DriveStraightDistance(Robot.AutoSpeed, inches, followSide, stop);
	}

	protected void DriveStraightDistance(double speed, double inches, char followSide, boolean stop) {
		DriveStraightDistance(speed, inches, followSide, stop, 0, false, 0);
	}

	protected void DriveStraightDistance(double speed, double inches, char followSide, boolean stop,
			double elevatorPosition, boolean lowerForks, double timeLimit) {
		if (timeLimit <= 0)
			timeLimit = 15;
		if (lowerForks)
			addParallel(new ForksDown());
		if (elevatorPosition > 0)
			addParallel(new RaiseElevator(elevatorPosition));
		addSequential(new DriveStraight(speed, timeLimit, inches * Robot.AutoScale, 0, followSide, 0));
		if (stop)
			addSequential(new DriveStop(Robot.AutoDwell));
	}

	protected void ReturnToSetHeading() {
		addSequential(new DriveReturnToSetHeading());
	}

	protected void DriveStraightDistanceFromRear(double inches) {

		DriveStraightDistanceFromRear(Robot.AutoSpeed, inches, 0);
	}
	
	protected void DriveRaiseElevator (double elevatorposition) {
		addSequential(new RaiseElevator(elevatorposition));
	}

	protected void DriveStraightDistanceFromRear(double speed, double inches, double elevatorPosition) {
		if (elevatorPosition > 0)
			addParallel(new RaiseElevator(elevatorPosition));
		addSequential(new DriveStraight(speed, 15, 0, 0, 'N', inches * Robot.AutoScale));
	}

	/**
	 * 
	 * @param inches
	 *            Distance Limit in Inches.
	 * @param stopAtInches
	 *            Distance (in inches) to stop at, as read from front sonar before
	 *            moving forward.
	 */
	protected void DriveStraightDistanceFromFront(double inches, double stopAtInches) {
		addSequential(new DriveStraight(Robot.AutoSpeed, 15, inches * Robot.AutoScale, stopAtInches, 'N', 0));
		addSequential(new DriveStop(Robot.AutoDwell));
	}

	public void ClampOpen() {
		addSequential(new OpenClamp());
	}
}
