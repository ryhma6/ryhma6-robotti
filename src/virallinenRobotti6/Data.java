package virallinenRobotti6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import lejos.nxt.LCD;
import lejos.nxt.comm.Bluetooth;
import lejos.nxt.comm.NXTConnection;
import lejos.nxt.comm.USB;
import lejos.nxt.comm.USBConnection;

public class Data {

	NXTConnection connection = null;
	DataInputStream dis = null;
	DataOutputStream dos = null;
	
	private int puoli;
	
	//Liikearvot
	private int nopeus = 200;
	private float kaantoHidas = 0.85f;
	private float kaantoNopea = 0.2f;
	private int rotaatio90 = 400;
	private int rotaatio45 = 300;
	private int kaantoVaihe = 0;
	private int esteUlospainAika = 2000;
	private int esteOhitusAika = 2000;
	
	//Valoarvot
	private int alarajaValkoinen = 60;
	private int keskiraja = 56;
	private int ylarajaMusta = 49;
	private int alarajaMusta = 45;
	
	public void Connect() {
		while (connection == null)
		{
			//connection = Bluetooth.waitForConnection(30, 0);
			connection = USB.waitForConnection(30, 0);
		}
	}
	
	public void ReadData() throws IOException {
		dis = connection.openDataInputStream();
		dis.close();
	}
	
	public void OutputData() throws IOException {
		dos = connection.openDataOutputStream();
		dos.writeUTF("nopeus");
		dos.writeInt(getNopeus());
		LCD.clear();
		LCD.drawInt(dos.size(), 7, 1);
		dos.flush();
		dos.close();
	}

	public void setLightValues(String lightValues) {
		// TODO Auto-generated method stub
		
	}

	public int getValo() {
		// TODO Auto-generated method stub
		return 0;
	}

	public Object getTeho() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setPuoli(int puoli){
		
		this.puoli = puoli;
	}
	
	public int getPuoli() {
		// TODO Auto-generated method stub
		return puoli;
	}
	
	public void setNopeus(int nopeus){
		
		this.nopeus = nopeus;
	}
	
	public int getNopeus() {
		// TODO Auto-generated method stub
		return nopeus;
	}
	
	public float getKaantoHidas() {
		return kaantoHidas;
	}

	public void setKaantoHidas(float kaantoHidas) {
		this.kaantoHidas = kaantoHidas;
	}

	public float getKaantoNopea() {
		return kaantoNopea;
	}

	public void setKaantoNopea(float kaantoNopea) {
		this.kaantoNopea = kaantoNopea;
	}

	public int getRotaatio90() {
		return rotaatio90;
	}

	public void setRotaatio90(int rotaatio90) {
		this.rotaatio90 = rotaatio90;
	}

	public int getRotaatio45() {
		return rotaatio45;
	}

	public void setRotaatio45(int rotaatio45) {
		this.rotaatio45 = rotaatio45;
	}

	public int getKaantoVaihe() {
		return kaantoVaihe;
	}

	public void setKaantoVaihe(int kaantoVaihe) {
		this.kaantoVaihe = kaantoVaihe;
	}

	public int getEsteUlospainAika() {
		return esteUlospainAika;
	}

	public void setEsteUlospainAika(int esteUlospainAika) {
		this.esteUlospainAika = esteUlospainAika;
	}

	public int getEsteOhitusAika() {
		return esteOhitusAika;
	}

	public void setEsteOhitusAika(int esteOhitusAika) {
		this.esteOhitusAika = esteOhitusAika;
	}

	public int getAlarajaValkoinen() {
		return alarajaValkoinen;
	}

	public void setAlarajaValkoinen(int alaRajaValkoinen) {
		this.alarajaValkoinen = alaRajaValkoinen;
	}

	public int getKeskiraja() {
		return keskiraja;
	}

	public void setKeskiraja(int keskiraja) {
		this.keskiraja = keskiraja;
	}

	public int getYlarajaMusta() {
		return ylarajaMusta;
	}

	public void setYlarajaMusta(int ylaRajaMusta) {
		this.ylarajaMusta = ylaRajaMusta;
	}

	public int getAlarajaMusta() {
		return alarajaMusta;
	}

	public void setAlarajaMusta(int alaRajaMusta) {
		this.alarajaMusta = alaRajaMusta;
	}

}
