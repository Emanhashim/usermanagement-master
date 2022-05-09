package com.bazra.usermanagement.model;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "Transaction")
public class Transaction {
   

    @Id
    @GeneratedValue
    private Long transactionId;

    private String accountNumber;

    private BigDecimal transactionAmount;

    private Date transactionDateTime;
    
    private String message;
//    
//    private Transaction() {
//        
//    }
//    
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

//    public Transaction() {
//     
//    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BigDecimal getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(BigDecimal transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public Date getTransactionDateTime() {
        return transactionDateTime;
    }

    public void setTransactionDateTime(Date transactionDateTime) {
        this.transactionDateTime = transactionDateTime;
    }

    public Transaction(  String toAccountNumber, BigDecimal amount, Date timestamp, String message) {
    
     this.accountNumber = toAccountNumber;
     this.transactionAmount = amount;
     this.transactionDateTime = timestamp;
     this.message= message;
    }
    
    public Transaction(BigDecimal amount, Date timestamp,String message) {
        this.transactionAmount=amount;
        this.message=message;
        this.transactionDateTime=timestamp;
        
    }
}
