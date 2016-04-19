package virallinenRobotti6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import lejos.nxt.LCD;
import lejos.nxt.Sound;
import lejos.nxt.comm.NXTConnection;
import lejos.nxt.comm.USB;

public class Yhteys implements Runnable {
	NXTConnection connection = null;
	DataInputStream dis = null;
	DataOutputStream dos = null;
	private boolean isRunning = false;
	private LineFollower lf = null;

	public Yhteys(LineFollower lf) {
		this.lf = lf;
		isRunning = true;
	}

	public boolean Connect() {
		while (connection == null) {
			// connection = Bluetooth.waitForConnection(30000, 0);
			connection = USB.waitForConnection(30000, 0);
		}

		if (connection != null)
			return true;
		else
			return false;
	}

	public void ReadData() throws IOException {
		dis = connection.openDataInputStream();
		dis.close();
	}

	public void OutputData() throws IOException {
		dos = connection.openDataOutputStream();
		dos.writeUTF("nopeus");
		dos.writeInt(lf.data.getNopeus());
		LCD.clear();
		LCD.drawInt(dos.size(), 7, 1);
		dos.flush();
		dos.close();
	}

	public void run() {
		while (isRunning) {
			if (Connect()) {
				try {
					LCD.clear();
					LCD.drawString("CONNECTED", 0, 0);

					ReadData();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					LCD.clear();
					LCD.drawString("READ FAIL", 7, 2);
					Sound.twoBeeps();
				}

				try {
					OutputData();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					LCD.clear();
					LCD.drawString("OUTPUT FAIL", 7, 2);
					Sound.twoBeeps();
				}
			}
			else {
				LCD.clear();
				LCD.drawString("CONNECT FAIL", 0, 0);
				Sound.twoBeeps();
			}
		}
	}
}
