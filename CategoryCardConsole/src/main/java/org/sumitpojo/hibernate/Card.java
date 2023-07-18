package org.sumitpojo.hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="cards_0016")
public class Card {
	
	@Id
	@Column(name="cardNo")
	long cardNo;
	
	@Column(name="expiry")
	String expiry;
	
	@Column(name="balance")
	double balance;
	
	@Column(name="status")
	String status;
	
	public Card(){

	}

	public Card(long cardNo, String expiry, double balance, String status) {
		super();
		this.cardNo = cardNo;
		this.expiry = expiry;
		this.balance = balance;
		this.status = status;
	}
	public long getCardNo() {
		return cardNo;
	}
	public void setCardNo(long cardNo) {
		this.cardNo = cardNo;
	}
	public String getExpiry() {
		return expiry;
	}
	public void setExpiry(String expiry) {
		this.expiry = expiry;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}