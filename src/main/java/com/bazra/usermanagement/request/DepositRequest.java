package com.bazra.usermanagement.request;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;

public class DepositRequest {
    @NotBlank(message = "Enter withdraw amount")
    private BigDecimal amount;
    @NotBlank(message = "Enter your account")
    private String toaccountnumber;
    
   

    public String getToaccountnumber() {
        return toaccountnumber;
    }

    public void setToaccountnumber(String toaccountnumber) {
        this.toaccountnumber = toaccountnumber;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
