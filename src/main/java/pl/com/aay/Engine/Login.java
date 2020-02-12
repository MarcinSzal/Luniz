package pl.com.aay.test_data.model;

public class Login {
	
	
	private String url;
	private String login;
	private String password;
	
	public Login () {}
	
	public Login (String ur) {
		this.url=url;	
	}
	
	public Login (String url, String login, String password) {
		this.url=url;
		this.login=login;
		this.password=password;		
	}
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
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

}
