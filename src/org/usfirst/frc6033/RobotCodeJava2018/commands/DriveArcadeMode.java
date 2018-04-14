// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc6033.RobotCodeJava2018.commands;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc6033.RobotCodeJava2018.Robot;

/**
 *
 */
public class DriveArcadeMode extends Command {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
 
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
    public DriveArcadeMode() {

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.drive);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
    	double moveValue = this.scalePower(Robot.oi.getDriveStick().getRawAxis(1));
    	moveValue = moveValue * Math.abs(moveValue);
    	double rotateValue = this.scalePower(Robot.oi.getDriveStick().getRawAxis(0));
    	rotateValue = rotateValue * Math.abs(rotateValue);
    	Robot.drive.ArcadeDrive( moveValue, rotateValue, true);
    	//Robot.drive.ArcadeDrive(Robot.oi.getDriveStick());
    }
    
    private double scalePower (double power) {
    	double minPower = 0.2;
    	double factor = (1 + (Robot.oi.getDriveStick().getRawAxis(3) * -1));
    	return (power * minPower) + (1 - minPower) * power * factor;
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
    	Robot.drive.Stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
    	this.end();
    }
}
