package beans;

import java.util.ArrayList;

public class Grafikon {
	private String grad;
	private ArrayList<String> merenja = new ArrayList<String>();
	private ArrayList<String> datumi = new ArrayList<String>();
	
	public ArrayList<String> getDatumi() {
		return datumi;
	}
	public void setDatumi(ArrayList<String> datumi) {
		this.datumi = datumi;
	}
	public String getGrad() {
		return grad;
	}
	public Grafikon(String grad) {
		super();
		this.grad = grad;
	}
	public void setGrad(String grad) {
		this.grad = grad;
	}
	public ArrayList<String> getMerenja() {
		return merenja;
	}
	public void setMerenja(ArrayList<String> merenja) {
		this.merenja = merenja;
	}
}
