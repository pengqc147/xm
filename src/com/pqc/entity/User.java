package com.pqc.entity;

import java.util.Date;


public class User {

	private  Integer uid;
	private String uname;
	private Integer gender;
	private String phone;
	private String area;
	private Integer manager;
	private String username;
	private String password;
	private String photo;
	private Date create_time;
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public Integer getGender() {
		return gender;
	}
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public Integer getManager() {
		return manager;
	}
	public void setManager(Integer manager) {
		this.manager = manager;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public User(Integer uid, String uname, Integer gender, String phone, String area, Integer manager, String username,
			String password, String photo, Date create_time) {
		super();
		this.uid = uid;
		this.uname = uname;
		this.gender = gender;
		this.phone = phone;
		this.area = area;
		this.manager = manager;
		this.username = username;
		this.password = password;
		this.photo = photo;
		this.create_time = create_time;
	}
	
	public User(String uname, Integer gender, String phone, String area, Integer manager, String username,
			String password, String photo, Date create_time) {
		super();
		this.uname = uname;
		this.gender = gender;
		this.phone = phone;
		this.area = area;
		this.manager = manager;
		this.username = username;
		this.password = password;
		this.photo = photo;
		this.create_time = create_time;
	}
	
	public User(String uname, Integer gender, String phone, String area, String username, String password,
			String photo, Date create_time) {
		super();
		this.uname = uname;
		this.gender = gender;
		this.phone = phone;
		this.area = area;
		this.username = username;
		this.password = password;
		this.photo = photo;
		this.create_time = create_time;

	}
	
	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	
	public User(Integer uid, Integer manager) {
		super();
		this.uid = uid;
		this.manager = manager;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "User [uid=" + uid + ", uname=" + uname + ", gender=" + gender + ", phone=" + phone + ", area=" + area
				+ ", manager=" + manager + ", username=" + username + ", password=" + password + ", photo=" + photo
				+ ", create_time=" + create_time + "]";
	}
	
	
}
