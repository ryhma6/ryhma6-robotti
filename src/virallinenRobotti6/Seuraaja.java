package virallinenRobotti6;

import java.io.IOException;

import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;
import lejos.util.*;

public class Seuraaja implements Runnable {

	LineFollower lf;
	// Huom. ColorSensoria käyttävissä roboteissa pitää tehdä tähän tarvittavat
	// muutokset!!!
	LightSensor light = new LightSensor(SensorPort.S4);
	UltrasonicSensor ultra = new UltrasonicSensor(SensorPort.S2);
	Stopwatch sw = new Stopwatch();
	Stopwatch swKaanto = new Stopwatch();
	Stopwatch stopwatch = new Stopwatch();
	private int aika;
	private int vaAika = 0;
	private int blackWhiteThreshold = 55;
	private int tMax = 60;
	private String lightValues = "";
	private boolean isRunning;
	int estelkm = 0;

	// etaisyys
	boolean minDistReached = false;
	int MINDISTANCE = 300;

	public synchronized boolean isRunning() {
		return isRunning;
	}

	public synchronized void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}

	public synchronized int getBlackWhiteThreshold() {
		return blackWhiteThreshold;
	}

	public synchronized void setBlackWhiteThreshold(int blackWhiteThreshold) {
		this.blackWhiteThreshold = blackWhiteThreshold;
	}

	public Seuraaja(LineFollower lf) {
		this.lf = lf;
		isRunning = true;
	}

	public void run() {
		light.setFloodlight(true);
		// LCD.drawString("Valo: ", 0, 0);
		// LCD.drawString("Paina vasen ", 0, 2);
		// LCD.drawString("aloittaaksesi", 0, 3);
		// LCD.drawString("tai yhdista!", 0, 4);

		LCD.drawString("Valitse puoli", 0, 0);
		LCD.drawInt(light.readValue(), 9, 1);

		int vallu = ultra.getDistance();
		LCD.drawString("Etaisyys:", 4, 3);
		LCD.drawInt(vallu, 4, 5);

		Button.waitForAnyPress();

		if (Button.LEFT.isPressed()) {
			LCD.clear();
			lf.data.setPuoli(2);
			LCD.drawString("Vasen puoli", 0, 0);
		}

		if (Button.RIGHT.isPressed()) {
			LCD.clear();
			lf.data.setPuoli(1);
			LCD.drawString("Oikea puoli", 0, 0);
		}

		// 5 Sekunnin viive
		try {
			Thread.sleep(2000);
			LCD.drawString("Kierros alkaa 2 sekunnin kuluttua!", 0, 0);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}

		// Aseteetaan vaihde 1 eli viivanseuranta
		lf.moot.setVaihde(1);
		sw.reset();

		// LCD.clear();
		// LCD.drawString("Ajossa", 0, 0);
		// LCD.drawString("Aika: ", 0, 2);
		// LCD.drawString("Valo: ", 0, 3);

		while (isRunning) {
			aika = stopwatch.elapsed();
			LCD.drawInt(light.readValue(), 9, 1);
			int value = ultra.getDistance();
			LCD.drawString("Etaisyys:", 1, 4);
			LCD.drawInt(value, 8, 4);

			int vaihdepaalla = lf.moot.getVaihde();
			LCD.drawString("VAIHDE:", 1, 3);
			LCD.drawInt(vaihdepaalla, 7, 3);

			LCD.drawInt(aika / 1000, 6, 6);

			// // Haetaan valo ja teho arvot
			// blackWhiteThreshold = lf.data.getValo();
			// lf.moot.setPower(lf.data.getTeho());
			//
			// // Vasemman puolen seuraus

			if (lf.data.getPuoli() == 2 && lf.moot.getVaihde() == 1) {

				LCD.drawString("Vasen puoli", 0, 0);
				if (light.readValue() > lf.data.getAlarajaValkoinen())
					lf.moot.rightTurn(lf.data.getNopeus(),
							lf.data.getKaantoNopea());

				if (light.readValue() <= lf.data.getAlarajaValkoinen()
						&& light.readValue() > lf.data.getKeskiraja())
					lf.moot.leftTurn(lf.data.getNopeus(),
							lf.data.getKaantoHidas());

				if (light.readValue() <= lf.data.getKeskiraja()
						&& light.readValue() >= lf.data.getYlarajaMusta())
					lf.moot.eteenpain(lf.data.getNopeus());

				if (light.readValue() < lf.data.getYlarajaMusta()
						&& light.readValue() >= lf.data.getAlarajaMusta())
					lf.moot.rightTurn(lf.data.getNopeus(),
							lf.data.getKaantoHidas());

				if (light.readValue() < lf.data.getAlarajaMusta())
					lf.moot.leftTurn(lf.data.getNopeus(),
							lf.data.getKaantoNopea());
			}

			// Oikean puolen seuraus
			if (lf.data.getPuoli() == 1 && lf.moot.getVaihde() == 1) {
				LCD.drawString("oikea puoli", 0, 0);

				if (light.readValue() > lf.data.getAlarajaValkoinen())
					lf.moot.leftTurn(lf.data.getNopeus(),
							lf.data.getKaantoNopea());

				if (light.readValue() <= lf.data.getAlarajaValkoinen()
						&& light.readValue() > lf.data.getKeskiraja())
					lf.moot.rightTurn(lf.data.getNopeus(),
							lf.data.getKaantoHidas());

				if (light.readValue() <= lf.data.getKeskiraja()
						&& light.readValue() >= lf.data.getYlarajaMusta())
					lf.moot.eteenpain(lf.data.getNopeus());

				if (light.readValue() < lf.data.getYlarajaMusta()
						&& light.readValue() >= lf.data.getAlarajaMusta())
					lf.moot.leftTurn(lf.data.getNopeus(),
							lf.data.getKaantoHidas());

				if (light.readValue() < lf.data.getAlarajaMusta())
					lf.moot.rightTurn(lf.data.getNopeus(),
							lf.data.getKaantoNopea());

			} else if (lf.moot.getVaihde() == 2) {

				if (estelkm >= 1) {
					lf.moot.setVaihde(0);
				}
				else {
					
				estelkm++;

				if (lf.data.getPuoli() == 2) {
					lf.moot.rotateLeft(200, -520, 4000);
					lf.moot.rotateRight(200, -520, 5000);
					lf.moot.rotateRight(200, -520);
					lf.moot.setVaihde(3);
				} else {
					lf.moot.rotateRight(200, -520, 4000);
					lf.moot.rotateLeft(200, -520, 5000);
					lf.moot.rotateLeft(200, -520);
					lf.moot.setVaihde(3);
				}

				lf.moot.setVaihde(3);
				lf.moot.stop();
				}
			}

			 else if (lf.moot.getVaihde() == 3) {

				if (light.readValue() > lf.data.getYlarajaMusta()) {
					lf.moot.eteenpain(lf.data.getNopeus());
				}

				else {
					lf.moot.stop();

					if (lf.data.getPuoli() == 2) {
						lf.moot.rotateRight(lf.data.getNopeus(),
								lf.data.getRotaatio45());
					}

					else {
						lf.moot.rotateLeft(lf.data.getNopeus(),
								lf.data.getRotaatio45());
					}
					lf.moot.setVaihde(1);
				}
			}
			

			// // Paluu radalle väistön jälkeen
			// while (lf.moot.getVaihde() == 3
			// && light.getLightValue() > blackWhiteThreshold) {
			// lf.moot.forwardSlow();
			// }
			// if (lf.moot.getVaihde() == 3
			// && light.getLightValue() < blackWhiteThreshold) {
			// lf.moot.setVaihde(1);
			// }
			// Lopetus
			if (lf.moot.getVaihde() == 0) {
				lf.moot.stop();
				isRunning = false;
			}

		}
			LCD.clear();
			LCD.drawString("Kierros ohi!", 0, 0);
			LCD.drawString("Aika: ", 0, 2);
			LCD.drawInt(aika / 1000, 6, 2);
			LCD.drawString("sekuntia", 0, 3);
			Button.waitForAnyPress();
	}
}
