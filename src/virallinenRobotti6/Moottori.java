package virallinenRobotti6;

import lejos.nxt.LCD;
import lejos.nxt.Motor;
import lejos.nxt.MotorPort;
import lejos.util.Stopwatch;

public class Moottori {

	private int vaihde;
	private int forward = 1;
	private int backward = 2;
	private int stop = 3;
	Stopwatch stopwatch = new Stopwatch();

	public void setVaihde(int vaihde) {
		this.vaihde = vaihde;

	}

	public void setPower(Object teho) {
		// TODO Auto-generated method stub

	}

	public int getVaihde() {
		// TODO Auto-generated method stub
		return vaihde;
	}

	public void rightTurn() {
		// TODO Auto-generated method stub
		Motor.C.forward();
		Motor.C.setSpeed(500);

		Motor.B.forward();
		Motor.B.setSpeed(470);
	}

	public void rightTurn(int amount, float suhde) {
		Motor.C.forward();
		Motor.C.setSpeed(amount);

		Motor.B.forward();
		Motor.B.setSpeed(amount * suhde);
	}

	public void leftTurn(int amount, float suhde) {

		Motor.B.forward();
		Motor.B.setSpeed(amount);

		Motor.C.forward();
		Motor.C.setSpeed(amount * suhde);

	}

	public void rightHardTurn() {
		// TODO Auto-generated method stub
		Motor.C.forward();
		Motor.C.setSpeed(500);

		Motor.B.backward();
		Motor.B.setSpeed(100);

	}

	public void leftHardTurn() {
		Motor.B.forward();
		Motor.B.setSpeed(500);

		Motor.C.backward();
		Motor.C.setSpeed(100);
	}

	public void eteenpain(int nopeus) {
		Motor.B.forward();
		Motor.B.setSpeed(nopeus);

		Motor.C.forward();
		Motor.C.setSpeed(nopeus);

	}

	public void forwardSlow() {
		Motor.C.setSpeed(100);
		Motor.B.setSpeed(100);
		Motor.C.forward();
		Motor.B.forward();
	}

	// public void rotateRight() {
	//
	//
	// MotorPort.C.resetTachoCount();
	//
	// while (MotorPort.C.getTachoCount() < 400){
	// Motor.C.forward();
	// Motor.C.setSpeed(50);
	// }
	// Motor.C.stop();
	// MotorPort.C.resetTachoCount();
	//
	// while(MotorPort.C.getTachoCount() < 1000) {
	// forwardSlow();
	// }
	// stop();
	//
	// MotorPort.B.resetTachoCount();
	//
	// while(MotorPort.B.getTachoCount() < 400){
	// Motor.B.forward();
	// Motor.B.setSpeed(50);
	// }
	// stop();
	// setVaihde(3);
	//
	// }

	public void rotateLeft(int speed, int degrees) {

		Motor.B.setSpeed(speed);
		Motor.B.rotate(degrees);
		stop();
	}

	public void rotateLeft(int speed, int degrees, int pauseTime) {

		Motor.B.setSpeed(speed);
		Motor.B.rotate(degrees);
		stop();

		stopwatch.reset();

		while (stopwatch.elapsed() < pauseTime) {

			eteenpain(200);
		}
	}
	
	public void rotateRight(int speed, int degrees) {

		Motor.C.setSpeed(speed);
		Motor.C.rotate(degrees);
		stop();
	}

	public void rotateRight(int speed, int degrees, int pauseTime) {

		Motor.C.setSpeed(speed);
		Motor.C.rotate(degrees);
		stop();

		stopwatch.reset();

		while (stopwatch.elapsed() < pauseTime) {
			eteenpain(200);
		}
	}

	public void stop() {
		// TODO Auto-generated method stub
		Motor.B.stop();
		Motor.C.stop();
	}

}
