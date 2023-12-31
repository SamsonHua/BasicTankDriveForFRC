// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;

/**
 * This is a demo program showing the use of the DifferentialDrive class. Runs the motors with
 * arcade steering.
 */
public class Robot extends TimedRobot {
  //Declare all the Spark Objects
  private final Spark leftFront = new Spark(2);
  private final Spark leftBack = new Spark(3);
  private final Spark rightFront = new Spark(1);
  private final Spark rightBack = new Spark(0);
  private final Spark elevator = new Spark(4);
  //Because there are two motors in a gearbox that run in the same direction, it makes more sense to assign them to a motor group with MotorControllerGroup
  private final MotorControllerGroup leftGroup = new MotorControllerGroup(leftFront, leftBack);
  private final MotorControllerGroup rightGroup = new MotorControllerGroup(rightFront, rightBack);
  //WPI provides a differential drive class where it takes in a left and right side. We can give it each motor group
  private final DifferentialDrive m_robotDrive = new DifferentialDrive(leftGroup, rightGroup);
  //Declares an XBox Controller object on USB Port 0
  private final XboxController m_stick = new XboxController(0);


  @Override
  public void robotInit() {
    // We need to invert one side of the drivetrain so that positive voltages
    // result in both sides moving forward. Depending on how your robot's
    // gearbox is constructed, you might have to invert the left side instead.
    rightGroup.setInverted(true);
  }

  @Override
  public void teleopPeriodic() {
    // Drive with arcade drive.
    // That means that the Y axis drives forward
    // and backward, and the X turns left and right.
    
    //Drive the elevator motor speed by setting the Right X Box Joystick multipled by 0.8
    elevator.set(-m_stick.getRightY() * 0.8);

    //Differential drive has built in arcadeDrive method to take in both X and Y analog sticks to drive.
    m_robotDrive.arcadeDrive(m_stick.getLeftY()*0.5, m_stick.getLeftX()*0.5);
  }
}
