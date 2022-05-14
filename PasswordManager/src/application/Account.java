package application;

public class Account {
	private int userID;
	private int accID;
	private String appName;
	private String userName;
	private String appPass;
	private String email;
	private String dateExpired;
	private String dateCreated;
	private String duration;
	
	public Account()
	{
		this.userID = -1;
		this.accID = -1;	 
		this.appName = "";
		this.appPass = "";
		this.userName = "";
		this.email = "";
		this.dateExpired = "";
		this.dateCreated = "";
		this.duration = "";
	}
	
	public String getAppName() {
		return appName;
	}
	 public Account(int userID, int accID, String name, String username ,String pass,String email,String creation,String expiration,String days){
		this.userID = userID;
		this.accID = accID;	 
		this.appName = name;
		this.appPass = pass;
		this.userName = username;
		this.email = email;
		this.dateExpired = expiration;
		this.dateCreated = creation;
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
	public String getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
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
	
	public int getUserID()
	{
		return this.userID;
	}
	public int getAccID()
	{
		return this.accID;
	}
}
