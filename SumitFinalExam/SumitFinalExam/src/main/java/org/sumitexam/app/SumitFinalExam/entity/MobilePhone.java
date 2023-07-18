package org.sumitexam.app.SumitFinalExam.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="mobilephone_0016")
public class MobilePhone 
{
	@Id
	@Column(name="phoneid")
	int phoneId;	

	@Column(name="phonename")
	String phoneName;

	@Column(name="phonememory")
	String phoneMemory;	

	@Column(name="phoneos")
	String phoneOS;

	@Column(name="phonesize")
	String phoneSize;
	
	public MobilePhone() {
		// TODO Auto-generated constructor stub
	}

	public int getPhoneId() {
		return phoneId;
	}

	public void setPhoneId(int phoneId) {
		this.phoneId = phoneId;
	}

	public String getPhoneName() {
		return phoneName;
	}

	public void setPhoneName(String phoneName) {
		this.phoneName = phoneName;
	}

	public String getPhoneMemory() {
		return phoneMemory;
	}

	public void setPhoneMemory(String phoneMemory) {
		this.phoneMemory = phoneMemory;
	}

	public String getPhoneOS() {
		return phoneOS;
	}

	public void setPhoneOS(String phoneOS) {
		this.phoneOS = phoneOS;
	}

	public String getPhoneSize() {
		return phoneSize;
	}

	public void setPhoneSize(String phoneSize) {
		this.phoneSize = phoneSize;
	}
}
