package beans;

public class GradFajl {
	private String country;
	private String city;
	
	public GradFajl() {
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.city;
	}
	public GradFajl(String country, String city) {
		super();
		this.country = country;
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
}
