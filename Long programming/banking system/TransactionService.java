package org.example;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class TransactionService {

    public transaction transactionUpdate(String acc,int amount,LocalDateTime date,BigDecimal bal,String transactiontype) {
        transaction tra=new transaction(acc,transactiontype,amount,bal,date);
        return tra;
    }
}
class transaction {
    private String accountId;
    private String transactionType;
    private int amount;
    private BigDecimal currentBalance;
    private LocalDateTime dateAndTime;

    public transaction(String accountId, String transactionType, int amount, BigDecimal currentBalance, LocalDateTime dateAndTime) {
        this.accountId = accountId;
        this.transactionType = transactionType;
        this.amount = amount;
        this.currentBalance = currentBalance;
        this.dateAndTime = dateAndTime;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public BigDecimal getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(BigDecimal currentBalance) {
        this.currentBalance = currentBalance;
    }

    public LocalDateTime getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(LocalDateTime dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    @Override
    public String toString() {
        return "transaction{" +
                "accountId='" + accountId + '\'' +
                ", transactionType='" + transactionType + '\'' +
                ", amount=" + amount +
                ", currentBalance=" + currentBalance +
                ", dateAndTime=" + dateAndTime +
                '}';
    }
}

