package org.sumitexam.app.SumitFinalExam.dto;

public class MobilePhoneDTO 
{
	int phoneId;	
	String phoneName;
	String phoneMemory;	
	String phoneOS;
	String phoneSize;

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