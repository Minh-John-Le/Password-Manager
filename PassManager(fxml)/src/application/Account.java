package application;

public class Account {
	private String appName;
	private String userName;
	private String appPass;
	private String email;
	private String dateExpired;
	private String datCreated;
	private String duration;
	
	public String getAppName() {
		return appName;
	}
	 public Account(String name, String username ,String pass,String email,String creation,String expiration,String days){
		this.appName = name;
		this.appPass = pass;
		this.userName = username;
		this.userName = email;
		this.dateExpired = expiration;
		this.datCreated = creation;
		this.duration = days;
	 }
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDateExpired() {
		return dateExpired;
	}
	public void setDateExpired(String dateExpited) {
		this.dateExpired = dateExpited;
	}
	public String getDatCreated() {
		return datCreated;
	}
	public void setDatCreated(String datCreated) {
		this.datCreated = datCreated;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public void setAppName(String name) {
		this.appName = name;
	}
	public String getAppPass() {
		return appPass;
	}
	public void setAppPass(String pass) {
		this.appPass = pass;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String username) {
		this.userName = username;
	}
}
