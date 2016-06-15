package wbdpackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;


 

public class KawiarniaBAZA {

	 public static final String DRIVER = "org.sqlite.JDBC";
	 public static final String DB_URL = "jdbc:sqlite:ede.db";
	 private Connection conn;
	 private Statement stat;
	 
	    public KawiarniaBAZA() {
	        try {
	            Class.forName(KawiarniaBAZA.DRIVER);
	        } catch (ClassNotFoundException e) {
	            System.err.println("Brak sterownika JDBC");
	            e.printStackTrace();
	        }
	 
	        try {
	            conn = DriverManager.getConnection(DB_URL);
	            stat = conn.createStatement();
	        } catch (SQLException e) {
	            System.err.println("Problem z otwarciem polaczenia");
	            e.printStackTrace();
	        }
	 
	        createTables();
	    }
	    
	    
	    public boolean createTables()  {
	    	
	        String createPracownicy = "CREATE TABLE IF NOT EXISTS Pracownicy("
	        		+"ID_pracownika INTEGER PRIMARY KEY AUTOINCREMENT,"
	        		+"Imie Varchar2(30 ) NOT NULL,"
	        		+"Nazwisko Varchar2(30 ) NOT NULL,"
	        		+"Data_zatrudnienia Date NOT NULL,"
	        		+"Numer_konta Varchar2(26 ) NOT NULL,"
	        		+"PESEL Varchar2(11 ) NOT NULL,"
	        		+"Numer_telefonu Varchar2(15 ) NOT NULL,"
	        		+"Email Varchar2(30 ),"
	        		+"Ulica Varchar2(30 ) NOT NULL,"
	        		+"Miasto Varchar2(30 ) NOT NULL,"
	        		+"Nr_domu Number NOT NULL,"
	        		+"Nr_mieszkania Number,"
	        		+"Kod_pocztowy Varchar2(8 ) NOT NULL,"
	        		+"Kraj Varchar2(30 ) NOT NULL,"
	        		+"ID_kawiarni Integer NOT NULL,"
	        		+"ID_znizki Integer NOT NULL,"
	        		+"ID_konta Integer NOT NULL"
	        		+")";
	        
	        String createFaktury = "CREATE TABLE IF NOT EXISTS Faktury("
	        		+"ID_faktury INTEGER PRIMARY KEY AUTOINCREMENT,"
	        		+"Data_wystawienia Date NOT NULL,"
	        		+"Kwota Number(10,2) NOT NULL,"
	        		+"ID_kawiarni Integer NOT NULL,"
	        		+"ID_pracownika Integer NOT NULL,"
	        		+"ID_znizki Integer"
	        		+")";
	        
	        String createWynagrodzenia = "CREATE TABLE IF NOT EXISTS Wynagrodzenia("
	        		+"ID_wynagrodzenia INTEGER PRIMARY KEY AUTOINCREMENT,"
	        		+"Data_wyplaty Date NOT NULL,"
	        		+"Kwota Number(10,2) NOT NULL,"
	        		+"ID_pracownika Integer NOT NULL"
	        		+")";
	        
	        String createProdukty = "CREATE TABLE IF NOT EXISTS Produkty("
	        		+"ID_produktu INTEGER PRIMARY KEY AUTOINCREMENT,"
	        		+"Nazwa Varchar2(30 ) NOT NULL,"
	        		+"Cena Number(10,2) NOT NULL,"
	        		+"ID_kawiarni Integer NOT NULL,"
	        		+"ID_podatku Integer NOT NULL"
	        		+")";
	        
	        String createZnizki = "CREATE TABLE IF NOT EXISTS Znizki("
	        		+"ID_znizki INTEGER PRIMARY KEY AUTOINCREMENT,"
	        		+"Wysokosc_znizki Number NOT NULL"
	        		+")";
	        
	        String createPodatkiVat = "CREATE TABLE IF NOT EXISTS Podatki_VAT("
	        		+"ID_podatku INTEGER PRIMARY KEY AUTOINCREMENT,"
	        		+"Wartosc Number NOT NULL"
	        		+")";
	        
	        String createKontaFirmowe = "CREATE TABLE IF NOT EXISTS Konta_firmowe("
	        		+"ID_konta INTEGER PRIMARY KEY AUTOINCREMENT,"
	        		+"Login Varchar2(20 ) NOT NULL,"
	        		+"Has³o Varchar2(20 ) NOT NULL,"
	        		+"Typ_konta Number NOT NULL"
	        		+")";
	        
	        try {
	            stat.execute(createPracownicy);
	            stat.execute(createFaktury);
	            stat.execute(createWynagrodzenia);
	            stat.execute(createProdukty);
	            stat.execute(createZnizki);
	            stat.execute(createPodatkiVat);
	            stat.execute(createKontaFirmowe);
	        } catch (SQLException e) {
	            System.err.println("Blad przy tworzeniu tabeli");
	            e.printStackTrace();
	            return false;
	        }
	        return true;
	    }
	    
	    public boolean insertPracownicy(String imie, String nazwisko, String data, String kontoBankowe, String pesel, String telefon, String email, String ulica, String miasto, int nrDomu, int nrMieszkania, String kodPocztowy, String kraj, int idZnizki, int idKonta) {
	        try {
	            PreparedStatement prepStmt = conn.prepareStatement(
	                    "INSERT INTO Pracownicy VALUES (NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 1, ?, ?);");
	            prepStmt.setString(1, imie);
	            prepStmt.setString(2, nazwisko);
	            prepStmt.setString(3, data);
	            prepStmt.setString(4, kontoBankowe);
	            prepStmt.setString(5, pesel);
	            prepStmt.setString(6, telefon);
	            prepStmt.setString(7, email);
	            prepStmt.setString(8, ulica);
	            prepStmt.setString(9, miasto);
	            prepStmt.setInt(10, nrDomu);
	            prepStmt.setInt(11, nrMieszkania);
	            prepStmt.setString(12, kodPocztowy);
	            prepStmt.setString(13, kraj);
	            prepStmt.setInt(14, idZnizki);
	            prepStmt.setInt(15, idKonta);	            
	            prepStmt.execute();
	        } catch (SQLException e) {
	            System.err.println("Blad przy wstawianiu pracownika");
	            e.printStackTrace();
	            return false;
	        }
	        return true;
	    }
	    
	    public boolean insertFaktury(String data, float kwota, int idPracownika, int idZnizki) {
	        try {
	            PreparedStatement prepStmt = conn.prepareStatement(
	                    "INSERT INTO Faktury VALUES (NULL, ?, ?, 1, ?, ?);");
	            prepStmt.setString(1, data);
	            prepStmt.setFloat(2, kwota);
	            prepStmt.setInt(3, idPracownika);
	            prepStmt.setInt(4, idZnizki);	            
	            prepStmt.execute();
	        } catch (SQLException e) {
	            System.err.println("Blad przy wstawianiu faktury");
	            e.printStackTrace();
	            return false;
	        }
	        return true;
	    }
	    
	    public boolean insertWynagrodzenia(String data, float kwota, int idPracownika) {
	        try {
	            PreparedStatement prepStmt = conn.prepareStatement(
	                    "INSERT INTO Wynagrodzenia VALUES (NULL, ?, ?, ?);");
	            prepStmt.setString(1, data);
	            prepStmt.setFloat(2, kwota);
	            prepStmt.setInt(3, idPracownika);	            
	            prepStmt.execute();
	        } catch (SQLException e) {
	            System.err.println("Blad przy wstawianiu wyplaty");
	            e.printStackTrace();
	            return false;
	        }
	        return true;
	    }
	    
	    public boolean insertProdukty(String nazwa, float cena, int idPodatku) {
	        try {
	            PreparedStatement prepStmt = conn.prepareStatement(
	                    "INSERT INTO Produkty VALUES (NULL, ?, ?, 1, ?);");
	            prepStmt.setString(1, nazwa);
	            prepStmt.setFloat(2, cena);
	            prepStmt.setInt(3, idPodatku);	            
	            prepStmt.execute();
	        } catch (SQLException e) {
	            System.err.println("Blad przy wstawianiu produktu");
	            e.printStackTrace();
	            return false;
	        }
	        return true;
	    }
	    
	    public boolean insertZnizki(float wysokoscZnizki) {
	        try {
	            PreparedStatement prepStmt = conn.prepareStatement(
	                    "INSERT INTO Znizki VALUES (NULL, ?);");
	            prepStmt.setFloat(1, wysokoscZnizki);	            
	            prepStmt.execute();
	        } catch (SQLException e) {
	            System.err.println("Blad przy wstawianiu produktu");
	            e.printStackTrace();
	            return false;
	        }
	        return true;
	    }
	    
	    public boolean insertPodatkiVat(float wartosc) {
	        try {
	            PreparedStatement prepStmt = conn.prepareStatement(
	                    "INSERT INTO Podatki_VAT VALUES (NULL, ?);");
	            prepStmt.setFloat(1, wartosc);	            
	            prepStmt.execute();
	        } catch (SQLException e) {
	            System.err.println("Blad przy wstawianiu produktu");
	            e.printStackTrace();
	            return false;
	        }
	        return true;
	    }
	    
	    public boolean insertKontaFirmowe(String login, String haslo, int typKonta) {
	        try {
	            PreparedStatement prepStmt = conn.prepareStatement(
	                    "INSERT INTO Konta_firmowe VALUES (NULL, ?, ?, ?);");
	            prepStmt.setString(1, login);	
	            prepStmt.setString(2, haslo);
	            prepStmt.setInt(3, typKonta);
	            prepStmt.execute();
	        } catch (SQLException e) {
	            System.err.println("Blad przy wstawianiu konta firmowego");
	            e.printStackTrace();
	            return false;
	        }
	        return true;
	    }
	    
	    
	    
	    public List<Pracownicy> selectPracownicy() {
	        List<Pracownicy> pracownicy = new LinkedList<Pracownicy>();
	        try {
	            ResultSet result = stat.executeQuery("SELECT * FROM Pracownicy");
	            int idPracownika, nrDomu, nrMieszkania, idZnizki, idKawiarni, idKonta;
	            String imie, nazwisko, dataZatrudnienia, kontoBankowe, pesel, telefon, email, ulica, miasto, kodPocztowy, kraj;
	            while(result.next()) {
	                idPracownika = result.getInt("ID_pracownika");
	                imie = result.getString("Imie");
	                nazwisko = result.getString("Nazwisko");
	                dataZatrudnienia = result.getString("Data_zatrudnienia");
	                kontoBankowe = result.getString("Numer_konta");
	                pesel = result.getString("PESEL");
	                telefon = result.getString("Numer_telefonu");
	                email = result.getString("Email");
	                ulica = result.getString("Ulica");
	                miasto = result.getString("Miasto");
	                nrDomu = result.getInt("Nr_domu");
	                nrMieszkania = result.getInt("Nr_mieszkania");
	                kodPocztowy = result.getString("Kod_pocztowy");
	                kraj = result.getString("Kraj");
	                idKawiarni = result.getInt("ID_kawiarni");
	                idZnizki = result.getInt("ID_znizki");
	                idKonta = result.getInt("ID_konta");
	                
	                pracownicy.add(new Pracownicy(idPracownika, nrDomu, nrMieszkania, idZnizki, idKawiarni, idKonta, imie, nazwisko, dataZatrudnienia, kontoBankowe, pesel, telefon, email, ulica, miasto, kodPocztowy, kraj));
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return null;
	        }
	        return pracownicy;
	    }
	    
	    public List<Faktury> selectFaktury() {
	    	List<Faktury> faktury = new LinkedList<Faktury>();
	    	try {
	    		ResultSet result = stat.executeQuery("SELECT * FROM Faktury");
		        int idFaktury, idKawiarni, idPracownika, idZnizki;
		        String data;
		        float kwota;
		        while(result.next()) {
		            idFaktury = result.getInt("ID_faktury");
		            data = result.getString("Data_wystawienia");
		            kwota = result.getFloat("Kwota");
		            idKawiarni = result.getInt("ID_kawiarni");
		            idPracownika = result.getInt("ID_pracownika");
		            idZnizki = result.getInt("ID_znizki");
		            
		            faktury.add(new Faktury(idFaktury, idKawiarni, idPracownika, idZnizki, data, kwota));
		        }
	    	} catch (SQLException e) {
	    		e.printStackTrace();
		        return null;
		    }
	    	return faktury;
		}
	    
	    public List<Produkty> selectProdukty() {
	    	List<Produkty> produkty = new LinkedList<Produkty>();
	    	try {
	    		ResultSet result = stat.executeQuery("SELECT * FROM Produkty");
	    		int idProduktu, idKawiarni, idPodatku;
		        String nazwa;
		        float cena;
		        while(result.next()) {
		            idProduktu = result.getInt("ID_produktu");
		            nazwa = result.getString("Nazwa");
		            cena = result.getFloat("Cena");
		            idKawiarni = result.getInt("ID_kawiarni");
		            idPodatku = result.getInt("ID_podatku");
		            
		            produkty.add(new Produkty(idProduktu, idKawiarni, idPodatku, nazwa, cena));
		        }
	    	} catch (SQLException e) {
	    		e.printStackTrace();
		        return null;
		    }
	    	return produkty;
		}
	    
	    public List<Wynagrodzenia> selectWynagrodzenia() {
	    	List<Wynagrodzenia> wynagrodzenia = new LinkedList<Wynagrodzenia>();
	    	try {
	    		ResultSet result = stat.executeQuery("SELECT * FROM Wynagrodzenia");
	    		int idWynagrodzenia, idPracownika;
		        String dataWyplaty;
		        float kwota;
		        while(result.next()) {
		            idWynagrodzenia = result.getInt("ID_wynagrodzenia");
		            dataWyplaty = result.getString("Data_wyplaty");
		            kwota = result.getFloat("Kwota");
		            idPracownika = result.getInt("ID_pracownika");
		            
		            wynagrodzenia.add(new Wynagrodzenia(idWynagrodzenia, idPracownika, dataWyplaty, kwota));
		        }
	    	} catch (SQLException e) {
	    		e.printStackTrace();
		        return null;
		    }
	    	return wynagrodzenia;
		}
	    
	    public List<Znizki> selectZnizki() {
	    	List<Znizki> znizki = new LinkedList<Znizki>();
	    	try {
	    		ResultSet result = stat.executeQuery("SELECT * FROM Znizki");
	    		int idZnizki;
		        float wysokoscZnizki;
		        while(result.next()) {
		            idZnizki = result.getInt("ID_znizki");
		            wysokoscZnizki = result.getFloat("Wysokosc_znizki");
		            
		            znizki.add(new Znizki(idZnizki, wysokoscZnizki));
		        }
	    	} catch (SQLException e) {
	    		e.printStackTrace();
		        return null;
		    }
	    	return znizki;
		}
	    
	    public List<PodatkiVat> selectPodatkiVat() {
	    	List<PodatkiVat> podatkiVat = new LinkedList<PodatkiVat>();
	    	try {
	    		ResultSet result = stat.executeQuery("SELECT * FROM Podatki_VAT");
	    		int idPodatku;
		        float wartosc;
		        while(result.next()) {
		            idPodatku = result.getInt("ID_podatku");
		            wartosc = result.getFloat("Wartosc");
		            
		            podatkiVat.add(new PodatkiVat(idPodatku, wartosc));
		        }
	    	} catch (SQLException e) {
	    		e.printStackTrace();
		        return null;
		    }
	    	return podatkiVat;
		}
	    
	    public List<KontaFirmowe> selectKontaFirmowe() {
	    	List<KontaFirmowe> kontaFirmowe = new LinkedList<KontaFirmowe>();
	    	try {
	    		ResultSet result = stat.executeQuery("SELECT * FROM Konta_firmowe");
	    		int idKonta, typKonta;
		        String login, haslo;
		        while(result.next()) {
		            idKonta = result.getInt("ID_konta");
		            login = result.getString("Login");
		            haslo = result.getString("Has³o");
		            typKonta = result.getInt("Typ_konta");
		            
		            kontaFirmowe.add(new KontaFirmowe(idKonta, login, haslo, typKonta));
		        }
	    	} catch (SQLException e) {
	    		e.printStackTrace();
		        return null;
		    }
	    	return kontaFirmowe;
		}
	    
	    
	    public boolean updateFaktury(int id, String data, float kwota, int idPracownika, int idZnizki) {
	        try {
	            PreparedStatement prepStmt = conn.prepareStatement(
	                    "UPDATE Faktury "
	                    + "SET Data_wystawienia = ?, Kwota = ?, ID_kawiarni = 1, ID_pracownika = ?, ID_znizki = ? "
	                    + "WHERE ID_faktury = "+id);
	            prepStmt.setString(1, data);
	            prepStmt.setFloat(2, kwota);
	            prepStmt.setInt(3, idPracownika);
	            prepStmt.setInt(4, idZnizki);	            
	            prepStmt.execute();
	        } catch (SQLException e) {
	            System.err.println("Blad przy edytowaniu faktury");
	            e.printStackTrace();
	            return false;
	        }
	        return true;
	    }
	    
	    public boolean updateWynagrodzenia(int id, String data, float kwota, int idPracownika){
	    	try {
				PreparedStatement prepStmt = conn.prepareStatement(
				        "UPDATE Wynagrodzenia "
				        + "SET Data_wyplaty = ?, Kwota = ?, ID_pracownika = ? "
				        + "WHERE ID_wynagrodzenia = "+id);
				prepStmt.setString(1, data);
				prepStmt.setFloat(2, kwota);
				prepStmt.setInt(3, idPracownika);	            
				prepStmt.execute();
			} catch (SQLException e) {
				System.err.println("Blad przy aktualizowwaniu Wynagrodzenia");
				e.printStackTrace();
				return false;
			}
	    	return true;
	    }
	    
	    public boolean updateProdukty(int id, String nazwa, float cena, int idPodatku) {
	        try {
	            PreparedStatement prepStmt = conn.prepareStatement(
	                    "UPDATE Produkty "
	                    + "SET Nazwa = ?, Cena = ?, ID_kawiarni = 1, ID_podatku = ? "
	                    + "WHERE ID_produktu = "+id);
	            prepStmt.setString(1, nazwa);
	            prepStmt.setFloat(2, cena);
	            prepStmt.setInt(3, idPodatku);	            
	            prepStmt.execute();
	        } catch (SQLException e) {
	            System.err.println("Blad przy edytowaniu produktu");
	            e.printStackTrace();
	            return false;
	        }
	        return true;
	    }
	    
	    public boolean updateZnizki(int id, float wysokoscZnizki) {
	        try {
	            PreparedStatement prepStmt = conn.prepareStatement(
	                    "UPDATE Znizki "
	                    + "SET Wysokosc_znizki = ? "
	                    + "WHERE ID_znizki = "+id);
	            prepStmt.setFloat(1, wysokoscZnizki);	            
	            prepStmt.execute();
	        } catch (SQLException e) {
	            System.err.println("Blad przy edytowaniu produktu");
	            e.printStackTrace();
	            return false;
	        }
	        return true;
	    }
	    
	    public boolean updatePodatkiVat(int id, float wartosc) {
	        try {
	            PreparedStatement prepStmt = conn.prepareStatement(
	                    "UPDATE Podatki_VAT "
	                    + "SET Wartosc = ? "
	                    + "WHERE ID_podatku = "+id);
	            prepStmt.setFloat(1, wartosc);	            
	            prepStmt.execute();
	        } catch (SQLException e) {
	            System.err.println("Blad przy edytowaniu produktu");
	            e.printStackTrace();
	            return false;
	        }
	        return true;
	    }
	    
	    public boolean updateKontaFirmowe(int id, String login, String haslo, String typKonta) {
	        try {
	            PreparedStatement prepStmt = conn.prepareStatement(
	                    "UPDATE Konta_firmowe "
	                    + "SET Login = ?, Has³o = ?, Typ_konta = ? "
	                    + "WHERE ID_konta = "+id);
	            prepStmt.setString(1, login);	
	            prepStmt.setString(2, haslo);
	            prepStmt.setString(3, typKonta);
	            prepStmt.execute();
	        } catch (SQLException e) {
	            System.err.println("Blad przy edytowaniu konta firmowego");
	            e.printStackTrace();
	            return false;
	        }
	        return true;
	    }
	    
	    public boolean updatePracownicy(int id, String imie, String nazwisko, String data, String kontoBankowe, String pesel, String telefon, String email, String ulica, String miasto, int nrDomu, int nrMieszkania, String kodPocztowy, String kraj, int idZnizki, int idKonta) {
	        try {
	            PreparedStatement prepStmt = conn.prepareStatement(
	                    "UPDATE Pracownicy "
	                    + "SET Imie = ?, Nazwisko = ?, Data_zatrudnienia = ?, Numer_konta = ?, PESEL = ?, Numer_telefonu = ?, Email = ?, Ulica = ?, Miasto = ?, Nr_domu = ?, Nr_mieszkania = ?, Kod_pocztowy = ?, Kraj = ?, ID_znizki = ?, ID_konta = ? "
	                    + "WHERE ID_pracownika = "+id);
	            prepStmt.setString(1, imie);
	            prepStmt.setString(2, nazwisko);
	            prepStmt.setString(3, data);
	            prepStmt.setString(4, kontoBankowe);
	            prepStmt.setString(5, pesel);
	            prepStmt.setString(6, telefon);
	            prepStmt.setString(7, email);
	            prepStmt.setString(8, ulica);
	            prepStmt.setString(9, miasto);
	            prepStmt.setInt(10, nrDomu);
	            prepStmt.setInt(11, nrMieszkania);
	            prepStmt.setString(12, kodPocztowy);
	            prepStmt.setString(13, kraj);
	            prepStmt.setInt(14, idZnizki);
	            prepStmt.setInt(15, idKonta);	            
	            prepStmt.execute();
	        } catch (SQLException e) {
	            System.err.println("Blad przy edytowaniu pracownika");
	            e.printStackTrace();
	            return false;
	        }
	        return true;
	    }
	    
	    public boolean deleteRow(String entityName, String attributeIdName, int id) {
	    	try {
	    		String statement = "DELETE FROM "+entityName+" WHERE "+attributeIdName+" = "+id+";";
				PreparedStatement prepStmt = conn.prepareStatement(statement);
				prepStmt.execute();
			} catch (SQLException e) {
				System.err.println("Blad przy usuwaniu z "+entityName);
				e.printStackTrace();
				return false;
			}
	    	return true;
	    }
	    
	    public void closeConnection() {
	        try {
	            conn.close();
	        } catch (SQLException e) {
	            System.err.println("Problem z zamknieciem polaczenia");
	            e.printStackTrace();
	        }
	    }
	    	    
}