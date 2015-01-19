package org.usfirst.frc.team1635.robot;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	DriveTrain driveTrain;
	Joystick joy;
	int autoLoopCounter;
	CameraServer server;
	long autoLoopCount;
	boolean autoDriveStraight; // DEBUG

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		server = CameraServer.getInstance();
		server.setQuality(50);
		server.startAutomaticCapture("cam0");
		driveTrain = new DriveTrain();
		joy = new Joystick(0);
	}

	public void operatorControl() {
		while (isOperatorControl() && isEnabled()) {
			/** robot code here! **/
			Timer.delay(0.005); // wait for a motor update time
		}
	}

	/**
	 * This function is run once each time the robot enters autonomous mode
	 */
	public void autonomousInit() {
		autoLoopCounter = 0;
		driveTrain.reset();
		double loopSetting = SmartDashboard.getNumber("DB/Slider 0", 80);
		autoDriveStraight = SmartDashboard.getBoolean("DB/Button 1", true);
		autoLoopCount = (long) (loopSetting * 100);

		System.out.println("autoLoopCount = " + autoLoopCount);
		SmartDashboard.putNumber("autoLoopCounter", autoLoopCount);
	}

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic() {
		if (autoLoopCounter < autoLoopCount) // Check if we've completed a set
												// number of loops
		{
			if (autoDriveStraight) {
				driveTrain.drive(0.5, 0.5); //positive values drive forward
			} else {
				driveTrain.spin();
			}
			autoLoopCounter++;
			driveTrain.log();
		} else {
			driveTrain.drive(0.0, 0.0); // stop robot
		}
	}

	// myRobot.tankDrive(0.5, 0.5);
	// }
	public void disabledInit() {

	}

	/**
	 * This function is called once each time the robot enters tele-operated
	 * mode
	 */
	public void teleopInit() {
		driveTrain.reset();
	}

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {
		driveTrain.drive(joy);
	}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {
		LiveWindow.run();
	}

}