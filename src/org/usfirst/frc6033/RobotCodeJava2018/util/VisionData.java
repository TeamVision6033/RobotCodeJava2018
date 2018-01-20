package org.usfirst.frc6033.RobotCodeJava2018.util;

import edu.wpi.first.wpilibj.networktables.NetworkTable;
//import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class VisionData {
	private int seqNo;
	private int age;
	private double steer;
	private double distance;
	private double angle;
	private double heading;
	private NetworkTable table;

	public void VisionData() {
		ConnectToTable();
	}
	
	private void ConnectToTable() {
		table = NetworkTable.getTable("datatable");
	}

	public void readVisionData() {
	
		if (table != null) {
			seqNo = (int) table.getNumber("VisionSeqNo", -1);
			age = (int) table.getNumber("VisionAge", -1);
			steer = table.getNumber("VisionSteer", -1);
			distance = table.getNumber("VisionDistance", -1);
			angle = table.getNumber("VisionAngle", -1);
			heading = table.getNumber("VisionHeading", -1);
		}
		else
			ConnectToTable();

		SmartDashboard.putNumber("V SeqNo", seqNo);
		SmartDashboard.putNumber("V Age", age);
		SmartDashboard.putNumber("V Steer", steer);
		SmartDashboard.putNumber("V Distance", distance);
		SmartDashboard.putNumber("V Angle", angle);
		SmartDashboard.putNumber("V Heading", heading);
	
	}

	public int getSeqNo() {
		return seqNo;
	}

	public int getAge() {
		return age;
	}

	public double getSteer() {
		return steer;
	}

	public double getDistance() {
		return distance;
	}

	public double getAngle() {
		return angle;
	}

	public double getHeading() {
		return heading;
	}

}
