package org.sumit.mvchiberbootapp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity //Hibernate Managed
@Table(name="users_0016")
@NamedQueries({
	@NamedQuery(name="authenticate", query="select OBJECT(oUser) from User oUser where oUser.userName=?1 and oUser.password=?2")
})
public class User 
{
	@Id
	@Column(name="username")	
	String userName;

	@Column(name="password")
	String password;

	@Column(name="name")
	String name;

	@Column(name="email")
	String email;

	@Column(name="phone")
	long phone;

	@Column(name="city")
	String city;

	public User() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return "User: [userName=" + userName + ", name=" + name + ", email=" + email + ", phone=" + phone + ", city=" + city + "]";
	}

	public User(String userName, String password, String name, String email, long phone, String city) {
		super();
		this.userName = userName;
		this.password = password;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.city = city;
	}


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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
}
