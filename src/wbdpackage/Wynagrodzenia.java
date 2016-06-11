package wbdpackage;


public class Wynagrodzenia {

	private int idWynagrodzenia, idPracownika;
	String dataWyplaty;
	float kwota;
	
	public int getIdWynagrodzenia(){
		return idWynagrodzenia;
	}
	
	public void setIdWynagrodzenia(int idWynagrodzenia){
		this.idWynagrodzenia = idWynagrodzenia;
	}
	
	public int getIdPracownika(){
		return idPracownika;
	}
	
	public void setIdPracownika(int idPracownika){
		this.idPracownika = idPracownika;
	}
	
	public String getDataWyplaty(){
		return dataWyplaty;
	}
	
	public void setDataWyplaty(String dataWyplaty){
		this.dataWyplaty = dataWyplaty;
	}
	
	public float getKwota(){
		return kwota;
	}
	
	public void setKwota(float kwota){
		this.kwota = kwota;
	}
	
	public Wynagrodzenia(int idWynagrodzenia, int idPracownika, String dataWyplaty, float kwota){
		this.idWynagrodzenia = idWynagrodzenia;
		this.idPracownika = idPracownika;
		this.dataWyplaty = dataWyplaty;
		this.kwota = kwota;
	}
}
