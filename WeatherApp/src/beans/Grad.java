package beans;

public class Grad {
	private String ime;
	private String datum;
	private double temperatura;
	private double tempMin;
	private double tempMax;
	private double pritisak;
	private double vidljivost;
	private double vlaznost;
	
	public Grad(String ime, String datum, double temperatura, double tempMin, double tempMax, double pritisak,
			double vidljivost, double vlaznost) {
		super();
		this.ime = ime;
		this.datum = datum;
		this.temperatura = temperatura;
		this.tempMin = tempMin;
		this.tempMax = tempMax;
		this.pritisak = pritisak;
		this.vidljivost = vidljivost;
		this.vlaznost = vlaznost;
	}
	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	public String getDatum() {
		return datum;
	}
	public void setDatum(String datum) {
		this.datum = datum;
	}
	public double getTemperatura() {
		return temperatura;
	}
	public void setTemperatura(double temperatura) {
		this.temperatura = temperatura;
	}
	public double getTempMin() {
		return tempMin;
	}
	public void setTempMin(double tempMin) {
		this.tempMin = tempMin;
	}
	public double getTempMax() {
		return tempMax;
	}
	public void setTempMax(double tempMax) {
		this.tempMax = tempMax;
	}
	public double getPritisak() {
		return pritisak;
	}
	public void setPritisak(double pritisak) {
		this.pritisak = pritisak;
	}
	public double getVidljivost() {
		return vidljivost;
	}
	public void setVidljivost(double vidljivost) {
		this.vidljivost = vidljivost;
	}
	public double getVlaznost() {
		return vlaznost;
	}
	public void setVlaznost(double vlaznost) {
		this.vlaznost = vlaznost;
	}
}
