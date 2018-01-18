// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

//JPK - This is a test comment.

package org.usfirst.frc6033.RobotCodeJava2018;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc6033.RobotCodeJava2018.util.VisionData;
import org.usfirst.frc6033.RobotCodeJava2018.commands.*;
import org.usfirst.frc6033.RobotCodeJava2018.subsystems.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in 
 * the project.
 */
public class Robot extends TimedRobot {

    Command autonomousCommand;
    SendableChooser<Command> chooser = new SendableChooser<>();

    public static OI oi;
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public static Drive drive;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    
    public static double AutoSpeed = 0.3;
    public static double AutoTurnSpeed = 0.5;
    public static double AutoDwell = 0.5;
    
    public DriverStation driveStation;
    public CameraServer cameraServer;
    public Alliance alliance;
    
    public double gyroAngle;
    public static VisionData visionData;
    
    private int startLocation;
    


    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    @Override
    public void robotInit() {
        RobotMap.init();
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        drive = new Drive();

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        // OI must be constructed after subsystems. If the OI creates Commands
        //(which it very likely will), subsystems are not guaranteed to be
        // constructed yet. Thus, their requires() statements may grab null
        // pointers. Bad news. Don't move it.
        oi = new OI();

        // Add commands to Autonomous Sendable Chooser
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS

        chooser.addObject("AutoSwitch", new AutoSwitch());
        chooser.addObject("AutoScale", new AutoScale());
        chooser.addObject("AutoOpponentSwitch", new AutoOpponentSwitch());
        chooser.addObject("AutoNothing", new AutoNothing());
        chooser.addObject("AutoTurnLeft", new AutoTurnLeft());
        chooser.addObject("AutoTurnRight", new AutoTurnRight());

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS
        SmartDashboard.putData("Auto mode", chooser);
        
        visionData = new VisionData();
        
        driveStation = DriverStation.getInstance();
        // added 1-16-18 Bill S
        alliance = driveStation.getAlliance();
        startLocation = driveStation.getLocation();
        

    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    @Override
    public void disabledInit(){

    }

    @Override
    public void disabledPeriodic() {
        Scheduler.getInstance().run();
        updateDashboardParameters();
    }

    @Override
    public void autonomousInit() {
        autonomousCommand = chooser.getSelected();
        // schedule the autonomous command (example)
        if (autonomousCommand != null) autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    @Override
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
        updateDashboardParameters();
    }

    @Override
    public void teleopInit() {
        // This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
    }

    /**
     * This function is called periodically during operator control
     */
    @Override
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        updateDashboardParameters();
    }
    
	private void updateDashboardParameters() {
		
		//gyroAngle = drive.getGyroAngle();
		//SmartDashboard.putNumber("gyroAngle", gyroAngle);
		//SmartDashboard.putNumber("FrontSonarDistance", drive.getFrontSonarDistance());
		//visionData.readVisionData();
	}
	
	/**
	 * returns position of current teams starting location
	 * @return int 1=left 2=center 3=right
	 */
	public int getStartLocation()
	{
		return this.startLocation;
	}
	

	
	/**
	 * returns the team color
	 * @return char R = Red, B = Blue, I = Invalid
	 */
	public char getColor()
	{
		String color_name = this.alliance.name().toString();
		return color_name.charAt(0);
	}
}
