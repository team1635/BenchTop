Sunday 1/18/2015

TODO: Check code into GitHub
TODO: Get the Gyro working
TODO: Get the robot to turn 90 degrees in automonmous.
TODO: Write the code CommandBased
TODO: Figure out how to get the Java SmartDashboard working with the program.
TODO: Figure out how to test subsystems
TODO: Test that R2D2 can drive the robot.
TODO: Set up the CAN bus to the PDP
TODO: Read current draw for various commands.

Get .1 male pin headers
Get PayPal account for donations


Code we used in autonomous

			System.out.println("autoLoopCounter=" + autoLoopCounter);
			int count = rightEncoder.get();
			double distance = rightEncoder.getDistance();
			// double period = rightEncoder.getDistance();
			double rate = rightEncoder.getRate();
			// boolean direction = rightEncoder.getStopped();
			System.out.println("rightencoder values" + " count=" + count
					+ " distance=" + distance +
					// + " period=" + period +
					" rate=" + rate);
			// + rate + " direction= " + direction);

			int count1 = leftEncoder.get();
			double distance1 = leftEncoder.getDistance();
			// double period1 = leftEncoder.getDistance();
			double rate1 = leftEncoder.getRate();
			// boolean direction1 = leftEncoder.getStopped();
			System.out.println("leftencoder values" + " count1=" + count1
					+ " distance1=" + distance1
					// + " period1=" + period1
					+ " rate1=" + rate1);
			// + " direction1=" + direction1);

This is what we had in teleop
		//myRobot.tankDrive(gameController.getRawAxis(1),
		//		gameController.getRawAxis(5), true);
		
		// System.out.println("Count=" + rightEncoder.get() + " distance=" +
		// rightEncoder.getDistance());
		// int count=rightEncoder.get();
		// double distance= rightEncoder.getRaw();
		// double period= rightEncoder.getDistance();
		// double rate= rightEncoder.getRate();
		// boolean direction= rightEncoder.getStopped();
		// System.out.println("rightencoder values" + count + distance + period
		// + rate + direction);
		// int count1= leftEncoder.get();
		// double distance1= leftEncoder.get();
		// double period1= leftEncoder.getDistance();
		// double rate1= leftEncoder.getRate();
		// boolean direction1= leftEncoder.getStopped();
		// System.out.println("leftencoder values" + count1 + distance1 +
		// period1 + rate1 + direction1);

