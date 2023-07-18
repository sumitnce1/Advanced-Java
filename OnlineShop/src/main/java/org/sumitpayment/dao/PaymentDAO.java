package org.sumitpayment.dao;

public interface PaymentDAO {
    public boolean addCard(String cardNo, String expiry, String balance, String status);    
    public boolean transact(String cardNo, String expiry, double balance);
    public boolean reverseTransaction(String cardNo, String txDate, double amount, String status);
}