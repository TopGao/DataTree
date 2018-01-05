package com.jira.po;

public class JiraUser {

	private String csl;
	private String fullName;
	private String email;
	private String inputInfo;
	
	
	public String getCsl() {
		return csl;
	}
	public void setCsl(String csl) {
		this.csl = csl;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getInputInfo() {
		return inputInfo;
	}
	public void setInputInfo(String inputInfo) {
		this.inputInfo = inputInfo;
	}
	
	@Override
	public String toString() {
		return "JiraUser [csl=" + csl + ", fullName=" + fullName + ", email=" + email + "]";
	}
}
