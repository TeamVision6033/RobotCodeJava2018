package org.usfirst.frc6033.RobotCodeJava2018.util;

import edu.wpi.first.wpilibj.livewindow.LiveWindowSendable;
import edu.wpi.first.wpilibj.tables.ITable;

public class SonarMB1010 extends DistanceSensor implements LiveWindowSendable {

	// ultrasonics
	public static final Model MB1010 = new Model(.009766); // LV-MaxSonar-EZ
	public static final Model MB1340 = new Model(.012446);

	public SonarMB1010(int channel) {
		super(channel, MB1010);
	}

	public double getRawDistance() {
		return (getVoltage() - model.getLowV()) * model.getScale() + model.getLowD();
	}

	public double getDistance() {
		return (getVoltage() - model.getLowV()) * model.getScale() + model.getLowD();
	}

	public double getAverageDistance() {
		return (getAverageVoltage() - model.getLowV()) * model.getScale() + model.getLowD();
	}

	@Override
	public void updateTable() {
		if (m_table != null) {
			m_table.putNumber("Value", getDistance());
		}
	}

}
