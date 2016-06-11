package wbdpackage;


public class PodatkiVat {

	private int idPodatku;
	private float wartosc;
	
	public int getIdPodatku(){
		return idPodatku;
	}
	
	public void setIdPodatku(int idPodatku){
		this.idPodatku = idPodatku;
	}
	
	public float getWartosc(){
		return wartosc;
	}
	
	public void setWartosc(float wartosc){
		this.wartosc = wartosc;
	}
	
	public PodatkiVat(int idPodatku, float wartosc){
		this.idPodatku = idPodatku;
		this.wartosc = wartosc;
	}
}
