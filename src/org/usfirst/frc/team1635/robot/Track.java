package org.usfirst.frc.team1635.robot;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

public class Track extends PIDSubsystem {

	private SpeedController motCont1, motCont2;
	private Encoder enc;

	public Track(String name, Encoder myEncoder, SpeedController firstMotor, SpeedController secondMotor) {
		super(name, 1.0, 0.0, 0.0);
		setAbsoluteTolerance(0.05);
		getPIDController().setContinuous(false);
	
		motCont1 = firstMotor;
		motCont2 = secondMotor;
		enc = myEncoder;
		
		LiveWindow.addActuator(name, "PIDSubsystem Controller", getPIDController());
	}

	@Override
	protected double returnPIDInput() {
		return enc.getRate();
	}

	@Override
	public void usePIDOutput(double output) {
		motCont1.pidWrite(output);
		motCont2.pidWrite(output);
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub

	}

}
