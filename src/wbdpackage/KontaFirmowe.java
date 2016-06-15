package wbdpackage;

public class KontaFirmowe {

	private int idKonta;
	private String login;
	private String password;
	private int accountType;
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getAccountType() {
		return accountType;
	}
	public void setAccountType(int accountType) {
		this.accountType = accountType;
	}
	
	public KontaFirmowe(int idKonta, String login, String password, int accountType){
		this.setIdKonta(idKonta);
		this.login = login;
		this.password = password;
		this.accountType = accountType;
	}
	public KontaFirmowe() {}
	public int getIdKonta() {
		return idKonta;
	}
	public void setIdKonta(int idKonta) {
		this.idKonta = idKonta;
	}
}
