package wbdpackage;


public class Faktury {

	private int idFaktury, idKawiarni, idPracownika, idZnizki;
	private String data;
	private float kwota;
	
	public int getIdFaktury() {
		return idFaktury;
	}
	
	public void setIdFaktury(int idFaktury) {
		this.idFaktury = idFaktury;
	}
	
	public int getIdKawiarni() {
		return idKawiarni;
	}
	
	public void setIdKawiarni(int idKawiarni) {
		this.idKawiarni = idKawiarni;
	}
	
	public int getIdPracownika() {
		return idPracownika;
	}
	
	public void setIdPracownika(int idPracownika) {
		this.idPracownika = idPracownika;
	}
	
	public int getIdZnizki() {
		return idZnizki;
	}
	
	public void setIdZnizki(int idZnizki) {
		this.idZnizki = idZnizki;
	}
	
	public String getData() {
		return data;
	}
	
	public void setData(String data) {
		this.data = data;
	}
	
	public float getKwota() {
		return kwota;
	}
	
	public void setKwota(float kwota) {
		this.kwota = kwota;
	}
	
	public Faktury(int idFaktury, int idKawiarni, int idPracownika, int idZnizki, String data, float kwota){
		this.idFaktury = idFaktury;
		this.idKawiarni = idKawiarni;
		this.idPracownika = idPracownika;
		this.idZnizki = idZnizki;
		this.data = data;
		this.kwota = kwota;
		
	}
	
    @Override
    public String toString() {
        return "["+idFaktury+"] - "+data+" "+kwota+"zl - "+idPracownika+" - "+idZnizki+" - "+idKawiarni;
    }
	
}
