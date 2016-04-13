package virallinenRobotti6;

import lejos.nxt.*;
import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;
import lejos.util.*;

public class Etaisyys implements Runnable {

//	boolean minDistReach = false;
	//int minDistance = 60;
	
	LineFollower lf;
    Etaisyys Et;
    
    
    private int distance = 0;
    private int Arvo = 0;
    UltrasonicSensor ultra = new UltrasonicSensor(SensorPort.S2);
    
    public Etaisyys(LineFollower lf) {
		this.lf = lf;
	}
    
//	private boolean isRunning;
//	
//	public synchronized boolean isRunning() {
//		return isRunning;
//	}
    public synchronized int getArvo(){
    	return Arvo;
    }
    public synchronized void setArvo(int arvo){
    	this.Arvo = arvo;
    }
    
	
    public synchronized int getDistance(){
    	return distance;
    }
    public synchronized void setDistance(int distance){
    	this.distance = distance;
    	
    }



	public void run() {

		while (true){
			
			
			setArvo(30);
			setDistance(ultra.getDistance());
			
			Este();
		}
		
		
	}
	
	public void Este() {
		
		if (distance <= Arvo && lf.moot.getVaihde() == 1){
			lf.moot.setVaihde(2);
			lf.moot.stop();
			
			if (lf.data.getPuoli() == 2){
				lf.moot.rotateLeft();
				lf.moot.setVaihde(3);
			}
//			
//			else {
//			lf.moot.rotateRight();	
//			}
			
		}
	}
	
	
}
