package virallinenRobotti6;





class LineFollower {

	public Etaisyys et;
	public Moottori moot;
	public Data data;
	public Seuraaja se;

	public LineFollower() {
		moot = new Moottori();
		data = new Data();	
		
	}

	public static void main(String[] aArg) throws Exception {
		LineFollower nxt = new LineFollower();
		
		nxt.Threadit(nxt);
		nxt.data.Connect();
	}
	
	

	public void Threadit(LineFollower nxt) {
		Thread tarkkailija = new Thread(new Etaisyys(nxt));
		Thread seuraaja = new Thread(new Seuraaja(nxt));

		
		
		tarkkailija.start();
		seuraaja.start();
	}
}
