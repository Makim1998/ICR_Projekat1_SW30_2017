package beans;

import java.util.ArrayList;

public class Grafikoni {
	private String podatak;
	private ArrayList<Grafikon> gradovi =  new ArrayList<Grafikon>();;

	
	public Grafikoni(String podatak) {
		super();
		this.podatak = podatak;
	}
	public String getPodatak() {
		return podatak;
	}
	public void setPodatak(String podatak) {
		this.podatak = podatak;
	}
	public ArrayList<Grafikon> getGradovi() {
		return gradovi;
	}
	public void setGradovi(ArrayList<Grafikon> gradovi) {
		this.gradovi = gradovi;
	}
}
