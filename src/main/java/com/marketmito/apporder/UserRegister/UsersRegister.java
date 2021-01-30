package com.marketmito.apporder.UserRegister;

import javax.persistence.Column;


public class UsersRegister {
	
	@Column(name="user_name",length = 30,unique = true)
	private String userName;
	
	@Column(name="password",length = 60,unique = true)
	private String password;
	
	private Boolean enabled;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	
	
	

}
