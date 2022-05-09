package com.bazra.usermanagement.request;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;

public class WithdrawRequest {
    @NotBlank(message = "Enter withdraw amount")
    private BigDecimal amount;
    @NotBlank(message = "Enter your account")
    private String fromAccountNumber;
    
    
    
    public String getFromAccountNumber() {
        return fromAccountNumber;
    }

    public void setFromAccountNumber(String fromAccountNumber) {
        this.fromAccountNumber = fromAccountNumber;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    
}
