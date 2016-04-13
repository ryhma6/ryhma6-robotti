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
	
	public void leftTurn() {

		Motor.B.forward();
		Motor.B.setSpeed(500);
		
		Motor.C.forward();
		Motor.C.setSpeed(470);
		
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

	public void eteenpain() {
		Motor.B.forward();
		Motor.B.setSpeed(200);
		
		Motor.C.forward();
		Motor.C.setSpeed(200);
		
	}
	
	public void forwardSlow() {
		Motor.C.setSpeed(100);
		Motor.B.setSpeed(100);	
		Motor.C.forward();
		Motor.B.forward();
	}
	
//	public void rotateRight() {
//	
//
//		MotorPort.C.resetTachoCount();
//		
//		while (MotorPort.C.getTachoCount() < 400){
//			Motor.C.forward();
//			Motor.C.setSpeed(50);	
//		}
//		Motor.C.stop();
//		MotorPort.C.resetTachoCount();
//		
//		while(MotorPort.C.getTachoCount() < 1000) {
//			forwardSlow();
//		}
//		stop();
//		
//		MotorPort.B.resetTachoCount();
//		
//		while(MotorPort.B.getTachoCount() < 400){
//			Motor.B.forward();
//			Motor.B.setSpeed(50);
//		}
//		stop();
//		setVaihde(3);
//		
//	}
	
	public void rotateLeft() {
		
		Motor.B.setSpeed(150);
		Motor.B.rotate(420);
		stop();
			
		stopwatch.reset();
		
		while(stopwatch.elapsed() < 3000)
		{
			eteenpain();
		}

		stop();
		
	}

	public void stop() {
		// TODO Auto-generated method stub
		Motor.B.stop();
		Motor.C.stop();
	}


}
