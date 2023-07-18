package org.sumit.hibernate;

public class Card {
	String cardNo;
	String expiry;
	String balance;
	String status;
	public Card(String cardNo, String expiry, String balance, String status) {
		super();
		this.cardNo = cardNo;
		this.expiry = expiry;
		this.balance = balance;
		this.status = status;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getExpiry() {
		return expiry;
	}
	public void setExpiry(String expiry) {
		this.expiry = expiry;
	}
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
