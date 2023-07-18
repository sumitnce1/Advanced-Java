package org.sumitpayment.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class PaymentDAOImpl implements PaymentDAO {
    Connection connection;
    PreparedStatement psAddCard;
    PreparedStatement psTransact;
    PreparedStatement psTransaction;

    public PaymentDAOImpl(Connection connection) {
        this.connection = connection;
        try {
            psAddCard = connection.prepareStatement("INSERT INTO cards_0016 (cardNo, expiry, balance, status) VALUES (?,?,?,?)");
            psTransact = connection.prepareStatement("SELECT * FROM cards_0016 WHERE cardNo=? AND expiry=?");
            psTransaction = connection.prepareStatement("INSERT INTO transactions_0016 (txId, cardNo, txDate, amount, status) VALUES (?,?,?,?,?)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean addCard(String cardNo, String expiry, String balance, String status) {
        try {
            psAddCard.setString(1, cardNo);
            psAddCard.setString(2, expiry);
            psAddCard.setString(3, balance);
            psAddCard.setString(4, status);
            int rowsUpdated = psAddCard.executeUpdate();
            if (rowsUpdated > 0) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Duplicate Card Number Entry in Cards Table");
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean transact(String cardNo, String expiry, double balance) {
        PreparedStatement psUpdateBalance = null;
        try {
            psTransact.setString(1, cardNo);
            psTransact.setString(2, expiry);
            try (ResultSet result = psTransact.executeQuery()) {
                if (result.next()) {
                    double cardBalance = Double.parseDouble(result.getString("balance"));
                    double transactionAmount = balance;
                    if (cardBalance >= balance) {
                        // Payment successful
                        cardBalance -= balance;

                        // Generate transaction ID
                        int txId = generateTransactionId();
                        // Get the current date
                        String txDate = getCurrentDate();

                        // Insert the transaction into transactions_0016 table
                        psTransaction.setInt(1, txId);
                        psTransaction.setString(2, cardNo);
                        psTransaction.setString(3, txDate);
                        psTransaction.setDouble(4, transactionAmount);
                        psTransaction.setString(5, "Success");

                        int rowsUpdated = psTransaction.executeUpdate();
                        if (rowsUpdated > 0) {
                            // Update card balance in the database
                            psUpdateBalance = connection.prepareStatement("UPDATE cards_0016 SET balance=? WHERE cardNo=?");
                            psUpdateBalance.setDouble(1, cardBalance);
                            psUpdateBalance.setString(2, cardNo);
                            int rowsUpdatedBalance = psUpdateBalance.executeUpdate();
                            if (rowsUpdatedBalance > 0) {
                                return true;
                            }
                        }
                    } else {
                        // Not enough balance in the card
                        return false;
                    }
                } else {
                    // Card not found
                    return false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (psUpdateBalance != null) {
                    psUpdateBalance.close();
                }
                psTransact.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean reverseTransaction(String cardNo, String txDate, double amount, String status) {
        PreparedStatement psUpdateBalance = null;
        PreparedStatement psFailedTransaction = null;
        try {
            // Generate transaction ID
            int txId = generateTransactionId();

            // Insert the cancel transaction into transactions_0016 table
            psTransaction.setInt(1, txId);
            psTransaction.setString(2, cardNo);
            psTransaction.setString(3, txDate);
            psTransaction.setDouble(4, amount);
            psTransaction.setString(5, status);

            int rowsUpdated = psTransaction.executeUpdate();
            if (rowsUpdated > 0) {
                // Insert failed/canceled transaction query
                psFailedTransaction = connection.prepareStatement("INSERT INTO transactions_0016 (txId, cardNo, txDate, amount, status) VALUES (?,?,?,?,?)");
                psFailedTransaction.setInt(1, txId);
                psFailedTransaction.setString(2, cardNo);
                psFailedTransaction.setString(3, txDate);
                psFailedTransaction.setDouble(4, amount);
                psFailedTransaction.setString(5, status);

                int rowsUpdatedFailedTransaction = psFailedTransaction.executeUpdate();
                if (rowsUpdatedFailedTransaction > 0) {
                    // Payment cancellation or failure query
                    psUpdateBalance = connection.prepareStatement("UPDATE cards_0016 SET balance = balance + ? WHERE cardNo = ?");
                    psUpdateBalance.setDouble(1, amount);
                    psUpdateBalance.setString(2, cardNo);
                    int rowsUpdatedBalance = psUpdateBalance.executeUpdate();
                    if (rowsUpdatedBalance > 0) {
                        return true;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (psFailedTransaction != null) {
                    psFailedTransaction.close();
                }
                if (psUpdateBalance != null) {
                    psUpdateBalance.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }


    private int generateTransactionId() {
        return (int) (Math.random() * (999999 - 1001 + 1) + 1001);
    }

    private String getCurrentDate() {
        return LocalDate.now().toString();
    }
}