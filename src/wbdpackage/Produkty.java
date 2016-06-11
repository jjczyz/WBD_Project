package wbdpackage;


public class Produkty {
	
	private int idProduktu, idKawiarni, idPodatku;
	private String nazwa;
	private float cena;
	
	public int getIdProduktu(){
		return idProduktu;
	}
	
	public void setIdProduktu(int idProduktu){
		this.idProduktu = idProduktu;
	}
	
	public int getIdKawiarni(){
		return idKawiarni;
	}
	
	public void setIdKawiarni(int idKawiarni){
		this.idKawiarni = idKawiarni;
	}
	
	public int getIdPodatku(){
		return idPodatku;
	}
	
	public void setIdPodatku(int idPodatku){
		this.idPodatku = idPodatku;
	}
	
	public String getNazwa(){
		return nazwa;
	}
	
	public void setNazwa(String nazwa){
		this.nazwa = nazwa;
	}
	
	public float getCena(){
		return cena;
	}
	
	public void setCena(float cena){
		this.cena = cena;
	}
	
	public Produkty(int idProduktu, int idKawiarni, int idPodatku, String nazwa, float cena) {
		this.idProduktu = idProduktu;
		this.idKawiarni = idKawiarni;
		this.idPodatku = idPodatku;
		this.nazwa = nazwa;
		this.cena = cena;
	}

	@Override
	public String toString() {
		return "Produkty [idProduktu=" + idProduktu + ", idKawiarni=" + idKawiarni + ", idPodatku=" + idPodatku
				+ ", nazwa=" + nazwa + ", cena=" + cena + "]";
	}
	
}
