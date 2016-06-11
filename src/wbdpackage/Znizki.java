package wbdpackage;


public class Znizki {

	private int idZnizki;
	private float wysokoscZnizki;
	
	public int getIdZnizki(){
		return idZnizki;
	}
	
	public void setIdZnizki(int idZnizki){
		this.idZnizki = idZnizki;
	}
	
	public float getWysokoscZnizki(){
		return wysokoscZnizki;
	}
	
	public void setWysokoscZnizki(float wysokoscZnizki){
		this.wysokoscZnizki = wysokoscZnizki;
	}
	
	public Znizki(int idZnizki, float wysokoscZnizki){
		this.idZnizki = idZnizki;
		this.wysokoscZnizki = wysokoscZnizki;
	}
}
