package application;

public class User {
	private int userID;
	private String userName;
	private String userPass;
	private String secQuestion;
	private String answer;
	
	public User(int userId, String userName, String userPass, String secQuestion, String answer) {
		
		this.userID = userId;
		this.userName = userName;
		this.userPass = userPass;
		this.secQuestion = secQuestion;
		this.answer = answer;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPass() {
		return userPass;
	}
	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}
	public String getSecQuestion() {
		return secQuestion;
	}
	public void setSecQuestion(String secQuestion) {
		this.secQuestion = secQuestion;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	public int getUserID()
	{
		return this.userID;
	}
}
