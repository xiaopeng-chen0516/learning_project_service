package com.mingrisoft.bean;

import java.io.Serializable;

/**
 * 用户基本信息
 * @author Administrator
 *
 */
public class User {

	private String name;//用户
	private String password;//密码
	private String nick;//昵称
	private String gender;//性别
	private String phone;//联系方式
	private String email;//E-mail
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	

	
}
