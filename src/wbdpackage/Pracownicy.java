package wbdpackage;

public class Pracownicy {

	private int idPracownika, nrDomu, nrMieszkania, idZnizki, idKawiarni, idKonta;
	private String imie, nazwisko, dataZatrudnienia, kontoBankowe, pesel, telefon, email, ulica, miasto, kodPocztowy, kraj; 


	public int getIdPracownika() {
		return idPracownika;
	}

	public void setIdPracownika(int idPracownika) {
		this.idPracownika = idPracownika;
	}

	public int getNrDomu() {
		return nrDomu;
	}

	public void setNrDomu(int nrDomu) {
		this.nrDomu = nrDomu;
	}

	public int getNrMieszkania() {
		return nrMieszkania;
	}

	public void setNrMieszkania(int nrMieszkania) {
		this.nrMieszkania = nrMieszkania;
	}

	public int getIdZnizki() {
		return idZnizki;
	}

	public void setIdZnizki(int idZnizki) {
		this.idZnizki = idZnizki;
	}

	public int getIdKawiarni() {
		return idKawiarni;
	}

	public void setIdKawiarni(int idKawiarni) {
		this.idKawiarni = idKawiarni;
	}

	public int getIdKonta() {
		return idKonta;
	}

	public void setIdKonta(int idKonta) {
		this.idKonta = idKonta;
	}

	public String getImie() {
		return imie;
	}

	public void setImie(String imie) {
		this.imie = imie;
	}

	public String getNazwisko() {
		return nazwisko;
	}

	public void setNazwisko(String nazwisko) {
		this.nazwisko = nazwisko;
	}

	public String getDataZatrudnienia() {
		return dataZatrudnienia;
	}

	public void setDataZatrudnienia(String dataZatrudnienia) {
		this.dataZatrudnienia = dataZatrudnienia;
	}

	public String getKontoBankowe() {
		return kontoBankowe;
	}

	public void setKontoBankowe(String kontoBankowe) {
		this.kontoBankowe = kontoBankowe;
	}

	public String getPesel() {
		return pesel;
	}

	public void setPesel(String pesel) {
		this.pesel = pesel;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUlica() {
		return ulica;
	}

	public void setUlica(String ulica) {
		this.ulica = ulica;
	}

	public String getMiasto() {
		return miasto;
	}

	public void setMiasto(String miasto) {
		this.miasto = miasto;
	}

	public String getKodPocztowy() {
		return kodPocztowy;
	}

	public void setKodPocztowy(String kodPocztowy) {
		this.kodPocztowy = kodPocztowy;
	}

	public String getKraj() {
		return kraj;
	}

	public void setKraj(String kraj) {
		this.kraj = kraj;
	}

	public Pracownicy(int idPracownika, int nrDomu, int nrMieszkania, int idZnizki, int idKawiarni, int idKonta, String imie, String nazwisko, String dataZatrudnienia, String kontoBankowe, String pesel, String telefon, String email, String ulica, String miasto, String kodPocztowy, String kraj){
		this.idPracownika = idPracownika;
		this.nrDomu = nrDomu;
		this.nrMieszkania = nrMieszkania;
		this.idZnizki = idZnizki;
		this.idKawiarni = idKawiarni;
		this.idKonta = idKonta;
		this.imie = imie;
		this.nazwisko = nazwisko;
		this.dataZatrudnienia = dataZatrudnienia;
		this.kontoBankowe = kontoBankowe;
		this.pesel = pesel;
		this.telefon = telefon;
		this.email = email;
		this.ulica = ulica;
		this.miasto = miasto;
		this.kodPocztowy = kodPocztowy;
		this.kraj = kraj;
	}

	@Override
	public String toString() {
		return "Pracownicy [idPracownika=" + idPracownika + ", nrDomu=" + nrDomu + ", nrMieszkania=" + nrMieszkania
				+ ", idZnizki=" + idZnizki + ", idKawiarni=" + idKawiarni + ", idKonta=" + idKonta + ", imie=" + imie
				+ ", nazwisko=" + nazwisko + ", dataZatrudnienia=" + dataZatrudnienia + ", kontoBankowe=" + kontoBankowe
				+ ", pesel=" + pesel + ", telefon=" + telefon + ", email=" + email + ", ulica=" + ulica + ", miasto="
				+ miasto + ", kodPocztowy=" + kodPocztowy + ", kraj=" + kraj + "]";
	}

 
}
