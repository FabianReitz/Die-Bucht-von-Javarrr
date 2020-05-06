package units;

public class GegnerBewegung {
	private static  int rechts = 2;
	private  int links = -2;
	private static  int richtung = rechts;


	public static int getRichtung() {
		return richtung;
	}
	public void richtungLinks() {
		richtung = links;
	}
	public void richtungRechts() {
		
}
}
