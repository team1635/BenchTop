package org.usfirst.frc.team1635.robot;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
//import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.Joystick.AxisType;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveTrain extends Subsystem {

	private SpeedController frontLeftMotor, backLeftMotor,
			frontRightMotor, backRightMotor;
	private RobotDrive drive;
	private Encoder leftEncoder, rightEncoder;

	public DriveTrain() {
		super();
	
		//myRobot = new RobotDrive(0, 2, 1, 3);
//		frontLeftMotor = new Talon(0);
//		backLeftMotor = new Talon(2);
//		frontRightMotor = new Talon(1);
//		backRightMotor = new Talon(3);
		
		frontLeftMotor = new Victor(0);
		backLeftMotor = new Victor(2);
		frontRightMotor = new Victor(1);
		backRightMotor = new Victor(3);
		
		drive = new RobotDrive(frontLeftMotor, backLeftMotor,
							   frontRightMotor, backRightMotor);

		//leftEncoder = new Encoder(2, 3, true, Encoder.EncodingType.k4X);
		leftEncoder = new Encoder(0, 1);
		leftEncoder.setMaxPeriod(.1);
		leftEncoder.setMinRate(10);
		leftEncoder.setDistancePerPulse(0.06);
		leftEncoder.setReverseDirection(true);
		leftEncoder.setSamplesToAverage(20);

		//rightEncoder = new Encoder(0, 1, false, Encoder.EncodingType.k4X);
		rightEncoder = new Encoder(2, 3);
		rightEncoder.setMaxPeriod(.1);
		rightEncoder.setMinRate(10);
		rightEncoder.setDistancePerPulse(0.07967557);
		rightEncoder.setReverseDirection(true);
		rightEncoder.setSamplesToAverage(20);
		
		// Encoders may measure differently in the real world and in
		// simulation. In this example the robot moves 0.042 barleycorns
		// per tick in the real world, but the simulated encoders
		// simulate 360 tick encoders. This if statement allows for the
		// real robot to handle this difference in devices.
		if (Robot.isReal()) {
			leftEncoder.setDistancePerPulse(0.042);
			rightEncoder.setDistancePerPulse(0.042);
		} else {
			// Circumference in ft = 4in/12(in/ft)*PI
			leftEncoder.setDistancePerPulse((4.0/12.0*Math.PI) / 360.0);
			rightEncoder.setDistancePerPulse((4.0/12.0*Math.PI) / 360.0);
		}

		// Let's show everything on the LiveWindow
//		LiveWindow.addActuator("Drive Train", "Front_Left Motor", (Talon) frontLeftMotor);
//		LiveWindow.addActuator("Drive Train", "Back Left Motor", (Talon) backLeftMotor);
//		LiveWindow.addActuator("Drive Train", "Front Right Motor", (Talon) frontRightMotor);
//		LiveWindow.addActuator("Drive Train", "Back Right Motor", (Talon) backRightMotor);
		LiveWindow.addSensor("Drive Train", "Left Encoder", leftEncoder);
		LiveWindow.addSensor("Drive Train", "Right Encoder", rightEncoder);
	}

	@Override
	protected void initDefaultCommand() {
		//setDefaultCommand(new TankDriveWithJoystick());
	}
	
	/**
	 * The log method puts interesting information to the SmartDashboard.
	 */
	public void log() {
		SmartDashboard.putNumber("Left Distance", leftEncoder.getDistance());
		SmartDashboard.putNumber("Right Distance", rightEncoder.getDistance());
		SmartDashboard.putNumber("Left Speed", leftEncoder.getRate());
		SmartDashboard.putNumber("Right Speed", rightEncoder.getRate());
	}
	
	public void drive(double left, double right, boolean squared) {
		drive.tankDrive(left, right, squared);
	}
	
	public void drive(double left, double right) {
		drive(left, right, false);
	}

	/**
	 * @param joy The ps3 style joystick to use to drive tank style
	 */
	public void drive(Joystick joy) {
		//drive(joy.getY(), -joy.getAxis(AxisType.kThrottle)); //this is the left trigger
		//drive(joy.getY(), joy.getZ()); //this is the right trigger
		//drive(joy.getY(), joy.getX()); //this is the left horizontal axis
		//drive(joy.getY(), joy.getAxis(AxisType.kTwist)); //this is the left trigger again
		drive(joy.getY(), joy.getRawAxis(5), true);
	}

	/**
	 * Reset the robots sensors to the zero states.
	 */
	public void reset() {
		leftEncoder.reset();
		rightEncoder.reset();
	}
	
	/**
	 * @return The distance driven (average of left and right encoders).
	 */
	public double getDistance() {
		return (leftEncoder.getDistance() + rightEncoder.getDistance())/2;
	}
	


}
