package application;

import java.util.List;

public class User {
	private String userName;
	private String userPass;
	private String secQuestion;
	private String answer;
	private List<Account> accountList;
	
	public User(String userName, String userPass, String secQuestion, String answer, List<Account> accountList) {
		super();
		this.userName = userName;
		this.userPass = userPass;
		this.secQuestion = secQuestion;
		this.answer = answer;
		this.accountList = accountList;
	}
	
	public List<Account> getAccountList() {
		return accountList;
	}
	public void setAccountList(List<Account> accountList) {
		this.accountList = accountList;
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
}
